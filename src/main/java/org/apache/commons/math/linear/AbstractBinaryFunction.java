package org.apache.commons.math.linear;

public abstract class AbstractBinaryFunction implements BinaryFunction
{

  public abstract double apply(double d1, double d2);

  public double apply(int index, double d1, double d2)
  {
    return apply(d1, d2);
  }

  public BinaryCollector asCollector()
  {
    return asCollector(Add);
  }

  public BinaryCollector asCollector(BinaryFunction combiner)
  {
    return asCollector(combiner, 0);
  }

  public BinaryCollector asCollector(final BinaryFunction combiner, final double initialValue)
  {
    return new AbstractBinaryCollector(initialValue)
    {
      public void collect(int index, double d1, double d2)
      {
        result = combiner.apply(index, result, AbstractBinaryFunction.this.apply(index, d1, d2));
      }
      public void collect(double d1, double d2)
      {
        result = combiner.apply(result, AbstractBinaryFunction.this.apply(d1, d2));
      }     
    };
  }

  public UnaryFunction provideDefaultFirstArgument(final double d1)
  {
    return new AbstractUnaryFunction()
    {
      public double apply(double d)
      {
        return AbstractBinaryFunction.this.apply(d1, d);
      }
      public double apply(int index, double d)
      {
        return AbstractBinaryFunction.this.apply(index, d1, d);
      }
    };
  }

  public UnaryFunction provideDefaultSecondArgument(final double d2)
  {
    return new AbstractUnaryFunction()
    {
      public double apply(double d)
      {
        return AbstractBinaryFunction.this.apply(d, d2);
      }
      public double apply(int index, double d)
      {
        return AbstractBinaryFunction.this.apply(index, d, d2);
      }
    };
  }


}
