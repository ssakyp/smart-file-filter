package kz.sakyp.filter.stats;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticsService {

    private final List<String> intRaw;
    private final List<String> floatRaw;
    private final List<String> stringRaw;

    public StatisticsService(List<String> integers, List<String> floats, List<String> strings) {
        this.intRaw = integers;
        this.floatRaw = floats;
        this.stringRaw = strings;
    }

    public void print(boolean shortStats, boolean fullStats) {
        if (shortStats) {
            System.out.println("\n Short statistics:");
            System.out.println("Integers: " + intRaw.size());
            System.out.println("Floats: " + floatRaw.size());
            System.out.println("Strings: " + stringRaw.size());
        }

        if (fullStats) {
            System.out.println("\n Full statistics:");

            // Integers
            List<Integer> intValues = intRaw.stream()
                    .map(s -> {
                        try { return Integer.parseInt(s); }
                        catch (NumberFormatException e) { return null; }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            if (!intValues.isEmpty()) {
                int min = Collections.min(intValues);
                int max = Collections.max(intValues);
                int sum = intValues.stream().mapToInt(i -> i).sum();
                double avg = sum / (double) intValues.size();

                System.out.printf("Integers: %d | Min: %d | Max: %d | Sum: %d | Avg: %.2f%n",
                        intValues.size(), min, max, sum, avg);
            }

            // Floats
            List<Double> floatValues = floatRaw.stream()
                    .map(s -> {
                        try { return Double.parseDouble(s); }
                        catch (NumberFormatException e) { return null; }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            if (!floatValues.isEmpty()) {
                double min = Collections.min(floatValues);
                double max = Collections.max(floatValues);
                double sum = floatValues.stream().mapToDouble(d -> d).sum();
                double avg = sum / floatValues.size();

                System.out.printf("Floats: %d | Min: %.5f | Max: %.5f | Sum: %.5f | Avg: %.5f%n",
                        floatValues.size(), min, max, sum, avg);
            }

            // Strings
            if (!stringRaw.isEmpty()) {
                int minLen = stringRaw.stream().mapToInt(String::length).min().orElse(0);
                int maxLen = stringRaw.stream().mapToInt(String::length).max().orElse(0);
                System.out.printf("Strings: %d | Shortest: %d chars | Longest: %d chars%n",
                        stringRaw.size(), minLen, maxLen);
            }
        }
    }
}
