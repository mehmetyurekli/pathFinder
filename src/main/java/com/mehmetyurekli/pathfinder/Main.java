package com.mehmetyurekli.pathfinder;

import com.mehmetyurekli.pathfinder.util.ExcelReadUtility;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try {
            int[][] test = ExcelReadUtility.ExcelToJaggedArray("ilmeasafe.xlsx");
            System.out.println(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}