package com.github.coerschkes.application.external.hardware.daemon;


import net.sf.yad2xx.FTDIException;

import java.util.concurrent.CompletableFuture;

public class EmuConnector implements EmuInterface {
    private EmuDaemon daemon;
    private static EmuConnector instance;

    private EmuConnector() {
        createDaemon();
    }

    public static EmuConnector getInstance() {
        if (instance == null) {
            instance = new EmuConnector();
        }
        return instance;
    }

    @Override
    public void connect() {
        this.daemon.setConnected(true);
        this.daemon.start();
        System.out.println("Establishing connection to device..");
        this.daemon.executeCommand(EmuCommand.START_COMMUNICATION);
    }

    @Override
    public void disconnect() {
        System.out.println("Closing connection..");
        this.daemon.executeCommand(EmuCommand.END_COMMUNICATION);
    }

    @Override
    public void activateProgrammingMode() {
        System.out.println("Activating programming mode..");
        this.daemon.executeCommand(EmuCommand.PROGRAMMING_MODE);
    }

    @Override
    public CompletableFuture<String> getCurrentPower() {
        System.out.println("Retrieving power..");
        if (!daemon.isConnected()) {
            createDaemon();
        }
        return this.daemon.executeCommand(EmuCommand.POWER).thenApply(this::formatPowerMeasurementValue);
    }

    private String formatPowerMeasurementValue(final String measurement) {
        return "{ \"power\": \"" + measurement.substring(measurement.indexOf("(") + 1, measurement.indexOf("*")) + "\" }";
    }

    private void createDaemon() {
        try {
            this.daemon = new EmuDaemon(new EmuDevice());
            this.connect();
            this.activateProgrammingMode();
        } catch (FTDIException e) {
            throw new RuntimeException(e);
        }
    }
}
