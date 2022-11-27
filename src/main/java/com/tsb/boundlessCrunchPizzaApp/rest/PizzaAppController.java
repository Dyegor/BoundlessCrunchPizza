package com.tsb.boundlessCrunchPizzaApp.rest;

import static org.springframework.http.ResponseEntity.ok;

import com.tsb.boundlessCrunchPizzaApp.dto.OrderRequestDto;
import com.tsb.boundlessCrunchPizzaApp.dto.OrderResponseDto;
import com.tsb.boundlessCrunchPizzaApp.dto.PizzaBaseResponseDto;
import com.tsb.boundlessCrunchPizzaApp.dto.PizzaSizeResponseDto;
import com.tsb.boundlessCrunchPizzaApp.dto.ToppingResponseDto;
import com.tsb.boundlessCrunchPizzaApp.service.PizzaAppService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizza")
public class PizzaAppController {

  private final PizzaAppService pizzaAppService;

  public PizzaAppController(PizzaAppService pizzaAppService) {
    this.pizzaAppService = pizzaAppService;
  }

  /**
   * @return Sizes response
   */
  @GetMapping("/sizes")
  public ResponseEntity<List<PizzaSizeResponseDto>> getAllPizzaSizes() {

    return ok(pizzaAppService.getAllPizzaSizes());
  }

  /**
   * @return Bases response
   */
  @GetMapping("/bases")
  public ResponseEntity<List<PizzaBaseResponseDto>> getAllPizzaBasses() {

    return ok(pizzaAppService.getAllPizzaBasses());
  }

  /**
   * @return Toppings response
   */
  @GetMapping("/toppings")
  public ResponseEntity<List<ToppingResponseDto>> getAllToppings() {

    return ok(pizzaAppService.getAllToppings());
  }

  /**
   * @return Orders response
   */
  @GetMapping("/orders")
  public ResponseEntity<List<OrderResponseDto>> getAllOrders() {

    return ok(pizzaAppService.getAllOrders());
  }


  /**
   * @param orderRequestDto
   * @return PizzaOrder response
   */
  @PostMapping("/orders")
  public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {

    return new ResponseEntity<>(pizzaAppService.createOrder(orderRequestDto), HttpStatus.CREATED);
  }

  /**
   * @param orderId
   * update Order's delivery status
   */
  @PutMapping("/orders/makeDelivered/{orderId}")
  public ResponseEntity<OrderResponseDto> makeDelivered(@PathVariable int orderId) {

    return ok(pizzaAppService.makeDelivered(orderId));
  }
}
