package com.tsb.boundlessCrunchPizzaApp.service;

import com.tsb.boundlessCrunchPizzaApp.dto.OrderRequestDto;
import com.tsb.boundlessCrunchPizzaApp.dto.OrderResponseDto;
import com.tsb.boundlessCrunchPizzaApp.dto.PizzaBaseResponseDto;
import com.tsb.boundlessCrunchPizzaApp.dto.PizzaSizeResponseDto;
import com.tsb.boundlessCrunchPizzaApp.dto.ToppingResponseDto;
import java.util.List;

public interface PizzaAppService {

  List<ToppingResponseDto> getAllToppings();

  List<PizzaSizeResponseDto> getAllPizzaSizes();

  List<PizzaBaseResponseDto> getAllPizzaBasses();

  List<OrderResponseDto> getAllOrders();

  OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

  OrderResponseDto makeDelivered(int orderId);
}
