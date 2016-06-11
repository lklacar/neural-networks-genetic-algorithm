package rs.ac.uns.ftn.nansi.desktop.settings;

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
	
	

	public int getGenerationType() {
		return generationType;
	}

	public void setGenerationType(int generationType) {
		this.generationType = generationType;
	}

	private int displaySensors = 2;
	private boolean autoNext = false;

	public int getDisplaySensors() {
		return displaySensors;
	}

	public void setDisplaySensors(int displaySensors) {
		this.displaySensors = displaySensors;
	}

	public boolean isAutoNext() {
		return autoNext;
	}

	public void setAutoNext(boolean autoNext) {
		this.autoNext = autoNext;
	}

	private SimulationSettings() {

	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public int getHiddenLayerCount() {
		return hiddenLayerCount;
	}

	public void setHiddenLayerCount(int hiddenLayerCount) {
		this.hiddenLayerCount = hiddenLayerCount;
	}

	public int getNeuronsPerHiddenLayer() {
		return neuronsPerHiddenLayer;
	}

	public void setNeuronsPerHiddenLayer(int neuronsPerHiddenLayer) {
		this.neuronsPerHiddenLayer = neuronsPerHiddenLayer;
	}

	public int getLeftAngle() {
		return leftAngle;
	}

	public void setLeftAngle(int leftAngle) {
		this.leftAngle = leftAngle;
	}

	public int getNextAngle() {
		return nextAngle;
	}

	public void setNextAngle(int nextAngle) {
		this.nextAngle = nextAngle;
	}

	public int getRightAngle() {
		return rightAngle;
	}

	public void setRightAngle(int rightAngle) {
		this.rightAngle = rightAngle;
	}

	public int getRoadWidth() {
		return roadWidth;
	}

	public void setRoadWidth(int roadWidth) {
		this.roadWidth = roadWidth;
	}

	public static SimulationSettings getInstance() {
		if (instance == null)
			instance = new SimulationSettings();

		return instance;
	}

}
