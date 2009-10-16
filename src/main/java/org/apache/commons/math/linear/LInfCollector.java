package org.apache.commons.math.linear;

public class LInfCollector implements UnaryCollector, NonDefaultCollector
{
  protected double inf = Double.POSITIVE_INFINITY;
  
  public void collect(int index, double d)
  {
    if(d < inf) inf = d;
  }

  public double result()
  {
    return inf;
  }

}
