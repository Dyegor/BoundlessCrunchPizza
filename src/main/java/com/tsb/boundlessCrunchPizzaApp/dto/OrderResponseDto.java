package com.tsb.boundlessCrunchPizzaApp.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonDeserialize
public class OrderResponseDto {

  private static final long serialVersionUID = 1L;

  private int orderId;

  private List<PizzaResponseDto> pizzaResponseDtoList;

  private String customerName;

  private String customerAddress;

  private boolean isDelivered;
}
