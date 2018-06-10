package com.github.maenolis.switchcase;

import java.util.List;
import java.util.function.Function;

public class SwitchWithDefault<T, R> extends Switch<T, R> {

    private final Function<T, R> defaultCase;

    SwitchWithDefault(List<Case<T, R>> cases, final Function<T, R> defaultCase) {
        super(cases);
        this.defaultCase = defaultCase;
    }

    @Override
    public R apply(T value) {
        final R result = super.apply(value);
        if (result == null) {
            return defaultCase.apply(value);
        } else {
            return result;
        }
    }
}
