package org.apache.commons.math.analysis;

import org.apache.commons.math.linear.BinaryCollector;

public interface BinaryRealFunction
{

  static class Multiply extends AbstractBinaryRealFunction implements DefaultPreservingBinaryRealFunction
  {
    public double value(double d1, double d2) { return d1 * d2; }  
  }
  
  static class Divide extends AbstractBinaryRealFunction implements DefaultPreservingBinaryRealFunction
  {
    public double value(double d1, double d2) { return d1 / d2; }   
  }
  
  public static final DefaultPreservingBinaryRealFunction Multiply = new Multiply();

  public static final DefaultPreservingBinaryRealFunction Divide = new Divide();

  public static final BinaryRealFunction Add = new AbstractBinaryRealFunction()
  {
    public double value(double d1, double d2) { return d1 + d2; }    
  };

  public static final BinaryRealFunction Subtract = new AbstractBinaryRealFunction()
  {
    public double value(double d1, double d2) { return d1 - d2; }    
  };
  
  public static final BinaryRealFunction Pow = new AbstractBinaryRealFunction()
  {
    public double value(double d1, double d2)
    {
      return Math.pow(d1, d2);
    }
  };
  
  double value(int index, double d1, double d2);
  
  double value(double d1, double d2);
  
  UnivariateRealFunction provideDefaultSecondArgument(double d2);
  
  UnivariateRealFunction provideDefaultFirstArgument(double d1);
  
  BinaryCollector asCollector(BinaryRealFunction combiner, double initialValue);
  
  BinaryCollector asCollector(BinaryRealFunction combiner);
  
  BinaryCollector asCollector();
}
