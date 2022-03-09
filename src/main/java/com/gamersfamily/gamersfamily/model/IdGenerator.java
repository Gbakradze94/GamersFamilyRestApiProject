package com.gamersfamily.gamersfamily.model;

public class IdGenerator {

    private IdGenerator() {
        throw new IllegalArgumentException("this class is not meant to be initialised");
    }

    private static long id;

    public static long createId() {
        return id++;
    }
}
