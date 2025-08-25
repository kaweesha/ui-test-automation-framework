package com.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        String env = System.getProperty("env", "qa");
        String configFile = "src/test/resources/config." + env + ".properties";

        try {
            FileInputStream fis = new FileInputStream(configFile);
            properties.load(fis);
            System.out.println("✅ Loaded config file: " + configFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to load config file: " + configFile);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
