package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> list() {
        TypedQuery<Restaurant> query = manager.createQuery("from Restaurant", Restaurant.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Restaurant save (Restaurant Restaurant) {
        return manager.merge(Restaurant);
    }

    @Override
    public Restaurant byId(Long id) {
        return manager.find(Restaurant.class, id);
    }

    @Transactional
    @Override
    public void remove(Restaurant Restaurant) {
        Restaurant = byId(Restaurant.getId());
        manager.remove(Restaurant);
    }
}
