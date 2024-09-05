package com.empik.utils;

import com.empik.enums.PropertiesValues;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class ConfigurationReader {
    private static final Logger log = LoggerFactory.getLogger(ConfigurationReader.class);

    public static String getProperty(PropertiesValues property){
        Properties properties = new Properties();

        try {
            FileReader fileReader = new FileReader("src/main/resources/configuration.properties");
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            log.error("File was not found");
        } catch (IOException e) {
            log.error(" ");;
        }

        for (Object key : properties.keySet()) {
            String systemValue = System.getProperty((String) key);
            if (!StringUtils.isEmpty(systemValue)) {
                properties.put(key, systemValue);
            }
        }

        return properties.getProperty(property.getKey(), property.getDefaultValue());
    }
}
