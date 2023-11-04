package com.mehmetyurekli.pathfinder;

import com.mehmetyurekli.pathfinder.services.MyDistanceService;

public class Main {
    public static void main(String[] args) {

        MyDistanceService service = new MyDistanceService();
        service.load("cities.txt", "ilmesafe.xlsx");
        System.out.println(service.findCitiesInRangeOf("ERZURUM", 450));
        System.out.println(service.findFurthestCities());
        System.out.println(service.findClosestCities());
        System.out.println(service.findDistanceBetween("izmir", "ANKARA"));


    }
}