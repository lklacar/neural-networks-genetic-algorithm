package rs.ac.uns.ftn.nansi.world;

import java.util.ArrayList;
import java.util.Collections;

import lombok.val;
import rs.ac.uns.ftn.nansi.desktop.settings.SimulationSettings;
import rs.ac.uns.ftn.nansi.genetic.GeneticAlgorithm;
import rs.ac.uns.ftn.nansi.genetic.NeuralNetworkFactory;
import rs.ac.uns.ftn.nansi.genetic.TrainingAlgorithm;
import rs.ac.uns.ftn.nansi.neuralnetwork.NeuralNetwork;
import rs.ac.uns.ftn.nansi.neuralnetwork.SigmoidActivationFunction;
import rs.ac.uns.ftn.nansi.util.Intersector;
import rs.ac.uns.ftn.nansi.world.inputprocessors.CameraControlInputProcessor;
import rs.ac.uns.ftn.nansi.world.inputprocessors.WorldInputProcessor;
import rs.ac.uns.ftn.nansi.world.inputprocessors.WorldResetInputProcessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameWorld {
    private final SimulationSettings settings;

    // Genetic attributes
    private int populationSize = 50;
    private ArrayList<Car> population;
    private TrainingAlgorithm<ArrayList<NeuralNetwork>> geneticAlgorithm;
    private int generation = 1;

    private ArrayList<Car> allCars;

    // World attributes
    private World box2dWorld;
    private OrthographicCamera camera;
    private Road road;
    private long time = 0;
    private Vector2 startingPos;
    private boolean pause = false;

    // LibGdx attributes
    private InputMultiplexer inputMultiplexer;

    public ArrayList<Car> getAllCars() {
        return allCars;
    }


    public void addPopulation() {

        for (int i = 0; i < populationSize; i++) {
            Car c = new Car(settings.getLeftAngle(), settings.getRightAngle(), settings.getNextAngle(), this, startingPos);

            c.setNetwork(geneticAlgorithm.getResult().get(i));

            population.add(c);
            allCars.add(c);

        }
    }


    private int calculateInputSize() {
        int count = 0;
        for (int i = settings.getLeftAngle(); i <= settings.getRightAngle(); i += settings.getNextAngle()) {

            count += 1;
        }

        return count;
    }

    public GameWorld(SimulationSettings settings) {
        this.settings = settings;
        populationSize = settings.getPopulationSize();

        this.allCars = new ArrayList<Car>();

        val inputLayerSize = calculateInputSize();
        val hiddenLayers = new int[settings
                .getHiddenLayerCount()];

        for (int i = 0; i < hiddenLayers.length; i++)
            hiddenLayers[i] = settings
                    .getNeuronsPerHiddenLayer();


        val outputLayers = 2;
        val activationFunction = new SigmoidActivationFunction();


        this.geneticAlgorithm = new GeneticAlgorithm(new NeuralNetworkFactory(inputLayerSize, hiddenLayers, outputLayers, activationFunction), populationSize);


        this.box2dWorld = new World(new Vector2(0, 0), true);
        this.camera = new OrthographicCamera(100, 76);
        this.camera.translate(new Vector2(50, 76 / 2));
        this.camera.update();
        this.road = new RoadFactory(settings.getInterpolationType(), settings.getRoadWidth()).create();
        this.population = new ArrayList<Car>();
        this.startingPos = getStartingPos(this.road.getObjectives().get(180));

        setupKeyboardInput();
        addPopulation();

    }

    // Public Methods
    public void draw(ShapeRenderer shapeRenderer,
                     Box2DDebugRenderer debugRenderer) {

        if (!pause) {
            // Updating
            update();
            updatePopulation();
            updateSensors();
            box2dWorld.step(1 / 60f, 8, 4);
        }

        // Drawing
        shapeRenderer.setProjectionMatrix(camera.combined);
        debugRenderer.render(box2dWorld, camera.combined);

        shapeRenderer.begin(ShapeType.Line);
        drawSensors(shapeRenderer);
        road.draw(shapeRenderer);
        shapeRenderer.end();

    }

    public void nextGenerationReset() {
        Collections.sort(geneticAlgorithm.getResult());

        geneticAlgorithm.nextIteration();

        generation++;

        box2dWorld = new World(new Vector2(0, 0), true);

        population.clear();
        allCars.clear();

        RoadLine startLine = this.road.getObjectives().get(180);
        startingPos = new Vector2(
                (startLine.getP1().x + startLine.getP2().x) / 2,
                (startLine.getP1().y + startLine.getP2().y) / 2);

        addPopulation();

        time = 0;
    }

    public void sameGenerationReset() {
        box2dWorld = new World(new Vector2(0, 0), true);

        population.clear();
        allCars.clear();

        RoadLine startLine = this.road.getObjectives().get(180);
        startingPos = new Vector2(
                (startLine.getP1().x + startLine.getP2().x) / 2,
                (startLine.getP1().y + startLine.getP2().y) / 2);

        addPopulation();

        time = 0;
    }

    // Private Methods
    private void update() {

        updateInformation();
        checkCarHit();
        time++;

        if ((population.size() == 0 || time > 150)
                && settings.isAutoNext()) {

            nextGenerationReset();

        }

    }

    private void updateInformation() {
        InformationDisplay.setRow(0,
                "Generation: " + Integer.toString(generation));
        InformationDisplay.setRow(1,
                "Best fittness: " + Double.toString(getBestFittness()));

    }

    private double getBestFittness() {
        return getFittest().getNetwork().getFitness();
    }

    private void setupKeyboardInput() {
        this.inputMultiplexer = new InputMultiplexer();

        inputMultiplexer.addProcessor(new WorldResetInputProcessor(this));
        inputMultiplexer.addProcessor(new CameraControlInputProcessor(camera));
        inputMultiplexer.addProcessor(new WorldInputProcessor(this));

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private Vector2 getStartingPos(RoadLine startLine) {
        return new Vector2((startLine.getP1().x + startLine.getP2().x) / 2,
                (startLine.getP1().y + startLine.getP2().y) / 2);
    }

    private void updatePopulation() {
        for (int i = 0; i < population.size(); i++) {

            population.get(i).update();

        }
    }

    private void updateSensors() {
        for (Car c : population) {
            c.updateSensors();

        }
    }

    private void drawSensors(ShapeRenderer shapeRenderer) {

        try {

            if (settings.getDisplaySensors() == 2)
                getFittest().drawSensors(shapeRenderer);

            else if (settings.getDisplaySensors() == 1) {

                for (Car c : population)
                    c.drawSensors(shapeRenderer);

            }
        } catch (Exception ex) {

        }
    }

    private Car getFittest() {
        Car fittest = population.get(0);

        for (Car c : population) {
            if (c.getNetwork().getFitness() > fittest.getNetwork().getFitness())
                fittest = c;
        }

        return fittest;

    }

    private void checkCarHit() {
        ArrayList<Car> remove = new ArrayList<Car>();

        for (Car car : population) {

            // Check if car has hit the wall
            for (RoadLine rl : road.getRoadLines())
                if (Intersector.intersectSegmentCircle(rl.getP1(), rl.getP2(),
                        car.getBody().getPosition(), 0.1f)) {

                    remove.add(car);
                    car.getBody().setLinearVelocity(new Vector2(0, 0));
                    car.getBody().setAngularVelocity(0f);
                    time = 0;

                }

            // Check if car has went through an objective
            for (RoadLine rl : road.getObjectives())
                if (Intersector.intersectSegmentCircle(rl.getP1(), rl.getP2(),
                        car.getBody().getPosition(), 0.1f))

                    if (car.addFitness(rl))
                        time = 0;

        }

        // Remove dead cars from the population
        population.removeAll(remove);

    }

    public TrainingAlgorithm getGeneticAlgorithm() {
        return geneticAlgorithm;
    }

    public void setGeneticAlgorithm(GeneticAlgorithm geneticAlgorithm) {
        this.geneticAlgorithm = geneticAlgorithm;
    }

    // Getters
    public World getWorld() {
        return box2dWorld;
    }

    public Road getRoad() {
        return road;
    }

    public void togglePause() {
        pause = !pause;
    }

    public void setRoad(Road generate) {
        this.road = generate;

    }

}
