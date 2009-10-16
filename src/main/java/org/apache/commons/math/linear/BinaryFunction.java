package org.apache.commons.math.linear;

public interface BinaryFunction
{
  double apply(double d1, double d2);
  
  UnaryFunction provideDefaultSecondArgument(double d2);
  
  UnaryFunction provideDefaultFirstArgument(double d1);
  
  BinaryCollector asCollector(BinaryFunction combiner, double initialValue);
  
  BinaryCollector asCollector(BinaryFunction combiner);
}
