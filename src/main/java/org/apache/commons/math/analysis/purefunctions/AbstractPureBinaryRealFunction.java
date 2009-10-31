package org.apache.commons.math.analysis.purefunctions;

import org.apache.commons.math.linear.AbstractBinaryCollector;
import org.apache.commons.math.linear.BinaryCollector;

public abstract class AbstractPureBinaryRealFunction implements PureBinaryRealFunction
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

  public BinaryCollector asCollector(PureBinaryRealFunction combiner)
  {
    return asCollector(combiner, 0);
  }

  public BinaryCollector asCollector(final PureBinaryRealFunction combiner, final double initialValue)
  {
    return new AbstractBinaryCollector(initialValue)
    {
      public void collect(int index, double d1, double d2)
      {
        result = combiner.value(index, result, AbstractPureBinaryRealFunction.this.value(index, d1, d2));
      }
      public void collect(double d1, double d2)
      {
        result = combiner.value(result, AbstractPureBinaryRealFunction.this.value(d1, d2));
      }     
    };
  }

  public PureUnivariateRealFunction provideDefaultFirstArgument(final double d1)
  {
    return new AbstractUnivariateRealFunction()
    {
      public double value(double d)
      {
        return AbstractPureBinaryRealFunction.this.value(d1, d);
      }
      public double value(int index, double d)
      {
        return AbstractPureBinaryRealFunction.this.value(index, d1, d);
      }
    };
  }

  public PureUnivariateRealFunction provideDefaultSecondArgument(final double d2)
  {
    return new AbstractUnivariateRealFunction()
    {
      public double value(double d)
      {
        return AbstractPureBinaryRealFunction.this.value(d, d2);
      }
      public double value(int index, double d)
      {
        return AbstractPureBinaryRealFunction.this.value(index, d, d2);
      }
    };
  }


}
