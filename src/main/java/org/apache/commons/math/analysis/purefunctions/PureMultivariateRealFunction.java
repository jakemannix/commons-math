package org.apache.commons.math.analysis.purefunctions;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.MultivariateRealFunction;
import org.apache.commons.math.linear.RealVector;


public interface PureMultivariateRealFunction extends MultivariateRealFunction {

  public double value(RealVector vector) throws FunctionEvaluationException;

}
