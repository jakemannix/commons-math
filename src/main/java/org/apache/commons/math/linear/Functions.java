package org.apache.commons.math.linear;

public class Functions
{

  public static DefaultPreservingBinaryFunction Multiply = new DefaultPreservingBinaryFunction()
  {
    public double apply(double d1, double d2) { return d1 * d2; }    
  };

  public static DefaultPreservingBinaryFunction Divide = new DefaultPreservingBinaryFunction()
  {
    public double apply(double d1, double d2) { return d1 / d2; }    
  };

  public static BinaryFunction Add = new BinaryFunction()
  {
    public double apply(double d1, double d2) { return d1 + d2; }    
  };

  public static BinaryFunction Subtract = new BinaryFunction()
  {
    public double apply(double d1, double d2) { return d1 - d2; }    
  };
}
