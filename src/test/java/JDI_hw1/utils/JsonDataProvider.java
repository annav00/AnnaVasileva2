package JDI_hw1.utils;

import JDI_hw1.entities.MetalColorsEntity;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

public class JsonDataProvider {
    private static final String PATH_TO_JSON_DATA = "src/test/resources/JDI_hw1/JDI_ex8_metalsColorsDataSet.json";

    @DataProvider(name = "metalColorsEntities")
    public static Iterator<Object[]> getMetalColorsEntities() {
        return JsonParser.parseReader(readJson(PATH_TO_JSON_DATA))
                .getAsJsonObject()
                .entrySet()
                .stream()
                .map(entry -> entry.getValue())
                .map(JsonElement -> new Gson().fromJson(JsonElement, MetalColorsEntity.class))
                .map(entry -> new Object[] {entry})
                .iterator();
    }

    private static JsonReader readJson(String filePath) {
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return reader;
    }
}
