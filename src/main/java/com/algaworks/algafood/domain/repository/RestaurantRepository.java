package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestaurantRepository
        extends JpaRepository<Restaurant, Long>,
        RestaurantRepositoryQueries,
        JpaSpecificationExecutor<Restaurant> {

    List<Restaurant> findByShippingFeeBetween(BigDecimal initialFee, BigDecimal endFee);

    Restaurant consultByName(String name, Long kitchenId);

    List<Restaurant> find(String name,
                          BigDecimal shippingFeeInitial,
                          BigDecimal shippingFeeEnd);
}