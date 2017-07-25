package rs.ac.uns.ftn.nansi.neuralnetwork;

import java.io.Serializable;

public class SigmoidActivationFunction implements ActivationFunction, Serializable {

    private static final long serialVersionUID = 5931885019037882693L;

    @Override
    public double function(double x) {
        return 1 / (1 + Math.pow(Math.E, -x));
    }

}
