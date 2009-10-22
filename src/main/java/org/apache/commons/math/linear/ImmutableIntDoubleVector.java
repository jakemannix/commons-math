package org.apache.commons.math.linear;

public class ImmutableIntDoubleVector extends IntDoubleVector
{
  protected double[] asArray = null;
  
  public ImmutableIntDoubleVector(RealVector v)
  {
    super(v);
  }

  @Override
  public AbstractRealVector copy()
  {
    return new IntDoubleVector(this);
  }

  @Override
  public double[] toArray()
  {
    if(asArray == null)
    {
      asArray = super.toArray();
    }
    return asArray;
  }

  public RealVector append(RealVector v)
  {
    throw new UnsupportedOperationException("Class is immutable");
  }

  public RealVector append(double d)
  {
    throw new UnsupportedOperationException("Class is immutable");
  }

  public RealVector append(double[] a)
  {
    throw new UnsupportedOperationException("Class is immutable");
  }

  public void setEntry(int index, double value) throws MatrixIndexException
  {
    throw new UnsupportedOperationException("Class is immutable");
  }

  public void setSubVector(int index, RealVector v) throws MatrixIndexException
  {
    throw new UnsupportedOperationException("Class is immutable");
  }

  public void setSubVector(int index, double[] v) throws MatrixIndexException
  {
    throw new UnsupportedOperationException("Class is immutable");
  }

}
