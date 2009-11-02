package org.apache.commons.math.analysis;

import org.apache.commons.math.FunctionEvaluationException;


public abstract class AbstractUnivariateRealFunction implements UnivariateRealFunction
{
  public double value(int index, double d) throws FunctionEvaluationException
  {
    return value(d);
  }
  
  public abstract double value(double d) throws FunctionEvaluationException;

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
