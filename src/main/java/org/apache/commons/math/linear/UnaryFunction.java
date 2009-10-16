package org.apache.commons.math.linear;

public interface UnaryFunction
{
  double apply(double d);
  
  UnaryCollector asCollector(BinaryFunction combiner, double initialValue);
  
  UnaryCollector asCollector(BinaryFunction combiner);
}
