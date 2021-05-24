package com.br.latavelhaapi.repository;

import com.br.latavelhaapi.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findByID(long id);

    boolean existsByName(String name);
}