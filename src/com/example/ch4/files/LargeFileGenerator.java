package com.example.ch4.files;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.IntStream;

public class LargeFileGenerator {
    public static void main(String[] args) {
        Path largeFile = Paths.get("largeFile1.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(largeFile, StandardOpenOption.TRUNCATE_EXISTING)){
            IntStream.range(0, 1000_000_000).forEach(i -> {
                try {
                    writer.write("This is line number " + i);
                    writer.newLine();
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
            System.out.println("대용량 파일 생성 완료: " + largeFile.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
