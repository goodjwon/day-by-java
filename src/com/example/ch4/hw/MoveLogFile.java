package com.example.ch4.hw;

import java.io.IOException;
import java.nio.file.*;

public class MoveLogFile {

    public static void main(String[] args) {
        Path source = Paths.get("C:\\example\\aa");
        Path target = Paths.get("C:\\example\\bb");

        try {
            moveLogFiles(source, target);
        } catch (IOException e) {
            System.err.println("파일 이동 중 오류 발생: " + e.getMessage());
        }
    }

    public static void moveLogFiles(Path source, Path target) throws IOException {
        if (Files.notExists(target)) {
            Files.createDirectories(target);
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(source, "*.log")) {
            for (Path file : stream) {
                Path targetFile = target.resolve(file.getFileName());
                Files.move(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}

