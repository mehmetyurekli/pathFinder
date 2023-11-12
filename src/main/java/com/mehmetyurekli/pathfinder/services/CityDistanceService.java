package com.mehmetyurekli.pathfinder.services;

import com.mehmetyurekli.pathfinder.Route;
import com.mehmetyurekli.pathfinder.util.FileReadUtility;

import java.util.ArrayList;
import java.util.HashMap;

public interface CityDistanceService {

    public void load(String cityPath, String distancePath);
    public HashMap<String, Integer> findCitiesInRangeOf(Integer from, int distance);
    public int[] findFurthestCities();
    public int[] findClosestCities();
    public int findDistanceBetween(int code1, int code2);
    public int getRandomCity();
    public int getPlateOf(String city);
    public String getNameOf(int code);
    public void printRandomMatrix(int numOfCities);
    public Route findRoute(Route route, int maxDistance, int currentIndex);

}
