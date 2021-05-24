package com.br.latavelhaapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "price_ranges")
public class PriceRange implements Serializable {
  
  private static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ID;

  private String label;
  
  @Column(name = "range_start")
  private double rangeStart;
  
  @Column(name = "range_end")
  private double rangeEnd;

  public Long getID() {
    return this.ID;
  }

  public void setID(Long ID) {
    this.ID = ID;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public double getRangeStart() {
    return this.rangeStart;
  }

  public void setRangeStart(double rangeStart) {
    this.rangeStart = rangeStart;
  }

  public double getRangeEnd() {
    return this.rangeEnd;
  }

  public void setRangeEnd(double rangeEnd) {
    this.rangeEnd = rangeEnd;
  }

}
