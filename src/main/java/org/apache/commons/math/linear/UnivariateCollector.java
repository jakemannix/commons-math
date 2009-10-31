package org.apache.commons.math.linear;

import org.apache.commons.math.analysis.purefunctions.PureMultivariateRealFunction;

public interface UnivariateCollector extends Collector, PureMultivariateRealFunction
{
  void collect(int index, double d);
  void collect(double d);
}
