package com.github.maenolis.switchcase;

import com.github.maenolis.switchcase.exception.AlternativeSwitchCaseException;

import java.util.List;
import java.util.function.Function;

public class Switch<T, R> implements Function<T, R> {

    protected final List<Case<T, R>> cases;

    public static <T, R> SwitchBuilder.FlavouredSwitchBuilder<T, R> addCase(final Case<T, R> caze) {
        return SwitchBuilder.addCase(caze);
    }

    Switch(List<Case<T, R>> cases) {
        this.cases = cases;
    }

    @Override
    public R apply(final T value) {
        for (Case<T, R> current : cases) {
            if (current.test(value)) {
                return current.apply(value);
            }
        }
        return null;
    }

//    private R checkDefault(final T value) {
//        if (defaultCase != null) {
//            return defaultCase.apply(value);
//        } else {
//            return null;
//        }
//    }
}
