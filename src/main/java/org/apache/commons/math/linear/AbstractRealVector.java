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
  protected void checkVectorDimensions(RealVector v)
      throws IllegalArgumentException {
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
  /*
  public RealVector add(RealVector v) throws IllegalArgumentException
  {
    return map(BinaryRealFunction.Add, v);
  }
*/
  public RealVector add(double[] v) throws IllegalArgumentException
  {
    return add(new ArrayRealVector(v, false));
  }

  public abstract AbstractRealVector copy();
/*
  public double dotProduct(RealVector v) throws IllegalArgumentException
  {
    return collect(Collectors.Dot, v);
  }
*/
  public double dotProduct(double[] v) throws IllegalArgumentException
  {
    return dotProduct(new ArrayRealVector(v, false));
  }
/*
  public RealVector ebeDivide(RealVector v) throws IllegalArgumentException
  {
    return mapToSelf(BinaryRealFunction.Divide, v);
  }
*/
  public RealVector ebeDivide(double[] v) throws IllegalArgumentException
  {
    return ebeDivide(new ArrayRealVector(v, false));
  }
/*
  public RealVector ebeMultiply(RealVector v) throws IllegalArgumentException
  {
    return mapToSelf(BinaryRealFunction.Multiply, v);
  }
  */

  public RealVector ebeMultiply(double[] v) throws IllegalArgumentException
  {
    return ebeMultiply(new ArrayRealVector(v, false));
  }

  public double getDistance(RealVector v) throws IllegalArgumentException
  {
    return subtract(v).getNorm();
  }

  public double getDistance(double[] v) throws IllegalArgumentException
  {
    return getDistance(new ArrayRealVector(v, false));
  }

  public double getL1Distance(RealVector v) throws IllegalArgumentException
  {
    return subtract(v).getL1Norm();
  }

  public double getL1Distance(double[] v) throws IllegalArgumentException
  {
    return getL1Distance(new ArrayRealVector(v, false));
  }
/*
  public double getL1Norm()
  {
    try
    {
      return collect(new AbstractUnaryCollector()
      {
        public void collect(double d)
        {
          value += Math.abs(d);
        }
      });
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }
*/
  public double getLInfDistance(RealVector v) throws IllegalArgumentException
  {
    return subtract(v).getLInfNorm();
  }

  public double getLInfDistance(double[] v) throws IllegalArgumentException
  {
    return getLInfDistance(new ArrayRealVector(v, false));
  }
/*
  public double getLInfNorm()
  {
    try
    {
      return collect(new AbstractUnaryCollector()
      {
        public void collect(double d)
        {
          value = Math.max(value, d);
        }
        
      });
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  public double getNorm()
  {
    return Math.sqrt(dotProduct(this));
  }

**************/
  
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

  public RealVector mapAdd(double d)
  {
    return copy().mapAddToSelf(d);
  }
/*
  public RealVector mapAddToSelf(double d)
  {
    try
    {
      return mapToSelf(BinaryRealFunction.Add.provideDefaultSecondArgument(d));
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }
*/
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
/*
  public RealVector mapDivideToSelf(double d)
  {
    try
    {
      return mapToSelf(BinaryRealFunction.Divide.provideDefaultSecondArgument(d));
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }
*/
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
    return copy().mapFloorToSelf();
  }
/*
  public RealVector mapInvToSelf()
  {
    try
    {
      return mapToSelf(BinaryRealFunction.Divide.provideDefaultFirstArgument(1d));
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }*/

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
/*
  public RealVector mapMultiplyToSelf(double d)
  {
    try
    {
      return mapToSelf(BinaryRealFunction.Multiply.provideDefaultSecondArgument(d));
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException();
    }
  }*/

  public RealVector mapPow(double d)
  {
    return copy().mapPowToSelf(d);
  }
/*
  public RealVector mapPowToSelf(double d)
  {
    try
    {
      return mapToSelf(BinaryRealFunction.Pow.provideDefaultSecondArgument(d));
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }*/

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
/*
  public RealVector mapSubtractToSelf(double d)
  {
    try
    {
      return mapToSelf(BinaryRealFunction.Subtract.provideDefaultSecondArgument(d));
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }*/

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
/*****************************/
  
  
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
    Iterator<Entry> thisIt = nonDefaultIterator();
    Entry thisE = null;
    while(thisIt.hasNext() && (thisE = thisIt.next()) != null)
    {
      Iterator<Entry> otherIt = v.nonDefaultIterator();
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
/*
  public RealVector projection(RealVector v) throws IllegalArgumentException
  {
    final double proj = dotProduct(v);
    try
    {
      return v.map(BinaryRealFunction.Multiply.provideDefaultSecondArgument(proj));
    }
    catch (FunctionEvaluationException e)
    {
      throw new IllegalArgumentException(e);
    }
  }*/

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
  /*
  public RealVector subtract(RealVector v) throws IllegalArgumentException
  {
    return map(BinaryRealFunction.Subtract, v);
  }
*/
  public RealVector subtract(double[] v) throws IllegalArgumentException
  {
    return subtract(new ArrayRealVector(v, false));
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
/*
  public double collect(UnaryCollector collector) throws FunctionEvaluationException
  {
    Iterator<Entry> it = collector instanceof NonDefaultCollector
                       ? nonDefaultIterator()
                       : iterator();
    Entry e = null;
    while(it.hasNext() && (e = it.next()) != null)
    {
      collector.collect(e.index(), e.getValue());
    }
    return collector.collectedValue();
  }

  public double collect(BinaryCollector collector, RealVector other)
  {
    Iterator<Entry> it = collector instanceof NonDefaultCollector
                       ? nonDefaultIterator()
                       : iterator();
    Entry e = null;
    while(it.hasNext() && (e = it.next()) != null)
    {
      collector.collect(e.index(), e.getValue(), other.getEntry(e.index()));
    }
    return collector.collectedValue();
  }*/

  public Iterator<Entry> nonDefaultIterator()
  {
    return iterator();
  }

  public Iterator<Entry> iterator()
  {
    final int dim = getDimension();
    return new Iterator<Entry>()
    {
      int i=0;
      EntryImpl e = new EntryImpl();      
      public boolean hasNext() { return i<dim; }
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
/*
  public RealVector map(BinaryRealFunction function, RealVector other)
  {
    return copy().mapToSelf(function, other);
  }
*/
  public RealVector mapToSelf(UnivariateRealFunction function) throws FunctionEvaluationException
  {
    Iterator<Entry> it = getDefaultValue() == function.value(getDefaultValue())
                       ? nonDefaultIterator()
                       : iterator();
    Entry e = null;
    while(it.hasNext() && (e = it.next()) != null)
    {
      e.setValue(function.value(e.getValue()));
    }
    return this;
  }
/*
  public RealVector mapToSelf(BinaryRealFunction function, RealVector other)
  {
    Iterator<Entry> it = function instanceof DefaultPreservingBinaryRealFunction 
                       ? other.nonDefaultIterator()
                       : other.iterator();
    Entry e = null;
    while(it.hasNext() && (e = it.next()) != null)
    {
      setEntry(e.index(), function.value(getEntry(e.index()), e.getValue()));
    }
    return this;
  }*/

  protected class EntryImpl extends Entry
  {
    public double getValue() { return getEntry(index); }
    public void setValue(double newValue) { setEntry(index, newValue); }    
  }
  
}
