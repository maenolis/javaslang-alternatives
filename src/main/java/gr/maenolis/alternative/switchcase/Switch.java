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
        return new Switch<>(value, false);
    }

    public static <T> Switch<T> caseOfErroneous(final T value) {
        return new Switch<>(value, true);
    }

    public <R> R test(final Case<T, R>... cases) {
        for (Case<T, R> current : cases) {
            if (current.test(value)) {
                return current.apply(value);
            }
        }
        checkErroneous();
        return null;
    }

    private void checkErroneous() {
        if (erroneous) {
            throw new AlternativeSwitchCaseException("No case matched.");
        }
    }
}
