package rs.ac.uns.ftn.nansi.genetic;

public interface TrainingAlgorithm<TResult> {

    void nextIteration();

    TResult getResult();

}
