package com.br.latavelhaapi.service;

import com.br.latavelhaapi.model.Brand;
import com.br.latavelhaapi.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand add(Brand brand){
        return brandRepository.save(brand);
    }

    public List<Brand> list(){
        return brandRepository.findAll();
    }

    public Brand delete(long id){
        Brand brand = brandRepository.findByID(id);
        brandRepository.delete(brand);
        return brand;
    }

    public Brand update(Brand brand){
        return brandRepository.save(brand);
    }

    public boolean existsByName(String name) {
        return brandRepository.existsByName(name);
    }

    public Brand findById(Long id) {
        return brandRepository.findByID(id);
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}