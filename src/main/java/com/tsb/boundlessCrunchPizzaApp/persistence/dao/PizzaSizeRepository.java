package com.tsb.boundlessCrunchPizzaApp.persistence.dao;

import com.tsb.boundlessCrunchPizzaApp.persistence.model.PizzaSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaSizeRepository extends JpaRepository<PizzaSize, Integer> {

}
