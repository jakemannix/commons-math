package org.apache.commons.math.analysis.purefunctions;

import org.apache.commons.math.analysis.UnivariateRealFunction;


public interface PureUnivariateRealFunction extends UnivariateRealFunction {

    PureMultivariateRealFunction asCollector();

    PureMultivariateRealFunction asCollector(PureBinaryRealFunction combiner);

    PureMultivariateRealFunction asCollector(final PureBinaryRealFunction combiner, final double initialValue);

    PureUnivariateRealFunction preCompose(final UnivariateRealFunction f);

    PureUnivariateRealFunction postCompose(final UnivariateRealFunction f);

    PureUnivariateRealFunction combine(final UnivariateRealFunction f, final PureBinaryRealFunction combiner);

    PureUnivariateRealFunction plus(UnivariateRealFunction f);

    PureUnivariateRealFunction minus(UnivariateRealFunction f);

    PureUnivariateRealFunction times(UnivariateRealFunction f);

    PureUnivariateRealFunction scale(final double scaleFactor);
}
