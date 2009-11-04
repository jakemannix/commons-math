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
      Iterator<Entry> it = vector.sparseIterator();
      Entry e;
      while(it.hasNext() && (e = it.next()) != null)
      {
        if(currentSize >= indices.length)
          expand();
        indices[currentSize] = e.index();
        values[currentSize] = e.getValue();
        currentSize++;
      }
    }
  }

  public IntDoubleVector(int[] indices, double[] values, boolean copy)
  {
    dimension = indices.length;
    this.indices = copy ? indices.clone() : indices;
    this.values = copy ? values.clone() : values;
    currentSize = indices.length;
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

  @Override
  public double dotProduct(RealVector v)
  {
    if(v instanceof IntDoubleVector)
    {
      return dotProduct((IntDoubleVector)v);
    }
    return super.dotProduct(v);
  }

  public double dotProduct(IntDoubleVector v)
  {
    double result = 0;
    int i = 0;
    int j = 0;
    final int[] ind = indices;
    final int[] vInd = v.indices;
    final double[] val = values;
    final double[] vVal = v.values;
    final int sz = currentSize;
    final int vSz = v.currentSize;
    do
    {
      while(ind[i] < vInd[j] && i < sz) i++;
      while(vInd[j] < ind[i] && j < vSz) j++;
      if(ind[i] == vInd[j])
      {
        result += val[i] * vVal[j];
        i++;
        j++;
      }
    } while(i < sz && j < vSz);
    return result;
  }

  // TODO
  public double getL1Norm()
  {
     return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  // TODO
  public double getLInfNorm()
  {
     return 0;  //To change body of implemented methods use File | Settings | File Templates.
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
    int thisStart = Arrays.binarySearch(indices, index);
    if(thisStart < 0) thisStart = -(thisStart + 1);
    int thisEnd = Arrays.binarySearch(indices, end);
    if(thisEnd < 0) thisEnd = -(thisEnd + 1);
    // TODO: fix start and end points!
    int[] resInd = new int[thisEnd - thisStart];
    double[] resVal = new double[resInd.length];
    System.arraycopy(indices, thisStart, resInd, 0, resInd.length);
    System.arraycopy(values, thisStart, resVal, 0, resVal.length);
    return new IntDoubleVector(resInd, resVal, false);
  }

  @Override
  public Iterator<Entry> sparseIterator()
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
      try
      {
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

  protected class SparseEntryImpl extends EntryImpl
  {
    public int index() { return indices[index]; }
    public double getValue() { return values[indices[index]]; }
    public void setValue(double newValue) { values[indices[index]] = newValue; }
  }
  
}
