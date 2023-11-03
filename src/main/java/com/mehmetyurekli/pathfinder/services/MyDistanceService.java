package com.mehmetyurekli.pathfinder.services;

import com.mehmetyurekli.pathfinder.util.FileReadUtility;

import java.util.Locale;
import java.util.Random;

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
        from = from.toUpperCase(Locale.forLanguageTag("tr-TR"));
        StringBuilder sb = new StringBuilder();
        //plateNum = index + 1
        int index = -1;
        for(int i = 0; i < cities.length; i++){
            if(cities[i].equals(from)){
                index = i;
            }
        }
        if (index < 0) {
            System.out.println("CITY NOT FOUND");
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

    public void findFurthestCities() {
        int zeroIndex = 0; //for skipping the filled up and already checked squares in the Excel file for efficiency. ex: distance of adana to adana
        int longestDistance = Integer.MIN_VALUE;
        String city1 = "ANKARA"; //by default
        String city2 = "ANKARA"; //by default
        for(int row = 0; row < jaggedArray.length; row++){
            for(int col = zeroIndex+1; col < jaggedArray[0].length; col++){
                if(jaggedArray[row][col] > longestDistance){
                    longestDistance = jaggedArray[row][col];
                    city1 = cities[row];
                    city2 = cities[col];
                }
            }
            zeroIndex++;
        }
        System.out.println(city1 + " - " + city2 + " " + longestDistance + "KM");
    }

    @Override
    public void findClosestCities() {
        int zeroIndex = 0; //for skipping the filled up and already checked squares in the Excel file for efficiency. ex: distance of adana to adana
        int shortestDistance = Integer.MAX_VALUE;
        String city1 = "İZMİR"; //by default
        String city2 = "İZMİR"; //by default
        for(int row = 0; row < jaggedArray.length; row++){
            for(int col = zeroIndex+1; col < jaggedArray[0].length; col++){
                if(jaggedArray[row][col] < shortestDistance){
                    shortestDistance = jaggedArray[row][col];
                    city1 = cities[row];
                    city2 = cities[col];
                }
            }
            zeroIndex++;
        }
        System.out.println(city1 + " - " + city2 + " " + shortestDistance + "KM");
    }

    @Override
    public void findDistanceBetween(String city1, String city2) {
        city1 = city1.toUpperCase(Locale.forLanguageTag("tr-TR"));
        city2 = city2.toUpperCase();
        if(city1.equals(city2)){
            System.out.println("ENTER TWO DIFFERENT CITIES");
            return;
        }
        int city1Index = -1;
        int city2Index = -1;
        for(int i = 0; i < cities.length; i++){
            if(cities[i].equals(city1)){
                city1Index = i;
            }
            if(cities[i].equals(city2)){
                city2Index = i;
            }
        }
        if(city1Index == -1 || city2Index == -1){
            System.out.println("CITY NOT FOUND");
            return;
        }
        System.out.println("DISTANCE BETWEEN CITIES " + city1 + " AND " + city2 + " IS " + jaggedArray[city1Index][city2Index] + "KM");
    }

    @Override
    public String getRandomCity() {
        int randNum = new Random().nextInt(0, cities.length);
        int i = 0;
        for(String word : cities){
            if(i == randNum){
                return cities[i];
            }
            i++;
        }
        System.out.println("error choosing random word.");
        return null;
    }
}
