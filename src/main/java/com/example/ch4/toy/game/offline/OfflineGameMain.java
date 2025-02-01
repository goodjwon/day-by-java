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
        File playerDateFile = new File(playerDataPath);
        // Task 1.1 없을 경우만 캐릭터 생성.
        if(!playerDateFile.exists()){
            createPlayerData(playerDataPath);
        }

        //Task 1.1 유저데이터 로드.
        try {
            FileManager.readFile(playerDataPath);
        } catch (IOException e) {
            e.printStackTrace();    //todo 사용자 예외처리 하기.
        }

        //Task 2.1 멥데이터 로드.

        //Task 3.1 게임로직 실행

        //Task 3.1 던전에 입장. => 몬스처 처치 => 보스 처리.

        //Task 3.3 게임 종료 및 데이터 저장.

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
    private final String dataPath;
    private final String logFilePath;

    public GameManager(String dataPath, String logFilePath){
        this.dataPath = dataPath;
        this.logFilePath = logFilePath;
    }

    // Task 3.1: 던전 진입 이벤트 처리
    public void enterDungeon(String dungeonName){

        logActivity("Entered dungeon: " + dungeonName); // Task 3.1 던전 입장.
    }

    // Task 3.2: 몬스터 처치 이벤트 처리 => 몬스터 이름, 경험치, 골드, 아이템
    public void defeatMonster(String monsterName, int exp, int gold){
        //todo 작성하기 => 2.7
    }
    // Task 3.2: 보스 처치 이벤트 처리
    public void defeatBoss(String bossName, int exp, int gold, String loot){
        //todo 작성하기 => 2.7
    }

    // Task 1.2, Task 3.2: 활동 로그 작성
    private void logActivity(String activity){
        //[2024-01-01 10:15:43]
        String logEntry = String.format("[%tF %<tT] %s\n", System.currentTimeMillis(), activity); // Task 1.2: 활동 시간 기록
        try {
            FileManager.writeFile(logFilePath, logEntry);
        } catch (IOException e) {
            e.printStackTrace();    // todo 사용자 예외 전환
        }
    }

    // Task 3.3: 게임 종료 및 로그 백업
    public void saveAndExit() {

    }
}
// (유저(), 몬스터(), 보스()), 전투진행(), 로그파일(), 게임종료()