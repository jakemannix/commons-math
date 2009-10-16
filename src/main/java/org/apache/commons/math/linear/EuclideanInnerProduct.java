package org.apache.commons.math.linear;

public class EuclideanInnerProduct implements NonDefaultCollector, BinaryCollector
{
  protected double value = 0;
  
  public double result()
  {
    return value;
  }

  public void collect(int index, double d1, double d2)
  {
    value += d1 * d2;
  }

}
