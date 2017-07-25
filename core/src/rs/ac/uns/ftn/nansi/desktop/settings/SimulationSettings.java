package rs.ac.uns.ftn.nansi.desktop.settings;

import lombok.Data;

@Data
public class SimulationSettings {

    private static SimulationSettings instance = null;

    private int populationSize = 50;
    private int hiddenLayerCount = 4;
    private int neuronsPerHiddenLayer = 100;
    private int leftAngle = -80;
    private int nextAngle = 20;
    private int rightAngle = 80;
    private int roadWidth = 50;
    private int generationType = 1;
    private int displaySensors = 2;
    private boolean autoNext = false;

    private SimulationSettings() {

    }

    public static SimulationSettings getInstance() {
        if (instance == null)
            instance = new SimulationSettings();

        return instance;
    }

}
