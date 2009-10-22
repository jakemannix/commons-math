package org.apache.commons.math.linear;


public abstract class AbstractBinaryCollector implements BinaryCollector
{
  protected double result;

  public AbstractBinaryCollector()
  {
    this(0);
  }
  
  public AbstractBinaryCollector(double initialValue)
  {
    result = initialValue;
  }
  
  public double value(RealVector v1, RealVector v2)
  {
    for(int i=0; i<v1.getDimension(); i++)
      collect(i, v1.getEntry(i), v2.getEntry(i));
    return collectedValue();
  }
  
  public void collect(int index, double d1, double d2)
  {
    collect(d1, d2);
  }
  
  public double collectedValue()
  {
    return result;
  }

}
