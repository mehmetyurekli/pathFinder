package com.mehmetyurekli.pathfinder.services;

import com.mehmetyurekli.pathfinder.util.FileReadUtility;

public interface CityDistanceService {

    public void load(String cityPath, String distancePath);
    public void findCitiesInRangeOf(String from, int distance);
    public void findFurthestCities();
    public void findClosestCities();
    //public void getRandomCities();

}