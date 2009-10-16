package org.apache.commons.math.linear;

public class MultiplyFunction implements DefaultPreservingBinaryFunction
{

  public double apply(double d1, double d2)
  {
    return d1 * d2;
  }

  public UnaryFunction provideDefault(final double d2)
  {
    return new DefaultPreservingUnaryFunction()
    {
      public double apply(double d)
      {
        return d * d2;
      }
    };
  }

}
