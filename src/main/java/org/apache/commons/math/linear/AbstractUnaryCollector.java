package org.apache.commons.math.linear;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.AbstractMultivariateRealFunction;

public abstract class AbstractUnaryCollector extends AbstractMultivariateRealFunction implements UnaryCollector
{
  protected double value;
  
  public AbstractUnaryCollector()
  {
    this(0);
  }
  
  public AbstractUnaryCollector(double initialValue)
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
