package com.tsb.boundlessCrunchPizzaApp.persistence.dao;

import com.tsb.boundlessCrunchPizzaApp.persistence.model.PizzaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<PizzaOrder, Integer> {

  PizzaOrder findOneByPizzaOrderId(int Id);
}
