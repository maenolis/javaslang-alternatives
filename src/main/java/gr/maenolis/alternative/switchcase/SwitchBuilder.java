package gr.maenolis.alternative.switchcase;

import gr.maenolis.alternative.switchcase.Case;
import gr.maenolis.alternative.switchcase.Switch;

import java.util.ArrayList;
import java.util.List;

class SwitchBuilder {

    private SwitchBuilder() {

    }

    public static <T, R> FlavouredSwitchBuilder<T, R> addCase(final Case<T, R> caze) {
        return new FlavouredSwitchBuilder<>(caze);
    }

    public static class FlavouredSwitchBuilder<T, R> {

        private final List<Case<T, R>> cases;
        private boolean erroneous;
        private Case<T, R> defaultCase;

        FlavouredSwitchBuilder(final Case<T, R> caze) {
            this.cases = new ArrayList<>();
            this.cases.add(caze);
        }

        public FlavouredSwitchBuilder<T, R> addCase(final Case<T, R> caze) {
            this.cases.add(caze);
            this.erroneous = false;
            return this;
        }

        public FlavouredSwitchBuilder<T, R> erroneous() {
            this.erroneous = true;
            return this;
        }

        public FlavouredSwitchBuilder<T, R> defaultCase(final Case<T, R> defaultCase) {
            this.defaultCase = defaultCase;
            return this;
        }

        public Switch<T, R> build() {
            if (erroneous) {
                return new Switch<>(cases, erroneous);
            } else {
                return new Switch<>(cases, defaultCase);
            }

        }

    }

}


