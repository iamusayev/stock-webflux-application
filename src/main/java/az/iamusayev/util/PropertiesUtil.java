package az.iamusayev.util;

import java.util.Properties;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }


    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }


    private static void loadProperties() {
        try (var stream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
