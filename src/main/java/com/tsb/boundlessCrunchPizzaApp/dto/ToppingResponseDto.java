package com.tsb.boundlessCrunchPizzaApp.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonDeserialize
public class ToppingResponseDto {

  private static final long serialVersionUID = 1L;

  private int toppingId;

  private String toppingName;

}
