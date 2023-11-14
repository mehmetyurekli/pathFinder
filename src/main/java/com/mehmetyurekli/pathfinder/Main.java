package com.mehmetyurekli.pathfinder;

import com.mehmetyurekli.pathfinder.services.CityDistanceService;
import com.mehmetyurekli.pathfinder.services.MyDistanceService;
import com.mehmetyurekli.pathfinder.util.InputUtility;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        CityDistanceService service = new MyDistanceService();
        service.load("cities.txt", "ilmesafe.xlsx");

        int menuInput = 0;
        while (menuInput != 5) {
            System.out.println("""     
                                                
                    1)Find visitable cities in a range from the given city.
                    2)Find closest and furthest cities in Turkey.
                    3)Find the best route from a given city in a range to visit max number of cities.
                    4)Get random cities and the distances between them in a matrix.
                    5)Exit.
                    """);

            System.out.println("SELECT AN OPERATION");
            menuInput = InputUtility.getNumber(1, 5);
            System.out.println();

            switch (menuInput) {
                case 1 -> {
                    int from = InputUtility.getCode(service);
                    System.out.println("ENTER RANGE (KM)");
                    int range = InputUtility.getNumber(0, Integer.MAX_VALUE);
                    System.out.println("Visitable cities from " + service.getNameOf(from) + " in " + range + "KMS are: ");
                    System.out.println(service.findCitiesInRangeOf(from, range));
                }
                case 2 -> {
                    int[] closest = service.findClosestCities();
                    System.out.println("Closest cities are: " + service.getNameOf(closest[0]) + " - " + service.getNameOf(closest[1]));
                    System.out.println("Distance between them: " + closest[2] + "KM");
                    int[] furthest = service.findFurthestCities();
                    System.out.println("Closest cities are: " + service.getNameOf(furthest[0]) + " - " + service.getNameOf(furthest[1]));
                    System.out.println("Distance between them: " + furthest[2] + "KM");
                }
                case 3 -> {
                    int startCity = InputUtility.getCode(service);
                    System.out.println("ENTER LIMIT (KM)");
                    int limit = InputUtility.getNumber(0, Integer.MAX_VALUE);
                    ArrayList<Integer> arr = new ArrayList<>();
                    arr.add(startCity);
                    long startTime = System.nanoTime();
                    Route result = service.findRoute(new Route(arr, 0), limit);
                    long endTime = System.nanoTime();
                    System.out.println("Runtime: " + (endTime-startTime) + " nanoseconds.");
                    if (result != null) {

                        System.out.println("Total distance traveled: " + result.getDistance() + ", Total cities traveled: " + result.getCities().size());
                        System.out.print("ROUTE: ");
                        for (Integer city : result.getCities()) {
                            System.out.printf("%5s - ", service.getNameOf(city));
                        }
                        System.out.println();
                    } else {
                        System.out.println("Result is null");
                    }
                }
                case 4 -> {
                    System.out.println("ENTER NUMBER OF CITIES");
                    int cities = InputUtility.getNumber(1, 81);
                    service.printRandomMatrix(cities);
                }
            }
        }
    }
}