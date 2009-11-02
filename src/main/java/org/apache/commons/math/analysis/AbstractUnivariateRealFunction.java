package org.apache.commons.math.analysis;

import org.apache.commons.math.FunctionEvaluationException;
//import org.apache.commons.math.linear.AbstractUnaryCollector;
//import org.apache.commons.math.linear.RealVector;


public abstract class AbstractUnivariateRealFunction implements UnivariateRealFunction
{
  public double value(int index, double d) throws FunctionEvaluationException
  {
    return value(d);
  }
  
  public abstract double value(double d) throws FunctionEvaluationException;
/*
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
        return point.collect(this);
      }
    };
  }
  */
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

}
