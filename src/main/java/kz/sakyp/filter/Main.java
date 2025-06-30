package kz.sakyp.filter;

import kz.sakyp.filter.cli.CommandLineHandler;
import kz.sakyp.filter.core.FileFilterService;

public class Main {
    public static void main(String[] args) {
        CommandLineHandler cmdHandler = new CommandLineHandler();
        var config = cmdHandler.parse(args);

        if (config == null) return;

        FileFilterService service = new FileFilterService(config);
        service.run();
    }
}