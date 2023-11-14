package com.mehmetyurekli.pathfinder.util;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReadUtility {

    public static int[][] ExcelToJaggedArray(String path) {
        Workbook wb = null;
        try {
            wb = new Workbook(path);
        } catch (Exception e) {
            System.out.println("Couldn't read the file.");
            e.printStackTrace();
        }
        assert wb != null;
        Worksheet worksheet = wb.getWorksheets().get(0);
        int size = worksheet.getCells().getMaxRow() - 1; //getMaxRow method doesn't count empty rows.
        // -1 is for avoiding non distance entries and getting the number of cities.
        int[][] tempJaggedArray = new int[size][];

        //row and col indexes are 2 to avoid getting non distance entries
        for (int row = 2; row < 83; row++) {
            int[] someRow = new int[size];

            for (int col = 2; col < 83; col++) {
                if (worksheet.getCells().get(row, col).getValue() instanceof Integer) {
                    someRow[col - 2] = (int) worksheet.getCells().get(row, col).getValue();
                } else {
                    someRow[col - 2] = 0; //same city
                }
            }
            tempJaggedArray[row - 2] = someRow;

        }
        return tempJaggedArray;
    }

    public static String[] TxtToArray(String path, int size) {
        Scanner file;
        String[] cityArray = new String[size];
        try {
            file = new Scanner(new FileInputStream(path));
            for (int i = 0; i < size; i++) {
                cityArray[i] = file.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't read the file.");
        }

        return cityArray;
    }
}
