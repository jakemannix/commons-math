/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.math.linear;

import java.io.Serializable;
import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.math.TestUtils;

import com.sun.corba.se.impl.orbutil.DenseIntMapImpl;

/**
 * Test cases for the {@link ArrayRealVector} class.
 *
 * @version $Revision$ $Date$
 */
public class ArrayRealVectorTest extends TestCase {

    //
    protected double[][] ma1 = {{1d, 2d, 3d}, {4d, 5d, 6d}, {7d, 8d, 9d}};
    protected double[] vec1 = {1d, 2d, 3d};
    protected double[] vec2 = {4d, 5d, 6d};
    protected double[] vec3 = {7d, 8d, 9d};
    protected double[] vec4 = {1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d};
    protected double[] vec_null = {0d, 0d, 0d};
    protected Double[] dvec1 = {1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d};
    protected double[][] mat1 = {{1d, 2d, 3d}, {4d, 5d, 6d},{ 7d, 8d, 9d}};

    // tolerances
    protected double entryTolerance = 10E-16;
    protected double normTolerance = 10E-14;

    // Testclass to test the RealVector interface
    // only with enough content to support the test
    public static class RealVectorTestImpl implements RealVector, Serializable {

        /** Serializable version identifier. */
        private static final long serialVersionUID = 4715341047369582908L;

        /** Entries of the vector. */
        protected double data[];

        public RealVectorTestImpl(double[] d) {
            data = d.clone();
        }

        private UnsupportedOperationException unsupported() {
            return new UnsupportedOperationException("Not supported, unneeded for test purposes");
        }

        public RealVector copy() {
            throw unsupported();
        }

        public RealVector add(RealVector v) throws IllegalArgumentException {
            throw unsupported();
        }

        public RealVector add(double[] v) throws IllegalArgumentException {
            throw unsupported();
        }

        public RealVector subtract(RealVector v) throws IllegalArgumentException {
            throw unsupported();
        }

        public RealVector subtract(double[] v) throws IllegalArgumentException {
            throw unsupported();
        }

        public RealVector mapAdd(double d) {
            throw unsupported();
        }

        public RealVector mapAddToSelf(double d) {
            throw unsupported();
        }

        public RealVector mapSubtract(double d) {
            throw unsupported();
        }

        public RealVector mapSubtractToSelf(double d) {
            throw unsupported();
        }

        public RealVector mapMultiply(double d) {
            double[] out = new double[data.length];
            for (int i = 0; i < data.length; i++) {
                out[i] = data[i] * d;
            }
            return new ArrayRealVector(out);
        }

        public RealVector mapMultiplyToSelf(double d) {
            throw unsupported();
        }

        public RealVector mapDivide(double d) {
            throw unsupported();
        }

        public RealVector mapDivideToSelf(double d) {
            throw unsupported();
        }

        public RealVector mapPow(double d) {
            throw unsupported();
        }

        public RealVector mapPowToSelf(double d) {
            throw unsupported();
        }

        public RealVector mapExp() {
            throw unsupported();
        }

        public RealVector mapExpToSelf() {
            throw unsupported();
        }

        public RealVector mapExpm1() {
            throw unsupported();
        }

        public RealVector mapExpm1ToSelf() {
            throw unsupported();
        }

        public RealVector mapLog() {
            throw unsupported();
        }

        public RealVector mapLogToSelf() {
            throw unsupported();
        }

        public RealVector mapLog10() {
            throw unsupported();
        }

        public RealVector mapLog10ToSelf() {
            throw unsupported();
        }

        public RealVector mapLog1p() {
            throw unsupported();
        }

        public RealVector mapLog1pToSelf() {
            throw unsupported();
        }

        public RealVector mapCosh() {
            throw unsupported();
        }

        public RealVector mapCoshToSelf() {
            throw unsupported();
        }

        public RealVector mapSinh() {
            throw unsupported();
        }

        public RealVector mapSinhToSelf() {
            throw unsupported();
        }

        public RealVector mapTanh() {
            throw unsupported();
        }

        public RealVector mapTanhToSelf() {
            throw unsupported();
        }

        public RealVector mapCos() {
            throw unsupported();
        }

        public RealVector mapCosToSelf() {
            throw unsupported();
        }

        public RealVector mapSin() {
            throw unsupported();
        }

        public RealVector mapSinToSelf() {
            throw unsupported();
        }

        public RealVector mapTan() {
            throw unsupported();
        }

        public RealVector mapTanToSelf() {
            throw unsupported();
        }

        public RealVector mapAcos() {
            throw unsupported();
        }

        public RealVector mapAcosToSelf() {
            throw unsupported();
        }

        public RealVector mapAsin() {
            throw unsupported();
        }

        public RealVector mapAsinToSelf() {
            throw unsupported();
        }

        public RealVector mapAtan() {
            throw unsupported();
        }

        public RealVector mapAtanToSelf() {
            throw unsupported();
        }

        public RealVector mapInv() {
            throw unsupported();
        }

        public RealVector mapInvToSelf() {
            throw unsupported();
        }

        public RealVector mapAbs() {
            throw unsupported();
        }

        public RealVector mapAbsToSelf() {
            throw unsupported();
        }

        public RealVector mapSqrt() {
            throw unsupported();
        }

        public RealVector mapSqrtToSelf() {
            throw unsupported();
        }

        public RealVector mapCbrt() {
            throw unsupported();
        }

        public RealVector mapCbrtToSelf() {
            throw unsupported();
        }

        public RealVector mapCeil() {
            throw unsupported();
        }

        public RealVector mapCeilToSelf() {
            throw unsupported();
        }

        public RealVector mapFloor() {
            throw unsupported();
        }

        public RealVector mapFloorToSelf() {
            throw unsupported();
        }

        public RealVector mapRint() {
            throw unsupported();
        }

        public RealVector mapRintToSelf() {
            throw unsupported();
        }

        public RealVector mapSignum() {
            throw unsupported();
        }

        public RealVector mapSignumToSelf() {
            throw unsupported();
        }

        public RealVector mapUlp() {
            throw unsupported();
        }

        public RealVector mapUlpToSelf() {
            throw unsupported();
        }

        public RealVector ebeMultiply(RealVector v) throws IllegalArgumentException {
            throw unsupported();
        }

        public RealVector ebeMultiply(double[] v) throws IllegalArgumentException {
            throw unsupported();
        }

        public RealVector ebeDivide(RealVector v) throws IllegalArgumentException {
            throw unsupported();
        }

        public RealVector ebeDivide(double[] v) throws IllegalArgumentException {
            throw unsupported();
        }

        public double[] getData() {
            return data.clone();
        }

        public double dotProduct(RealVector v) throws IllegalArgumentException {
            double dot = 0;
            for (int i = 0; i < data.length; i++) {
                dot += data[i] * v.getEntry(i);
            }
            return dot;
        }

        public double dotProduct(double[] v) throws IllegalArgumentException {
            double dot = 0;
            for (int i = 0; i < data.length; i++) {
                dot += data[i] * v[i];
            }
            return dot;
        }

        public double getNorm() {
            throw unsupported();
        }

        public double getL1Norm() {
            throw unsupported();
        }

        public double getLInfNorm() {
            throw unsupported();
        }

        public double getDistance(RealVector v) throws IllegalArgumentException {
            throw unsupported();
        }

        public double getDistance(double[] v) throws IllegalArgumentException {
            throw unsupported();
        }

        public double getL1Distance(RealVector v) throws IllegalArgumentException {
            throw unsupported();
        }

        public double getL1Distance(double[] v) throws IllegalArgumentException {
            throw unsupported();
        }

        public double getLInfDistance(RealVector v) throws IllegalArgumentException {
            throw unsupported();
        }

        public double getLInfDistance(double[] v) throws IllegalArgumentException {
            throw unsupported();
        }

        public RealVector unitVector() {
            throw unsupported();
        }

        public void unitize() {
            throw unsupported();
        }

        public RealVector projection(RealVector v) throws IllegalArgumentException {
            throw unsupported();
        }

        public RealVector projection(double[] v) throws IllegalArgumentException {
            throw unsupported();
        }

        public RealMatrix outerProduct(RealVector v) throws IllegalArgumentException {
            throw unsupported();
        }

        public RealMatrix outerProduct(double[] v) throws IllegalArgumentException {
            throw unsupported();
        }

        public double getEntry(int index) throws MatrixIndexException {
            return data[index];
        }

        public int getDimension() {
            return data.length;
        }

        public RealVector append(RealVector v) {
            throw unsupported();
        }

        public RealVector append(double d) {
            throw unsupported();
        }

        public RealVector append(double[] a) {
            throw unsupported();
        }

        public RealVector getSubVector(int index, int n) throws MatrixIndexException {
            throw unsupported();
        }

        public void setEntry(int index, double value) throws MatrixIndexException {
            throw unsupported();
        }

        public void setSubVector(int index, RealVector v) throws MatrixIndexException {
            throw unsupported();
        }

        public void setSubVector(int index, double[] v) throws MatrixIndexException {
            throw unsupported();
        }

        public void set(double value) {
            throw unsupported();
        }

        public double[] toArray() {
            throw unsupported();
        }

        public boolean isNaN() {
            throw unsupported();
        }

        public boolean isInfinite() {
            throw unsupported();
        }

        public double collect(UnaryCollector coll) {
          throw unsupported();
        }

        public double collect(BinaryCollector coll, RealVector v) {
          throw unsupported();
        }

        public Iterator<Entry> iterateNonZero() {
          throw unsupported();
        }

        public RealVector map(UnaryFunction func) {
          double[] d = data.clone();
          for(int i=0; i<d.length; i++) {
            d[i] = func.apply(d[i]);
          }
          return new ArrayRealVectorTest.RealVectorTestImpl(d);
        }

        public RealVector map(BinaryFunction func, RealVector v) {
          throw unsupported();
        }

        public RealVector mapToSelf(UnaryFunction func) {
          throw unsupported();
        }

        public Iterator<Entry> iterator() {
          throw unsupported();
        }

        public RealVector mapToSelf(BinaryFunction func, RealVector v) {
          throw unsupported();
        }

    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ArrayRealVectorTest.class);
        suite.setName("ArrayRealVector Tests");
        return suite;
    }

    public void testConstructors() {

        ArrayRealVector v0 = new ArrayRealVector();
        assertEquals("testData len", 0, v0.getDimension());

        ArrayRealVector v1 = new ArrayRealVector(7);
        assertEquals("testData len", 7, v1.getDimension());
        assertEquals("testData is 0.0 ", 0.0, v1.getEntry(6));

        ArrayRealVector v2 = new ArrayRealVector(5, 1.23);
        assertEquals("testData len", 5, v2.getDimension());
        assertEquals("testData is 1.23 ", 1.23, v2.getEntry(4));

        ArrayRealVector v3 = new ArrayRealVector(vec1);
        assertEquals("testData len", 3, v3.getDimension());
        assertEquals("testData is 2.0 ", 2.0, v3.getEntry(1));

        ArrayRealVector v4 = new ArrayRealVector(vec4, 3, 2);
        assertEquals("testData len", 2, v4.getDimension());
        assertEquals("testData is 4.0 ", 4.0, v4.getEntry(0));
        try {
            new ArrayRealVector(vec4, 8, 3);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException ex) {
            // expected behavior
        } catch (Exception e) {
            fail("wrong exception caught");
        }

        RealVector v5_i = new ArrayRealVector(dvec1);
        assertEquals("testData len", 9, v5_i.getDimension());
        assertEquals("testData is 9.0 ", 9.0, v5_i.getEntry(8));

        ArrayRealVector v5 = new ArrayRealVector(dvec1);
        assertEquals("testData len", 9, v5.getDimension());
        assertEquals("testData is 9.0 ", 9.0, v5.getEntry(8));

        ArrayRealVector v6 = new ArrayRealVector(dvec1, 3, 2);
        assertEquals("testData len", 2, v6.getDimension());
        assertEquals("testData is 4.0 ", 4.0, v6.getEntry(0));
        try {
            new ArrayRealVector(dvec1, 8, 3);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException ex) {
            // expected behavior
        } catch (Exception e) {
            fail("wrong exception caught");
        }

        ArrayRealVector v7 = new ArrayRealVector(v1);
        assertEquals("testData len", 7, v7.getDimension());
        assertEquals("testData is 0.0 ", 0.0, v7.getEntry(6));

        RealVectorTestImpl v7_i = new RealVectorTestImpl(vec1);

        ArrayRealVector v7_2 = new ArrayRealVector(v7_i);
        assertEquals("testData len", 3, v7_2.getDimension());
        assertEquals("testData is 0.0 ", 2.0d, v7_2.getEntry(1));

        ArrayRealVector v8 = new ArrayRealVector(v1, true);
        assertEquals("testData len", 7, v8.getDimension());
        assertEquals("testData is 0.0 ", 0.0, v8.getEntry(6));
        assertNotSame("testData not same object ", v1.data, v8.data);

        ArrayRealVector v8_2 = new ArrayRealVector(v1, false);
        assertEquals("testData len", 7, v8_2.getDimension());
        assertEquals("testData is 0.0 ", 0.0, v8_2.getEntry(6));
        assertEquals("testData same object ", v1.data, v8_2.data);

        ArrayRealVector v9 = new ArrayRealVector(v1, v3);
        assertEquals("testData len", 10, v9.getDimension());
        assertEquals("testData is 1.0 ", 1.0, v9.getEntry(7));

    }

    public void testDataInOut() {

        ArrayRealVector v1 = new ArrayRealVector(vec1);
        ArrayRealVector v2 = new ArrayRealVector(vec2);
        ArrayRealVector v4 = new ArrayRealVector(vec4);
        RealVectorTestImpl v2_t = new RealVectorTestImpl(vec2);

        RealVector v_append_1 = v1.append(v2);
        assertEquals("testData len", 6, v_append_1.getDimension());
        assertEquals("testData is 4.0 ", 4.0, v_append_1.getEntry(3));

        RealVector v_append_2 = v1.append(2.0);
        assertEquals("testData len", 4, v_append_2.getDimension());
        assertEquals("testData is 2.0 ", 2.0, v_append_2.getEntry(3));

        RealVector v_append_3 = v1.append(vec2);
        assertEquals("testData len", 6, v_append_3.getDimension());
        assertEquals("testData is  ", 4.0, v_append_3.getEntry(3));

        RealVector v_append_4 = v1.append(v2_t);
        assertEquals("testData len", 6, v_append_4.getDimension());
        assertEquals("testData is 4.0 ", 4.0, v_append_4.getEntry(3));

        RealVector v_copy = v1.copy();
        assertEquals("testData len", 3, v_copy.getDimension());
        assertNotSame("testData not same object ", v1.data, v_copy.getData());

        double[] a_double = v1.toArray();
        assertEquals("testData len", 3, a_double.length);
        assertNotSame("testData not same object ", v1.data, a_double);


//      ArrayRealVector vout4 = (ArrayRealVector) v1.clone();
//      assertEquals("testData len", 3, vout4.getDimension());
//      assertEquals("testData not same object ", v1.data, vout4.data);


        RealVector vout5 = v4.getSubVector(3, 3);
        assertEquals("testData len", 3, vout5.getDimension());
        assertEquals("testData is 4.0 ", 5.0, vout5.getEntry(1));
        try {
            v4.getSubVector(3, 7);
            fail("MatrixIndexException expected");
        } catch (MatrixIndexException ex) {
            // expected behavior
        } catch (Exception e) {
            fail("wrong exception caught");
        }

        ArrayRealVector v_set1 = (ArrayRealVector) v1.copy();
        v_set1.setEntry(1, 11.0);
        assertEquals("testData is 11.0 ", 11.0, v_set1.getEntry(1));
        try {
            v_set1.setEntry(3, 11.0);
            fail("MatrixIndexException expected");
        } catch (MatrixIndexException ex) {
            // expected behavior
        } catch (Exception e) {
            fail("wrong exception caught");
        }

        ArrayRealVector v_set2 = (ArrayRealVector) v4.copy();
        v_set2.set(3, v1);
        assertEquals("testData is 1.0 ", 1.0, v_set2.getEntry(3));
        assertEquals("testData is 7.0 ", 7.0, v_set2.getEntry(6));
        try {
            v_set2.set(7, v1);
            fail("MatrixIndexException expected");
        } catch (MatrixIndexException ex) {
            // expected behavior
        } catch (Exception e) {
            fail("wrong exception caught");
        }

        ArrayRealVector v_set3 = (ArrayRealVector) v1.copy();
        v_set3.set(13.0);
        assertEquals("testData is 13.0 ", 13.0, v_set3.getEntry(2));

        try {
            v_set3.getEntry(23);
            fail("ArrayIndexOutOfBoundsException expected");
        } catch (ArrayIndexOutOfBoundsException ex) {
            // expected behavior
        } catch (Exception e) {
            fail("wrong exception caught");
        }

        ArrayRealVector v_set4 = (ArrayRealVector) v4.copy();
        v_set4.setSubVector(3, v2_t);
        assertEquals("testData is 1.0 ", 4.0, v_set4.getEntry(3));
        assertEquals("testData is 7.0 ", 7.0, v_set4.getEntry(6));
        try {
            v_set4.setSubVector(7, v2_t);
            fail("MatrixIndexException expected");
        } catch (MatrixIndexException ex) {
            // expected behavior
        } catch (Exception e) {
            fail("wrong exception caught");
        }


        ArrayRealVector vout10 = (ArrayRealVector) v1.copy();
        ArrayRealVector vout10_2 = (ArrayRealVector) v1.copy();
        assertEquals(vout10, vout10_2);
        vout10_2.setEntry(0, 1.1);
        assertNotSame(vout10, vout10_2);

    }

    public void testBasicFunctions() {
        ArrayRealVector v1 = new ArrayRealVector(vec1);
        ArrayRealVector v2 = new ArrayRealVector(vec2);
        ArrayRealVector v_null = new ArrayRealVector(vec_null);

        RealVectorTestImpl v2_t = new RealVectorTestImpl(vec2);

        //octave =  sqrt(sumsq(v1))
        double d_getNorm = v1.getNorm();
        assertEquals("compare values  ", 3.7416573867739413,d_getNorm);

        double d_getL1Norm = v1.getL1Norm();
        assertEquals("compare values  ",6.0, d_getL1Norm);

        double d_getLInfNorm = v1.getLInfNorm();
        assertEquals("compare values  ",6.0, d_getLInfNorm);

        //octave =  sqrt(sumsq(v1-v2))
        double dist = v1.getDistance(v2);
        assertEquals("compare values  ",v1.subtract(v2).getNorm(), dist );

        //octave =  sqrt(sumsq(v1-v2))
        double dist_2 = v1.getDistance(v2_t);
        assertEquals("compare values  ", v1.subtract(v2).getNorm(),dist_2 );

        //octave =  ???
        double d_getL1Distance = v1. getL1Distance(v2);
        assertEquals("compare values  ",9d, d_getL1Distance );

        double d_getL1Distance_2 = v1. getL1Distance(v2_t);
        assertEquals("compare values  ",9d, d_getL1Distance_2 );

        //octave =  ???
        double d_getLInfDistance = v1. getLInfDistance(v2);
        assertEquals("compare values  ",3d, d_getLInfDistance );

        double d_getLInfDistance_2 = v1. getLInfDistance(v2_t);
        assertEquals("compare values  ",3d, d_getLInfDistance_2 );

        //octave =  v1 + v2
        ArrayRealVector v_add = v1.add(v2);
        double[] result_add = {5d, 7d, 9d};
        assertClose("compare vect" ,v_add.getData(),result_add,normTolerance);

        RealVectorTestImpl vt2 = new RealVectorTestImpl(vec2);
        RealVector v_add_i = v1.add(vt2);
        double[] result_add_i = {5d, 7d, 9d};
        assertClose("compare vect" ,v_add_i.getData(),result_add_i,normTolerance);

        //octave =  v1 - v2
        ArrayRealVector v_subtract = v1.subtract(v2);
        double[] result_subtract = {-3d, -3d, -3d};
        assertClose("compare vect" ,v_subtract.getData(),result_subtract,normTolerance);

        RealVector v_subtract_i = v1.subtract(vt2);
        double[] result_subtract_i = {-3d, -3d, -3d};
        assertClose("compare vect" ,v_subtract_i.getData(),result_subtract_i,normTolerance);

        // octave v1 .* v2
        ArrayRealVector  v_ebeMultiply = v1.ebeMultiply(v2);
        double[] result_ebeMultiply = {4d, 10d, 18d};
        assertClose("compare vect" ,v_ebeMultiply.getData(),result_ebeMultiply,normTolerance);

        RealVector  v_ebeMultiply_2 = v1.ebeMultiply(v2_t);
        double[] result_ebeMultiply_2 = {4d, 10d, 18d};
        assertClose("compare vect" ,v_ebeMultiply_2.getData(),result_ebeMultiply_2,normTolerance);

        // octave v1 ./ v2
        ArrayRealVector  v_ebeDivide = v1.ebeDivide(v2);
        double[] result_ebeDivide = {0.25d, 0.4d, 0.5d};
        assertClose("compare vect" ,v_ebeDivide.getData(),result_ebeDivide,normTolerance);

        RealVector  v_ebeDivide_2 = v1.ebeDivide(v2_t);
        double[] result_ebeDivide_2 = {0.25d, 0.4d, 0.5d};
        assertClose("compare vect" ,v_ebeDivide_2.getData(),result_ebeDivide_2,normTolerance);

        // octave  dot(v1,v2)
        double dot =  v1.dotProduct(v2);
        assertEquals("compare val ",32d, dot);

        // octave  dot(v1,v2_t)
        double dot_2 =  v1.dotProduct(v2_t);
        assertEquals("compare val ",32d, dot_2);

        RealMatrix m_outerProduct = v1.outerProduct(v2);
        assertEquals("compare val ",4d, m_outerProduct.getEntry(0,0));

        RealMatrix m_outerProduct_2 = v1.outerProduct(v2_t);
        assertEquals("compare val ",4d, m_outerProduct_2.getEntry(0,0));

        RealVector v_unitVector = v1.unitVector();
        RealVector v_unitVector_2 = v1.mapDivide(v1.getNorm());
        assertClose("compare vect" ,v_unitVector.getData(),v_unitVector_2.getData(),normTolerance);

        try {
            v_null.unitVector();
            fail("Expecting ArithmeticException");
        } catch (ArithmeticException ex) {
            // expected behavior
        } catch (Exception e) {
            fail("wrong exception caught");
        }

        ArrayRealVector v_unitize = (ArrayRealVector)v1.copy();
        v_unitize.unitize();
        assertClose("compare vect" ,v_unitVector_2.getData(),v_unitize.getData(),normTolerance);
        try {
            v_null.unitize();
            fail("Expecting ArithmeticException");
        } catch (ArithmeticException ex) {
            // expected behavior
        } catch (Exception e) {
            fail("wrong exception caught");
        }

        ArrayRealVector v_projection = v1.projection(v2);
        double[] result_projection = {1.662337662337662, 2.0779220779220777, 2.493506493506493};
        assertClose("compare vect", v_projection.getData(), result_projection, normTolerance);

        RealVector v_projection_2 = v1.projection(v2_t);
        double[] result_projection_2 = {1.662337662337662, 2.0779220779220777, 2.493506493506493};
        assertClose("compare vect", v_projection_2.getData(), result_projection_2, normTolerance);

    }

    public void testMisc() {
        ArrayRealVector v1 = new ArrayRealVector(vec1);
        ArrayRealVector v4 = new ArrayRealVector(vec4);
        RealVector v4_2 = new ArrayRealVector(vec4);

        String out1 = v1.toString();
        assertTrue("some output ",  out1.length()!=0);
        /*
         double[] dout1 = v1.copyOut();
        assertEquals("testData len", 3, dout1.length);
        assertNotSame("testData not same object ", v1.data, dout1);
         */
        try {
            v1.checkVectorDimensions(2);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException ex) {
            // expected behavior
        } catch (Exception e) {
            fail("wrong exception caught");
        }

       try {
            v1.checkVectorDimensions(v4);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException ex) {
            // expected behavior
        } catch (Exception e) {
            fail("wrong exception caught");
        }

        try {
            v1.checkVectorDimensions(v4_2);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException ex) {
            // expected behavior
        } catch (Exception e) {
            fail("wrong exception caught");
        }

    }

    public void testPredicates() {

        ArrayRealVector v = new ArrayRealVector(new double[] { 0, 1, 2 });

        assertFalse(v.isNaN());
        v.setEntry(1, Double.NaN);
        assertTrue(v.isNaN());

        assertFalse(v.isInfinite());
        v.setEntry(0, Double.POSITIVE_INFINITY);
        assertFalse(v.isInfinite());
        v.setEntry(1, 1);
        assertTrue(v.isInfinite());

        v.setEntry(0, 0);
        assertEquals(v, new ArrayRealVector(new double[] { 0, 1, 2 }));
        assertNotSame(v, new ArrayRealVector(new double[] { 0, 1, 2 + Math.ulp(2)}));
        assertNotSame(v, new ArrayRealVector(new double[] { 0, 1, 2, 3 }));

        assertEquals(new ArrayRealVector(new double[] { Double.NaN, 1, 2 }).hashCode(),
                     new ArrayRealVector(new double[] { 0, Double.NaN, 2 }).hashCode());

        assertTrue(new ArrayRealVector(new double[] { Double.NaN, 1, 2 }).hashCode() !=
                   new ArrayRealVector(new double[] { 0, 1, 2 }).hashCode());

    }

    public void testSerial()  {
        ArrayRealVector v = new ArrayRealVector(new double[] { 0, 1, 2 });
        assertEquals(v,TestUtils.serializeAndRecover(v));
    }


    /** verifies that two vectors are close (sup norm) */
    protected void assertClose(String msg, double[] m, double[] n,
            double tolerance) {
        if (m.length != n.length) {
            fail("vectors have different lengths");
        }
        for (int i = 0; i < m.length; i++) {
            assertEquals(msg + " " +  i + " elements differ", m[i],n[i],tolerance);
        }
    }

}
