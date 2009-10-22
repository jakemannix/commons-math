package org.apache.commons.math.analysis;

import java.util.Random;


import junit.framework.TestCase;

public class FunctionPerfTest extends TestCase
{

  public FunctionPerfTest(String name)
  {
    super(name);
  }

  protected void setUp() throws Exception
  {
    super.setUp();
  }

  protected void tearDown() throws Exception
  {
    super.tearDown();
  }
  
  public void testPerf() throws Exception
  {
    int size = 100000;
    long total = System.nanoTime();
    double[] inputs = new double[size];
    Random r = new Random(3141529);
    for(int i=0; i<inputs.length; i++) inputs[i] = r.nextGaussian();
    double time = 0;
    int numPasses = 1000;
    for(int n=0; n<numPasses + 2000; n++)
    {
      long start = System.nanoTime();
      for(double d : inputs)
      {
//        double out = Math.exp(Math.pow(Math.sin(d), 2));
      }
      if(n > 2000) time += (double)(System.nanoTime() - start);
    }
    double staticTime = time / (numPasses * 1000 * 1000);
    time = 0;
    for(int n=0; n<numPasses + 2000; n++)
    {
 //     UnaryFunction f = Functions.Exp.preCompose((Functions.Pow2.preCompose(Functions.Sin)));
      long start = System.nanoTime();
      for(double d : inputs)
      {
   //     double out = f.value(d);
      }
      if(n > 2000) time += (double)(System.nanoTime() - start);
    }
    double abstractTime = time / (numPasses * 1000 * 1000);
    System.out.println("Static time: " + staticTime + "ms");
    System.out.println("Abstract time: " + abstractTime + "ms");
    System.out.println("Test time: " + (System.nanoTime() - total)/(1000 * 1000 * 1000) + "s");
  }

  public static final double applyGaussian(double d)
  {
    return Math.exp(-Math.pow(d, 2));
  }
  
}
