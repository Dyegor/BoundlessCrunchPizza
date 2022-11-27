package com.tsb.boundlessCrunchPizzaApp.persistence.model;

import static javax.persistence.GenerationType.SEQUENCE;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PIZZAS")
@Getter
@Setter
public class Pizza {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = SEQUENCE)
  @Column(name = "PIZZAID", nullable = false, updatable = false)
  private int pizzaId;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
  @JoinColumn(name = "PIZZAORDERID", referencedColumnName = "PIZZAORDERID")
  @JsonBackReference
  private PizzaOrder pizzaOrder;

  @OneToOne(targetEntity = PizzaSize.class)
  @JoinColumn(name = "PIZZASIZEID", nullable = false, updatable = false)
  private PizzaSize pizzaSize;

  @OneToOne(targetEntity = PizzaBase.class)
  @JoinColumn(name = "PIZZABASEID", nullable = false, updatable = false)
  private PizzaBase pizzaBase;

  @OneToOne(targetEntity = Topping.class)
  @JoinColumn(name = "TOPPINGID", nullable = false, updatable = false)
  private Topping topping;
}
