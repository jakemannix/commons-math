package org.apache.commons.math.analysis;

import org.apache.commons.math.linear.AbstractBinaryCollector;
import org.apache.commons.math.linear.BinaryCollector;

public abstract class AbstractBinaryRealFunction implements BinaryRealFunction
{

  public abstract double value(double d1, double d2);

  public double value(int index, double d1, double d2)
  {
    return value(d1, d2);
  }

  public BinaryCollector asCollector()
  {
    return asCollector(Add);
  }

  public BinaryCollector asCollector(BinaryRealFunction combiner)
  {
    return asCollector(combiner, 0);
  }

  public BinaryCollector asCollector(final BinaryRealFunction combiner, final double initialValue)
  {
    return new AbstractBinaryCollector(initialValue)
    {
      public void collect(int index, double d1, double d2)
      {
        result = combiner.value(index, result, AbstractBinaryRealFunction.this.value(index, d1, d2));
      }
      public void collect(double d1, double d2)
      {
        result = combiner.value(result, AbstractBinaryRealFunction.this.value(d1, d2));
      }     
    };
  }

  public UnivariateRealFunction provideDefaultFirstArgument(final double d1)
  {
    return new AbstractUnivariateRealFunction()
    {
      public double value(double d)
      {
        return AbstractBinaryRealFunction.this.value(d1, d);
      }
      public double value(int index, double d)
      {
        return AbstractBinaryRealFunction.this.value(index, d1, d);
      }
    };
  }

  public UnivariateRealFunction provideDefaultSecondArgument(final double d2)
  {
    return new AbstractUnivariateRealFunction()
    {
      public double value(double d)
      {
        return AbstractBinaryRealFunction.this.value(d, d2);
      }
      public double value(int index, double d)
      {
        return AbstractBinaryRealFunction.this.value(index, d, d2);
      }
    };
  }


}
