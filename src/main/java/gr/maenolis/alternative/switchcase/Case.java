package gr.maenolis.alternative.switchcase;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Case<T> {


    public static <T> ConsumableCase<T> ofConsumable(final Predicate<T> predicate, final Consumer<T> consumer) {
        return new ConsumableCase(predicate, consumer);
    }

    public static <T, R> FunctionalCase<T, R> ofFunctional(final Predicate<T> predicate, final Function<T, R> function) {
        return new FunctionalCase(predicate, function);
    }

    public static final class ConsumableCase<T> {
        final Predicate<T> predicate;
        final Consumer<T> consumer;

        private ConsumableCase(final Predicate<T> predicate, final Consumer<T> consumer) {
            this.predicate = predicate;
            this.consumer = consumer;
        }

        public Predicate<T> getPredicate() {
            return predicate;
        }

        public Consumer<T> getConsumer() {
            return consumer;
        }
    }

    public static final class FunctionalCase<T, R> {
        final Predicate<T> predicate;
        final Function<T, ? extends R> function;

        private FunctionalCase(final Predicate<T> predicate, final Function<T, ? extends R> function) {
            this.predicate = predicate;
            this.function = function;
        }

        public Predicate<T> getPredicate() {
            return predicate;
        }

        public Function<T, ? extends R> getFunction() {
            return function;
        }
    }
}
