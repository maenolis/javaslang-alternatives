package com.github.maenolis.switchcase;

import com.github.maenolis.switchcase.exception.AlternativeSwitchCaseException;

import java.util.List;
import java.util.function.Function;

public final class Switch<T, R> implements Function<T, R> {

    private final boolean erroneous;
    private final List<Case<T, R>> cases;
    private final Case<T, R> defaultCase;

    public static <T, R> SwitchBuilder.FlavouredSwitchBuilder<T, R> addCase(final Case<T, R> caze) {
        return SwitchBuilder.addCase(caze);
    }

    Switch(List<Case<T, R>> cases, final boolean erroneous) {
        this.cases = cases;
        this.erroneous = erroneous;
        this.defaultCase = null;
    }

    Switch(List<Case<T, R>> cases, final Case<T, R> defaultCase) {
        this.cases = cases;
        this.erroneous = false;
        this.defaultCase = defaultCase;
    }

    @Override
    public R apply(final T value) {
        for (Case<T, R> current : cases) {
            if (current.test(value)) {
                return current.apply(value);
            }
        }
        checkErroneous();
        return checkDefault(value);
    }

    private R checkDefault(final T value) {
        if (defaultCase != null) {
            return defaultCase.apply(value);
        } else {
            return null;
        }
    }

    private void checkErroneous() {
        if (erroneous) {
            throw new AlternativeSwitchCaseException("No case matched.");
        }
    }
}
