package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConnectionProperty {
  public static final String CONFIG_NAME = "config.properties";
  public static final Properties GLOBAL_CONFIG = new Properties();

  public ConnectionProperty()
      throws FileNotFoundException, IOException {
    ClassLoader classLoader = getClass().getClassLoader();
    GLOBAL_CONFIG.load(classLoader.getResourceAsStream(CONFIG_NAME));
  }

  // Получить значение параметра из конфигурации по имени свойства
  public static String getProperty(String property) {
    return GLOBAL_CONFIG.getProperty(property);
  }
}
