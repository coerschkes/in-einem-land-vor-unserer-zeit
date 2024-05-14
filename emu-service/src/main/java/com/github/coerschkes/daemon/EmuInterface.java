package com.github.coerschkes.daemon;

import java.util.concurrent.CompletableFuture;

public interface EmuInterface {

    void connect();

    void disconnect();

    void activateProgrammingMode();

    CompletableFuture<String> getCurrentPower();
}