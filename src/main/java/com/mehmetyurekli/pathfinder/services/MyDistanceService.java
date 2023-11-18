package com.mehmetyurekli.pathfinder.services;

import com.mehmetyurekli.pathfinder.models.Route;
import com.mehmetyurekli.pathfinder.util.FileReadUtility;

import java.util.*;

public class MyDistanceService implements CityDistanceService {

    private int[][] jaggedArray;
    private String[] cities;

    @Override
    public void load(String cityPath, String distancePath) {
        this.jaggedArray = FileReadUtility.ExcelToJaggedArray(distancePath);
        this.cities = FileReadUtility.TxtToArray(cityPath, 81);
    }

    @Override
    public HashMap<String, Integer> findCitiesInRangeOf(Integer from, int distance) {
        HashMap<String, Integer> citiesInRange = new HashMap<>();
        int[] searchRow = jaggedArray[from];
        for (int i = 0; i < searchRow.length; i++) {
            if (searchRow[i] < distance && searchRow[i] != 0) {
                citiesInRange.put(cities[i], searchRow[i]);
            }
        }
        return citiesInRange;
    }

    @Override
    public int[] findFurthestCities() {
        int zeroIndex = 0; //for skipping the filled up and already checked squares in the Excel file for efficiency. ex: distance of adana to adana
        int longestDistance = Integer.MIN_VALUE;
        int city1 = 0; //by default
        int city2 = 0; //by default
        for (int row = 0; row < jaggedArray.length; row++) {
            for (int col = zeroIndex + 1; col < jaggedArray[0].length; col++) {
                if (jaggedArray[row][col] > longestDistance) {
                    longestDistance = jaggedArray[row][col];
                    city1 = row;
                    city2 = col;
                }
            }
            zeroIndex++;
        }
        int[] out = new int[3];
        out[0] = city1;
        out[1] = city2;
        out[2] = longestDistance;
        return out;
    }

    @Override
    public int[] findClosestCities() {
        int zeroIndex = 0; //for skipping the filled up and already checked squares in the Excel file for efficiency. ex: distance of adana to adana
        int shortestDistance = Integer.MAX_VALUE;
        int city1 = 0; //by default
        int city2 = 0; //by default
        for (int row = 0; row < jaggedArray.length; row++) {
            for (int col = zeroIndex + 1; col < jaggedArray[0].length; col++) {
                if (jaggedArray[row][col] < shortestDistance) {
                    shortestDistance = jaggedArray[row][col];
                    city1 = row;
                    city2 = col;
                }
            }
            zeroIndex++;
        }
        int[] out = new int[3];
        out[0] = city1;
        out[1] = city2;
        out[2] = shortestDistance;
        return out;
    }

    @Override
    public int findDistanceBetween(int code1, int code2) {
        if (code1 == code2) {
            System.out.println("ENTER TWO DIFFERENT CITIES");
            return -1;
        }
        return jaggedArray[code1][code2];
    }

    @Override
    public int getRandomCity() {
        int randNum = new Random().nextInt(0, cities.length);

        for (int i = 0; i < cities.length; i++) {
            if (i == randNum) {
                return randNum;
            }
        }
        System.out.println("Error choosing a random word.");
        return -1;
    }

    @Override
    public int getPlateOf(String city) {
        city = city.toUpperCase(Locale.forLanguageTag("tr-TR"));
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equals(city)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String getNameOf(int code) {
        return cities[code];
    }

    @Override
    public void printRandomMatrix(int numOfCities) {
        int[] cities = new int[numOfCities];
        ArrayList<Integer> chosen = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            int randomCity = getRandomCity();
            if (chosen.contains(randomCity)) {
                i--;
                continue;
            }
            cities[i] = randomCity;
            chosen.add(randomCity);
        }

        int[][] matrix = new int[numOfCities][numOfCities];

        for (int row = 0; row < numOfCities; row++) {
            for (int col = 0; col < numOfCities; col++) {
                if (row == col) {
                    matrix[row][col] = 0;
                    continue;
                }
                matrix[row][col] = findDistanceBetween(cities[row], cities[col]);
            }
        }

        System.out.printf("%15s", "CITIES");
        for (int i = 0; i < numOfCities; i++) {
            System.out.printf("%15s", this.cities[cities[i]]);
        }
        System.out.println();
        System.out.println();
        for (int i = 0; i < numOfCities; i++) {
            System.out.printf("%15s", this.cities[cities[i]]);
            for (int j = 0; j < numOfCities; j++) {
                System.out.printf("%15s", matrix[i][j]);
            }
            System.out.println();
            System.out.println();
        }
    }

    @Override
    public Route findRoute(Route currentRoute, int limit) {
        Route result = currentRoute;
        for (int i = 0; i < jaggedArray.length; i++) {
            if (currentRoute.getCities().contains(i)) {
                //already visited.
                continue;
            }
            int distanceToCity = 0;
            if (!currentRoute.getCities().isEmpty()) {
                //the if check is not necessary in this problem. but if we run the program without a starting city,
                //this check is necessary to find the best route in a given range.
                Integer from = currentRoute.getCities().get(currentRoute.getCities().size() - 1);
                distanceToCity += findDistanceBetween(from, i);
            }
            if (distanceToCity + currentRoute.getDistance() > limit) {
                continue;
            }
            Route clone = currentRoute.copy();
            clone.addCity(i, distanceToCity);
            Route subRoute = findRoute(clone, limit);
            result = Route.pickBetter(result, subRoute);
        }
        return result;
    }
}
