package org.apache.commons.math.linear;

public class Functions
{

  public static DefaultPreservingBinaryFunction Multiply = new AbstractDefaultPreservingBinaryFunction()
  {
    public double apply(double d1, double d2) { return d1 * d2; }    
  };

  public static DefaultPreservingBinaryFunction Divide = new AbstractDefaultPreservingBinaryFunction()
  {
    public double apply(double d1, double d2) { return d1 / d2; }    
  };

  public static BinaryFunction Add = new AbstractBinaryFunction()
  {
    public double apply(double d1, double d2) { return d1 + d2; }    
  };

  public static BinaryFunction Subtract = new AbstractBinaryFunction()
  {
    public double apply(double d1, double d2) { return d1 - d2; }    
  };
}
