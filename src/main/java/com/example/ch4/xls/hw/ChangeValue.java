package com.example.ch4.xls.hw;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChangeValue {
    static class InventoryData {
        String name;
        int amount;
        int price;

        public InventoryData(String name, int amount, int price) {
            this.name = name;
            this.amount = amount;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getAmount(int newAmount) {
            return amount;
        }

        @Override
        public String toString() {
            return name + "," + amount + "," + price;
        }
    }

    // 수정.
    public static List<InventoryData> readExcel(String filePath) {
        List<InventoryData> inventoryData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)){
            Sheet sheet = workbook.getSheetAt(0);
            boolean isFirstRow = true;

            for (Row row : sheet) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }
                String name = row.getCell(0).getStringCellValue();
                int amount = (int) row.getCell(1).getNumericCellValue();
                int price = (int) row.getCell(2).getNumericCellValue();
                inventoryData.add(new InventoryData(name, amount, price));
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return inventoryData;
    }

    // 수정.
    public static void writeExcel(String filePath, List<InventoryData> inventoryData, boolean includeHeader) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(filePath)) {
            Sheet sheet = workbook.createSheet();
            int rowIndex = 0;

            if (includeHeader) {
                Row headerRow = sheet.createRow(rowIndex++);
                headerRow.createCell(0).setCellValue("제품명");
                headerRow.createCell(1).setCellValue("수량");
                headerRow.createCell(2).setCellValue("가격");
            }

            for (InventoryData inv : inventoryData) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(inv.name);
                row.createCell(1).setCellValue(inv.amount);
                row.createCell(2).setCellValue(inv.price);
            }

            workbook.write(fos);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static List<InventoryData> changeAmount(List<InventoryData> inventoryData, String productName, int newAmount) {
        for (InventoryData inv : inventoryData) {
            if (inv.getName().equals(productName)) {
                inv.getAmount(newAmount);
                break;
            }
        }
        return inventoryData;
    }

    public static String getResourceFilePath(String fileName) {
        ClassLoader classLoader = ChangeValue.class.getClassLoader();
        if (classLoader.getResource(fileName) != null) {
            return classLoader.getResource(fileName).getPath();
        }
        return null;
    }

    // 수정.
    public static void main(String[] args) {
        String inputFilePath = getResourceFilePath("inventory.xlsx");
        String outputFilePath = getResourceFilePath("change_amount.xlsx");

        if (inputFilePath == null || outputFilePath == null) {
            System.out.println("파일 경로를 찾을 수 없습니다.");
            return;
        }

        List<InventoryData> inventoryData = readExcel(inputFilePath);

        String productNameToUpdate = "제품1";
        inventoryData = changeAmount(inventoryData, productNameToUpdate, 100);

        writeExcel(outputFilePath, inventoryData, true);
        inventoryData.forEach(System.out::println);
    }
}
