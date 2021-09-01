package API_hw2.core;

import API_hw2.beans.TrelloBoard;
import API_hw2.data.BoardConstant;
import API_hw2.data.ResponseStatus;
import API_hw2.data.URLConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import static io.restassured.http.ContentType.*;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import java.util.Properties;

import static org.hamcrest.Matchers.*;

public class TrelloServiceObj {

    public static String URL;
    private final Method requestMethod;

    private final Map<String, String> parameters;

    private final String pathToProperties = "src/main/resources/API_hw2/credentials.properties";
    private static Properties properties = new Properties();

    private TrelloServiceObj (Map<String, String> parameters, Method method) {
        this.parameters = parameters;
        this.requestMethod = method;

        try {
            FileInputStream file = new FileInputStream(pathToProperties);
            properties.load(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ApiRequestBuilder requestBuilder() {
        return new ApiRequestBuilder();
    }

    public static class ApiRequestBuilder {
        private final Map<String, String> parameters = new HashMap<>();
        private Method requestMethod = Method.GET;

        public ApiRequestBuilder setMethod (Method method){
            requestMethod = method;
            return this;
        }

        public ApiRequestBuilder name(String name) {
            parameters.put(BoardConstant.NAME.getConstantName(), name);
            return this;
        }

        public ApiRequestBuilder customParameter(String paramName, String param) {
            parameters.put(paramName, param);
            return this;
        }

        public TrelloServiceObj buildRequest() {
            return new TrelloServiceObj(parameters, requestMethod);
        }
    }

    private static RequestSpecification baseRequestConfiguration() {
        return new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri(URI.create(URLConstant.BASE.getURLConstant()))
                .addParam("key", properties.getProperty("key"))
                .addParam("token",properties.getProperty("token"))
                .build();
    }

    public TrelloBoard boardRequest(int status) {
        Response response = RestAssured
                .with()
                .spec(baseRequestConfiguration())
                .contentType(URLENC.withCharset(StandardCharsets.UTF_8))
                .log().all()
                .queryParams(parameters)
                .request(requestMethod, URL)
                .prettyPeek();

        switch (status) {
            case (ResponseStatus.GOOD_RESPONSE) -> response.then().assertThat().spec(TrelloServiceObj.goodResponseSpecification());
            case (ResponseStatus.BAD_RESPONSE) -> response.then().assertThat().spec(TrelloServiceObj.badResponseSpecification());
            case (ResponseStatus.NOT_FOUND_RESPONSE) -> response.then().assertThat().spec(TrelloServiceObj.notFoundResponse());
        }

        return formBoardFromResponse(response);
    }

    public static TrelloBoard createBoard(String name, int status) {
        URL = URLConstant.BASE.getURLConstant() + URLConstant.BOARDS.getURLConstant();
        return requestBuilder()
                .setMethod(Method.POST)
                .name(name)
                .buildRequest()
                .boardRequest(status);
    }

    public static void deleteBoard(String id, int status) {
        URL = URLConstant.BASE.getURLConstant() + URLConstant.BOARDS.getURLConstant() + id;
        requestBuilder()
                .setMethod(Method.DELETE)
                .buildRequest()
                .boardRequest(status);
    }

    public static TrelloBoard getBoard(String id, int status) {
        URL = URLConstant.BASE.getURLConstant() + URLConstant.BOARDS.getURLConstant() + id;
        return requestBuilder()
                .setMethod(Method.GET)
                .buildRequest()
                .boardRequest(status);
    }

    public static TrelloBoard updateBoard(String id, String paramName, String param, int status) {
        URL = URLConstant.BASE.getURLConstant() + URLConstant.BOARDS.getURLConstant() + id;
        return requestBuilder()
                .setMethod(Method.PUT)
                .customParameter(paramName, param)
                .buildRequest()
                .boardRequest(status);
    }

    public static TrelloBoard formBoardFromResponse(Response response) {
        if (HttpStatus.SC_OK == response.then().extract().statusCode()) {
            return new Gson().
                    fromJson(response.asString().trim(),
                            new TypeToken<TrelloBoard>() {}.getType());
        } else {
            return null;
        }
    }

    private static ResponseSpecification notFoundResponse() {
        return new ResponseSpecBuilder()
                .expectContentType(TEXT)
                .expectResponseTime(lessThan(10000L))
                .expectStatusCode(HttpStatus.SC_NOT_FOUND)
                .build();
    }


    public static ResponseSpecification goodResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(10000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static ResponseSpecification badResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.TEXT)
                .expectResponseTime(lessThan(10000L))
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .build();
    }
}