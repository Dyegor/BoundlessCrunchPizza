package com.tsb.boundlessCrunchPizzaApp.persistence.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PIZZASIZES")
@Getter
@Setter
public class PizzaSize {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = SEQUENCE)
  @Column(name = "PIZZASIZEID", unique = true, nullable = false, updatable = false)
  private int pizzaSizeId;

  @Column(name = "PIZZASIZE", unique = true, nullable = false)
  private String pizzaSizeName;
}
