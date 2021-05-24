package com.br.latavelhaapi.service;

import com.br.latavelhaapi.model.Brand;
import com.br.latavelhaapi.model.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class VehicleServiceTest {

    @Autowired
    VehicleService vehicleServiceTest;

    @Autowired
    BrandService brandServiceTest;

    private Vehicle createVehicle(String model, Brand brand, double price, String year) {
        Vehicle vehicle = new Vehicle();
        vehicle.setModel(model);
        vehicle.setBrand(brand);
        vehicle.setPrice(price);
        vehicle.setYear(year);

        return vehicle;
    }

    private Brand createBrand(String name) {
        Brand brand = new Brand();
        brand.setName(name);
        return brand;
    }

    @Test
    public void saveVehicle() {
        Brand brandSave = brandServiceTest.add(createBrand("Fiat"));
        Vehicle vehicleSave = vehicleServiceTest.add(
                createVehicle("Uno", brandSave, 1000, "2021")
        );

        assertThat(vehicleSave).isNotNull();
    }

    @Test
    public void listVehicles(){
        Brand brandSaveOne = brandServiceTest.add(createBrand("Fiat"));
        Brand brandSaveTwo = brandServiceTest.add(createBrand("BMW"));

        Vehicle vehicleSaveOne = vehicleServiceTest.add(
                createVehicle("Uno", brandSaveOne, 1000, "2021")
        );
        Vehicle vehicleSavetwo = vehicleServiceTest.add(
                createVehicle("Argo", brandSaveOne, 2000, "2019")
        );
        Vehicle vehicleSaveTree = vehicleServiceTest.add(
                createVehicle("Z4", brandSaveTwo, 3000, "2018")
        );

        List<Vehicle> findAll = vehicleServiceTest.list();

        assertThat(findAll.get(0).getID()).isEqualTo(vehicleSaveOne.getID());
        assertThat(findAll.get(1).getID()).isEqualTo(vehicleSavetwo.getID());
        assertThat(findAll.get(2).getID()).isEqualTo(vehicleSaveTree.getID());
    }

    @Test
    public void deleteVehicleSuccess(){
        Brand brandSaveOne = brandServiceTest.add(createBrand("Fiat"));
        Brand brandSaveTwo = brandServiceTest.add(createBrand("BMW"));

        Vehicle vehicleSaveOne = vehicleServiceTest.add(
                createVehicle("Uno", brandSaveOne, 1000, "2021")
        );
        Vehicle vehicleSavetwo = vehicleServiceTest.add(
                createVehicle("Argo", brandSaveOne, 2000, "2019")
        );
        Vehicle vehicleSaveTree = vehicleServiceTest.add(
                createVehicle("Z4", brandSaveTwo, 3000, "2018")
        );

        List<Vehicle> findAllBefore = vehicleServiceTest.list();
        assertThat(findAllBefore.size()).isEqualTo(3);

        vehicleServiceTest.delete(vehicleSaveOne.getID());

        List<Vehicle> findAllAfter = vehicleServiceTest.list();
        assertThat(findAllAfter.size()).isEqualTo(2);
    }

    @Test
    public void deleteVehicleFail(){
        Brand brandSaveOne = brandServiceTest.add(createBrand("Fiat"));
        Brand brandSaveTwo = brandServiceTest.add(createBrand("BMW"));

        vehicleServiceTest.add(
                createVehicle("Uno", brandSaveOne, 1000, "2021")
        );
        vehicleServiceTest.add(
                createVehicle("Argo", brandSaveOne, 2000, "2019")
        );
        vehicleServiceTest.add(
                createVehicle("Z4", brandSaveTwo, 3000, "2018")
        );

        List<Vehicle> findAllBefore = vehicleServiceTest.list();
        assertThat(findAllBefore.size()).isEqualTo(3);

        vehicleServiceTest.delete(10);

        List<Vehicle> findAllAfter = vehicleServiceTest.list();
        assertThat(findAllAfter.size()).isEqualTo(3);
    }


    @Test
    public void updateVehicle(){
        Brand brandSave = brandServiceTest.add(createBrand("Fiat"));
        Vehicle vehicleSave = vehicleServiceTest.add(
                createVehicle("Uno", brandSave, 1000, "2021")
        );

        vehicleSave.setModel("Argo");
        vehicleSave.setYear("2015");
        Vehicle newVehicle = vehicleServiceTest.update(vehicleSave);

        assertThat(vehicleSave.getModel()).isEqualTo(newVehicle.getModel());
        assertThat(vehicleSave.getYear()).isEqualTo(newVehicle.getYear());
    }


    @Test
    public void getVehicleByID() {
        Brand brandSave = brandServiceTest.add(createBrand("Fiat"));
        Vehicle vehicleSave = vehicleServiceTest.add(
                createVehicle("Uno", brandSave, 1000, "2021")
        );

        Vehicle found = vehicleServiceTest.findById(vehicleSave.getID());

        assertThat(found.getModel())
                .isEqualTo(vehicleSave.getModel());
        assertThat(found.getPrice())
                .isEqualTo(vehicleSave.getPrice());
    }

    @Test
    public void getVehicleByBrandID() {
        Brand brandSave = brandServiceTest.add(createBrand("Fiat"));
        Vehicle vehicleSave = vehicleServiceTest.add(
                createVehicle("Uno", brandSave, 1000, "2021")
        );

        List<Vehicle> foundAll = vehicleServiceTest.findByBrandID(brandSave.getID());

        assertThat(foundAll.size()).isEqualTo(1);
    }


    public void listVehiclesByBrandName(){
        Brand brandSaveOne = brandServiceTest.add(createBrand("Fiat"));
        Brand brandSaveTwo = brandServiceTest.add(createBrand("BMW"));

        vehicleServiceTest.add(
                createVehicle("Uno", brandSaveOne, 1000, "2021")
        );
        vehicleServiceTest.add(
                createVehicle("Argo", brandSaveOne, 2000, "2019")
        );
        vehicleServiceTest.add(
                createVehicle("Z4", brandSaveTwo, 3000, "2018")
        );

        List<Vehicle> findAllOne = vehicleServiceTest.listByBrandName(brandSaveOne.getName());
        List<Vehicle> findAllTwo = vehicleServiceTest.listByModel(brandSaveTwo.getName());

        assertThat(findAllOne.size()).isEqualTo(2);
        assertThat(findAllTwo.size()).isEqualTo(1);
    }


    public void listVehiclesByPriceRange(){
        Brand brandSaveOne = brandServiceTest.add(createBrand("Fiat"));
        Brand brandSaveTwo = brandServiceTest.add(createBrand("BMW"));

        vehicleServiceTest.add(
                createVehicle("Uno", brandSaveOne, 1000, "2021")
        );
        vehicleServiceTest.add(
                createVehicle("Argo", brandSaveOne, 2000, "2019")
        );
        vehicleServiceTest.add(
                createVehicle("Z4", brandSaveTwo, 3000, "2018")
        );

        List<Vehicle> findAll = vehicleServiceTest.listByPriceRange(0L, 2050);

        assertThat(findAll.size()).isEqualTo(2);
    }

    public void findAllVehicles(){
        Brand brandSaveOne = brandServiceTest.add(createBrand("Fiat"));
        Brand brandSaveTwo = brandServiceTest.add(createBrand("BMW"));

        vehicleServiceTest.add(
                createVehicle("Uno", brandSaveOne, 1000, "2021")
        );
        vehicleServiceTest.add(
                createVehicle("Argo", brandSaveOne, 2000, "2019")
        );
        vehicleServiceTest.add(
                createVehicle("Z4", brandSaveTwo, 3000, "2018")
        );

        List<Vehicle> findAll = vehicleServiceTest.findAll();

        assertThat(findAll.size()).isEqualTo(3);
    }
}
