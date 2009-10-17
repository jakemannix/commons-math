package org.apache.commons.math.linear;

public interface UnaryCollector extends Collector
{
  static class Inf extends AbstractUnaryCollector
  {
    public Inf()
    {
      super(Double.POSITIVE_INFINITY);
    }
    
    public void collect(double d)
    {
      result = Math.abs(d)  <  Math.abs(result) ? Math.abs(d) : result;
    }
  }
  
  public static UnaryCollector Inf = new Inf();
  
  void collect(int index, double d);
  
  void collect(double d);
}
