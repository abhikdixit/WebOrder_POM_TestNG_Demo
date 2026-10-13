package com.webOrders.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {

        properties = new Properties();

        try {

            FileInputStream fis = new FileInputStream(
                    System.getProperty("user.dir")
                            + "/src/test/resources/config.properties");

            properties.load(fis);

        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }
    }

    public static String getProperty(String key) {

        return System.getProperty(key,
                properties.getProperty(key));
    }
}