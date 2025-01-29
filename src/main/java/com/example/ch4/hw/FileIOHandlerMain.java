package com.example.ch4.hw;

import java.util.ArrayList;
import java.util.List;

import static com.example.ch4.xls.ExcelProcessor.getResourceFilePath;

public class FileIOHandlerMain {
    public static void main(String[] args) {
        //Person 리스트 준비
        List<Person> people = new ArrayList<>();
        people.add(new Person("홍길동", 30));
        people.add(new Person("김영희", 25));
        people.add(new Person("박철수", 35));

        //1) 텍스트 파일 쓰기/읽기
        String textFile = getResourceFilePath("people.txt");
        FileIOHandler.writeTextFile(textFile, people);
        List<Person> textRead = FileIOHandler.readTextFile(textFile);
        System.out.println("[Text 파일 읽기 결과]");
        for (Person p : textRead) {
            System.out.println(p.getName() + " / " + p.getAge());
        }

        //2) CSV 파일 쓰기/읽기
        String csvFile = getResourceFilePath("people.csv");
        FileIOHandler.writeCSV(csvFile, people);
        List<Person> csvRead = FileIOHandler.readCSV(csvFile);
        System.out.println("\n[CSV 파일 읽기 결과]");
        for (Person p : csvRead) {
            System.out.println(p.getName() + " / " + p.getAge());
        }

        //3) 이진(직렬화) 파일 쓰기/읽기
        String binFile = getResourceFilePath("people.dat");
        FileIOHandler.writeBinary(binFile, people);
        List<Person> binRead = FileIOHandler.readBinary(binFile);
        System.out.println("\n[Binary 파일 읽기 결과]");
        for (Person p : binRead) {
            System.out.println(p.getName() + " / " + p.getAge());
        }

        //4) JSON 파일 쓰기/읽기
        String jsonFile = getResourceFilePath("people.json");
        FileIOHandler.writeJson(jsonFile, people);
        List<Person> jsonRead = FileIOHandler.readJson(jsonFile);
        System.out.println("\n[JSON 파일 읽기 결과]");
        if (jsonRead != null) {
            for (Person p : jsonRead) {
                System.out.println(p.getName() + " / " + p.getAge());
            }
        }

        //5) XML 파일 쓰기/읽기
        String xmlFile = getResourceFilePath("people.xml");
        FileIOHandler.writeXml(xmlFile, people);
        List<Person> xmlRead = FileIOHandler.readXml(xmlFile);
        System.out.println("\n[XML 파일 읽기 결과]");
        for (Person p : xmlRead) {
            System.out.println(p.getName() + " / " + p.getAge());
        }

        //6) YAML 파일 쓰기/읽기
        String yamlFile = getResourceFilePath("people.yaml");
        FileIOHandler.writeYaml(yamlFile, people);
        List<Person> yamlRead = FileIOHandler.readYaml(yamlFile);
        System.out.println("\n[YAML 파일 읽기 결과]");
        for (Person p : yamlRead) {
            System.out.println(p.getName() + " / " + p.getAge());
        }

        //7) Properties 파일 쓰기/읽기
        String propFile = getResourceFilePath("people.properties");
        FileIOHandler.writeProperties(propFile, people);
        List<Person> propRead = FileIOHandler.readProperties(propFile);
        System.out.println("\n[Properties 파일 읽기 결과]");
        for (Person p : propRead) {
            System.out.println(p.getName() + " / " + p.getAge());
        }

        //8) HTML 파일 쓰기/읽기
        String htmlFile = getResourceFilePath("people.html");
        FileIOHandler.writeHtml(htmlFile, people);
        List<Person> htmlRead = FileIOHandler.readHtml(htmlFile);
        System.out.println("\n[HTML 파일 읽기 결과]");
        for (Person p : htmlRead) {
            System.out.println(p.getName() + " / " + p.getAge());
        }

        //9) Markdowm 파일 쓰기/읽기
        String mdFile = getResourceFilePath("people.md");
        FileIOHandler.writeMarkdown(mdFile, people);
        List<Person> mdRead = FileIOHandler.readMarkdown(mdFile);
        System.out.println("\n[Markdown 파일 읽기 결과]");
        for (Person p : mdRead) {
            System.out.println(p.getName() + " / " + p.getAge());
        }

        //10) ZIP 파일 쓰기/읽기
        String zipFile = getResourceFilePath("people.zip");
        FileIOHandler.writeZip(zipFile, people);
        List<Person> zipRead = FileIOHandler.readZip(zipFile);
        System.out.println("\n[ZIP 파일 읽기 결과]");
        for (Person p : zipRead) {
            System.out.println(p.getName() + " / " + p.getAge());
        }
    }
}
