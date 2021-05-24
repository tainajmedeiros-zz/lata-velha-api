package com.br.latavelhaapi.repository;

import com.br.latavelhaapi.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByID(long id);

    List<Vehicle> findByModel(String model);

    List<Vehicle> findByBrandID(long id);

	List<Vehicle> findByBrandName(String brand);

    @Query(value = "SELECT * FROM vehicles WHERE price BETWEEN :range_start AND :range_end", nativeQuery = true)
    List<Vehicle> findByRangeStartBetweenRangeEnd(@Param("range_start") double rangeStart,@Param("range_end") double rangeEnd);
}
