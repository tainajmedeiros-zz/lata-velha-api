package com.br.latavelhaapi.controller;

import com.br.latavelhaapi.model.PriceRange;
import com.br.latavelhaapi.model.Vehicle;
import com.br.latavelhaapi.payload.Response;
import com.br.latavelhaapi.service.PriceRangeService;
import com.br.latavelhaapi.service.VehicleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin(origins = "http://localhost:3000")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private PriceRangeService priceRangeService;

    @ApiOperation(value = "Add a new Vehicle")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns the registered vehicle", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class), })
    @PostMapping
    public ResponseEntity<?> add(@RequestBody Vehicle vehicle) {
        try {
            vehicleService.add(vehicle);
            return new ResponseEntity(
                new Response(true, "Vehicle registred successfully"),
                HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity(
                new Response(false, "Bad request"),
                HttpStatus.BAD_REQUEST
            );
        }
    }

    private ResponseEntity<?> listByModel(String model) {
        try {
            return new ResponseEntity<List<Vehicle>>(
                vehicleService.listByModel(model), 
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(
                new Response(false, "Bad request"),
                HttpStatus.BAD_REQUEST
            );
        }
    }

    private ResponseEntity<?> listByBrand(String brand) {
        try {
            return new ResponseEntity<List<Vehicle>>(
                vehicleService.listByBrandName(brand), 
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(
                new Response(false, "Bad request"),
                HttpStatus.BAD_REQUEST
            );
        }
    }

    private ResponseEntity<?> listByPriceRangeID(Long priceRangeId) {
        try {
            PriceRange priceRange = priceRangeService.findById(priceRangeId);

            return new ResponseEntity<List<Vehicle>>(
                vehicleService.listByPriceRange(priceRange.getRangeStart(), priceRange.getRangeEnd()), 
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(
                new Response(false, "Bad request"),
                HttpStatus.BAD_REQUEST
            );
        }
    }

    @ApiOperation(value = "Finds a list of vehicles")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Returns the list of vehicles", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "Vehicle not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class), })
    @GetMapping
    public ResponseEntity<?> list(String brand, String model, Long priceRangeId) {
        try {
            if (model != null && !model.isEmpty()) {
                return this.listByModel(model);
            }
            
            if (brand != null && !brand.isEmpty()) {
                return this.listByBrand(brand);
            }

            if (priceRangeId != null) {
                return this.listByPriceRangeID(priceRangeId);
            }

            return new ResponseEntity<List<Vehicle>>(
                vehicleService.list(), 
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(
                new Response(false, "Bad request"), 
                HttpStatus.BAD_REQUEST
            );
        }
    }


    @ApiOperation(value = "Delete a vhicle")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the a vehicle deleted", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "Vehicle not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class), })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        try {
            Vehicle findVehicle = vehicleService.findById(id);
            if (findVehicle != null) {
                vehicleService.delete(id);
                return new ResponseEntity<Vehicle>(
                    findVehicle,
                    HttpStatus.OK
                );
            }
            return new ResponseEntity<>(
                new Response(false, "Not found vehicle with id:" + id), 
                HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity(
                new Response(false, "Bad request"), 
                HttpStatus.BAD_REQUEST
            );
        }

    }

    @ApiOperation(value = "Edit vehicle")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the vehicle who was modified", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "Vehicle not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class), })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Vehicle vehicle, @PathVariable("id") Long id) {
        try {
            Vehicle findVehicle = vehicleService.findById(id);
            if (findVehicle != null) {
                vehicle.setID(findVehicle.getID());
                vehicleService.update(vehicle);
                return new ResponseEntity<Vehicle>(
                    findVehicle,
                    HttpStatus.OK
                );
            }
            return new ResponseEntity<>(
                new Response(false, "Not found user with id: " + id), 
                HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity(
                new Response(false, "Bad request"), 
                HttpStatus.BAD_REQUEST
            );
        }
    }
}
