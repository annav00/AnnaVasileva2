package core;

import beans.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import constants.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.http.ContentType.*;
import static org.hamcrest.Matchers.*;

public class TrelloServiceObj {

    public static String URL;
    private Method requestMethod;

    private Map<String, String> parameters;

    private TrelloServiceObj(Map<String, String> parameters, Method method) {
        this.parameters = parameters;
        this.requestMethod = method;
    }

    public static ApiRequestBuilder requestBuilder() {
        return new ApiRequestBuilder();
    }

    public static class ApiRequestBuilder {
        private Map<String, String> parameters = new HashMap<>();
        private Method requestMethod = Method.GET;

        public ApiRequestBuilder setMethod (Method method){
            requestMethod = method;
            return this;
        }

        public ApiRequestBuilder id(String id) {
            parameters.put(BoardConstant.ID.getConstantName(), id);
            return this;
        }

        public ApiRequestBuilder name(String name) {
            parameters.put(BoardConstant.NAME.getConstantName(), name);
            return this;
        }

        public ApiRequestBuilder closed(Boolean closingState) {
            parameters.put(BoardConstant.CLOSED.getConstantName(), closingState.toString());
            return this;
        }

        public ApiRequestBuilder desc(String desc) {
            parameters.put(BoardConstant.DESC.getConstantName(), desc);
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
                //.setAccept(ContentType.JSON)
                //.addQueryParam("requestNumber", ++requestNumber)
                .setRelaxedHTTPSValidation()
                .setBaseUri(URI.create(URLConstant.BASE.get()))
                .addParam("key","05d638746f0f484a63b83713772fac99")
                .addParam("token","8380036c3f0f4c82a99e669bd763719cb9df855bfd5991d6893694223f3223c4")
                .build();
    }

    public  TrelloBoard boardRequest(int status) {
        Response response = RestAssured
                .with()
                .spec(baseRequestConfiguration())
                .contentType(URLENC.withCharset(Charset.defaultCharset()))
                .log().all()
                .queryParams(parameters)
                .request(requestMethod, URL)
                .prettyPeek();

        switch (status) {
            case(ResponseStatus.GOOD_RESPONSE):
                response.then().assertThat().spec(TrelloServiceObj.goodResponseSpecification());
                break;
            case(ResponseStatus.BAD_RESPONSE):
                response.then().assertThat().spec(TrelloServiceObj.badResponseSpecification());
                break;
            case(ResponseStatus.NOT_FOUND_RESPONSE):
                response.then().assertThat().spec(TrelloServiceObj.notFoundResponse());
                break;
        }

        return formBoardFromResponse(response);
    }

    public static TrelloBoard createBoard(String name, int status) {
        URL = URLConstant.BASE.get() + URLConstant.BOARDS.get();
        return requestBuilder()
                .setMethod(Method.POST)
                .name(name)
                .buildRequest()
                .boardRequest(status);
    }

    public static TrelloBoard deleteBoard(String id, int status) {
        URL = URLConstant.BASE.get() + URLConstant.BOARDS.get() + id;
        return requestBuilder()
                .setMethod(Method.DELETE)
                .buildRequest()
                .boardRequest(status);
    }

    public static TrelloBoard getBoard(String id, int status) {
        URL = URLConstant.BASE.get() + URLConstant.BOARDS.get() + id;
        return requestBuilder()
                .setMethod(Method.GET)
                .buildRequest()
                .boardRequest(status);
    }

    public static TrelloBoard updateBoard(String id, String paramName, String param, int status) {
        URL = URLConstant.BASE.get() + URLConstant.BOARDS.get() + id;
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