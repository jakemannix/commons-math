package org.apache.commons.math.linear;

public class AbstractCollector implements Collector
{
  protected double result;

  public AbstractCollector()
  {
    this(0);
  }
  
  public AbstractCollector(double initialValue)
  {
    result = initialValue;
  }
  
  public double result()
  {
    return result;
  }

}
