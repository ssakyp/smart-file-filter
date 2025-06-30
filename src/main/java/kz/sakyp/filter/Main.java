package kz.sakyp.filter;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Smart File Filter ===");
        if (args.length == 0) {
            System.out.println("No arguments provided.");
            System.out.println("Use -h or --help for usage information.");
            return;
        }

        Options options = new Options();
        options.addOption("s", "short", false, "Show short statistics");
        options.addOption("f", "full", false, "Show full statistics");
        options.addOption("a", "append", false, "Append mode");
        options.addOption("o", "output", true, "Output directory");
        options.addOption("p", "prefix", true, "Output file name prefix");
        options.addOption("h", "help", false, "Show help messages");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                formatter.printHelp("java -jar smart-file-filter.jar [options] <input-files>", options);
                return;
            }

            boolean shortStats = cmd.hasOption("s");
            boolean fullStats = cmd.hasOption("f");
            boolean appendMode = cmd.hasOption("a");
            String outputDir = cmd.getOptionValue("o", ".");
            String prefix = cmd.getOptionValue("p","");

            String[] inputFiles = cmd.getArgs();

            System.out.println("Short stats: " + shortStats);
            System.out.println("Full stats: " + fullStats);
            System.out.println("Append mode: " + appendMode);
            System.out.println("Output dir: " + outputDir);
            System.out.println("Prefix: " + prefix);

            System.out.println("Input files: ");
            for (String file : inputFiles) {
                System.out.println("- " + file);
            }

            List<String> integerLines = new ArrayList<>();
            List<String> floatLines = new ArrayList<>();
            List<String> stringLines = new ArrayList<>();


            for (String filePath : inputFiles) {
                File file = new File(filePath);
                if (!file.exists()) {
                    System.out.println("File does not exist: " + filePath);
                    continue;
                }


                System.out.println(" Reading file: " + filePath);
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        line = line.trim();
                        if (line.isEmpty()) continue;

                        try {
                            Integer.parseInt(line);
                            integerLines.add(line);
                        } catch (NumberFormatException e1) {
                            try {
                                Double.parseDouble(line); // includes E-notation
                                floatLines.add(line);
                            } catch (NumberFormatException e2) {
                                stringLines.add(line);
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error reading file: " + filePath);
                    e.printStackTrace();
                }
            }


            String intPath = outputDir + File.separator + prefix + "integers.txt";
            String floatPath = outputDir + File.separator + prefix + "floats.txt";
            String stringPath = outputDir + File.separator + prefix + "strings.txt";


            writeToFile(integerLines, intPath, appendMode);
            writeToFile(floatLines, floatPath, appendMode);
            writeToFile(stringLines, stringPath, appendMode);


            if (shortStats) {
                System.out.println("\n Short statistics:");
                System.out.println("Integers: " + integerLines.size());
                System.out.println("Floats: " + floatLines.size());
                System.out.println("Strings: " + stringLines.size());
            }
            if (fullStats) {
                System.out.println("\n Full statistics:");


                List<Integer> intValues = new ArrayList<>();
                for (String s : integerLines) {
                    try {
                        intValues.add(Integer.parseInt(s));
                    } catch (NumberFormatException ignored) {}
                }
                if (!intValues.isEmpty()) {
                    int min = Collections.min(intValues);
                    int max = Collections.max(intValues);
                    int sum = intValues.stream().mapToInt(i -> i).sum();
                    double avg = sum / (double) intValues.size();
                    System.out.printf("Integers: %d | Min: %d | Max: %d | Sum: %d | Avg: %.2f%n",
                            intValues.size(), min, max, sum, avg);
                }


                List<Double> floatValues = new ArrayList<>();
                for (String s : floatLines) {
                    try {
                        floatValues.add(Double.parseDouble(s));
                    } catch (NumberFormatException ignored) {}
                }
                if (!floatValues.isEmpty()) {
                    double min = Collections.min(floatValues);
                    double max = Collections.max(floatValues);
                    double sum = floatValues.stream().mapToDouble(d -> d).sum();
                    double avg = sum / floatValues.size();
                    System.out.printf("Floats: %d | Min: %.5f | Max: %.5f | Sum: %.5f | Avg: %.5f%n",
                            floatValues.size(), min, max, sum, avg);
                }


                if (!stringLines.isEmpty()) {
                    int minLen = stringLines.stream().mapToInt(String::length).min().orElse(0);
                    int maxLen = stringLines.stream().mapToInt(String::length).max().orElse(0);
                    System.out.printf("Strings: %d | Shortest: %d chars | Longest: %d chars%n",
                            stringLines.size(), minLen, maxLen);
                }
            }

        } catch (ParseException e) {
            System.out.println("Error parsing command line arguments: " + e.getMessage());
            formatter.printHelp("java -jar smart-file-filter.jar [options] <input-files>", options);
        }

    }

    public static void writeToFile(List<String> lines, String filePath, boolean append) {
        if (lines.isEmpty()) return;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Wrote to " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }
}
