package com.example.ch4.toy.game.offline;


import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;


public class OfflineGameMain {
    public static void main(String[] args) {
        System.out.println("Welcome to My Offline Game!");

        // Task 1.1: 데이터 경로 설정 (유저 데이터와 로그 저장용)
        String dataPath = System.getProperty("user.dir") + "/game_data";
        String playerDataPath = dataPath + "/player_data.txt";


        // Task 1.1: 디렉터리 생성
        FileManager.createDirectory(dataPath);

        // Task 1.1: 유저 데이터 확인 및 생성
        createPlayerData(playerDataPath);

        System.out.println("Game is under development. Stay tuned!");
    }

    // Task 1.1: 새 유저 데이터 생성
    public static void createPlayerData(String filePath) {
        try(Scanner scanner = new Scanner(System.in)){
            System.out.print("Enter your player name: ");
            String playerName = scanner.nextLine();

            String content = String.format(
                    "PlayerName: %s\nLevel: 1\nExperience: 0\nInventory: None\nGold: 0\n",
                    playerName
            );

            FileManager.writeFile(filePath, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

// Task 1.1, Task 1.3: 파일 및 디렉터리 관리 유틸리티
class FileManager {
    public static void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
            System.out.println("Directory created: " + path);
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }

    public static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static void writeFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes());
        System.out.println("File written: " + filePath);
    }

    public static void moveFile(String srcFile, String destFile) throws IOException {
        Files.move(Paths.get(srcFile), Paths.get(destFile));
        System.out.println("File move " + srcFile + "to " + destFile);
    }

}

// Task 2.1: 맵 데이터 로드 및 출력
class MapLoader {
    public void loadMapData(InputStream mapFileStream) {
        try (InputStreamReader reader = new InputStreamReader(mapFileStream)) {
            Gson gson = new Gson();
            Map<String, Object> mapData = gson.fromJson(reader, Map.class); // Task 2.1: JSON 데이터 파싱
            System.out.println("Map Data Loaded: " + mapData);
        } catch (IOException e) {
            System.out.println("Error loading map data: " + e.getMessage());
        }
    }
}



// Task 3.1, Task 3.2, Task 3.3: 게임 로직 처리
// Task 3.1, Task 3.2, Task 3.3: 게임 로직 처리
class GameManager {
    // Task 3.1: 생성자에서 게임 데이터 및 로그 파일 경로 설정

    // Task 3.1: 던전 진입 이벤트 처리

    // Task 3.2: 몬스터 처치 이벤트 처리

    // Task 3.2: 보스 처치 이벤트 처리

    // Task 1.2, Task 3.2: 활동 로그 작성

    // Task 3.3: 게임 종료 및 로그 백업

}
// (유저(), 몬스터(), 보스()), 전투진행(), 로그파일(), 게임종료()