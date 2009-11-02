package org.apache.commons.math.analysis.purefunctions;

import org.apache.commons.math.analysis.UnivariateRealFunction;


public interface ComposableFunction extends UnivariateRealFunction {

    PureMultivariateRealFunction asCollector();

    PureMultivariateRealFunction asCollector(BinaryRealFunction combiner);

    PureMultivariateRealFunction asCollector(final BinaryRealFunction combiner, final double initialValue);

    ComposableFunction preCompose(final UnivariateRealFunction f);

    ComposableFunction postCompose(final UnivariateRealFunction f);

    ComposableFunction combine(final UnivariateRealFunction f, final BinaryRealFunction combiner);

    ComposableFunction plus(UnivariateRealFunction f);

    ComposableFunction minus(UnivariateRealFunction f);

    ComposableFunction times(UnivariateRealFunction f);

    ComposableFunction scale(final double scaleFactor);
}
