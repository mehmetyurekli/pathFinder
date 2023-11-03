package com.mehmetyurekli.pathfinder;

import com.mehmetyurekli.pathfinder.services.MyDistanceService;
import com.mehmetyurekli.pathfinder.util.FileReadUtility;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int[][] test = FileReadUtility.ExcelToJaggedArray("ilmesafe.xlsx");
        System.out.println(test);
        String[] cities = FileReadUtility.TxtToArray("cities.txt", 81);
        System.out.println();
        System.out.println(test[0]);
        System.out.println();

        MyDistanceService service = new MyDistanceService();
        service.load("cities.txt", "ilmesafe.xlsx");
        service.findCitiesInRangeOf("İZMİR", 200);
        service.findFurthestCities();
        service.findClosestCities();
        service.findDistanceBetween("izmir", "ANKARA");
        System.out.println("random cities");
        System.out.println(service.getRandomCity());
        System.out.println(service.getRandomCity());
        System.out.println(service.getRandomCity());
        System.out.println(service.getRandomCity());
        System.out.println(service.getRandomCity());

    }
}