package com.csv;

import com.csv.enums.DataType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataTypConverter {

    private List<Map<String, Object>> listOfLines;

    public DataTypConverter() throws IOException {
        FileList fileList = new FileList();
        listOfLines = new ArrayList<>();
        fileList.getFileList().forEach(file -> {
            List<List<String>> columns = extractColumns(file);
            List<DataType> columnTypes = columns.stream()
                    .map(this::resolveDataType).collect(Collectors.toList());
            List<String> lines = removeHeader(readLines(file));
            lines.forEach(line-> {
                listOfLines.add(new HashMap<>());
                String[] fields = line.split(",");
                IntStream.range(0,columnTypes.size()).forEach(i -> listOfLines.get(listOfLines.size()-1).put(fields[i],columnTypes.get(i)));
            });
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
        DataType dataType = DataType.INTEGER;
        for (String row : column) {
            if (!row.matches("[0-9]+")) {
                dataType = DataType.DOUBLE;
                if (!row.matches("(\\d+\\.\\d+)")){
                    dataType = DataType.BOOL;
                    if (!row.matches("^(false|true)$")){
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

    public List<Map<String, Object>> getListOfLines() {
        return listOfLines;
    }

}
