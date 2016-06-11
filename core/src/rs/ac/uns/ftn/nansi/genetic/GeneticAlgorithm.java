package rs.ac.uns.ftn.nansi.genetic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import rs.ac.uns.ftn.nansi.desktop.settings.SimulationSettings;
import rs.ac.uns.ftn.nansi.neuralnetwork.Matrix;
import rs.ac.uns.ftn.nansi.neuralnetwork.NeuralNetwork;
import rs.ac.uns.ftn.nansi.neuralnetwork.SigmoidActivationFunction;
import rs.ac.uns.ftn.nansi.util.MathUtil;

public class GeneticAlgorithm implements Serializable {

	private static final long serialVersionUID = 1343352967654384459L;
	private int inputLayerSize = 9;
	private int[] hiddenLayers = { 100, 100, 100, 100, };
	private int outputLayers = 2;

	private ArrayList<NeuralNetwork> population;

	public GeneticAlgorithm(int populationSize) {
		population = new ArrayList<NeuralNetwork>();

		inputLayerSize = calculateInputSize();
		hiddenLayers = new int[SimulationSettings.getInstance()
				.getHiddenLayerCount()];

		for (int i = 0; i < hiddenLayers.length; i++)
			hiddenLayers[i] = SimulationSettings.getInstance()
					.getNeuronsPerHiddenLayer();

		addPopulation(populationSize);

	}

	private void addPopulation(int populationSize) {
		for (int i = 0; i < populationSize; i++) {
			population.add(new NeuralNetwork(inputLayerSize, hiddenLayers,
					outputLayers, new SigmoidActivationFunction()));
		}

	}

	private int calculateInputSize() {
		int count = 0;
		for (int i = SimulationSettings.getInstance().getLeftAngle(); i <= SimulationSettings
				.getInstance().getRightAngle(); i += SimulationSettings
				.getInstance().getNextAngle()) {

			count += 1;
		}

		return count;
	}

	public void generateNewPopulation() {
		ArrayList<NeuralNetwork> newPopulation = new ArrayList<NeuralNetwork>();
		Collections.sort(population);

		// // ELITE
		for (NeuralNetwork nn : population) {
			if (nn.getFitness() > 359) {
				newPopulation.add(nn);
			}
		}
		while (newPopulation.size() < 2) {
			newPopulation.add(population.get(newPopulation.size()));
		}

		int k = newPopulation.size() / 2;
		for (int i = 0; i < population.size() / 2 - k; i++) {
			int first = choose();
			int second = choose();
			while (second == first)
				second = choose();

			newPopulation.addAll(generateNewPair(population.get(first),
					population.get(second)));

		}

		population = newPopulation;

	}

	private ArrayList<NeuralNetwork> generateNewPair(NeuralNetwork a,
			NeuralNetwork b) {
		Random random = new Random();
		NeuralNetwork first = new NeuralNetwork(inputLayerSize, hiddenLayers,
				outputLayers, new SigmoidActivationFunction());
		NeuralNetwork second = new NeuralNetwork(inputLayerSize, hiddenLayers,
				outputLayers, new SigmoidActivationFunction());
		double mutateTolerance = 0.02;
		for (int k = 0; k < a.getMatrices().size(); ++k) {
			Matrix M = a.getMatrices().get(k);
			Matrix N = b.getMatrices().get(k);
			int n = random.nextInt(M.getN());
			for (int i = 0; i < M.getM(); ++i) {
				for (int j = 0; j < M.getN(); ++j) {

					if (j < n) {
						if (Math.random() < mutateTolerance) {
							first.getMatrices().get(k).getData()[i][j] = mutate();
						} else {
							first.getMatrices().get(k).getData()[i][j] = M
									.getData()[i][j];
						}
						if (Math.random() < mutateTolerance) {
							second.getMatrices().get(k).getData()[i][j] = mutate();
						} else {
							second.getMatrices().get(k).getData()[i][j] = N
									.getData()[i][j];
						}
					} else {
						if (Math.random() < mutateTolerance) {
							first.getMatrices().get(k).getData()[i][j] = mutate();
						} else {
							first.getMatrices().get(k).getData()[i][j] = N
									.getData()[i][j];
						}
						if (Math.random() < mutateTolerance) {
							second.getMatrices().get(k).getData()[i][j] = mutate();
						} else {
							second.getMatrices().get(k).getData()[i][j] = M
									.getData()[i][j];
						}
					}

				}
			}
		}
		ArrayList<NeuralNetwork> pair = new ArrayList<NeuralNetwork>();
		pair.add(first);
		pair.add(second);
		return pair;
	}

	private double mutate() {
		return MathUtil.randRange(-4, 4);
	}

	private int choose() {
		double sum = 0;
		for (NeuralNetwork nn : population)
			sum += nn.getFitness();

		double random = MathUtil.randRange(0, sum);

		sum = 0;
		for (int i = 0; i < population.size(); i++) {
			sum += population.get(i).getFitness();
			if (sum > random)
				return i;
		}

		return 0;
	}

	public ArrayList<NeuralNetwork> getPopulation() {
		return population;
	}

	public void setPopulation(ArrayList<NeuralNetwork> population) {
		this.population = population;
	}

}
