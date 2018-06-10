package com.github.maenolis.switchcase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class SwitchBuilder {

    private SwitchBuilder() {

    }

    public static <T, R> FlavouredSwitchBuilder<T, R> addCase(final Case<T, R> caze) {
        return new FlavouredSwitchBuilder<>(caze);
    }

    public static class FlavouredSwitchBuilder<T, R> {

        protected final List<Case<T, R>> cases;

        FlavouredSwitchBuilder(final List<Case<T, R>> cases) {
            this.cases = cases;
        }

        FlavouredSwitchBuilder(final Case<T, R> caze) {
            this.cases = new ArrayList<>();
            this.cases.add(caze);
        }

        public FlavouredSwitchBuilder<T, R> addCase(final Case<T, R> caze) {
            this.cases.add(caze);
            return this;
        }

        public FlavouredSwitchBuilder<T, R> erroneous() {
            return new FlavouredSwitchBuilderErroneous<>(cases);
        }

        public FlavouredSwitchBuilder<T, R> withDefault(final Function<T, R> defaultCase) {
            return new FlavouredSwitchBuilderWithDefault<>(cases, defaultCase);
        }

        public Switch<T, R> build() {
            return new Switch<>(cases);

        }

    }

    public static class FlavouredSwitchBuilderErroneous<T, R> extends FlavouredSwitchBuilder<T, R> {

        FlavouredSwitchBuilderErroneous(final List<Case<T, R>> cases) {
            super(cases);
        }

        @Override
        public Switch<T, R> build() {
            return new ErroneousSwitch<>(cases);
        }
    }

    public static class FlavouredSwitchBuilderWithDefault<T, R> extends FlavouredSwitchBuilder<T, R> {

        private final Function<T, R> defaultCase;

        FlavouredSwitchBuilderWithDefault(final List<Case<T, R>> cases, final Function<T, R> defaultCase) {
            super(cases);
            this.defaultCase = defaultCase;
        }

        @Override
        public Switch<T, R> build() {
            return new SwitchWithDefault<>(cases, defaultCase);
        }
    }

}


