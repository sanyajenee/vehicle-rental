package com.vehiclerental.model;

import java.util.*;

public class Branch {
    private final String name;
    private final Map<String, TreeSet<Vehicle>> stock;
    private final BranchStrategy branchStrategy;

    public Branch(String name, BranchStrategy branchStrategy) {
        this.name = name;
        this.stock = new HashMap<>();
        this.branchStrategy = branchStrategy;
    }

    public String getName() {
        return name;
    }

    public void onboardVehicleTypes(List<String> vehicleTypes) {
        for (String vehicleType: vehicleTypes) {
            stock.computeIfAbsent(vehicleType, x -> new TreeSet<>(branchStrategy.getLeaseStrategy()));
        }
    }

    public boolean vehicleTypeIsOnboarded(String vehicleType) {
        return stock.containsKey(vehicleType);
    }

    public boolean vehicleIdIsOnboarded(String vehicleType, String vehicleId) {
        for (Vehicle vehicle: stock.get(vehicleType)) {
            if (vehicle.getId().equals(vehicleId)) {
                return true;
            }
        }

        return false;
    }

    public void addVehicle(String vehicleType, String id, Integer price) {
        Vehicle newVehicle = new Vehicle(id, price);
        stock.get(vehicleType).add(newVehicle);
    }

    public int bookVehicle(String vehicleType, int startTime, int endTime) {
        if (!stock.containsKey(vehicleType)) {
            return -2;
        }

        int price = -1;
        int bookedVehicles = 0;

        for (Vehicle vehicle: stock.get(vehicleType)) {
            if (vehicle.isAvailable(startTime, endTime)) {
                price = vehicle.book(startTime, endTime);
                break;
            }

            bookedVehicles++;
        }

        if (price != -1) {
            int totalVehicles = stock.get(vehicleType).size();
            int bookedVehiclesPercentage = (bookedVehicles * 100) / totalVehicles;

            price = branchStrategy.getDynamicPriceIfExists(vehicleType, price, bookedVehiclesPercentage);
        }

        return price;
    }

    public Set<String> listAvailableVehicles(int startTime, int endTime) {
        Set<String> availableVehicles = new HashSet<>();

        for (TreeSet<Vehicle> vehicles: stock.values()) {
            for (Vehicle vehicle: vehicles) {
                if (vehicle.isAvailable(startTime, endTime)) {
                    availableVehicles.add(vehicle.getId());
                }
            }
        }

        return availableVehicles;
    }
}
