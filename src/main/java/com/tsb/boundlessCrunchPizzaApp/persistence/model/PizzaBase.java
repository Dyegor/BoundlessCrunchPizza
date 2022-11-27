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
@Table(name = "PIZZABASES")
@Getter
@Setter
public class PizzaBase {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = SEQUENCE)
  @Column(name = "PIZZABASEID", unique = true, nullable = false, updatable = false)
  private int pizzaBaseId;

  @Column(name = "PIZZABASENAME", unique = true, nullable = false)
  private String pizzaBaseName;
}
