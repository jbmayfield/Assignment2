package org.example;

public class Store implements Comparable<Store> {

    private int id;
    private String address;
    private double latitude;
    private double longitude;
    private double distance;

    public Store(int id, String address, double latitude, double longitude) {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = Double.POSITIVE_INFINITY;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Store other) {
        // Compare the distances of the two stores.
        return Double.compare(this.distance, other.distance);
    }

    @Override
    public String toString() {
        // Return a string representation of the store.
        return String.format("%d %s (%.3f,%.3f) %.3f miles", id, address, latitude, longitude, distance);
    }

    public double distanceTo(double latitude, double longitude) {
        // Calculate the distance between the store and the given point.
        final double R = 6371e3; // radius of the earth in meters
        double lat1 = Math.toRadians(this.latitude);
        double lat2 = Math.toRadians(latitude);
        double deltaLat = Math.toRadians(latitude - this.latitude);
        double deltaLon = Math.toRadians(longitude - this.longitude);

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = R * c;
        return distance;
    }

    public Object getName() {
        String name = this.getClass().getName();
        return name;
    }
}