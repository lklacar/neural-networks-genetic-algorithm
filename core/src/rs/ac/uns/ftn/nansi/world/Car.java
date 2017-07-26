package rs.ac.uns.ftn.nansi.world;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import rs.ac.uns.ftn.nansi.desktop.settings.SimulationSettings;
import rs.ac.uns.ftn.nansi.factories.Box2DFactory;
import rs.ac.uns.ftn.nansi.neuralnetwork.Matrix;
import rs.ac.uns.ftn.nansi.neuralnetwork.NeuralNetwork;

import java.util.ArrayList;
import java.util.HashSet;

public class Car {

    private Body body;

    private NeuralNetwork network;

    private ArrayList<CarSensor> sensors;

    private HashSet<RoadLine> objectives;

    public Car(GameWorld gameWorld, Vector2 pos) {
        this.objectives = new HashSet<RoadLine>();

        this.body = new Box2DFactory(gameWorld.getWorld(), pos).create();


        this.sensors = new ArrayList<CarSensor>();

        for (int i = SimulationSettings.getInstance().getLeftAngle(); i <= SimulationSettings
                .getInstance().getRightAngle(); i += SimulationSettings
                .getInstance().getNextAngle()) {
            sensors.add(new CarSensor(this, gameWorld.getRoad(), i));

        }
    }

    public void drawSensors(ShapeRenderer renderer) {
        for (CarSensor cs : sensors) {
            cs.draw(renderer);
        }
    }

    public void updateSensors() {
        for (CarSensor cs : sensors)
            cs.update();
    }

    public Body getBody() {
        return body;
    }


    public NeuralNetwork getNetwork() {
        return network;
    }

    public void setNetwork(NeuralNetwork network) {
        this.network = network;
    }


    public void update() {

        double[] input = new double[sensors.size()];

        for (int i = 0; i < input.length; i++) {
            input[i] = sensors.get(i).getDistance();
        }

        Matrix output = network.calculate(input);

        body.setAngularVelocity((float) output.getData()[0][0] * 4 - 2f);

        Vector2 force = new Vector2();
        force.x = (float) output.getData()[1][0] * 10;
        force.y = 0;

        force.rotate((float) Math.toDegrees(body.getAngle()));

        body.setLinearVelocity(force);

    }

    public boolean addFitness(RoadLine objective) {

        if (objectives.contains(objective))
            return false;

        objectives.add(objective);
        network.setFitness(objectives.size());

        return true;

    }

}
