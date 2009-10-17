package org.apache.commons.math.linear;

public abstract class AbstractBinaryCollector extends AbstractCollector implements BinaryCollector
{

  public AbstractBinaryCollector()
  {
    super();
  }
  
  public AbstractBinaryCollector(double initialValue)
  {
    super(initialValue);
  }
  
  public void collect(int index, double d1, double d2)
  {
    collect(d1, d2);
  }

}
