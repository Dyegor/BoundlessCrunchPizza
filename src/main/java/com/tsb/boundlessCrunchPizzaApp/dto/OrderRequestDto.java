package com.tsb.boundlessCrunchPizzaApp.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonSerialize
public class OrderRequestDto {

  private static final long serialVersionUID = 1L;

  private List<PizzaRequestDto> pizzaRequestDtoList;

  private String customerName;

  private String customerAddress;

}
