package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> list() {
        TypedQuery<City> query = manager.createQuery("from City", City.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public City save (City city) {
        return manager.merge(city);
    }

    @Override
    public City byId(Long id) {
        return manager.find(City.class, id);
    }

    @Transactional
    @Override
    public void remove(Long cityId) {
        City city = byId(cityId);
        manager.remove(city);
    }
}
