package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.City;

import java.util.List;

public interface CityRepository {
    List<City> list();
    City byId(Long id);
    City save(City City);
    void remove(Long cityId);
}
