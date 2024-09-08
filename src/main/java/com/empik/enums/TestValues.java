package com.empik.enums;

public enum TestValues {
    PSEARCH("psearch", "pędzle"),
    NSEARCH("nsearch", "srgertjj"),
    EMAIL("email", "test@gmail.com");

    private String key;
    private String defaultValue;

    TestValues(String key, String defaultValue) {
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
