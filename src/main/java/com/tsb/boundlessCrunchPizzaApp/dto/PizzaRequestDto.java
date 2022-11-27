package com.tsb.boundlessCrunchPizzaApp.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.PizzaBase;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.PizzaSize;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.Topping;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonSerialize
public class PizzaRequestDto {

  private static final long serialVersionUID = 1L;

  private PizzaSize pizzaSize;

  private PizzaBase pizzaBase;

  private Topping topping;
}
