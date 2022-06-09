package com.vehiclerental.model;

import java.util.Comparator;
import java.util.Map;

public class BranchStrategy {
    private final Map<String, PricingStrategy> pricingStrategy;
    private final Comparator<Vehicle> leaseStrategy;

    public BranchStrategy(Map<String, PricingStrategy> dynamicPricingStrategy, Comparator<Vehicle> leaseStrategy) {
        this.pricingStrategy = dynamicPricingStrategy;
        this.leaseStrategy = leaseStrategy;
    }

    public Comparator<Vehicle> getLeaseStrategy() {
        return leaseStrategy;
    }

    public int getDynamicPriceIfExists(String vehicleType, int price, int bookedVehiclesPercentage) {
        if (pricingStrategy.containsKey(vehicleType)) {
            return pricingStrategy.get(vehicleType).getDynamicPrice(price, bookedVehiclesPercentage);
        }

        return price;
    }
}
