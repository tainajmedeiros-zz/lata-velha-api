package com.br.latavelhaapi.controller;

import com.br.latavelhaapi.model.Brand;
import com.br.latavelhaapi.model.DTO.DashboardResponse;
import com.br.latavelhaapi.model.Vehicle;
import com.br.latavelhaapi.payload.Response;
import com.br.latavelhaapi.service.BrandService;
import com.br.latavelhaapi.service.VehicleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class DashboardController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private BrandService brandService;

    @ApiOperation(value = "Finds a dashboard")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Returns dashboard", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "Dashboard not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class), })
    @GetMapping
    public ResponseEntity<?> list() {
        try {
            List<Brand> brands = brandService.findAll();
            List<Vehicle> vehicles= vehicleService.findAll();
            List<DashboardResponse> dashboards = new ArrayList<>();
            int numberOfVehicles = 0;
            double total = 0;

            for(Brand brand: brands){
                for(Vehicle vehicle: vehicles) {
                    if (vehicle.getBrand().getID().equals(brand.getID())) {
                        numberOfVehicles++;
                        total += vehicle.getPrice();
                    }
                }
                dashboards.add(new DashboardResponse(brand.getName(),numberOfVehicles,total));
                numberOfVehicles = 0;
                total = 0;
            }
            return new ResponseEntity<>(
                    dashboards,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(
                    new Response(false, "Bad request"),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
