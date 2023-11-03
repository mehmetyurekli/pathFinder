package com.mehmetyurekli.pathfinder;

import com.mehmetyurekli.pathfinder.util.FileReadUtility;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int[][] test = FileReadUtility.ExcelToJaggedArray("ilmesafe.xlsx");
        System.out.println(test);
        String[] cities = FileReadUtility.TxtToArray("cities.txt", 81);
        System.out.println();
    }
}