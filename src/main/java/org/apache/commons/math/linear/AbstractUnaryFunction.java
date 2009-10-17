package org.apache.commons.math.linear;


public abstract class AbstractUnaryFunction implements UnaryFunction
{
  public double apply(int index, double d)
  {
    return apply(d);
  }
  
  public abstract double apply(double d);

  public UnaryCollector asCollector()
  {
    return asCollector(BinaryFunction.Add);
  }

  public UnaryCollector asCollector(BinaryFunction combiner)
  {
    return asCollector(combiner, 0d);
  }
  
  public UnaryCollector asCollector(final BinaryFunction combiner, final double initialValue)
  {
    return new AbstractUnaryCollector(initialValue)
    {
      public void collect(double d)
      {
        result = combiner.apply(result, d);
      }
      
      public void collect(int index, double d)
      {
        result = combiner.apply(index, result, d);
      }
    };
  }
  

}
