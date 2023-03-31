package org.example;

public class Query {
    private double latitude;
    private double longitude;
    private int numStores;

    public Query(double latitude, double longitude, int numStores) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.numStores = numStores;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getNumStores() {
        return numStores;
    }
}
