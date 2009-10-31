package org.apache.commons.math.linear;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.purefunctions.AbstractMultivariateRealFunction;

public abstract class AbstractUnivariateCollector extends AbstractMultivariateRealFunction implements UnivariateCollector
{
  protected double value;
  
  public AbstractUnivariateCollector()
  {
    this(0);
  }
  
  public AbstractUnivariateCollector(double initialValue)
  {
    value = initialValue;
  }
  
  public void collect(int index, double d)
  {
    collect(d);
  }
  
  public double value(double[] point) throws FunctionEvaluationException,
      IllegalArgumentException
  {
    return value(new ArrayRealVector(point, false));
  }

  public double value(RealVector point) throws FunctionEvaluationException
  {
    return point instanceof ArrayRealVector ? value(((ArrayRealVector)point).getDataRef()) : value(point.getData());
  }

  public double collectedValue()
  {
    return value;
  }

}
