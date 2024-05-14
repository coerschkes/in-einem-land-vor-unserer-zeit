package com.github.coerschkes;

import com.github.coerschkes.daemon.EmuConnector;
import com.github.coerschkes.daemon.EmuDevice;
import com.github.coerschkes.daemon.EmuInterface;
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
