package org.apache.commons.math.linear;

import java.util.Iterator;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.UnivariateRealFunctions;

public abstract class AbstractRealVector implements RealVector
{

  /**
   * Check if instance and specified vectors have the same dimension.
   * @param v vector to compare instance with
   * @exception IllegalArgumentException if the vectors do not
   * have the same dimension
   */
  protected void checkVectorDimensions(RealVector v) {
      checkVectorDimensions(v.getDimension());
  }

  /**
   * Check if instance dimension is equal to some expected value.
   *
   * @param n expected dimension.
   * @exception IllegalArgumentException if the dimension is
   * inconsistent with vector size
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
   * @param index index to check
   * @exception MatrixIndexException if index is not valid
   */
  protected void checkIndex(final int index)
      throws MatrixIndexException {
      if (index < 0 || index >= getDimension()) {
          throw new MatrixIndexException(
                  "index {0} out of allowed range [{1}, {2}]",
                  index, 0, getDimension() - 1);
      }
  }

  /** {@inheritDoc} */
  public void setSubVector(int index, RealVector v) throws MatrixIndexException {
      checkIndex(index);
      checkIndex(index + v.getDimension() - 1);
      setSubVector(index, v.getData());
  }

  /** {@inheritDoc} */
  public void setSubVector(int index, double[] v) throws MatrixIndexException {
      checkIndex(index);
      checkIndex(index + v.length - 1);
      for (int i = 0; i < v.length; i++) {
          setEntry(i + index, v[i]);
      }
  }

  public RealVector add(double[] v) throws IllegalArgumentException
  {
    double[] result = v.clone();
    Iterator<Entry> it = sparseIterator();
    Entry e;
    while(it.hasNext() && (e = it.next()) != null)
    {
      result[e.index] += e.getValue();
    }
    return new ArrayRealVector(result, false);
  }

  public RealVector add(RealVector v) throws IllegalArgumentException
  {
    if(v instanceof ArrayRealVector)
    {
      double[] values = ((ArrayRealVector)v).getDataRef();
      return add(values);
    }
    RealVector result = v.copy();
    Iterator<Entry> it = sparseIterator();
    Entry e;
    while(it.hasNext() && (e = it.next()) != null)
    {
      result.setEntry(e.index, e.getValue() + result.getEntry(e.index));
    }
    return result;
  }

  public RealVector subtract(double[] v) throws IllegalArgumentException
  {
    double[] result = v.clone();
    Iterator<Entry> it = sparseIterator();
    Entry e;
    while(it.hasNext() && (e = it.next()) != null)
    {
      result[e.index] = e.getValue() - result[e.index];
    }
    return new ArrayRealVector(result, false);
  }

  public RealVector subtract(RealVector v) throws IllegalArgumentException
  {
    if(v instanceof ArrayRealVector)
    {
      double[] values = ((ArrayRealVector)v).getDataRef();
      return add(values);
    }
    RealVector result = v.copy();
    Iterator<Entry> it = sparseIterator();
    Entry e;
    while(it.hasNext() && (e = it.next()) != null)
    {
      v.setEntry(e.index, e.getValue() - result.getEntry(e.index));
    }
    return result;
  }

  public RealVector mapAdd(double d)
  {
    return copy().mapAddToSelf(d);
  }

  public RealVector mapAddToSelf(double d)
  {
    if(d != 0)
    {
      Iterator<Entry> it = iterator();
      Entry e;
      while(it.hasNext() && (e = it.next()) != null)
      {
        e.setValue(e.getValue() + d);
      }
    }
    return this;
  }

  public abstract AbstractRealVector copy();

  public double dotProduct(double[] v) throws IllegalArgumentException
  {
    return dotProduct(new ArrayRealVector(v, false));
  }

  public double dotProduct(RealVector v) throws IllegalArgumentException
  {
    checkVectorDimensions(v);
    double d = 0;
    Iterator<Entry> it = sparseIterator();
    Entry e;
    while(it.hasNext() && (e = it.next()) != null) {
      d += e.getValue() * v.getEntry(e.index);
    }
    return d;
  }

  public RealVector ebeDivide(double[] v) throws IllegalArgumentException
  {
    return ebeDivide(new ArrayRealVector(v, false));
  }

  public RealVector ebeMultiply(double[] v) throws IllegalArgumentException
  {
    return ebeMultiply(new ArrayRealVector(v, false));
  }

  public double getDistance(RealVector v) throws IllegalArgumentException
  {
    checkVectorDimensions(v);
    double d = 0;
    Iterator<Entry> it = sparseIterator();
    Entry e;
    while(it.hasNext() && (e = it.next()) != null) {
      final double diff = e.getValue() - v.getEntry(e.index);
      d += diff * diff;
    }
    return Math.sqrt(d);
  }

  public double getDistance(double[] v) throws IllegalArgumentException
  {
    checkVectorDimensions(v.length);
    double d = 0;
    Iterator<Entry> it = iterator();
    Entry e;
    while(it.hasNext() && (e = it.next()) != null) {
      final double diff = e.getValue() - v[e.index];
      d += diff * diff;
    }
    return Math.sqrt(d);
  }

  public double getL1Distance(RealVector v) throws IllegalArgumentException
  {
    checkVectorDimensions(v);
    double d = 0;
    Iterator<Entry> it = iterator();
    Entry e;
    while(it.hasNext() && (e = it.next()) != null) {
      d += Math.abs(e.getValue() - v.getEntry(e.index));
    }
    return d;
  }

  public double getL1Distance(double[] v) throws IllegalArgumentException
  {
    checkVectorDimensions(v.length);
    double d = 0;
    Iterator<Entry> it = iterator();
    Entry e;
    while(it.hasNext() && (e = it.next()) != null) {
      d += Math.abs(e.getValue() - v[e.index]);
    }
    return d;
  }

  public double getLInfDistance(RealVector v) throws IllegalArgumentException
  {
    checkVectorDimensions(v);
    double d = 0;
    Iterator<Entry> it = iterator();
    Entry e;
    while(it.hasNext() && (e = it.next()) != null) {
      d = Math.max(Math.abs(e.getValue() - v.getEntry(e.index)), d);
    }
    return d;
  }

  public double getLInfDistance(double[] v) throws IllegalArgumentException
  {
    checkVectorDimensions(v.length);
    double d = 0;
    Iterator<Entry> it = iterator();
    Entry e;
    while(it.hasNext() && (e = it.next()) != null) {
      d = Math.max(Math.abs(e.getValue() - v[e.index]), d);
    }
    return d;
  }
  
  public RealVector mapAbs()
  {
    return copy().mapAbsToSelf();
  }

  public RealVector mapAbsToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Abs);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapAcos()
  {
    return copy().mapAcosToSelf();
  }

  public RealVector mapAcosToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Acos);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapAsin()
  {
    return copy().mapAsinToSelf();
  }

  public RealVector mapAsinToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Asin);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapAtan()
  {
    return copy().mapAtanToSelf();
  }

  public RealVector mapAtanToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Atan);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapCbrt()
  {
    return copy().mapCbrtToSelf();
  }

  public RealVector mapCbrtToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Cbrt);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapCeil()
  {
    return copy().mapCeilToSelf();
  }

  public RealVector mapCeilToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Ceil);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapCos()
  {
    return copy().mapCosToSelf();
  }

  public RealVector mapCosToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Cos);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapCosh()
  {
    return copy().mapCoshToSelf();
  }

  public RealVector mapCoshToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Cosh);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapDivide(double d)
  {
    return copy().mapDivideToSelf(d);
  }

  public RealVector mapExp()
  {
    return copy().mapExpToSelf();
  }

  public RealVector mapExpToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Exp);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapExpm1()
  {
    return copy().mapExpm1ToSelf();
  }

  public RealVector mapExpm1ToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Exp1m);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapFloor()
  {
    return copy().mapFloorToSelf();
  }

  public RealVector mapFloorToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Floor);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapInv()
  {
    return copy().mapInvToSelf();
  }

  public RealVector mapInvToSelf()
  {
    try
    {
      return mapToSelf(new UnivariateRealFunction()
      {
        public double value(double x) throws FunctionEvaluationException
        {
          return 1/x;
        }
      });
    }
    catch (FunctionEvaluationException e)
    {
      // cannot happen from Math.xxx methods.
      throw new RuntimeException(e);
    }
  }

  public RealVector mapLog()
  {
    return copy().mapLogToSelf();
  }
  
  public RealVector mapLogToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Log);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapLog10()
  {
    return copy().mapLog10ToSelf();
  }

  public RealVector mapLog10ToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Log10);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapLog1p()
  {
    return copy().mapLog1pToSelf();
  }

  public RealVector mapLog1pToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Asin);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapMultiply(double d)
  {
    return copy().mapMultiplyToSelf(d);
  }

  public RealVector mapMultiplyToSelf(final double d)
  {
    try
    {
      return mapToSelf(new UnivariateRealFunction()
      {
        public double value(double x) throws FunctionEvaluationException
        {
          return x * d;
        }
      });
    }
    catch (FunctionEvaluationException e)
    {
      // cannot happen from Math.xxx methods.
      throw new RuntimeException(e);
    }
  }

  public RealVector mapPow(double d)
  {
    return copy().mapPowToSelf(d);
  }

  public RealVector mapPowToSelf(final double d)
  {
    try
    {
      return mapToSelf(new UnivariateRealFunction()
      {
        public double value(double x) throws FunctionEvaluationException
        {
          return Math.pow(x, d);
        }
      });
    }
    catch (FunctionEvaluationException e)
    {
      // cannot happen from Math.xxx methods.
      throw new RuntimeException(e);
    }
  }

  public RealVector mapRint()
  {
    return copy().mapRintToSelf();
  }

  public RealVector mapRintToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Rint);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapSignum()
  {
    return copy().mapSignumToSelf();
  }

  public RealVector mapSignumToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Signum);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapSin()
  {
    return copy().mapSinToSelf();
  }

  public RealVector mapSinToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Sin);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapSinh()
  {
    return copy().mapSinhToSelf();
  }

  public RealVector mapSinhToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Sinh);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapSqrt()
  {
    return copy().mapSqrtToSelf();
  }

  public RealVector mapSqrtToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Sqrt);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapSubtract(double d)
  {
    return copy().mapSubtractToSelf(d);
  }

  public RealVector mapSubtractToSelf(final double d)
  {
    try
    {
      return mapToSelf(new UnivariateRealFunction()
      {
        public double value(double x) throws FunctionEvaluationException
        {
          return x - d;
        }
      });
    }
    catch (FunctionEvaluationException e)
    {
      // cannot happen from Math.xxx methods.
      throw new RuntimeException(e);
    }
  }

  public RealVector mapTan()
  {
    return copy().mapTanToSelf();
  }

  public RealVector mapTanToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Tan);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapTanh()
  {
    return copy().mapTanhToSelf();
  }

  public RealVector mapTanhToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Tanh);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public RealVector mapUlp()
  {
    return copy().mapUlpToSelf();
  }

  public RealVector mapUlpToSelf()
  {
    try
    {
      return mapToSelf(UnivariateRealFunctions.Ulp);
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }
  
  public RealMatrix outerProduct(RealVector v) throws IllegalArgumentException
  {
    RealMatrix product;
    if(v instanceof SparseRealVector || this instanceof SparseRealVector)
    {
      product = new OpenMapRealMatrix(this.getDimension(), v.getDimension());
    }
    else
    {
      product = new Array2DRowRealMatrix(this.getDimension(), v.getDimension());
    }
    Iterator<Entry> thisIt = sparseIterator();
    Entry thisE = null;
    while(thisIt.hasNext() && (thisE = thisIt.next()) != null)
    {
      Iterator<Entry> otherIt = v.sparseIterator();
      Entry otherE = null;
      while(otherIt.hasNext() && (otherE = otherIt.next()) != null)
      {
        product.setEntry(thisE.index, otherE.index, thisE.getValue() * otherE.getValue());
      }
    }
    
    return product;
  }

  public RealMatrix outerProduct(double[] v) throws IllegalArgumentException
  {
    return outerProduct(new ArrayRealVector(v, false));
  }

  public RealVector projection(double[] v) throws IllegalArgumentException
  {
    return projection(new ArrayRealVector(v, false));
  }

  public void set(double value)
  {
    Iterator<Entry> it = iterator();
    Entry e = null;
    while(it.hasNext() && (e = it.next()) != null)
    {
      e.setValue(value);
    }
  }

  public double[] toArray()
  {
    int dim = getDimension();
    double[] values = new double[dim];
    for(int i=0; i<dim; i++)
    {
      values[i] = getEntry(i);
    }
    return values;
  }
  
  public double[] getData()
  {
    return toArray();
  }

  public RealVector unitVector()
  {
    RealVector copy = copy();
    copy.unitize();
    return copy;
  }

  public void unitize()
  {
    mapDivideToSelf(getNorm());
  }

  public Iterator<Entry> sparseIterator()
  {
    return new SparseEntryIterator();
  }

  public Iterator<Entry> iterator()
  {
    final int dim = getDimension();
    return new Iterator<Entry>()
    {
      int i = 0;
      EntryImpl e = new EntryImpl();      
      public boolean hasNext() { return i < dim; }
      public Entry next() 
      {
        e.index = i++;
        return e;
      }
      public void remove()
      {
        throw new UnsupportedOperationException("Not supported");
      }
    };
  }
  
  public RealVector map(UnivariateRealFunction function) throws FunctionEvaluationException
  {
    return copy().mapToSelf(function);
  }

  public RealVector mapToSelf(UnivariateRealFunction function) throws FunctionEvaluationException
  {
    Iterator<Entry> it = function.value(0) == 0
                       ? sparseIterator()
                       : iterator();
    Entry e;
    while(it.hasNext() && (e = it.next()) != null)
    {
      e.setValue(function.value(e.getValue()));
    }
    return this;
  }

  protected class EntryImpl extends Entry
  {
    public EntryImpl() { index = 0; }
    public double getValue() { return getEntry(index); }
    public void setValue(double newValue) { setEntry(index, newValue); }
  }

    /**
     * This class should rare be used, but is here to provide
     * a default implementation of sparseIterator(), which is implemented
     * by walking over the entries, skipping those whose values are the default one.
     *
     * Concrete subclasses which are SparseVector implementations should
     * make their own sparse iterator, not use this one.
     *
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
      if(current.getValue() == 0) advance(current);
      next = new EntryImpl();
      next.index = current.index;
      advance(next);
    }

    protected void advance(EntryImpl e) {
      if(e == null) return;
      do { e.index++; } while(e.index < dim && e.getValue() == 0);
      if(e.index >= dim) e.index = -1;
    }

    public boolean hasNext() {
        return current != null;
    }

    public Entry next() {
        tmp.index = current.index;
        if(next != null) {
            current.index = next.index;
            advance(next);
            if(next.index < 0) next = null;
        } else { current = null; }
        return tmp;
    }
        
    public void remove() {
      throw new UnsupportedOperationException("Not supported");
    }
  }

}
