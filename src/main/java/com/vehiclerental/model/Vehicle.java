package com.vehiclerental.model;

public class Vehicle {
    private final String id;
    private final Integer price;
    private final boolean[] bookedSlots;

    public Vehicle(String id, int price) {
        this.id = id;
        this.price = price;
        this.bookedSlots = new boolean[24];
    }

    public String getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public boolean isAvailable(int startTime, int endTime) {
        for (int i = startTime; i < endTime ; i++) {
            if (bookedSlots[i]) {
                return false;
            }
        }

        return true;
    }

    public int book(int startTime, int endTime) {
        int cost = 0;

        for (int i = startTime; i < endTime ; i++) {
            bookedSlots[i] = true;
            cost += price;
        }

        return cost;
    }
}
