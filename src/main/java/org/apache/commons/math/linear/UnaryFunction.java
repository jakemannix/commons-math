package org.apache.commons.math.linear;

public interface UnaryFunction
{
  static class Abs extends AbstractUnaryFunction implements DefaultPreservingUnaryFunction 
  {
    public double apply(double d)
    {
      return Math.abs(d);
    }
  }
  
  static class Negate extends AbstractUnaryFunction implements DefaultPreservingUnaryFunction
  {
    public double apply(double d)
    {
      return -d;
    }
  }
  
  public static DefaultPreservingUnaryFunction Abs = new Abs();
  
  public static DefaultPreservingUnaryFunction Negate = new Negate();
  
  double apply(double d);
  
  double apply(int index, double d);
  
  UnaryCollector asCollector(BinaryFunction combiner, double initialValue);
  
  UnaryCollector asCollector(BinaryFunction combiner);
  
  UnaryCollector asCollector();
}
