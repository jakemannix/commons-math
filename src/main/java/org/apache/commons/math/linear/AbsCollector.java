package org.apache.commons.math.linear;

public class AbsCollector implements NonDefaultCollector, UnaryCollector
{
  protected double result = 0;
  
  public double result()
  {
    return result;
  }

  public void collect(int index, double d)
  {
    result += Math.abs(d);
  }

}
