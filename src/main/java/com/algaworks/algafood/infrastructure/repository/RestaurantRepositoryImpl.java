package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> find(String name,
                                 BigDecimal shippingFeeInitial,
                                 BigDecimal shippingFeeEnd) {
        var jpql = new StringBuilder();
        jpql.append("from Restaurant where 0 = 0 ");

        var parameters = new HashMap<String, Object>();
        if (StringUtils.hasLength(name)) {
            jpql.append("and name like :name ");
            parameters.put("name", "%" + name + "%");
        }
        if (shippingFeeInitial != null) {
            jpql.append("and shippingFee >= :shippingFeeInitial ");
            parameters.put("shippingFeeInitial", shippingFeeInitial);
        }

        if (shippingFeeEnd != null) {
            jpql.append("and shippingFee <= :shippingFeeEnd ");
            parameters.put("shippingFeeEnd", shippingFeeEnd);
        }

        TypedQuery<Restaurant> query = manager.createQuery(jpql.toString(), Restaurant.class);
        parameters.forEach((nameParameter, parameter) -> query.setParameter(nameParameter, parameter));
        return query.getResultList();
    }
}
