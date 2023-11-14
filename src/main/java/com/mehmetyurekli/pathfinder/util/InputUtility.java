package com.mehmetyurekli.pathfinder.util;

import com.mehmetyurekli.pathfinder.services.CityDistanceService;

import java.util.Scanner;

public class InputUtility {

    public static int getCode(CityDistanceService service) {
        Scanner scanner = new Scanner(System.in);
        int code;
        boolean correctInput = false;
        do {
            System.out.print("Enter starting city (plate or name): ");
            String city;
            city = scanner.nextLine();
            try {
                code = Integer.parseInt(city);
                if (code < 1 || code > 81) {
                    System.out.println("UNKNOWN CITY.");
                } else {
                    correctInput = true;
                    code -= 1; //to get the index of the plate
                }
            } catch (Exception ex) {
                code = service.getPlateOf(city);
                if (code < 0 || code > 80) {
                    System.out.println("UNKNOWN CITY.");
                    continue;
                }
                correctInput = true;
            }
        }
        while (!correctInput);
        return code;
    }

    public static int getNumber(int start, int end) {
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        boolean correctInput = false;
        do {
            if (end == Integer.MAX_VALUE) {
                System.out.printf("Enter an integer bigger than %d: ", start);
            } else {
                System.out.printf("Enter an integer between %d-%d: ", start, end);
            }
            try {
                number = scanner.nextInt();
                if (number < start || number > end) {
                    continue;
                }
                correctInput = true;
            } catch (Exception ex) {
                System.out.println("The input type must be an integer between the limits!");
                scanner.nextLine();
            }
        }
        while (!correctInput);

        return number;
    }
}
