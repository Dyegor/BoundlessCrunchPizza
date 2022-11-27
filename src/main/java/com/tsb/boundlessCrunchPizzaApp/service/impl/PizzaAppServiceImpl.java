package com.tsb.boundlessCrunchPizzaApp.service.impl;

import static java.util.stream.Collectors.toList;

import com.tsb.boundlessCrunchPizzaApp.dto.OrderRequestDto;
import com.tsb.boundlessCrunchPizzaApp.dto.OrderResponseDto;
import com.tsb.boundlessCrunchPizzaApp.dto.PizzaBaseResponseDto;
import com.tsb.boundlessCrunchPizzaApp.dto.PizzaRequestDto;
import com.tsb.boundlessCrunchPizzaApp.dto.PizzaResponseDto;
import com.tsb.boundlessCrunchPizzaApp.dto.PizzaSizeResponseDto;
import com.tsb.boundlessCrunchPizzaApp.dto.ToppingResponseDto;
import com.tsb.boundlessCrunchPizzaApp.exception.DatabaseConnectionException;
import com.tsb.boundlessCrunchPizzaApp.exception.InvalidRequestException;
import com.tsb.boundlessCrunchPizzaApp.persistence.dao.OrderRepository;
import com.tsb.boundlessCrunchPizzaApp.persistence.dao.PizzaBaseRepository;
import com.tsb.boundlessCrunchPizzaApp.persistence.dao.PizzaSizeRepository;
import com.tsb.boundlessCrunchPizzaApp.persistence.dao.ToppingRepository;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.PizzaOrder;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.Pizza;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.PizzaBase;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.PizzaSize;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.Topping;
import com.tsb.boundlessCrunchPizzaApp.service.PizzaAppService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PizzaAppServiceImpl implements PizzaAppService {

  private final PizzaSizeRepository pizzaSizeRepository;
  private final PizzaBaseRepository pizzaBaseRepository;
  private final ToppingRepository toppingRepository;
  private final OrderRepository orderRepository;

  public PizzaAppServiceImpl(PizzaSizeRepository pizzaSizeRepository,
      PizzaBaseRepository pizzaBaseRepository, ToppingRepository toppingRepository,
      OrderRepository orderRepository) {
    this.pizzaSizeRepository = pizzaSizeRepository;
    this.pizzaBaseRepository = pizzaBaseRepository;
    this.toppingRepository = toppingRepository;
    this.orderRepository = orderRepository;
  }

  /**
   * @return All available pizza sizes
   */
  @Override
  public List<PizzaSizeResponseDto> getAllPizzaSizes() {
    List<PizzaSize> pizzaSizes;

    try {
      pizzaSizes = pizzaSizeRepository.findAll();
    } catch (Exception e) {
      log.error("Error retrieving pizza sizes data, error message: {}", e.getMessage());

      throw new DatabaseConnectionException(
          "Error retrieving pizza sizes data, pls refer to the logs for more information");
    }

    return pizzaSizes
        .stream()
        .map(this::toPizzaSizeResponseDto)
        .collect(toList());
  }

  /**
   * @return All available pizza bases
   */
  @Override
  public List<PizzaBaseResponseDto> getAllPizzaBasses() {
    List<PizzaBase> pizzaBases;

    try {
      pizzaBases = pizzaBaseRepository.findAll();
    } catch (Exception e) {
      log.error("Error retrieving pizza bases data, error message: {}", e.getMessage());

      throw new DatabaseConnectionException(
          "Error retrieving pizza bases data, pls refer to the logs for more information");
    }

    return pizzaBases
        .stream()
        .map(this::toPizzaBaseResponseDto)
        .collect(toList());
  }

  /**
   * @return All available toppings
   */
  @Override
  public List<ToppingResponseDto> getAllToppings() {
    List<Topping> toppings;

    try {
      toppings = toppingRepository.findAll();
    } catch (Exception e) {
      log.error("Error retrieving toppings data, error message: {}", e.getMessage());

      throw new DatabaseConnectionException(
          "Error retrieving toppings data, pls refer to the logs for more information");
    }

    return toppings
        .stream()
        .map(this::toToppingResponseDto)
        .collect(toList());
  }

  /**
   * @return All orders stored in database
   */
  @Override
  public List<OrderResponseDto> getAllOrders() {
    List<PizzaOrder> pizzaOrders;

    try {
      pizzaOrders = orderRepository.findAll();
    } catch (Exception e) {
      log.error("Error retrieving orders data, error message: {}", e.getMessage());

      throw new DatabaseConnectionException(
          "Error retrieving orders data, pls refer to the logs for more information");
    }
    return pizzaOrders
        .stream()
        .map(this::toOrderResponseDto)
        .collect(toList());
  }

  /**
   * @param orderRequestDto
   * @return createdOrder
   */
  @Override
  public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
    PizzaOrder createdPizzaOrder;

    try {
      createdPizzaOrder = orderRepository.save(fromOrderRequestDto(orderRequestDto));
    } catch (Exception e) {
      log.error("Error creating new Order, error message: {}", e.getMessage());

      throw new InvalidRequestException("Error creating new Order");
    }

    return toOrderResponseDto(createdPizzaOrder);
  }

  /**
   * @param orderId
   * update Order's delivery status
   */
  @Override
  @Transactional
  public OrderResponseDto makeDelivered(int orderId) {
    PizzaOrder pizzaOrder;

    try {
      pizzaOrder = orderRepository.findOneByPizzaOrderId(orderId);

      pizzaOrder.setDelivered(true);
    } catch (Exception e) {
      log.error("Error updating order status, orderId: {}, error message: {}", orderId, e.getMessage());

      throw new InvalidRequestException(String.format("Can not find order with id: %s", orderId));
    }

    return toOrderResponseDto(pizzaOrder);
  }

  /**
   * @param orderRequestDto
   * @return PizzaOrder mapped from OrderRequestDto
   */
  private PizzaOrder fromOrderRequestDto(OrderRequestDto orderRequestDto) {
    PizzaOrder pizzaOrder = new PizzaOrder();

    pizzaOrder.setPizzas(orderRequestDto.getPizzaRequestDtoList()
        .stream()
        .map(pizzaRequestDto -> fromPizzaRequestDto(pizzaRequestDto, pizzaOrder))
        .collect(toList()));
    pizzaOrder.setCustomerName(orderRequestDto.getCustomerName());
    pizzaOrder.setCustomerAddress(orderRequestDto.getCustomerAddress());
    pizzaOrder.setDelivered(false);

    return pizzaOrder;
  }

  /**
   * @param pizzaRequestDto
   * @param pizzaOrder
   * @return Pizza mapped from PizzaRequestDto
   */
  private Pizza fromPizzaRequestDto(PizzaRequestDto pizzaRequestDto, PizzaOrder pizzaOrder) {

    Pizza pizza = new Pizza();

    pizza.setPizzaOrder(pizzaOrder);
    pizza.setPizzaSize(pizzaRequestDto.getPizzaSize());
    pizza.setPizzaBase(pizzaRequestDto.getPizzaBase());
    pizza.setTopping(pizzaRequestDto.getTopping());

    return pizza;
  }

  /**
   * @param pizza
   * @return PizzaResponseDto mapped from Pizza entity
   */
  private PizzaResponseDto toPizzaRequestDto(Pizza pizza) {

    return PizzaResponseDto.builder()
        .pizzaId(pizza.getPizzaId())
        .pizzaSize(pizza.getPizzaSize())
        .pizzaBase(pizza.getPizzaBase())
        .topping(pizza.getTopping())
        .build();
  }

  /**
   * @param pizzaOrder
   * @return OrderResponseDto mapped from PizzaOrder entity
   */
  private OrderResponseDto toOrderResponseDto(PizzaOrder pizzaOrder) {

    return OrderResponseDto.builder()
        .orderId(pizzaOrder.getPizzaOrderId())
        .pizzaResponseDtoList(pizzaOrder.getPizzas()
            .stream()
            .map(this::toPizzaRequestDto)
            .collect(toList()))
        .customerName(pizzaOrder.getCustomerName())
        .customerAddress(pizzaOrder.getCustomerAddress())
        .isDelivered(pizzaOrder.isDelivered())
        .build();
  }

  /**
   * @param pizzaSize
   * @return PizzaSizeResponseDto mapped from PizzaSize entity
   */
  private PizzaSizeResponseDto toPizzaSizeResponseDto(PizzaSize pizzaSize) {

    return PizzaSizeResponseDto.builder()
        .pizzaSizeId(pizzaSize.getPizzaSizeId())
        .pizzaSize(pizzaSize.getPizzaSizeName())
        .build();
  }

  /**
   * @param pizzaBase
   * @return PizzaBaseResponseDto mapped from PizzaBase entity
   */
  private PizzaBaseResponseDto toPizzaBaseResponseDto(PizzaBase pizzaBase) {

    return PizzaBaseResponseDto.builder()
        .pizzaBaseId(pizzaBase.getPizzaBaseId())
        .pizzaBaseName(pizzaBase.getPizzaBaseName())
        .build();
  }

  /**
   * @param topping
   * @return ToppingResponseDto mapped from Topping entity
   */
  private ToppingResponseDto toToppingResponseDto(Topping topping) {

    return ToppingResponseDto.builder()
        .toppingId(topping.getToppingId())
        .toppingName(topping.getToppingName())
        .build();
  }
}
