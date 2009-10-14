package org.apache.commons.math.linear;

public interface UnaryCollector
{
  void collect(int index, double value);
  
  double result();
}
