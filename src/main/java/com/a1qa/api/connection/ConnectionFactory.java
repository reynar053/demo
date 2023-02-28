package com.a1qa.api.connection;

public abstract class ConnectionFactory {

    public static Get get() {
        return new Get();
    }

    public static Post post() {
        return new Post();
    }

}
