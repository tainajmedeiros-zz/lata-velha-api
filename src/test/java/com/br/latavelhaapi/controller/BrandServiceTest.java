package com.br.latavelhaapi.controller;

import com.br.latavelhaapi.model.Brand;
import com.br.latavelhaapi.service.BrandService;
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
public class BrandServiceTest {

    @Autowired
    BrandService brandServiceTest;

    private Brand createBrand(String name) {
        Brand brand = new Brand();
        brand.setName(name);
        return brand;
    }

    @Test
    public void saveBrand() {
        Brand brandSave = brandServiceTest.add(createBrand("Fiat"));

        assertThat(brandSave).isNotNull();
    }

    @Test
    public void getBrandByIDSuccess() {
        Brand brandSave = brandServiceTest.add(createBrand("Fiat"));

        Brand found = brandServiceTest.findById(brandSave.getID());

        assertThat(found.getName())
                .isEqualTo(brandSave.getName());
    }

    @Test
    public void getAllBrands(){
        Brand brandSaveOne = brandServiceTest.add(createBrand("Fiat"));
        Brand brandSaveTwo = brandServiceTest.add(createBrand("BMW"));
        Brand brandSaveTree = brandServiceTest.add(createBrand("Ferrari"));

        List<Brand> findAll = brandServiceTest.findAll();

        assertThat(findAll.size()).isEqualTo(3);
    }

    @Test
    public void existsBrandWhitNameSuccess(){
        Brand brandSave = brandServiceTest.add(createBrand("Fiat"));

        Boolean found = brandServiceTest.existsByName(brandSave.getName());

        assertThat(found).isTrue();
    }

    @Test
    public void existsBrandWhitNameFalse(){
        Brand brandSaveOne = brandServiceTest.add(createBrand("Fiat"));
        Brand brandSaveTwo = brandServiceTest.add(createBrand("BMW"));

        Boolean found = brandServiceTest.existsByName("Ferrari");

        assertThat(found).isFalse();
    }

    @Test
    public void listBrands(){
        Brand brandSaveOne = brandServiceTest.add(createBrand("Fiat"));
        Brand brandSaveTwo = brandServiceTest.add(createBrand("BMW"));
        Brand brandSaveTree = brandServiceTest.add(createBrand("Ferrari"));

        List<Brand> findAll = brandServiceTest.list();

        assertThat(findAll.get(0).getName()).isEqualTo(brandSaveOne.getName());
        assertThat(findAll.get(1).getName()).isEqualTo(brandSaveTwo.getName());
        assertThat(findAll.get(2).getName()).isEqualTo(brandSaveTree.getName());
    }

    @Test
    public void deleteBrand(){
        Brand brandSaveOne = brandServiceTest.add(createBrand("Fiat"));
        Brand brandSaveTwo = brandServiceTest.add(createBrand("BMW"));
        Brand brandSaveTree = brandServiceTest.add(createBrand("Ferrari"));

        List<Brand> findAllBefore = brandServiceTest.findAll();
        assertThat(findAllBefore.size()).isEqualTo(3);

        Brand brandDelete = brandServiceTest.delete(brandSaveOne.getID());

        List<Brand> findAllAfter = brandServiceTest.findAll();
        assertThat(findAllAfter.size()).isEqualTo(2);
        assertThat(brandDelete.getName()).isEqualTo(brandSaveOne.getName());
    }

    @Test
    public void updateBrand(){
        Brand brandSaveOne = brandServiceTest.add(createBrand("Fiat"));
        Brand brandSaveTwo = brandServiceTest.add(createBrand("BMW"));
        Brand brandSaveTree = brandServiceTest.add(createBrand("Ferrari"));


        brandSaveOne.setName("Renault");
        Brand newBrandOne = brandServiceTest.update(brandSaveOne);

        assertThat(brandSaveOne.getName()).isEqualTo(newBrandOne.getName());
    }

}
