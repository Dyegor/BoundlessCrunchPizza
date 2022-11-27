package com.tsb.boundlessCrunchPizzaApp.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonSerialize
public class PizzaSizeResponseDto {

  private static final long serialVersionUID = 1L;

  private int pizzaSizeId;

  private String pizzaSize;
}
