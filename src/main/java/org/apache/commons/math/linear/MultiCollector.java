package org.apache.commons.math.linear;


public abstract class MultiCollector implements Collector
{
  protected double result = 0;

  public double collectedValue() {
    return result;
  }

  abstract void collect(double... d);

  void collect(int i, double... d) {
    collect(d);
  }
}
