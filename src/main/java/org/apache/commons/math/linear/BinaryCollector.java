package org.apache.commons.math.linear;

public interface BinaryCollector extends Collector
{
  public static final BinaryCollector DOT = BinaryFunction.Multiply.asCollector();
  
  void collect(int index, double d1, double d2);
  
  void collect(double d1, double d2);
}
