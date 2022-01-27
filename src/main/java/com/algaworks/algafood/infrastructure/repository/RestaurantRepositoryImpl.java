package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> find(String name,
                                 BigDecimal shippingFeeInitial,
                                 BigDecimal shippingFeeEnd) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);

        Root<Restaurant> root = criteria.from(Restaurant.class);

        var predicates = new ArrayList<Predicate>();
        if (StringUtils.hasText(name)) {
            predicates.add(builder.like(root.get("name"), "%" + name + "%"));
        }

        if (shippingFeeInitial != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("shippingFee"), shippingFeeInitial));
        }

        if (shippingFeeEnd != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("shippingFee"), shippingFeeEnd));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        return manager.createQuery(criteria)
                .getResultList();
    }
}
