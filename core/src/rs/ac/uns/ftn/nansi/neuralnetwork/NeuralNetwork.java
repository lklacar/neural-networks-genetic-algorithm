package rs.ac.uns.ftn.nansi.neuralnetwork;

import java.io.Serializable;
import java.util.ArrayList;

public class NeuralNetwork implements Comparable<NeuralNetwork>, Serializable {


    private static final long serialVersionUID = -8934095622912348236L;
    private int inputLayerCount;
    private int[] hiddenLayer;
    private int outputLayerCount;
    private ActivationFunction activationFunction;

    private ArrayList<Matrix> matrices;

    private double fitness;

    public NeuralNetwork(int inputLayerCount, int[] hiddenLayer,
                         int outputLayerCount, ActivationFunction activationFunction) {
        super();
        this.inputLayerCount = inputLayerCount;
        this.hiddenLayer = hiddenLayer;
        this.outputLayerCount = outputLayerCount;
        this.activationFunction = activationFunction;

        this.matrices = new ArrayList<Matrix>();
        createMatrices();
    }

    public Matrix calculate(double[] inputValues) {

        Matrix inputMatrix = new Matrix(inputValues.length, 1);

        for (int i = 0; i < inputValues.length; i++)
            inputMatrix.getData()[i][0] = inputValues[i];

        Matrix hiddenLayer = matrices.get(0).times(inputMatrix);
        // hiddenLayer.addFunction(activationFunction);

        for (int i = 1; i < matrices.size() - 1; i++) {
            hiddenLayer = matrices.get(i).times(hiddenLayer);
            hiddenLayer.addFunction(activationFunction);
        }

        Matrix output = matrices.get(matrices.size() - 1).times(hiddenLayer);
        output.addFunction(activationFunction);

        return output;
    }

    private void createMatrices() {

        // Create first matrix - input -> first hidden
        matrices.add(Matrix.random(hiddenLayer[0], inputLayerCount));

        // Create hidden layer matrices - first hidden -> last hidden
        for (int i = 0; i < hiddenLayer.length - 1; i++) {
            matrices.add(Matrix.random(hiddenLayer[i + 1], hiddenLayer[i]));
        }

        // create last matrix - last hidden layer -> output layer
        matrices.add(Matrix.random(outputLayerCount,
                hiddenLayer[hiddenLayer.length - 1]));

    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public ArrayList<Matrix> getMatrices() {
        return matrices;
    }

    @Override
    public int compareTo(NeuralNetwork o) {
        if (this.fitness > o.fitness) {
            return -1;
        } else if (this.fitness < o.fitness) {
            return 1;
        }
        return 0;
    }

}
