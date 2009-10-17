package org.apache.commons.math.linear;

public abstract class AbstractUnaryCollector extends AbstractCollector implements UnaryCollector
{
  public AbstractUnaryCollector()
  {
    super();
  }
  
  public AbstractUnaryCollector(double initialValue)
  {
    super(initialValue);
  }
  
  public void collect(int index, double d)
  {
    collect(d);
  }

}
