package com.vehiclerental.service;

import com.vehiclerental.model.Branch;

import java.util.HashMap;
import java.util.Map;

public class VehicleRentalService {
    private final Map<String, Branch> branches;

    public VehicleRentalService() {
        this.branches = new HashMap<>();
    }

    public boolean branchExists(String branchName) {
        return branches.containsKey(branchName);
    }

    public void addBranch(Branch branch) {
        branches.put(branch.getName(), branch);
    }

    public Branch getBranch(String branchName) {
        return branches.get(branchName);
    }
}
