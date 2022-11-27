package com.tsb.boundlessCrunchPizzaApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tsb.boundlessCrunchPizzaApp.dto.OrderRequestDto;
import com.tsb.boundlessCrunchPizzaApp.dto.OrderResponseDto;
import com.tsb.boundlessCrunchPizzaApp.dto.PizzaBaseResponseDto;
import com.tsb.boundlessCrunchPizzaApp.dto.PizzaRequestDto;
import com.tsb.boundlessCrunchPizzaApp.dto.PizzaSizeResponseDto;
import com.tsb.boundlessCrunchPizzaApp.dto.ToppingResponseDto;
import com.tsb.boundlessCrunchPizzaApp.exception.DatabaseConnectionException;
import com.tsb.boundlessCrunchPizzaApp.exception.InvalidRequestException;
import com.tsb.boundlessCrunchPizzaApp.persistence.dao.OrderRepository;
import com.tsb.boundlessCrunchPizzaApp.persistence.dao.PizzaBaseRepository;
import com.tsb.boundlessCrunchPizzaApp.persistence.dao.PizzaSizeRepository;
import com.tsb.boundlessCrunchPizzaApp.persistence.dao.ToppingRepository;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.Pizza;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.PizzaBase;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.PizzaOrder;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.PizzaSize;
import com.tsb.boundlessCrunchPizzaApp.persistence.model.Topping;
import com.tsb.boundlessCrunchPizzaApp.service.PizzaAppService;
import com.tsb.boundlessCrunchPizzaApp.service.impl.PizzaAppServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
class PizzaAppServiceImplTest {

  private PizzaAppService pizzaAppService;
  private PizzaSizeRepository pizzaSizeRepository;
  private PizzaBaseRepository pizzaBaseRepository;
  private ToppingRepository toppingRepository;
  private OrderRepository orderRepository;

  @BeforeEach
  void init() {
    pizzaSizeRepository = mock(PizzaSizeRepository.class);
    pizzaBaseRepository = mock(PizzaBaseRepository.class);
    toppingRepository = mock(ToppingRepository.class);
    orderRepository = mock(OrderRepository.class);

    pizzaAppService = new PizzaAppServiceImpl(pizzaSizeRepository, pizzaBaseRepository,
        toppingRepository, orderRepository);
  }

  private PizzaSize createNewPizzaSize() {
    PizzaSize pizzaSize = new PizzaSize();

    pizzaSize.setPizzaSizeId(111);
    pizzaSize.setPizzaSizeName("Pizza Size");

    return pizzaSize;
  }

  private List<PizzaSize> createNewPizzaSizesList() {
    List<PizzaSize> pizzaSizes = new ArrayList<>();

    pizzaSizes.add(createNewPizzaSize());

    return pizzaSizes;
  }

  private PizzaBase createNewBase() {
    PizzaBase pizzaBase = new PizzaBase();

    pizzaBase.setPizzaBaseId(222);
    pizzaBase.setPizzaBaseName("Pizza Base");

    return pizzaBase;
  }

  private List<PizzaBase> createNewPizzaBasesList() {
    List<PizzaBase> pizzaBases = new ArrayList<>();

    pizzaBases.add(createNewBase());

    return pizzaBases;
  }

  private Topping createNewTopping() {
    Topping topping = new Topping();

    topping.setToppingId(333);
    topping.setToppingName("Topping Name");

    return topping;
  }

  private List<Topping> createNewToppingsList() {
    List<Topping> toppings = new ArrayList<>();

    toppings.add(createNewTopping());

    return toppings;
  }

  private Pizza createNewPizza() {
    Pizza pizza = new Pizza();

    pizza.setPizzaId(444);
    pizza.setPizzaSize(createNewPizzaSize());
    pizza.setPizzaBase(createNewBase());
    pizza.setTopping(createNewTopping());

    return pizza;
  }

  private List<Pizza> createNewPizzaList() {
    List<Pizza> pizzaList = new ArrayList<>();

    pizzaList.add(createNewPizza());

    return pizzaList;
  }

  private PizzaOrder createNewPizzaOrder() {
    PizzaOrder pizzaOrder = new PizzaOrder();

    pizzaOrder.setPizzaOrderId(555);
    pizzaOrder.setPizzas(createNewPizzaList());
    pizzaOrder.setCustomerName("Customer Name");
    pizzaOrder.setCustomerAddress("Customer Address");
    pizzaOrder.setDelivered(false);

    return pizzaOrder;
  }

  private List<PizzaOrder> createNewPizzaOrders() {
    List<PizzaOrder> pizzaOrderList = new ArrayList<>();

    pizzaOrderList.add(createNewPizzaOrder());

    return pizzaOrderList;
  }

  private PizzaRequestDto createPizzaRequestDto() {
    PizzaRequestDto pizzaRequestDto = PizzaRequestDto.builder()
        .pizzaSize(createNewPizzaSize())
        .pizzaBase(createNewBase())
        .topping(createNewTopping())
        .build();
    return pizzaRequestDto;
  }

  private List<PizzaRequestDto> createPizzaRequestDtos() {
    List<PizzaRequestDto> pizzaRequestDtoList = new ArrayList<>();

    pizzaRequestDtoList.add(createPizzaRequestDto());

    return pizzaRequestDtoList;
  }

  private OrderRequestDto createOrderRequestDto() {

    return OrderRequestDto.builder()
        .pizzaRequestDtoList(createPizzaRequestDtos())
        .customerName("Customer Name")
        .customerAddress("Customer Address")
        .build();
  }

  @Test
  @DisplayName("Test retrieve all sizes")
  void testGetSizes() {
    when(pizzaSizeRepository.findAll()).thenReturn(createNewPizzaSizesList());

    List<PizzaSizeResponseDto> pizzaSizeResponseDtos = pizzaAppService.getAllPizzaSizes();

    verify(pizzaSizeRepository, times(1)).findAll();
    PizzaSizeResponseDto pizzaSizeResponseDto = pizzaSizeResponseDtos.get(0);

    assertEquals(1, pizzaSizeResponseDtos.size());
    assertEquals(111, pizzaSizeResponseDto.getPizzaSizeId());
    assertEquals("Pizza Size", pizzaSizeResponseDto.getPizzaSize());
  }

  @Test
  @DisplayName("Test retrieve all bases")
  void testGetBases() {
    when(pizzaBaseRepository.findAll()).thenReturn(createNewPizzaBasesList());

    List<PizzaBaseResponseDto> pizzaBaseResponseDtos = pizzaAppService.getAllPizzaBasses();

    verify(pizzaBaseRepository, times(1)).findAll();
    PizzaBaseResponseDto pizzaBaseResponseDto = pizzaBaseResponseDtos.get(0);

    assertEquals(1, pizzaBaseResponseDtos.size());
    assertEquals(222, pizzaBaseResponseDto.getPizzaBaseId());
    assertEquals("Pizza Base", pizzaBaseResponseDto.getPizzaBaseName());
  }

  @Test
  @DisplayName("Test retrieve all toppings")
  void testGetToppings() {
    when(toppingRepository.findAll()).thenReturn(createNewToppingsList());

    List<ToppingResponseDto> toppingResponseDtos = pizzaAppService.getAllToppings();

    verify(toppingRepository, times(1)).findAll();
    ToppingResponseDto toppingResponseDto = toppingResponseDtos.get(0);

    assertEquals(1, toppingResponseDtos.size());
    assertEquals(333, toppingResponseDto.getToppingId());
    assertEquals("Topping Name", toppingResponseDto.getToppingName());
  }

  @Test
  @DisplayName("Test retrieve all orders")
  void testGetOrders() {
    when(orderRepository.findAll()).thenReturn(createNewPizzaOrders());

    List<OrderResponseDto> orderResponseDtos = pizzaAppService.getAllOrders();

    verify(orderRepository, times(1)).findAll();
    OrderResponseDto orderResponseDto = orderResponseDtos.get(0);

    assertEquals(1, orderResponseDtos.size());
    assertEquals(555, orderResponseDto.getOrderId());
    assertEquals("Customer Name", orderResponseDto.getCustomerName());
    assertEquals("Customer Address", orderResponseDto.getCustomerAddress());
  }

  @Test
  @DisplayName("Test new order creation")
  void testCreateNewOrder() {
    when(orderRepository.save(any())).thenReturn(createNewPizzaOrder());

    OrderResponseDto orderResponseDto = pizzaAppService.createOrder(createOrderRequestDto());

    verify(orderRepository, times(1)).save(any());

    assertEquals("Customer Name", orderResponseDto.getCustomerName());
    assertEquals("Customer Address", orderResponseDto.getCustomerAddress());
    assertEquals(1, orderResponseDto.getPizzaResponseDtoList().size());
    assertEquals(false, orderResponseDto.isDelivered());
  }

  @Test
  @DisplayName("Test update delivery status")
  void testMakeDelivered() {
    when(orderRepository.findOneByPizzaOrderId(anyInt())).thenReturn(createNewPizzaOrder());

    OrderResponseDto orderResponseDto = pizzaAppService.makeDelivered(anyInt());

    verify(orderRepository, times(1)).findOneByPizzaOrderId(anyInt());

    assertTrue(orderResponseDto.isDelivered());
  }

  @Test
  @DisplayName("Test error when retrieving all pizza sizes")
  void testGetSizesException() {
    when(pizzaSizeRepository.findAll()).thenThrow(new HibernateException("Database Exception"));

    Exception thrown = assertThrows(DatabaseConnectionException.class,
        () -> pizzaAppService.getAllPizzaSizes());
    assertTrue(thrown.getMessage()
        .contains("Error retrieving pizza sizes data, pls refer to the logs for more information"));
  }

  @Test
  @DisplayName("Test error when retrieving all pizza bases")
  void testGetBasesException() {
    when(pizzaBaseRepository.findAll()).thenThrow(new HibernateException("Database Exception"));

    Exception thrown = assertThrows(DatabaseConnectionException.class,
        () -> pizzaAppService.getAllPizzaBasses());
    assertTrue(thrown.getMessage()
        .contains("Error retrieving pizza bases data, pls refer to the logs for more information"));
  }

  @Test
  @DisplayName("Test error when retrieving all toppings")
  void testGetToppingsException() {
    when(toppingRepository.findAll()).thenThrow(new HibernateException("Database Exception"));

    Exception thrown = assertThrows(DatabaseConnectionException.class,
        () -> pizzaAppService.getAllToppings());
    assertTrue(thrown.getMessage()
        .contains("Error retrieving toppings data, pls refer to the logs for more information"));
  }

  @Test
  @DisplayName("Test error when retrieving all orders")
  void testGetOrdersException() {
    when(orderRepository.findAll()).thenThrow(new HibernateException("Database Exception"));

    Exception thrown = assertThrows(DatabaseConnectionException.class,
        () -> pizzaAppService.getAllOrders());
    assertTrue(thrown.getMessage()
        .contains("Error retrieving orders data, pls refer to the logs for more information"));
  }

  @Test
  @DisplayName("Test error when creating new order")
  void testCreateOrderException() {
    when(orderRepository.save(any())).thenThrow(new DataIntegrityViolationException("Id exists"));

    Exception thrown = assertThrows(InvalidRequestException.class,
        () -> pizzaAppService.createOrder(createOrderRequestDto()));
    assertTrue(thrown.getMessage().contains("Error creating new Order"));
  }

  @Test
  @DisplayName("Test error when updating order status")
  void testMakeDeliveredException() {
    when(orderRepository.save(any())).thenThrow(new IllegalArgumentException("Id exists"));

    Exception thrown = assertThrows(InvalidRequestException.class,
        () -> pizzaAppService.makeDelivered(anyInt()));
    assertTrue(thrown.getMessage().contains("Can not find order with id"));
  }
}
