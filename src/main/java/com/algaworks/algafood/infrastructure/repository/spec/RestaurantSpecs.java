package com.algaworks.algafood.infrastructure.repository.spec;

import com.algaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {
    public static Specification<Restaurant> withoutShippingFee(){
        return ((root, criteriaQuery, builder) -> builder.equal(root.get("shippingFee"), BigDecimal.ZERO));
    }

    public static Specification<Restaurant> withSimilarName(String name){
        return  ((root, criteriaQuery, builder) -> builder.like(root.get("name"), name));
    }
}
