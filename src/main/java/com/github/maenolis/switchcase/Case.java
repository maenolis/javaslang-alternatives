package com.github.maenolis.switchcase;

public interface Case<T, R> {

    boolean test(final T input);

    R apply(final T input);
}
