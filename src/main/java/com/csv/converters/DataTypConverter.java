package com.csv.converters;

import com.csv.enums.DataType;
import com.csv.file.logic.Field;
import com.csv.file.logic.CSVFile;
import com.csv.file.logic.FileList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataTypConverter {

    private List<CSVFile> listOfFiles;

    public DataTypConverter() throws IOException {
        FileList fileList = new FileList();
        listOfFiles = new ArrayList<>();
        fileList.getFileList().forEach(file -> {
            CSVFile CSVFile = new CSVFile(file.getFileName().toString());
            List<String> columnNames = getFirstLine(file);
            List<List<String>> columns = extractColumns(file);
            List<DataType> columnTypes = columns.stream()
                    .map(this::resolveDataType).collect(Collectors.toList());
            List<String> lines = removeHeader(readLines(file));
            lines.forEach(line -> {
                CSVFile.addRow();
                String[] fields = line.split(",");
                IntStream.range(0, columnTypes.size()).forEach(i -> CSVFile.getRow(CSVFile.getData().size() - 1).addField(new Field(fields[i], columnTypes.get(i), columnNames.get(i))));
            });
            listOfFiles.add(CSVFile);
            changeFileName(file.toFile());
        });
        System.out.println();
    }


    private List<List<String>> extractColumns(Path file) {
        List<String> lines = removeHeader(readLines(file));
        List<List<String>> columns = new ArrayList<>();
        int numberOfColumns = lines.get(0).split(",").length;
        List<DataType> columnTypes = new ArrayList<>();
        IntStream.range(0, numberOfColumns).forEach(colNum -> {
            columns.add(new ArrayList<>());
            IntStream.range(0, lines.size()).forEach(lineNum -> {
                columns.get(colNum).add(
                        Arrays.stream(lines.get(lineNum).split(",")).collect(Collectors.toList()).get(colNum)
                );
            });
        });
        return columns;
    }

    private DataType resolveDataType(List<String> column) {
        DataType dataType = DataType.LONG;
        for (String row : column) {
            if (!row.matches("[0-9]+")) {
                dataType = DataType.DOUBLE;
                if (!row.matches("(\\d+\\.\\d+)")) {
                    dataType = DataType.BOOL;
                    if (!row.matches("^(false|true)$")) {
                        dataType = DataType.STRING;
                    }
                }
            }
        }
        return dataType;
    }

    private List<String> removeHeader(List<String> rowList) {
        rowList.remove(0);
        return rowList;
    }

    private List<String> readLines(Path path) {
        try {
            return Files.lines(path).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<String> getFirstLine(Path path) {
        try {
            return new ArrayList<>(Arrays.asList(Files.lines(path).findFirst().get().split(",")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void changeFileName(File oldFile) {
        File newFile = new File(oldFile.getParent(), oldFile.getName() + ".added");
        try {
            Files.move(oldFile.toPath(), newFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<CSVFile> getListOfFiles() {
        return listOfFiles;
    }

}
