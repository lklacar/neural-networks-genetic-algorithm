package rs.ac.uns.ftn.nansi.util;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

public class LinearInterpolation extends Interpolation {

    private PolynomialSplineFunction psf;

    public LinearInterpolation(double x[], double y[]) {
        interpolate(x, y);
    }

    public void interpolate(double x[], double y[])
            throws DimensionMismatchException, NumberIsTooSmallException,
            NonMonotonicSequenceException {
        if (x.length != y.length) {
            throw new DimensionMismatchException(x.length, y.length);
        }

        if (x.length < 2) {
            throw new NumberIsTooSmallException(
                    LocalizedFormats.NUMBER_OF_POINTS, x.length, 2, true);
        }

        // Number of intervals. The number of data points is n + 1.
        int n = x.length - 1;

        MathArrays.checkOrder(x);

        // Slope of the lines between the datapoints.
        final double m[] = new double[n];
        for (int i = 0; i < n; i++) {
            m[i] = (y[i + 1] - y[i]) / (x[i + 1] - x[i]);
        }

        final PolynomialFunction polynomials[] = new PolynomialFunction[n];
        final double coefficients[] = new double[2];
        for (int i = 0; i < n; i++) {
            coefficients[0] = y[i];
            coefficients[1] = m[i];
            polynomials[i] = new PolynomialFunction(coefficients);
        }

        this.psf = new PolynomialSplineFunction(x, polynomials);
    }

    public double value(double x) {
        return psf.value(x);
    }
}
