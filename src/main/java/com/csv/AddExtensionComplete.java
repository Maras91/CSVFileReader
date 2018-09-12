package com.csv;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class AddExtensionComplete {


    private static void changeNameFileToComplete(ArrayList<File> files) {
        files.forEach(oldFile -> {
            File newFile = new File(oldFile.getParent(), oldFile.getName() + ".complete");
            try {
                Files.move(oldFile.toPath(), newFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
