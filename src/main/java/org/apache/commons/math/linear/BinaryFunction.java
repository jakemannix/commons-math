package org.apache.commons.math.linear;

public interface BinaryFunction
{
  double apply(double d1, double d2);
  
  UnaryFunction provideDefault(double d2);
}
