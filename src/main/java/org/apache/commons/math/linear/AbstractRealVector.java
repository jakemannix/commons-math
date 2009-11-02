package org.apache.commons.math.linear;

import java.util.Iterator;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.purefunctions.BinaryRealFunction;
import org.apache.commons.math.analysis.purefunctions.UnivariateRealFunctions;

public class AbstractRealVector implements RealVector {
    protected double defaultValue = 0;

    public double getDefaultValue() {
        return defaultValue;
    }


    /**
     * Check if instance and specified vectors have the same dimension.
     *
     * @param v vector to compare instance with
     * @throws IllegalArgumentException if the vectors do not
     *                                  have the same dimension
     */
    protected void checkVectorDimensions(RealVector v) {
        checkVectorDimensions(v.getDimension());
    }

    /**
     * Check if instance dimension is equal to some expected value.
     *
     * @param n expected dimension.
     * @throws IllegalArgumentException if the dimension is
     *                                  inconsistent with vector size
     */
    protected void checkVectorDimensions(int n)
            throws IllegalArgumentException {
        double d = getDimension();
        if (d != n) {
            throw MathRuntimeException.createIllegalArgumentException(
                    "vector length mismatch: got {0} but expected {1}",
                    d, n);
        }
    }

    /**
     * Check if an index is valid.
     *
     * @param index index to check
     * @throws MatrixIndexException if index is not valid
     */
    protected void checkIndex(final int index)
            throws MatrixIndexException {
        if (index < 0 || index >= getDimension()) {
            throw new MatrixIndexException(
                    "index {0} out of allowed range [{1}, {2}]",
                    index, 0, getDimension() - 1);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setSubVector(int index, RealVector v) throws MatrixIndexException {
        checkIndex(index);
        checkIndex(index + v.getDimension() - 1);
        setSubVector(index, v.getData());
    }

    /**
     * {@inheritDoc}
     */
    public void setSubVector(int index, double[] v) throws MatrixIndexException {
        checkIndex(index);
        checkIndex(index + v.length - 1);
        for (int i = 0; i < v.length; i++) {
            setEntry(i + index, v[i]);
        }
    }

    public RealVector add(double[] v) throws IllegalArgumentException {
        return add(new ArrayRealVector(v, false));
    }

    public RealVector subtract(RealVector v) throws IllegalArgumentException {
        try {
            return map(BinaryRealFunction.Subtract, v);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public AbstractRealVector copy() {
        return this;
    }

    public RealVector add(RealVector v) throws IllegalArgumentException {
        try {
            return map(BinaryRealFunction.Add, v);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public double dotProduct(double[] v) throws IllegalArgumentException {
        return dotProduct(new ArrayRealVector(v, false));
    }

    public double getNorm() {
        try {
            return collect(Collectors.Dot, this);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public double getL1Norm() {
        try {
            return collect(new AbstractUnivariateCollector() {
                public void collect(double d) {
                    value += Math.abs(d);
                }
            });
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public double getLInfNorm() {
        try {
            return collect(new AbstractUnivariateCollector() {
                public void collect(double d) {
                    value = Math.max(value, Math.abs(d));
                }
            });
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector ebeDivide(double[] v) throws IllegalArgumentException {
        return ebeDivide(new ArrayRealVector(v, false));
    }

    public RealVector ebeMultiply(double[] v) throws IllegalArgumentException {
        return ebeMultiply(new ArrayRealVector(v, false));
    }

    public RealVector ebeDivide(RealVector v) throws IllegalArgumentException {
        try {
            return mapToSelf(BinaryRealFunction.Divide, v);
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public double getDistance(RealVector v) throws IllegalArgumentException {
        return subtract(v).getNorm();
    }

    public double getDistance(double[] v) throws IllegalArgumentException {
        return getDistance(new ArrayRealVector(v, false));
    }

    public double getL1Distance(RealVector v) throws IllegalArgumentException {
        return subtract(v).getL1Norm();
    }

    public double getL1Distance(double[] v) throws IllegalArgumentException {
        return getL1Distance(new ArrayRealVector(v, false));
    }

    public double getLInfDistance(RealVector v) throws IllegalArgumentException {
        return subtract(v).getLInfNorm();
    }

    public double getLInfDistance(double[] v) throws IllegalArgumentException {
        return getLInfDistance(new ArrayRealVector(v, false));
    }

    public RealVector mapAbs() {
        return copy().mapAbsToSelf();
    }

    public RealVector mapAbsToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Abs);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapAcos() {
        return copy().mapAcosToSelf();
    }

    public RealVector mapAcosToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Acos);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapAdd(double d) {
        return copy().mapAddToSelf(d);
    }

    public RealVector mapAddToSelf(double d) {
        try {
            return map(BinaryRealFunction.Add.provideDefaultSecondArgument(d));
        } catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapAsin() {
        return copy().mapAsinToSelf();
    }

    public RealVector mapAsinToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Asin);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapAtan() {
        return copy().mapAtanToSelf();
    }

    public RealVector mapAtanToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Atan);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapCbrt() {
        return copy().mapCbrtToSelf();
    }

    public RealVector mapCbrtToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Cbrt);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapCeil() {
        return copy().mapCeilToSelf();
    }

    public RealVector mapCeilToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Ceil);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapCos() {
        return copy().mapCosToSelf();
    }

    public RealVector mapCosToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Cos);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapCosh() {
        return copy().mapCoshToSelf();
    }

    public RealVector mapCoshToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Cosh);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapDivide(double d) {
        return copy().mapDivideToSelf(d);
    }

    public RealVector mapDivideToSelf(double d) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector mapExp() {
        return copy().mapExpToSelf();
    }

    public RealVector mapExpToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Exp);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapExpm1() {
        return copy().mapExpm1ToSelf();
    }

    public RealVector mapExpm1ToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Exp1m);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapFloor() {
        return copy().mapFloorToSelf();
    }

    public RealVector mapFloorToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Floor);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapInv() {
        return copy().mapFloorToSelf();
    }

    public RealVector mapInvToSelf() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector mapLog() {
        return copy().mapLogToSelf();
    }

    public RealVector mapLogToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Log);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapLog10() {
        return copy().mapLog10ToSelf();
    }

    public RealVector mapLog10ToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Log10);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapLog1p() {
        return copy().mapLog1pToSelf();
    }

    public RealVector mapLog1pToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Asin);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapMultiply(double d) {
        return copy().mapMultiplyToSelf(d);
    }

    public RealVector mapMultiplyToSelf(double d) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector mapPow(double d) {
        return copy().mapPowToSelf(d);
    }

    public RealVector mapPowToSelf(double d) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector mapRint() {
        return copy().mapRintToSelf();
    }

    public RealVector mapRintToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Rint);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapSignum() {
        return copy().mapSignumToSelf();
    }

    public RealVector mapSignumToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Signum);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapSin() {
        return copy().mapSinToSelf();
    }

    public RealVector mapSinToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Sin);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapSinh() {
        return copy().mapSinhToSelf();
    }

    public RealVector mapSinhToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Sinh);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapSqrt() {
        return copy().mapSqrtToSelf();
    }

    public RealVector mapSqrtToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Sqrt);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapSubtract(double d) {
        return copy().mapSubtractToSelf(d);
    }

    public RealVector mapSubtractToSelf(double d) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector mapTan() {
        return copy().mapTanToSelf();
    }

    public RealVector mapTanToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Tan);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapTanh() {
        return copy().mapTanhToSelf();
    }

    public RealVector mapTanhToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Tanh);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector mapUlp() {
        return copy().mapUlpToSelf();
    }

    public RealVector mapUlpToSelf() {
        try {
            return mapToSelf(UnivariateRealFunctions.Ulp);
        }
        catch (FunctionEvaluationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RealVector ebeMultiply(RealVector v) throws IllegalArgumentException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealMatrix outerProduct(RealVector v) throws IllegalArgumentException {
        RealMatrix product;
        if (v instanceof SparseRealVector || this instanceof SparseRealVector) {
            product = new OpenMapRealMatrix(this.getDimension(), v.getDimension());
        } else {
            product = new Array2DRowRealMatrix(this.getDimension(), v.getDimension());
        }
        Iterator<Entry> thisIt = nonDefaultIterator();
        Entry thisE = null;
        while (thisIt.hasNext() && (thisE = thisIt.next()) != null) {
            Iterator<Entry> otherIt = v.nonDefaultIterator();
            Entry otherE = null;
            while (otherIt.hasNext() && (otherE = otherIt.next()) != null) {
                product.setEntry(thisE.index, otherE.index, thisE.getValue() * otherE.getValue());
            }
        }

        return product;
    }

    public RealMatrix outerProduct(double[] v) throws IllegalArgumentException {
        return outerProduct(new ArrayRealVector(v, false));
    }

    public double getEntry(int index) throws MatrixIndexException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setEntry(int index, double value) throws MatrixIndexException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getDimension() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector append(RealVector v) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector append(double d) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector append(double[] a) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector getSubVector(int index, int n) throws MatrixIndexException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector projection(double[] v) throws IllegalArgumentException {
        return projection(new ArrayRealVector(v, false));
    }

    public void set(double value) {
        Iterator<Entry> it = iterator();
        Entry e = null;
        while (it.hasNext() && (e = it.next()) != null) {
            e.setValue(value);
        }
    }

    public RealVector subtract(double[] v) throws IllegalArgumentException {
        return subtract(new ArrayRealVector(v, false));
    }

    public double[] toArray() {
        int dim = getDimension();
        double[] values = new double[dim];
        for (int i = 0; i < dim; i++) {
            values[i] = getEntry(i);
        }
        return values;
    }

    public boolean isNaN() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isInfinite() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public double[] getData() {
        return toArray();
    }

    public double dotProduct(RealVector v) throws IllegalArgumentException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector unitVector() {
        RealVector copy = copy();
        copy.unitize();
        return copy;
    }

    public void unitize() {
        mapDivideToSelf(getNorm());
    }

    public RealVector projection(RealVector v) throws IllegalArgumentException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Iterator<Entry> nonDefaultIterator() {
        return new SparseEntryIterator();
    }

    public Iterator<Entry> iterator() {
        final int dim = getDimension();
        return new Iterator<Entry>() {
            int i = 0;
            EntryImpl e = new EntryImpl();

            public boolean hasNext() {
                return i < dim;
            }

            public Entry next() {
                e.index = i++;
                return e;
            }

            public void remove() {
                throw new UnsupportedOperationException("Not supported");
            }
        };
    }

    public RealVector map(UnivariateRealFunction function) throws FunctionEvaluationException {
        return copy().mapToSelf(function);
    }

    public RealVector map(BinaryRealFunction function, RealVector other) throws FunctionEvaluationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector mapToSelf(BinaryRealFunction function, RealVector other) throws FunctionEvaluationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public double collect(UnivariateCollector collector) throws FunctionEvaluationException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public double collect(BinaryCollector collector, RealVector other) throws FunctionEvaluationException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector mapToSelf(UnivariateRealFunction function) throws FunctionEvaluationException {
        Iterator<Entry> it = getDefaultValue() == function.value(getDefaultValue())
                ? nonDefaultIterator()
                : iterator();
        Entry e = null;
        while (it.hasNext() && (e = it.next()) != null) {
            e.setValue(function.value(e.getValue()));
        }
        return this;
    }

    protected class EntryImpl extends Entry {
        public EntryImpl() {
            index = 0;
        }

        public double getValue() {
            return getEntry(index);
        }

        public void setValue(double newValue) {
            setEntry(index, newValue);
        }
    }

    /**
     * This class should rare be used, but is here to provide
     * a default implementation of nonDefaultIterator(), which is implemented
     * by walking over the entries, skipping those whose values are the default one.
     * <p/>
     * Concrete subclasses which are SparseVector implementations should
     * make their own sparse iterator, not use this one.
     * <p/>
     * This implementation might be useful for ArrayRealVector, when expensive
     * operations which preserve the default value are to be done on the entries,
     * and the fraction of non-default values is small (i.e. someone took a
     * SparseVector, and passed it into the copy-constructor of ArrayRealVector)
     */
    protected class SparseEntryIterator implements Iterator<Entry> {
        int i;
        int dim;
        EntryImpl tmp = new EntryImpl();
        EntryImpl current;
        EntryImpl next;

        protected SparseEntryIterator() {
            i = 0;
            dim = getDimension();
            current = new EntryImpl();
            if (current.getValue() == getDefaultValue()) advance(current);
            next = new EntryImpl();
            next.index = current.index;
            advance(next);
        }

        protected void advance(EntryImpl e) {
            if (e == null) return;
            do {
                e.index++;
            } while (e.index < dim && e.getValue() == getDefaultValue());
            if (e.index >= dim) e.index = -1;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Entry next() {
            tmp.index = current.index;
            if (next != null) {
                current.index = next.index;
                advance(next);
                if (next.index < 0) next = null;
            } else {
                current = null;
            }
            return tmp;
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported");
    }
  }

}
