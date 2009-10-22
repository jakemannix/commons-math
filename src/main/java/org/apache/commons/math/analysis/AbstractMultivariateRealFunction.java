package org.apache.commons.math.analysis;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealVector;

/**
 * One of the two methods (or both) <code>value(double[] point)</code> or <code>value(RealVector vector)</code> 
 * <b>MUST</b> be overridden, because they forward to each other in this default implementation.
 *
 */
public abstract class AbstractMultivariateRealFunction implements MultivariateRealFunction
{
  public AbstractMultivariateRealFunction()
  {
    super();
  }
  
  public double value(double[] point) throws FunctionEvaluationException
  {
    return value(new ArrayRealVector(point, false));
  }
  
  public double value(RealVector vector) throws FunctionEvaluationException 
  {
    return value(vector instanceof ArrayRealVector ? ((ArrayRealVector)vector).getDataRef() : vector.getData());
  }
}
