package org.apache.commons.math.linear;

import java.util.Arrays;
import java.util.Iterator;

public class IntDoubleVector extends AbstractRealVector implements SparseRealVector
{
  protected int dimension;
  protected int[] indices;
  protected double[] values;
  
  protected int currentSize;

  public IntDoubleVector()
  {
    this(0);
  }
  
  public IntDoubleVector(int dimension)
  {
    this(new ArrayRealVector(dimension));
  }
  
  public IntDoubleVector(double[] v)
  {
    this(new ArrayRealVector(v));
  }
  
  public IntDoubleVector(RealVector vector)
  {
    this(vector, 0);
  }
  
  public IntDoubleVector(RealVector vector, int resize)
  {
    dimension = vector.getDimension() + resize;
    if(vector instanceof IntDoubleVector)
    {
      IntDoubleVector idv = (IntDoubleVector)vector;
      indices = idv.indices.clone();
      values = idv.values.clone();
      currentSize = idv.currentSize;
    }
    else
    {
      currentSize =  0;
      indices = new int[10];
      Arrays.fill(indices, Integer.MAX_VALUE);
      values = new double[10];
      Iterator<Entry> it = vector.nonDefaultIterator();
      Entry e = null;
      while(it.hasNext() && (e = it.next()) != null)
      {
        if(currentSize >= indices.length)
          expand();
        indices[currentSize] = e.index;
        values[currentSize] = e.getValue();
        currentSize++;
      }
    }
  }
  
  protected void expand()
  {
    int[] newIndices = new int[indices.length * 2];
    double[] newValues = new double[newIndices.length];
    Arrays.fill(newIndices, Integer.MAX_VALUE);
    System.arraycopy(indices, 0, newIndices, 0, indices.length);
    System.arraycopy(values, 0, newValues, 0, values.length);
  }

  @Override
  public AbstractRealVector copy()
  {
    return new IntDoubleVector(this);
  }

  public RealVector add(RealVector v) throws IllegalArgumentException
  {
    RealVector result = v.copy();
    for(int i=0; i<indices.length; i++)
    {
      result.setEntry(indices[i], values[i]);
    }
    return result;
  }

  public RealVector subtract(RealVector v) throws IllegalArgumentException
  {
    return null;
  }
    public RealVector mapAddToSelf(double d) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector mapSubtractToSelf(double d) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector mapMultiplyToSelf(double d) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector mapDivideToSelf(double d) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector mapPowToSelf(double d) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector mapInvToSelf() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector ebeMultiply(RealVector v) throws IllegalArgumentException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector ebeDivide(RealVector v) throws IllegalArgumentException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public double dotProduct(RealVector v) throws IllegalArgumentException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public double getNorm() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public double getL1Norm() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public double getLInfNorm() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public RealVector projection(RealVector v) throws IllegalArgumentException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

  public RealVector append(RealVector v)
  {
    return append(v.getData());
  }

  public RealVector append(double d)
  {
    IntDoubleVector v = new IntDoubleVector(this, 1);
    v.indices[currentSize] = dimension;
    v.values[currentSize] = d;
    return v;
  }

  public RealVector append(double[] a)
  {
    IntDoubleVector v = new IntDoubleVector(this, a.length);
    v.setSubVector(dimension, a);
    return v;
  }

  public int getDimension()
  {
    return dimension;
  }

  public double getEntry(int index) throws MatrixIndexException
  {
    int i = Arrays.binarySearch(indices, index);
    return i < 0 ? 0d : values[i]; 
  }

  public RealVector getSubVector(int index, int n) throws MatrixIndexException
  {
    checkIndex(index);
    checkIndex(index + n);
    int end = index + n;
    IntDoubleVector v = new IntDoubleVector(n);
    Iterator<Entry> it = nonDefaultIterator();
    Entry e = null;
    while(it.hasNext() && (e = it.next()) != null)
    {
      if(e.index < index) continue;
      if(e.index >= end) break;
      v.setEntry(e.index, e.getValue());
    }
    return v;
  }

  public boolean isInfinite()
  {
    if(isNaN()) return false;
    Iterator<Entry> it = nonDefaultIterator();
    Entry e = null;
    while(it.hasNext() && (e = it.next()) != null)
    {
      if(e.getValue() == Double.POSITIVE_INFINITY || e.getValue() == Double.POSITIVE_INFINITY) 
        return true;
    }
    return false;
  }

  public boolean isNaN()
  {
    Iterator<Entry> it = nonDefaultIterator();
    Entry e = null;
    while(it.hasNext() && (e = it.next()) != null)
    {
      if(e.getValue() == Double.NaN) 
        return true;
    }
    return false;
    
  }

  public Iterator<Entry> nonDefaultIterator()
  {
    return new Iterator<Entry>()
    {
      int i = 0;
      SparseEntryImpl e = new SparseEntryImpl();
      public boolean hasNext() { return i < currentSize; }
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

  public void setEntry(int index, double value) throws MatrixIndexException
  {
    int i = Arrays.binarySearch(indices, index);
    if(i >= 0)
    {
      values[i] = value;
    }
    else
    {
      i = -(i+1);
      if(currentSize >= indices.length)
        expand();
      try {
      System.arraycopy(indices, i, indices, i+1, currentSize - i);
      System.arraycopy(values, i, values, i+1, currentSize - i);
      } catch (Exception e) { 
        StringBuffer buf = new StringBuffer("i: ").append(i).append(" [");
        for(int j : indices) buf.append(j).append(", ");
        buf.append("]");
        throw new RuntimeException(buf.toString());
      }
      indices[i] = index;
      values[i] = value;
      currentSize++;
    }
  }

  protected class SparseEntryImpl extends Entry
  {
    int index = 0;
    public double getValue() { return values[indices[index]]; }
    public int index() { return indices[index]; }
    public void setValue(double newValue) { values[indices[index]] = newValue; }    
  }
  
}
