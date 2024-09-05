package com.empik.enums;

public enum PropertiesValues {
    BROWSER("browser", "chrome"),

    SELENIUM_URL("selenium_url", "http://localhost:4444");

    private String key;
    private String defaultValue;

    PropertiesValues(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
