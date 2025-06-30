package kz.sakyp.filter.core;

import kz.sakyp.filter.cli.CommandLineHandler.Config;
import kz.sakyp.filter.io.FileWriterService;
import kz.sakyp.filter.stats.StatisticsService;

import java.io.*;
import java.util.*;

public class FileFilterService {

    private final Config config;
    private final List<String> integerLines = new ArrayList<>();
    private final List<String> floatLines = new ArrayList<>();
    private final List<String> stringLines = new ArrayList<>();

    public FileFilterService(Config config) {
        this.config = config;
    }

    public void run() {
        System.out.println("Short stats: " + config.shortStats);
        System.out.println("Full stats: " + config.fullStats);
        System.out.println("Append mode: " + config.appendMode);
        System.out.println("Output dir: " + config.outputDir);
        System.out.println("Prefix: " + config.prefix);
        System.out.println("Input files:");
        config.inputFiles.forEach(f -> System.out.println("- " + f));

        readFiles();

        writeResults();

        if (config.shortStats || config.fullStats) {
            new StatisticsService(integerLines, floatLines, stringLines)
                    .print(config.shortStats, config.fullStats);
        }
    }

    private void readFiles() {
        for (String path : config.inputFiles) {
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("File does not exist: " + path);
                continue;
            }

            System.out.println("Reading file: " + path);

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;

                    if (isInteger(line)) {
                        integerLines.add(line);
                    } else if (isFloat(line)) {
                        floatLines.add(line);
                    } else {
                        stringLines.add(line);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading: " + path);
                e.printStackTrace();
            }
        }
    }

    private void writeResults() {
        FileWriterService writer = new FileWriterService(config.outputDir, config.prefix, config.appendMode);
        writer.write("integers.txt", integerLines);
        writer.write("floats.txt", floatLines);
        writer.write("strings.txt", stringLines);
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isFloat(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
