package com.javaops.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApi {
    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce((x, y) -> x * 10 + y).getAsInt();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
        if (sum % 2 == 0) {
            return integers.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        } else
            return integers.stream().filter(i -> i % 2 != 0).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] values = {1, 1, 9, 3, 5, 4, 4, 1, 6};
        System.out.println(minValue(values));
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 1, 9, 3, 5, 4, 4, 1, 6));
        System.out.println(oddOrEven(list));
    }
}
