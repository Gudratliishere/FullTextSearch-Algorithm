package com.gudratli.fts.util;

import java.io.*;

public class XMLCleaner {
    public static void removeTag(String filePath, String newFilePath, String tag) {
        System.out.printf("Starting clean xml with tags <%s></%s>\n", tag, tag);
        int readLineCount = 0, writeLineCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(newFilePath))) {
            String line = br.readLine();
            while (line != null) {
                if (!line.startsWith("<" + tag) && !line.startsWith("</" + tag + ">")) {
                    bw.write(line + "\n");
                    writeLineCount++;
                }
                line = br.readLine();
                System.out.printf("%s lines are read and %s lines are written.\r", ++readLineCount, writeLineCount);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        System.out.println();
        System.out.printf("Totally %s lines are read and %s lines are written. %s lines are removed. XML now is clean.",
                readLineCount, writeLineCount, readLineCount - writeLineCount);
    }
}
