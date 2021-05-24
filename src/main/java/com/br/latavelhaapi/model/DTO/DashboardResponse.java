package com.br.latavelhaapi.model.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DashboardResponse {

    @NotNull
    @NotEmpty
    private String brand;

    @NotNull
    @NotEmpty
    private int numberOfVehicles;

    @NotNull
    @NotEmpty
    private double total;

    public DashboardResponse(String brand, int numberOfVehicles, double total) {
        this.brand = brand;
        this.numberOfVehicles = numberOfVehicles;
        this.total = total;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(int numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double amount) {
        this.total = amount;
    }
}
