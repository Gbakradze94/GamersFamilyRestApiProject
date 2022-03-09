package com.gamersfamily.gamersfamily.utils.enums;

public enum Platform {
    TEST1("Xbox"),TEST2("PC"),TEST3("IDK"),TEST4("IDK2");

    private final String platform;

    Platform(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }
}
