package com.github.maenolis.switchcase;

import com.github.maenolis.switchcase.exception.AlternativeSwitchCaseException;

import java.util.List;

public class ErroneousSwitch<T ,R> extends Switch<T, R> {

    ErroneousSwitch(List<Case<T, R>> cases) {
        super(cases);
    }

    @Override
    public R apply(T value) {
        final R result = super.apply(value);
        if (result == null) {
            throw new AlternativeSwitchCaseException("No case matched.");
        } else {
            return result;
        }
    }
}
