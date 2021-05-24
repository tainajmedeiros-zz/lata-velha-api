package com.br.latavelhaapi.controller;

import java.util.List;

import com.br.latavelhaapi.model.PriceRange;
import com.br.latavelhaapi.payload.Response;
import com.br.latavelhaapi.service.PriceRangeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/priceRanges")
@CrossOrigin(origins = "http://localhost:3000")
public class PriceRangeController {

  @Autowired
  private PriceRangeService priceRangeService;

  @ApiOperation(value = "Finds a list price ranges")
  @ApiResponses(value = { 
      @ApiResponse(code = 200, message = "Returns the list price ranges", response = Response.class),
      @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
      @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class), 
  })
  @GetMapping
  public ResponseEntity<?> list() {
    try {
      return new ResponseEntity<List<PriceRange>>(
          priceRangeService.list(), 
          HttpStatus.OK
      );
    } catch (Exception e) {
      return new ResponseEntity<>(
          new Response(false, "Bad request"), 
          HttpStatus.BAD_REQUEST
      );
    }
  }

}
