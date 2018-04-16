package com.github.maenolis.switchcase;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Cases {


    public static <T> Case<T, Void> ofConsumable(final Predicate<T> predicate, final Consumer<T> consumer) {
        return new ConsumableCase<>(predicate, consumer);
    }

    public static <T, R> Case<T, R> ofFunctional(final Predicate<T> predicate, final Function<T, R> function) {
        return new FunctionalCase<>(predicate, function);
    }

    public static final class ConsumableCase<T> implements Case<T, Void> {
        final Predicate<T> predicate;
        final Consumer<T> consumer;

        private ConsumableCase(final Predicate<T> predicate, final Consumer<T> consumer) {
            this.predicate = predicate;
            this.consumer = consumer;
        }

        @Override
        public boolean test(final T input) {
            return predicate.test(input);
        }

        @Override
        public Void apply(final T input) {
            consumer.accept(input);
            return null;
        }
    }

    public static final class FunctionalCase<T, R> implements Case<T,R> {
        final Predicate<T> predicate;
        final Function<T, ? extends R> function;

        private FunctionalCase(final Predicate<T> predicate, final Function<T, ? extends R> function) {
            this.predicate = predicate;
            this.function = function;
        }

        @Override
        public boolean test(final T input) {
            return predicate.test(input);
        }

        @Override
        public R apply(final T input) {
            return function.apply(input);
        }
    }

}
