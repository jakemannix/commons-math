package org.apache.commons.math.analysis;


public class UnivariateRealFunctions
{
  public static final UnivariateRealFunction Abs = new UnivariateRealFunction() {
    public double value(double d) { return Math.abs(d); }
  };
  public static final UnivariateRealFunction Negate = new UnivariateRealFunction() {
    public double value(double d) { return -d; }
  };
  public static final UnivariateRealFunction Sin = new UnivariateRealFunction() {
    public double value(double d) { return Math.sin(d); }
  };
  public static final UnivariateRealFunction Sqrt = new UnivariateRealFunction() {
    public double value(double d) { return Math.sqrt(d); }
  };
  public static final UnivariateRealFunction Sinh = new UnivariateRealFunction() {
    public double value(double d) { return Math.sinh(d); }
  };
  public static final UnivariateRealFunction Exp = new UnivariateRealFunction() {
    public double value(double d) { return Math.exp(d); }
  };
  public static final UnivariateRealFunction Exp1m = new UnivariateRealFunction() {
    public double value(double d) { return Math.expm1(d); }
  };
  public static final UnivariateRealFunction Asin = new UnivariateRealFunction() {
    public double value(double d) { return Math.asin(d); }
  };
  public static final UnivariateRealFunction Atan = new UnivariateRealFunction() {
    public double value(double d) { return Math.atan(d); }
  };
  public static final UnivariateRealFunction Tan = new UnivariateRealFunction() {
    public double value(double d) { return Math.tan(d); }
  };
  public static final UnivariateRealFunction Tanh = new UnivariateRealFunction() {
    public double value(double d) { return Math.tanh(d); }
  };
  public static final UnivariateRealFunction Cbrt = new UnivariateRealFunction() {
    public double value(double d) { return Math.cbrt(d); }
  };
  public static final UnivariateRealFunction Ceil = new UnivariateRealFunction() {
    public double value(double d) { return Math.ceil(d); }
  };
  public static final UnivariateRealFunction Floor = new UnivariateRealFunction() {
    public double value(double d) { return Math.floor(d); }
  };
  public static final UnivariateRealFunction Log = new UnivariateRealFunction() {
    public double value(double d) { return Math.log(d); }
  };
  public static final UnivariateRealFunction Log10 = new UnivariateRealFunction() {
    public double value(double d) { return Math.log10(d); }
  };
  public static final UnivariateRealFunction Cos = new UnivariateRealFunction() {
    public double value(double d) { return Math.cos(d); }
  };
  public static final UnivariateRealFunction Acos = new UnivariateRealFunction() {
    public double value(double d) { return Math.acos(d); }
  };
  public static final UnivariateRealFunction Cosh = new UnivariateRealFunction() {
    public double value(double d) { return Math.cosh(d); }
  };
  public static final UnivariateRealFunction Rint = new UnivariateRealFunction() {
    public double value(double d) { return Math.rint(d); }
  };
  public static final UnivariateRealFunction Signum = new UnivariateRealFunction() {
    public double value(double d) { return Math.signum(d); }
  };
  public static final UnivariateRealFunction Ulp = new UnivariateRealFunction() {
    public double value(double d) { return Math.ulp(d); }
  };
  
  public static class Pow implements UnivariateRealFunction
  {
    private final double pow;
    public Pow(double pow) { this.pow = pow; }
    public double value(double d) { return Math.pow(d, pow); }
  }

}
