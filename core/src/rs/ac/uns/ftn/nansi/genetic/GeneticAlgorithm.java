package rs.ac.uns.ftn.nansi.genetic;

import rs.ac.uns.ftn.nansi.neuralnetwork.Matrix;
import rs.ac.uns.ftn.nansi.neuralnetwork.NeuralNetwork;
import rs.ac.uns.ftn.nansi.util.MathUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgorithm implements TrainingAlgorithm<ArrayList<NeuralNetwork>>, Serializable {

    private static final long serialVersionUID = 1343352967654384459L;

    private ArrayList<NeuralNetwork> population;

    private NeuralNetworkFactory neuralNetworkFactory;

    public GeneticAlgorithm(NeuralNetworkFactory neuralNetworkFactory, int populationSize) {
        this.population = new ArrayList<NeuralNetwork>();
        this.neuralNetworkFactory = neuralNetworkFactory;
        addPopulation(populationSize);
    }

    @Override
    public void nextIteration() {
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

    @Override
    public ArrayList<NeuralNetwork> getResult() {
        return population;
    }

    private ArrayList<NeuralNetwork> generateNewPair(NeuralNetwork a,
                                                     NeuralNetwork b) {
        Random random = new Random();
        NeuralNetwork first = neuralNetworkFactory.create();
        NeuralNetwork second = neuralNetworkFactory.create();

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
        for (NeuralNetwork nn : population) {
            sum += nn.getFitness();
        }

        double random = MathUtil.randRange(0, sum);

        sum = 0;
        for (int i = 0; i < population.size(); i++) {
            sum += population.get(i).getFitness();
            if (sum > random) {
                return i;
            }
        }

        return 0;
    }

    private void addPopulation(int populationSize) {
        for (int i = 0; i < populationSize; i++) {
            population.add(neuralNetworkFactory.create());
        }

    }

}
