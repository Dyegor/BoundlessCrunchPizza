package com.tsb.boundlessCrunchPizzaApp.persistence.dao;

import com.tsb.boundlessCrunchPizzaApp.persistence.model.PizzaBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaBaseRepository extends JpaRepository<PizzaBase, Integer> {

}
