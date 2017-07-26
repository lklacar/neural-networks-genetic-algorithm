package rs.ac.uns.ftn.nansi.genetic;

import rs.ac.uns.ftn.nansi.neuralnetwork.ActivationFunction;
import rs.ac.uns.ftn.nansi.neuralnetwork.NeuralNetwork;
import rs.ac.uns.ftn.nansi.util.Factory;

public class NeuralNetworkFactory implements Factory<NeuralNetwork> {

    private int inputLayerSize;
    private int[] hiddenLayers;
    private int outputLayers;
    private ActivationFunction activationFunction;

    public NeuralNetworkFactory(int inputLayerSize, int[] hiddenLayers, int outputLayers, ActivationFunction activationFunction) {
        this.inputLayerSize = inputLayerSize;
        this.hiddenLayers = hiddenLayers;
        this.outputLayers = outputLayers;
        this.activationFunction = activationFunction;
    }

    @Override
    public NeuralNetwork create() {
        return new NeuralNetwork(inputLayerSize, hiddenLayers,
                outputLayers, activationFunction);
    }
}
