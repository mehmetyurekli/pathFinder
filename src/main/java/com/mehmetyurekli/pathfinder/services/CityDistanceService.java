package com.mehmetyurekli.pathfinder.services;

import com.mehmetyurekli.pathfinder.util.FileReadUtility;

import java.util.ArrayList;
import java.util.HashMap;

public interface CityDistanceService {

    public void load(String cityPath, String distancePath);
    public HashMap<String, Integer> findCitiesInRangeOf(String from, int distance);
    public String findFurthestCities();
    public String findClosestCities();
    public int findDistanceBetween(String city1, String city2);
    public String getRandomCity();
    public String maxCitiesFrom(String start, int limit);

}
