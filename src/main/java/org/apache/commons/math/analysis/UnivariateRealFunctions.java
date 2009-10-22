package org.apache.commons.math.analysis;

import org.apache.commons.math.FunctionEvaluationException;

public class UnivariateRealFunctions
{
  public static final DefaultPreservingUnivariateRealFunction Abs = new Abs();
  public static final DefaultPreservingUnivariateRealFunction Negate = new Negate();
  public static final DefaultPreservingUnivariateRealFunction Sin = new Sin();
  public static final DefaultPreservingUnivariateRealFunction Sqrt = new Sqrt();
  public static final DefaultPreservingUnivariateRealFunction Sinh = new Sinh();
  public static final UnivariateRealFunction Exp = new Exp();
  public static final DefaultPreservingUnivariateRealFunction Exp1m = new Exp1m();
  public static final DefaultPreservingUnivariateRealFunction Asin = new Asin();
  public static final DefaultPreservingUnivariateRealFunction Atan = new Atan();
  public static final DefaultPreservingUnivariateRealFunction Tan = new Tan();
  public static final DefaultPreservingUnivariateRealFunction Tanh = new Tanh();
  public static final DefaultPreservingUnivariateRealFunction Cbrt = new Cbrt();
  public static final DefaultPreservingUnivariateRealFunction Ceil = new Ceil();  
  public static final DefaultPreservingUnivariateRealFunction Floor = new Floor();
  public static final DefaultPreservingUnivariateRealFunction Log = new Log();
  public static final DefaultPreservingUnivariateRealFunction Log10 = new Log10();
  public static final UnivariateRealFunction Cos = new Cos();
  public static final UnivariateRealFunction Acos = new Acos();
  public static final UnivariateRealFunction Cosh = new Cosh();
  public static final DefaultPreservingUnivariateRealFunction Rint = new Rint();
  public static final DefaultPreservingUnivariateRealFunction Signum = new Signum();
  public static final UnivariateRealFunction Ulp = new Ulp();
  
  public static class Abs extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction 
  {
    public double value(double d)
    {
      return Math.abs(d);
    }
  }
  
  public static class Negate extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d)
    {
      return -d;
    }
  }
  
  public static class Exp extends AbstractUnivariateRealFunction
  {
    public double value(double d)
    {
      return Math.exp(d);
    }
  }
  
  public static class Pow extends AbstractUnivariateRealFunction
  {
    private final double pow;
    public Pow(double pow) { this.pow = pow; }
    public double value(double d)
    {
      return Math.pow(d, pow);
    }
  }
  
  public static class Sin extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d)
    {
      return Math.sin(d);
    }
  }

  
  public static class Sqrt extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d)
    {
      return Math.sqrt(d);
    }
  }
  
  public static class Sinh extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d)
    {
      return Math.sinh(d);
    }
  }
  
  public static class Asin extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.asin(d);
    }
  }

  public static class Atan extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.atan(d);
    }
  }


  public static class Tan extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.tan(d);
    }
  }

  public static class Tanh extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.tanh(d);
    }
  }
  
  public static class Cbrt extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.cbrt(d);
    }
  }
  
  public static class Ceil extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.ceil(d);
    }
  }
  
  public static class Cos extends AbstractUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.cos(d);
    }
  }
  
  public static class Acos extends AbstractUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.acos(d);
    }
  }
  
  
  public static class Cosh extends AbstractUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.cosh(d);
    }
  }
  
  public static class Exp1m extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.expm1(d);
    }
  }
  
  public static class Floor extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.floor(d);
    }
  }

  public static class Log extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.log(d);
    }
  }

  public static class Log10 extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.log10(d);
    }
  }


  public static class Log1p extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.log1p(d);
    }
  }

  public static class Rint extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.rint(d);
    }
  }

  public static class Signum extends AbstractUnivariateRealFunction implements DefaultPreservingUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.signum(d);
    }
  }
  
  public static class Ulp extends AbstractUnivariateRealFunction
  {
    public double value(double d) throws FunctionEvaluationException
    {
      return Math.ulp(d);
    }
  }
}
