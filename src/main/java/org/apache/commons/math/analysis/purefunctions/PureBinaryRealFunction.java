package org.apache.commons.math.analysis.purefunctions;

import org.apache.commons.math.analysis.purefunctions.AbstractPureBinaryRealFunction;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.linear.BinaryCollector;

public interface PureBinaryRealFunction
{

  static class Multiply extends AbstractPureBinaryRealFunction {
    public double value(double d1, double d2) { return d1 * d2; }  
  }
  
  static class Divide extends AbstractPureBinaryRealFunction {
    public double value(double d1, double d2) { return d1 / d2; }   
  }
  
  public static final PureBinaryRealFunction Multiply = new Multiply();

  public static final PureBinaryRealFunction Divide = new Divide();

  public static final PureBinaryRealFunction Add = new AbstractPureBinaryRealFunction()
  {
    public double value(double d1, double d2) { return d1 + d2; }    
  };

  public static final PureBinaryRealFunction Subtract = new AbstractPureBinaryRealFunction()
  {
    public double value(double d1, double d2) { return d1 - d2; }    
  };
  
  public static final PureBinaryRealFunction Pow = new AbstractPureBinaryRealFunction()
  {
    public double value(double d1, double d2)
    {
      return Math.pow(d1, d2);
    }
  };
  
  double value(int index, double d1, double d2);
  
  double value(double d1, double d2);
  
  PureUnivariateRealFunction provideDefaultSecondArgument(double d2);
  
  PureUnivariateRealFunction provideDefaultFirstArgument(double d1);
  
  BinaryCollector asCollector(PureBinaryRealFunction combiner, double initialValue);
  
  BinaryCollector asCollector(PureBinaryRealFunction combiner);
  
  BinaryCollector asCollector();
}
