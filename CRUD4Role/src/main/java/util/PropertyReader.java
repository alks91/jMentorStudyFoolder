package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {


    public static String getPropertyValue(String key) {
        Properties properties = new Properties();
        InputStream propertyStream = PropertyReader.class.getClassLoader().getResourceAsStream("db.properties");
        String result = null;
        try {
          properties.load(propertyStream);
          result = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
