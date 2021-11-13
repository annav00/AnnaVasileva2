package Selenium_hw5.context;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private static TestContext instance;
    private Map<String, Object> context = new HashMap<>();

    private TestContext() {
    }

    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }

    public TestContext putObject(String key, Object object) {
        context.put(key, object);
        return this;
    }

//    public <T> void addTestObjectInList(String key, T value) {
//        if (context.containsKey(key)) {
//            List<T> oldList = (List<T>) context.get(key);
//            oldList.add(value);
//            context.put(key, oldList);
//        } else {
//            List<T> listSave = new ArrayList<>();
//            listSave.add(value);
//            context.put(key, listSave);
//        }
//    }


    public <T> T getObject(String key) {
        return (T) context.get(key);
    }


    public void clear() {
        context.clear();
        instance = null;
    }
}
