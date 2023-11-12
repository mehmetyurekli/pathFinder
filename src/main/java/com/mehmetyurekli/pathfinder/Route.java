package com.mehmetyurekli.pathfinder;

import java.util.ArrayList;

public class Route {

    private ArrayList<Integer> cities;
    private int distance = 0;


    public Route(ArrayList<Integer> cities, int distance){
        this.cities = cities;
        this.distance = distance;
    }

    public Route(int firstElement) {
        ArrayList<Integer> startList = new ArrayList<Integer>();
        startList.add(firstElement);
        this.cities = startList;
        this.distance = 0;
    }

    public void addCity(int code, int distance){
        cities.add(code);
        this.distance += distance;
    }

    public void addCity(int code){
        if(cities.size() == 0){
            cities.add(code);
        }
    }

    public ArrayList<Integer> getCities() {
        return cities;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
