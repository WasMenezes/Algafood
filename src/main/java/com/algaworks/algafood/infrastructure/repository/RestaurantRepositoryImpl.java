package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> find(String name,
                                 BigDecimal shippingFeeInitial,
                                 BigDecimal shippingFeeEnd) {
        var jpql = "from Restaurant where name like :name "
                + " and shippingFee between :shippingFeeInitial and :shippingFeeEnd";

        return manager.createQuery(jpql, Restaurant.class)
                .setParameter("name", "%" + name + "%")
                .setParameter("shippingFeeInitial", shippingFeeInitial)
                .setParameter("shippingFeeEnd", shippingFeeEnd)
                .getResultList();
    }

}
