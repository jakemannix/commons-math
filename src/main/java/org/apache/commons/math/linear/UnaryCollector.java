package org.apache.commons.math.linear;

import org.apache.commons.math.analysis.MultivariateRealFunction;

public interface UnaryCollector extends Collector, MultivariateRealFunction
{
  void collect(int index, double d);
  void collect(double d);
}
