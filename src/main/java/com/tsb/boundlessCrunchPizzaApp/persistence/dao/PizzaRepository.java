package com.tsb.boundlessCrunchPizzaApp.persistence.dao;

import com.tsb.boundlessCrunchPizzaApp.persistence.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

}
