package com.br.latavelhaapi.service;

import com.br.latavelhaapi.model.PriceRange;
import com.br.latavelhaapi.model.Vehicle;
import com.br.latavelhaapi.repository.PriceRangeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceRangeService {

    @Autowired
    private PriceRangeRepository priceRangeRepository;

    public PriceRange add(PriceRange range){
        return priceRangeRepository.save(range);
    }

    public List<PriceRange> list(){
        return priceRangeRepository.findAll();
    }

    public PriceRange delete(long id){
        PriceRange range = priceRangeRepository.findByID(id);
        priceRangeRepository.delete(range);
        return range;
    }

    public PriceRange update(PriceRange range){
        return priceRangeRepository.save(range);
    }

    public PriceRange findById(Long id) {
        return priceRangeRepository.findByID(id);
    }

}