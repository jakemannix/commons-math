package org.apache.commons.math.analysis.purefunctions;

import org.apache.commons.math.analysis.purefunctions.AbstractBinaryRealFunction;
import org.apache.commons.math.linear.BinaryCollector;

public interface BinaryRealFunction
{

  static class Multiply extends AbstractBinaryRealFunction {
    public double value(double d1, double d2) { return d1 * d2; }  
  }
  
  static class Divide extends AbstractBinaryRealFunction {
    public double value(double d1, double d2) { return d1 / d2; }   
  }
  
  public static final BinaryRealFunction Multiply = new Multiply();

  public static final BinaryRealFunction Divide = new Divide();

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
  
  ComposableFunction provideDefaultSecondArgument(double d2);
  
  ComposableFunction provideDefaultFirstArgument(double d1);
  
  BinaryCollector asCollector(BinaryRealFunction combiner, double initialValue);
  
  BinaryCollector asCollector(BinaryRealFunction combiner);
  
  BinaryCollector asCollector();
}
