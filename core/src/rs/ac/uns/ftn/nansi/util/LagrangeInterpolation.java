package rs.ac.uns.ftn.nansi.util;

public class LagrangeInterpolation extends Interpolation {

    private double[] x;
    private double[] y;

    public LagrangeInterpolation(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public double value(double x) {
        double y = 0;
        double p;
        int n = this.x.length;
        for (int i = 0; i < n; ++i) {
            p = this.y[i];
            for (int j = 0; j < n; ++j) {
                if (i != j) {
                    p = p * (x - this.x[j]) / (this.x[i] - this.x[j]);
                }
            }
            y += p;
        }
        return y;
    }
}
