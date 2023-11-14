package com.mehmetyurekli.pathfinder.services;

import com.mehmetyurekli.pathfinder.Route;

import java.util.HashMap;

public interface CityDistanceService {

    void load(String cityPath, String distancePath);

    HashMap<String, Integer> findCitiesInRangeOf(Integer from, int distance);

    int[] findFurthestCities();

    int[] findClosestCities();

    int findDistanceBetween(int code1, int code2);

    int getRandomCity();

    int getPlateOf(String city);

    String getNameOf(int code);

    void printRandomMatrix(int numOfCities);

    Route findRoute(Route currentRoute, int limit);

}
