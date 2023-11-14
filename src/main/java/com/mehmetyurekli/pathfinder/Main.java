package com.mehmetyurekli.pathfinder;

import com.mehmetyurekli.pathfinder.services.CityDistanceService;
import com.mehmetyurekli.pathfinder.services.MyDistanceService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CityDistanceService service = new MyDistanceService();
        service.load("cities.txt", "ilmesafe.xlsx");

        int menuInput = 0;
        while (menuInput != 5) {
            boolean correctInput = false;
            do {
                try {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("""     
                            1)Find visitable cities in a range from the given city.
                            2)Find closest and furthest cities in Turkey.
                            3)todo
                            4)Get random cities and the distances between them in a matrix.
                            5)Exit.""");

                    System.out.print("Please select an operation: ");
                    menuInput = sc.nextInt();
                    if (menuInput > 0 && menuInput < 8) {
                        correctInput = true;
                        continue;
                    }
                    System.out.println("Please enter an integer between 1-5!");
                } catch (Exception ex) {
                    System.out.println("Please enter an integer between 1-5!");
                }
            }
            while (!correctInput); //GETS CORRECT MENU INPUT

            correctInput = false;
            Scanner scanner = new Scanner(System.in);

            switch(menuInput){
                case 1:
                    int code;
                    int range = 0;
                    do{
                        System.out.print("Enter starting city(plate or name): ");
                        String city;
                        city = scanner.nextLine();
                        try{
                            code = Integer.parseInt(city);
                            if(code < 1 || code > 81){
                                System.out.println("UNKNOWN CITY.");
                                continue;
                            }
                            else{
                                correctInput = true;
                            }
                        }
                        catch (Exception ex){
                            code = service.getPlateOf(city);
                            if(code < 0 || code > 80){
                                System.out.println("UNKNOWN CITY.");
                                continue;
                            }
                            correctInput = true;
                        }
                    }
                    while (!correctInput);
                    code -=1;
                    correctInput = false;

                    do{
                        System.out.print("Enter range: ");
                        try{
                            range = scanner.nextInt();
                            if(range < 0) {
                                System.out.println("Enter a positive integer!");
                                continue;
                            }
                            correctInput = true;
                        }
                        catch (Exception ex){
                            System.out.println("Enter an integer!");
                            scanner.nextLine();
                        }
                    }
                    while(!correctInput);
                    System.out.println("Visitable cities from " + service.getNameOf(code) + " in " + range + "KMS are: ");
                    System.out.println(service.findCitiesInRangeOf(code, range));
                    continue;

                case 2:
                    int[] closest = service.findClosestCities();
                    System.out.println("Closest cities are: " + service.getNameOf(closest[0]) + " - " + service.getNameOf(closest[1]));
                    System.out.println("Distance between them: " + closest[2] + "KM");

                    int[] furthest = service.findFurthestCities();
                    System.out.println("Closest cities are: " + service.getNameOf(furthest[0]) + " - " + service.getNameOf(furthest[1]));
                    System.out.println("Distance between them: " + furthest[2] + "KM");
                    continue;

                case 3:
                    System.out.println("todo");
                    continue;

                case 4:
                    int cities = 0;
                    correctInput = false;
                    do{
                        System.out.print("Enter num of cities to be chosen: ");
                        try{
                            cities = scanner.nextInt();
                            if(cities < 1 || cities > 81){
                                System.out.println("Enter an integer between 1-81!");
                                continue;
                            }
                            correctInput = true;
                        }
                        catch (Exception e){
                            System.out.println("You must enter an integer between 1-81!");
                            scanner.nextLine();
                        }
                    }
                    while(!correctInput);
                    service.printRandomMatrix(cities);
                    continue;
            }


        }
    }
}