package org.apache.commons.math.linear;

import java.util.Iterator;

import org.apache.commons.math.MathRuntimeException;
import static org.apache.commons.math.linear.Functions.*;

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
  
  public RealVector add(RealVector v) throws IllegalArgumentException
  {
    return map(Add, v);
  }

  public RealVector add(double[] v) throws IllegalArgumentException
  {
    return add(new ArrayRealVector(v, false));
  }

  public abstract AbstractRealVector copy();

  public double dotProduct(RealVector v) throws IllegalArgumentException
  {
    return collect(new EuclideanInnerProduct(), v);
  }

  public double dotProduct(double[] v) throws IllegalArgumentException
  {
    return dotProduct(new ArrayRealVector(v, false));
  }

  public RealVector ebeDivide(RealVector v) throws IllegalArgumentException
  {
    return mapToSelf(Divide, (AbstractRealVector) v);
  }

  public RealVector ebeDivide(double[] v) throws IllegalArgumentException
  {
    return ebeDivide(new ArrayRealVector(v, false));
  }

  public RealVector ebeMultiply(RealVector v) throws IllegalArgumentException
  {
    return mapToSelf(Multiply, v);
  }

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

  public double getL1Norm()
  {
    return collect(new AbsCollector());
  }

  public double getLInfDistance(RealVector v) throws IllegalArgumentException
  {
    return subtract(v).getLInfNorm();
  }

  public double getLInfDistance(double[] v) throws IllegalArgumentException
  {
    return getLInfDistance(new ArrayRealVector(v, false));
  }

  public double getLInfNorm()
  {
    return collect(new LInfCollector());
  }

  public double getNorm()
  {
    return Math.sqrt(dotProduct(this));
  }

/**************/
  
  public RealVector mapAbs()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapAbsToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapAcos()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapAcosToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapAdd(double d)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapAddToSelf(double d)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapAsin()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapAsinToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapAtan()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapAtanToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapCbrt()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapCbrtToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapCeil()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapCeilToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapCos()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapCosToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapCosh()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapCoshToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapDivide(double d)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapDivideToSelf(double d)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapExp()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapExpToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapExpm1()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapExpm1ToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapFloor()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapFloorToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapInv()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapInvToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapLog()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapLog10()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapLog10ToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapLog1p()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapLog1pToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapLogToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapMultiply(double d)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapMultiplyToSelf(double d)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapPow(double d)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapPowToSelf(double d)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapRint()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapRintToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapSignum()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapSignumToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapSin()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapSinToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapSinh()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapSinhToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapSqrt()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapSqrtToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapSubtract(double d)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapSubtractToSelf(double d)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapTan()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapTanToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapTanh()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapTanhToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapUlp()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
  }

  public RealVector mapUlpToSelf()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet");
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
        product.setEntry(thisE.index(), otherE.index(), thisE.getValue() * otherE.getValue());
      }
    }
    
    return product;
  }

  public RealMatrix outerProduct(double[] v) throws IllegalArgumentException
  {
    return outerProduct(new ArrayRealVector(v, false));
  }

  public RealVector projection(RealVector v) throws IllegalArgumentException
  {
    final double proj = dotProduct(v);
    return v.map(Multiply.provideDefaultSecondArgument(proj));
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
  
  public RealVector subtract(RealVector v) throws IllegalArgumentException
  {
    return map(Subtract, v);
  }

  public RealVector subtract(double[] v) throws IllegalArgumentException
  {
    return subtract(new ArrayRealVector(v, false));
  }

  public abstract double[] toArray();

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

  public double collect(UnaryCollector collector)
  {
    Iterator<Entry> it = collector instanceof NonDefaultCollector
                       ? nonDefaultIterator()
                       : iterator();
    Entry e = null;
    while(it.hasNext() && (e = it.next()) != null)
    {
      collector.collect(e.index(), e.getValue());
    }
    return collector.result();
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
    return collector.result();
  }

  public Iterator<Entry> nonDefaultIterator()
  {
    return iterator();
  }
  
  public RealVector map(UnaryFunction function)
  {
    return copy().mapToSelf(function);
  }

  public RealVector map(BinaryFunction function, RealVector other)
  {
    return copy().mapToSelf(function, other);
  }

  public RealVector mapToSelf(UnaryFunction function)
  {
    Iterator<Entry> it = function instanceof DefaultPreservingUnaryFunction
                       ? nonDefaultIterator()
                       : iterator();
    Entry e = null;
    while(it.hasNext() && (e = it.next()) != null)
    {
      e.setValue(function.apply(e.getValue()));
    }
    return this;
  }

  /**
   * TODO: optimize w.r.t SparseVector
   * @param function
   * @param other
   * @return
   */
  public RealVector mapToSelf(BinaryFunction function, RealVector other)
  {
    Iterator<Entry> it = function instanceof DefaultPreservingBinaryFunction 
                       ? other.nonDefaultIterator()
                       : other.iterator();
    Entry e = null;
    while(it.hasNext() && (e = it.next()) != null)
    {
      setEntry(e.index(), function.apply(getEntry(e.index()), e.getValue()));
    }
    return this;
  }


  
}
