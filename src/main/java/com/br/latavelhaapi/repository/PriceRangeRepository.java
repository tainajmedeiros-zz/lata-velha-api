package com.br.latavelhaapi.repository;

import com.br.latavelhaapi.model.PriceRange;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRangeRepository extends JpaRepository<PriceRange, Long> {

  PriceRange findByID(long id);
  
}
