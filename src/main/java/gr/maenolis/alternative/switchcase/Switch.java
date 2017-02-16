package gr.maenolis.alternative.switchcase;

import gr.maenolis.alternative.exception.AlternativeSwitchCaseException;

public final class Switch<T> {

    private final T value;
    private final boolean erroneous;

    private Switch(final T value, final boolean erroneous) {
        this.value = value;
        this.erroneous = erroneous;
    }

    public static <T> Switch<T> caseOf(final T value) {
        return new Switch(value, false);
    }

    public static <T> Switch<T> caseOfErroneous(final T value) {
        return new Switch(value, true);
    }

    public void test(final Case.ConsumableCase<T>... cases) {
        for (Case.ConsumableCase<T> current : cases) {
            if (current.getPredicate().test(value)) {
                current.getConsumer().accept(value);
                return;
            }
        }
        if (erroneous) {
            throw new AlternativeSwitchCaseException("No case matched.");
        }
    }

    public <R> R get(final Case.FunctionalCase<T, R>... cases) {
        for (Case.FunctionalCase<T, R> current : cases) {
            if (current.getPredicate().test(value)) {
                return current.getFunction().apply(value);

            }
        }
        if (erroneous) {
            throw new AlternativeSwitchCaseException("No case matched.");
        } else {
            return null;
        }
    }
}
