package com.github.coerschkes.application.external.hardware.daemon;

import java.util.concurrent.CompletableFuture;

public interface EmuInterface {

    void connect();

    void disconnect();

    void activateProgrammingMode();

    CompletableFuture<String> getCurrentPower();
}
