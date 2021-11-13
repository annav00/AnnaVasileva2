package API_hw2.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadingProperties {
    private static java.util.Properties properties = new java.util.Properties();

    public void loadProperties (String PATH_TO_PROPERTIES) {
        try {
            FileInputStream file = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(file);
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
