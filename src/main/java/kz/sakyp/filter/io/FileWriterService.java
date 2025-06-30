package kz.sakyp.filter.io;

import java.io.*;
import java.util.List;

public class FileWriterService {

    private final String outputDir;
    private final String prefix;
    private final boolean append;

    public FileWriterService(String outputDir, String prefix, boolean append) {
        this.outputDir = outputDir;
        this.prefix = prefix;
        this.append = append;
    }

    public void write(String fileName, List<String> lines) {
        if (lines == null || lines.isEmpty()) return;

        File outFile = new File(outputDir, prefix + fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile, append))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Wrote to " + outFile.getPath());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + outFile.getPath());
            e.printStackTrace();
        }
    }
}
