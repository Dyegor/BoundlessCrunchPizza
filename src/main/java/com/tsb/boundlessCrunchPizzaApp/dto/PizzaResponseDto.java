package com.tsb.boundlessCrunchPizzaApp.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.PizzaBase;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.PizzaSize;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.Topping;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonDeserialize
public class PizzaResponseDto {

  private static final long serialVersionUID = 1L;

  private int pizzaId;

  private PizzaSize pizzaSize;

  private PizzaBase pizzaBase;

  private Topping topping;
}
