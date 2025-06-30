package kz.sakyp.filter.cli;

import org.apache.commons.cli.*;

import java.util.List;

public class CommandLineHandler {
    public static class Config {
        public boolean shortStats;
        public boolean fullStats;
        public boolean appendMode;
        public String outputDir;
        public String prefix;
        public List<String> inputFiles;
    }

    public Config parse(String[] args) {
        Options options = new Options();
        options.addOption("s", false, "Show short statistics");
        options.addOption("f", false, "Show full statistics");
        options.addOption("a", false, "Append mode");
        options.addOption("o", true, "Output directory");
        options.addOption("p", true, "File prefix");
        options.addOption("h", false, "Help");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h") || cmd.getArgs().length == 0) {
                formatter.printHelp("smart-file-filter", options);
                return null;
            }

            Config config = new Config();
            config.shortStats = cmd.hasOption("s");
            config.fullStats = cmd.hasOption("f");
            config.appendMode = cmd.hasOption("a");
            config.outputDir = cmd.getOptionValue("o", ".");
            config.prefix = cmd.getOptionValue("p", "");
            config.inputFiles = List.of(cmd.getArgs());
            return config;
        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
            formatter.printHelp("smart-file-filter", options);
            return null;
        }
    }
}
