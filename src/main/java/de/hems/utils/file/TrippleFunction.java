package de.hems.utils.file;

@FunctionalInterface
public interface TrippleFunction<A, B, C, R> {
    R apply(A a, B b, C c);
}
