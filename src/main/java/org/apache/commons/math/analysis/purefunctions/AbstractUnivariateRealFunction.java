package org.apache.commons.math.analysis.purefunctions;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.linear.AbstractUnivariateCollector;
import org.apache.commons.math.linear.RealVector;


public abstract class AbstractUnivariateRealFunction implements PureUnivariateRealFunction
{
  public double value(int index, double d) throws FunctionEvaluationException
  {
    return value(d);
  }
  
  public abstract double value(double d) throws FunctionEvaluationException;

  public PureMultivariateRealFunction asCollector()
  {
    return asCollector(PureBinaryRealFunction.Add);
  }

  public PureMultivariateRealFunction asCollector(PureBinaryRealFunction combiner)
  {
    return asCollector(combiner, 0d);
  }
  
  public PureMultivariateRealFunction asCollector(final PureBinaryRealFunction combiner, final double initialValue)
  {
    return new AbstractUnivariateCollector(initialValue)
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
  
  public PureUnivariateRealFunction preCompose(final UnivariateRealFunction f)
  {
    return new AbstractUnivariateRealFunction()
    {
      public double value(double d) throws FunctionEvaluationException
      {
        return f.value(AbstractUnivariateRealFunction.this.value(d));
      }
    };
  }

  public PureUnivariateRealFunction postCompose(final UnivariateRealFunction f)
  {
    return new AbstractUnivariateRealFunction()
    {
      public double value(double d) throws FunctionEvaluationException
      {
        return AbstractUnivariateRealFunction.this.value(f.value(d));
      }
    };
  }

  public PureUnivariateRealFunction combine(final UnivariateRealFunction f, final PureBinaryRealFunction combiner)
  {
    return new AbstractUnivariateRealFunction()
    {
      public double value(double d) throws FunctionEvaluationException
      {
        return combiner.value(AbstractUnivariateRealFunction.this.value(d), f.value(d));
      }
    };
  }

  public PureUnivariateRealFunction plus(UnivariateRealFunction f)
  {
    return this.combine(f, PureBinaryRealFunction.Add);
  }

  public PureUnivariateRealFunction minus(UnivariateRealFunction f)
  {
    return this.combine(f, PureBinaryRealFunction.Subtract);
  }

  public PureUnivariateRealFunction times(UnivariateRealFunction f)
  {
    return this.combine(f, PureBinaryRealFunction.Multiply);
  }

  public PureUnivariateRealFunction scale(final double scaleFactor)
  {
    return postCompose(PureBinaryRealFunction.Multiply.provideDefaultSecondArgument(scaleFactor));
  }
}
