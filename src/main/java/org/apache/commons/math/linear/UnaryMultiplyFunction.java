package org.apache.commons.math.linear;

public class UnaryMultiplyFunction implements UnaryFunction
{
  private final double factor;
  
  public UnaryMultiplyFunction(double factor) {
   this.factor = factor; 
  }
  
  public double apply(double d) {
    return d * factor;
  }

}
