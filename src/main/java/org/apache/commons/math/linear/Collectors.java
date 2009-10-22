package org.apache.commons.math.linear;

public class Collectors
{
  public static class Dot extends AbstractBinaryCollector
  {
    public void collect(double d1, double d2)
    {
      result += d1 * d2;
    }
  }
  
  public static final BinaryCollector Dot = new Dot();
}
