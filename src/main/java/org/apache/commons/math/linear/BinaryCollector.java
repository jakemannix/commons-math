package org.apache.commons.math.linear;

public interface BinaryCollector extends Collector
{
  void collect(int index, double d1, double d2);
}