package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class StoreLocator {
    private List<Store> stores;
    private List<Query> queries;
    public StoreLocator() {
        stores = new ArrayList<>();
        queries = new ArrayList<>();
    }
    public void readStoreData(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                double lat = Double.parseDouble(fields[2]);
                double lon = Double.parseDouble(fields[3]);
                Store store = new Store(id, name, lat, lon);
                stores.add(store);
            }
        }
    }
    public void readQueryData(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                double lat = Double.parseDouble(fields[0]);
                double lon = Double.parseDouble(fields[1]);
                int numStores = Integer.parseInt(fields[2]);
                Query query = new Query(lat, lon, numStores);
                queries.add(query);
            }
        }
    }
    public void findClosestStores() {
        for (Query query : queries) {
            for (Store store : stores) {
                double distance = store.distanceTo(query.getLatitude(), query.getLongitude());
                store.setDistance(distance);
            }
            stores.sort(Comparator.comparingDouble(Store::getDistance));
            int k = query.getNumStores();
            if (k > stores.size()) {
                k = stores.size();
            }
            List<Store> closeStores = stores.subList(0, k);
            System.out.printf("Closest %d stores to (%.3f,%.3f):\n", query.getNumStores(), query.getLatitude(), query.getLongitude());
            for (Store store : closeStores) {
                System.out.printf("- %s\n", store.getName());
            }
            System.out.println();
            for (Store store : stores) {
                store.setDistance(Double.POSITIVE_INFINITY);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        StoreLocator locator = new StoreLocator();
        locator.readStoreData("WhataburgerData.csv");
        locator.readStoreData("StarbucksData.csv");
        locator.readQueryData("Queries.csv");
        locator.findClosestStores();
    }

}