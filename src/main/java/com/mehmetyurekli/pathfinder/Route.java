package com.mehmetyurekli.pathfinder;

import java.util.ArrayList;

public class Route {

    private ArrayList<Integer> cities;
    private int distance;


    public Route(ArrayList<Integer> cities, int distance) {
        this.cities = cities;
        this.distance = distance;
    }

    public static Route pickBetter(Route r1, Route r2) {
        if (r1 == null || r2 == null) {
            return r1 == null ? r2 : r1;
        }
        if (r1.getCities().size() < r2.getCities().size()) {
            return r2;
        } else if (r1.getCities().size() > r2.getCities().size()) {
            return r1;
        } else {
            // equal city count look for route with shorter distance
            return r1.getDistance() < r2.getDistance() ? r1 : r2;
        }
    }

    public Route copy() {
        ArrayList<Integer> cities = new ArrayList<>();
        for (Integer city : this.getCities()
        ) {
            cities.add(city);
        }


        return new Route(cities, this.getDistance());
    }

    public void addCity(int code, int distance) {
        cities.add(code);
        this.distance += distance;
    }

    public ArrayList<Integer> getCities() {
        return cities;
    }

    public int getDistance() {
        return distance;
    }

}
