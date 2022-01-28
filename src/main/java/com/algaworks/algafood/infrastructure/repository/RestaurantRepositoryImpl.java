package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

import static com.algaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.withSimilarName;
import static com.algaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.withoutShippingFee;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestaurantRepository restaurantRepository;

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

    @Override
    public List<Restaurant> findWithoutShippingFee(String name) {
        return restaurantRepository.findAll(withoutShippingFee().and(withSimilarName(name)));
    }
}
