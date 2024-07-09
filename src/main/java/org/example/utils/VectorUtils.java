package org.example.utils;

import java.util.stream.IntStream;

public class VectorUtils {
    private VectorUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static double[] add(double[] arr1, double[] arr2) {
        return IntStream.range(0, arr1.length)
                .mapToDouble(i -> arr1[i] + arr2[i])
                .toArray();
    }
}
