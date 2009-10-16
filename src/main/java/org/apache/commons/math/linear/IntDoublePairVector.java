package org.apache.commons.math.linear;

import java.util.Iterator;

public class IntDoublePairVector implements RealVector
{
  protected int dimension;
  protected int[] indices;
  protected double[] values;
  
  public RealVector add(RealVector v) throws IllegalArgumentException
  {
    if(v.getDimension() != dimension) 
    {
      throw new IllegalArgumentException("Incompatible sizes: " + v.getDimension() + " != " + dimension);
    }
    for(Entry e : v)
    {
      //setEntry(e.index(), )
    }
    return this;
  }

  public RealVector append(RealVector v)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public RealVector append(double d)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public RealVector append(double[] a)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public double collect(UnaryCollector coll)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public double collect(BinaryCollector coll, RealVector v)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public RealVector copy()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public double dotProduct(RealVector v) throws IllegalArgumentException
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public double[] getData()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public int getDimension()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public double getDistance(RealVector v) throws IllegalArgumentException
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public double getEntry(int index) throws MatrixIndexException
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public double getL1Distance(RealVector v) throws IllegalArgumentException
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public double getL1Norm()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public double getLInfDistance(RealVector v) throws IllegalArgumentException
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public double getLInfNorm()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public double getNorm()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public RealVector getSubVector(int index, int n) throws MatrixIndexException
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public boolean isInfinite()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public boolean isNaN()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public Iterator<Entry> iterateNonZero()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public RealVector map(UnaryFunction func)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public RealVector map(BinaryFunction func, RealVector v)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public RealVector mapToSelf(UnaryFunction func)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public RealVector mapToSelf(BinaryFunction func, RealVector v)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public RealMatrix outerProduct(RealVector v) throws IllegalArgumentException
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public RealVector projection(RealVector v) throws IllegalArgumentException
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public void set(double value)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public void setEntry(int index, double value) throws MatrixIndexException
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public void setSubVector(int index, RealVector v) throws MatrixIndexException
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public void setSubVector(int index, double[] v) throws MatrixIndexException
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public RealVector subtract(RealVector v) throws IllegalArgumentException
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public double[] toArray()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public RealVector unitVector()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public void unitize()
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not yet supported");
  }

  public Iterator<Entry> iterator()
  {
    return new Iterator<Entry>()
    {
      int i=0;
      public boolean hasNext()
      {
        return i<indices.length;
      }

      public Entry next()
      {
        final int index = i;
        Entry e = new Entry()
        {
          public double getValue()
          {
            return values[index];
          }

          public int index()
          {
            return indices[index];
          }

          public void setValue(double newValue)
          {
            values[index] = newValue;
          }
        };
        i++;
        return e;
      }

      public void remove()
      {
        throw new UnsupportedOperationException();
      }
      
    };
  }

}
