package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByShippingFeeBetween(BigDecimal initialFee, BigDecimal endFee);

    @Query("from Restaurant where name like %:name% and kitchen.id = :kitchenId")
    Restaurant consultByName(String name, Long kitchenId);
}