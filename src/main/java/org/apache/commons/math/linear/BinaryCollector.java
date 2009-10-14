package org.apache.commons.math.linear;

public interface BinaryCollector
{
  void collect(int index, double d1, double d2);
  
  double result();
}
