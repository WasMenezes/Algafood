package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping
    public ResponseEntity<List<Restaurant>> listRestaurants() {
        return ResponseEntity.ok(restaurantRepository.list());
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> listRestaurants(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantRepository.byId(restaurantId);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurantRepository.byId(restaurantId));
        }
        return ResponseEntity.notFound().build();
    }

}
