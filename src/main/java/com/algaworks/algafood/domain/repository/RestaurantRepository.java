package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> list();
    Restaurant byId(Long id);
    Restaurant save(Restaurant Restaurant);
    void remove(Restaurant Restaurant);
}
