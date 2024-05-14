package com.github.coerschkes.application.external.hardware;

import com.github.coerschkes.application.external.hardware.daemon.EmuConnector;
import com.github.coerschkes.application.external.hardware.daemon.EmuDevice;
import com.github.coerschkes.application.external.hardware.daemon.EmuInterface;
import net.sf.yad2xx.FTDIException;

import java.util.concurrent.CompletableFuture;

public class EmuModel {
    private static EmuModel modelInstance;
    private final EmuInterface emuInterface;

//    public static EmuModel getInstance() {
//        if (modelInstance == null) {
//            modelInstance = new EmuModel();
//        }
//        return modelInstance;
//    }

    private EmuModel() {
        try {
            emuInterface = new EmuConnector(new EmuDevice());
            emuInterface.connect();
            emuInterface.activateProgrammingMode();
        } catch (FTDIException e) {
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<String> readMeasurement() {
        return emuInterface.getCurrentPower();
    }

    public void disconnect() {
        emuInterface.disconnect();
    }
}
