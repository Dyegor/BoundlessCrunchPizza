package com.tsb.boundlessCrunchPizzaApp.persistence.model;

import static javax.persistence.GenerationType.SEQUENCE;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PIZZAORDERS")
@Getter
@Setter
public class PizzaOrder {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = SEQUENCE)
  @Column(name = "PIZZAORDERID", nullable = false, updatable = false)
  private int pizzaOrderId;

  @OneToMany(mappedBy = "pizzaOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Pizza> pizzas = new ArrayList<>();

  @Column(name = "CUSTNAME", nullable = false)
  private String customerName;

  @Column(name = "CUSTADDRESS", nullable = false)
  private String customerAddress;

  @Column(name = "DELIVERED", nullable = false)
  private boolean isDelivered;
}
