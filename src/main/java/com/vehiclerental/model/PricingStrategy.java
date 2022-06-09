package com.vehiclerental.model;

public class PricingStrategy {
    private final int surgeThreshold;
    private final int priceSurge;

    public PricingStrategy(int surgeThreshold, int priceSurge) {
        this.surgeThreshold = surgeThreshold;
        this.priceSurge = priceSurge;
    }

    public int getDynamicPrice(int price, int bookedVehiclesPercentage) {
        if (bookedVehiclesPercentage >= surgeThreshold) {
            price = ((100 + priceSurge) * price) / 100;
        }
        return price;
    }
}
