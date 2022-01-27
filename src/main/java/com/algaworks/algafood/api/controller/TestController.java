package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.algaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.withSimilarName;
import static com.algaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.withoutShippingFee;

@RestController
@RequestMapping("/teste")
public class TestController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("kitchens/by-name")
    public List<Kitchen> kitchensByName(@RequestParam String name) {
        return kitchenRepository.findByNameContaining(name);
    }

    @GetMapping("restaurant/by-name")
    public Restaurant restaurantByName(@RequestParam String name, @RequestParam Long kitchenId) {
        return restaurantRepository.consultByName(name, kitchenId);
    }

    @GetMapping("/restaurant/without-shipping-fee")
    public List<Restaurant> restaurantWithoutShippingFee(@RequestParam String name) {
        return restaurantRepository.findAll(withoutShippingFee().and(withSimilarName(name)));
    }
}
