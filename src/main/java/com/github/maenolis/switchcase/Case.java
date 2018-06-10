package com.github.maenolis.switchcase;

import java.util.function.Function;

public interface Case<T, R> extends Function<T, R> {

    boolean test(final T input);
}
