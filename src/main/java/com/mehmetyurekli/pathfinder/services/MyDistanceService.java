package com.mehmetyurekli.pathfinder.services;

import com.mehmetyurekli.pathfinder.util.FileReadUtility;

public class MyDistanceService implements CityDistanceService {

    private int[][] jaggedArray;
    private String[] cities;

    @Override
    public void load(String cityPath, String distancePath) {
        this.jaggedArray = FileReadUtility.ExcelToJaggedArray(distancePath);
        this.cities = FileReadUtility.TxtToArray(cityPath, 81);
    }

    @Override
    public void findCitiesInRangeOf(String from, int distance) {
        StringBuilder sb = new StringBuilder();
        //plateNum = index + 1
        int index = -1;
        for(int i = 0; i < cities.length; i++){
            if(cities[i].equals(from)){
                index = i;
            }
        }
        if (index < 0) {
            System.out.println("city not found");
            return;
        }

        int[] searchRow = jaggedArray[index];

        for(int i = 0; i < searchRow.length; i++){
            if(searchRow[i] < distance && searchRow[i] != 0){
                sb.append(cities[i]).append("-").append(searchRow[i]).append("KM | ");
            }
        }
        System.out.println("DISTANCES BELOW " + distance + "KM FROM CITY " + from);
        System.out.println(sb);
    }
}
