package org.apache.commons.math.linear;

public interface BinaryFunction
{

  static class Multiply extends AbstractBinaryFunction implements DefaultPreservingBinaryFunction
  {
    public double apply(double d1, double d2) { return d1 * d2; }  
  }
  
  static class Divide extends AbstractBinaryFunction implements DefaultPreservingBinaryFunction
  {
    public double apply(double d1, double d2) { return d1 / d2; }   
  }
  
  public static final DefaultPreservingBinaryFunction Multiply = new Multiply();

  public static final DefaultPreservingBinaryFunction Divide = new Divide();

  public static final BinaryFunction Add = new AbstractBinaryFunction()
  {
    public double apply(double d1, double d2) { return d1 + d2; }    
  };

  public static final BinaryFunction Subtract = new AbstractBinaryFunction()
  {
    public double apply(double d1, double d2) { return d1 - d2; }    
  };
  
  double apply(int index, double d1, double d2);
  
  double apply(double d1, double d2);
  
  UnaryFunction provideDefaultSecondArgument(double d2);
  
  UnaryFunction provideDefaultFirstArgument(double d1);
  
  BinaryCollector asCollector(BinaryFunction combiner, double initialValue);
  
  BinaryCollector asCollector(BinaryFunction combiner);
  
  BinaryCollector asCollector();
}
