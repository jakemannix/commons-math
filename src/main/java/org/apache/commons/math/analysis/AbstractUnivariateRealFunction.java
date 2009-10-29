package org.apache.commons.math.analysis;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.linear.AbstractUnaryCollector;
import org.apache.commons.math.linear.RealVector;


public abstract class AbstractUnivariateRealFunction implements UnivariateRealFunction
{
  public double value(int index, double d) throws FunctionEvaluationException
  {
    return value(d);
  }
  
  public abstract double value(double d) throws FunctionEvaluationException;

  public MultivariateRealFunction asCollector()
  {
    return asCollector(BinaryRealFunction.Add);
  }

  public MultivariateRealFunction asCollector(BinaryRealFunction combiner)
  {
    return asCollector(combiner, 0d);
  }
  
  public MultivariateRealFunction asCollector(final BinaryRealFunction combiner, final double initialValue)
  {
    return new AbstractUnaryCollector(initialValue)
    {   
      public void collect(double d)
      {
        value = combiner.value(value, d);
      }
      
      public void collect(int index, double d)
      {
        value = combiner.value(index, value, d);
      }

      public double value(double[] point) throws FunctionEvaluationException
      {
        for(int i=0; i<point.length; i++) collect(i, point[i]);
        return value;
      }

      public double value(RealVector point) throws FunctionEvaluationException
      {
        //  TODO: replace with this:
        // return point.collect(this);
        return value(point.getData());
      }
    };
  }
  
  public UnivariateRealFunction preCompose(final UnivariateRealFunction f)
  {
    return new AbstractUnivariateRealFunction()
    {
      public double value(double d) throws FunctionEvaluationException
      {
        return f.value(AbstractUnivariateRealFunction.this.value(d));
      }
    };
  }

  public UnivariateRealFunction postCompose(final UnivariateRealFunction f)
  {
    return new AbstractUnivariateRealFunction()
    {
      public double value(double d) throws FunctionEvaluationException
      {
        return AbstractUnivariateRealFunction.this.value(f.value(d));
      }
    };
  }

  public UnivariateRealFunction combine(final UnivariateRealFunction f, final BinaryRealFunction combiner)
  {
    return new AbstractUnivariateRealFunction()
    {
      public double value(double d) throws FunctionEvaluationException
      {
        return combiner.value(AbstractUnivariateRealFunction.this.value(d), f.value(d));
      }
    };
  }

  public UnivariateRealFunction plus(UnivariateRealFunction f)
  {
    return this.combine(f, BinaryRealFunction.Add);
  }

  public UnivariateRealFunction minus(UnivariateRealFunction f)
  {
    return this.combine(f, BinaryRealFunction.Subtract);
  }

  public UnivariateRealFunction times(UnivariateRealFunction f)
  {
    return this.combine(f, BinaryRealFunction.Multiply);
  }

  public UnivariateRealFunction scale(final double scaleFactor)
  {
    return postCompose(BinaryRealFunction.Multiply.provideDefaultSecondArgument(scaleFactor));
  }
}
