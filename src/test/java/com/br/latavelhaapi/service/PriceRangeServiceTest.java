package com.br.latavelhaapi.service;

import com.br.latavelhaapi.model.PriceRange;
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
public class PriceRangeServiceTest {

    @Autowired
    PriceRangeService priceRangeServiceTest;


    private PriceRange createPriceRange(String label, double rangeStart, double rangeEnd) {
        PriceRange priceRange = new PriceRange();
        priceRange.setLabel(label);
        priceRange.setRangeStart(rangeStart);
        priceRange.setRangeEnd(rangeEnd);
        return priceRange;
    }

    @Test
    public void savePriceRange() {
        PriceRange priceRange = priceRangeServiceTest.add(
                createPriceRange("R$ 100 a R$ 200", 100, 200)
        );

        assertThat(priceRange).isNotNull();
    }

    @Test
    public void listPriceRange(){
        PriceRange priceRangeOne = priceRangeServiceTest.add(
                createPriceRange("R$ 100 a R$ 200", 100, 200)
        );
        PriceRange priceRangeTwo = priceRangeServiceTest.add(
                createPriceRange("R$ 201 a R$ 300", 201, 300)
        );
        PriceRange priceRangeTree = priceRangeServiceTest.add(
                createPriceRange("R$ 301 a R$ 400", 301, 400)
        );

        List<PriceRange> findAll = priceRangeServiceTest.list();

        assertThat(findAll.get(0).getLabel()).isEqualTo(priceRangeOne.getLabel());
        assertThat(findAll.get(1).getLabel()).isEqualTo(priceRangeTwo.getLabel());
        assertThat(findAll.get(2).getLabel()).isEqualTo(priceRangeTree.getLabel());
    }

    @Test
    public void deletePriceRange(){
        PriceRange priceRangeOne = priceRangeServiceTest.add(
                createPriceRange("R$ 100 a R$ 200", 100, 200)
        );
        PriceRange priceRangeTwo = priceRangeServiceTest.add(
                createPriceRange("R$ 201 a R$ 300", 201, 300)
        );
        PriceRange priceRangeTree = priceRangeServiceTest.add(
                createPriceRange("R$ 301 a R$ 400", 301, 400)
        );

        List<PriceRange> findAllBefore = priceRangeServiceTest.list();
        assertThat(findAllBefore.size()).isEqualTo(3);

        priceRangeServiceTest.delete(priceRangeOne.getID());

        List<PriceRange> findAllAfter = priceRangeServiceTest.list();
        assertThat(findAllAfter.size()).isEqualTo(2);
    }

    @Test
    public void updatePriceRange(){
        PriceRange priceRange = priceRangeServiceTest.add(
                createPriceRange("R$ 100 a R$ 200", 100, 200)
        );

        priceRange.setLabel("R$ 100 a R$ 250");
        priceRange.setRangeEnd(250);
        PriceRange newPriceRange = priceRangeServiceTest.update(priceRange);

        assertThat(priceRange.getLabel()).isEqualTo(newPriceRange.getLabel());
        assertThat(priceRange.getRangeEnd()).isEqualTo(newPriceRange.getRangeEnd());
    }

    @Test
    public void getPriceRangeByID() {
        PriceRange priceRange = priceRangeServiceTest.add(
                createPriceRange("R$ 100 a R$ 200", 100, 200)
        );

        PriceRange found = priceRangeServiceTest.findById(priceRange.getID());

        assertThat(found.getLabel())
                .isEqualTo(priceRange.getLabel());
    }
}
