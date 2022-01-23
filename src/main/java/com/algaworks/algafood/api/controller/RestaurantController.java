package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.service.RegisterRestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RegisterRestaurantService registerRestaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> listRestaurants() {
        return ResponseEntity.ok(restaurantRepository.findAll());
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> listRestaurants(@PathVariable Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            return ResponseEntity.ok(restaurant.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/by-shipping-fee")
    public ResponseEntity<List<Restaurant>> listRestaurantsByShippingFee(@RequestParam BigDecimal initialFee, @RequestParam BigDecimal endFee) {
        return ResponseEntity.ok(restaurantRepository.findByShippingFeeBetween(initialFee, endFee));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(registerRestaurantService.save(restaurant));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {
        try {
            Optional<Restaurant> restaurantActual = this.restaurantRepository.findById(restaurantId);
            if (restaurantActual == null) return ResponseEntity.notFound().build();
            BeanUtils.copyProperties(restaurant, restaurantActual, "id");
            return ResponseEntity.ok().body(this.registerRestaurantService.save(restaurant));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<?> delete(@PathVariable Long restaurantId) {
        try {
            this.registerRestaurantService.delete(restaurantId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long restaurantId,
                                           @RequestBody Map<String, Object> fields) {
        Optional<Restaurant> restaurantActual = this.restaurantRepository.findById(restaurantId);
        if (restaurantActual == null) return ResponseEntity.notFound().build();

        merge(fields, restaurantActual.get());
        return update(restaurantId, restaurantActual.get());
    }

    private void merge(Map<String, Object> fields, Restaurant restaurantDestiny) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant restaurant = objectMapper.convertValue(fields, Restaurant.class);

        fields.forEach((nameProperty, valueProperty) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, nameProperty);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, restaurant);
            ReflectionUtils.setField(field, restaurantDestiny, newValue);
        });
    }


}
