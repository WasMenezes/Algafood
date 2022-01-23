package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class KitchenRepositoryImpl implements KitchenRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Kitchen> list() {
        TypedQuery<Kitchen> query = manager.createQuery("from Kitchen", Kitchen.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Kitchen save (Kitchen kitchen) {
        return manager.merge(kitchen);
    }

    @Override
    public Kitchen byId(Long id) {
        return manager.find(Kitchen.class, id);
    }

    @Transactional
    @Override
    public void remove(Long kitchenId) {
        Kitchen kitchen = byId(kitchenId);
        if (kitchen == null) throw new EmptyResultDataAccessException(1);
        manager.remove(kitchen);
    }

    @Override
    public List<Kitchen> byName(String name) {
        return manager.createQuery("from Kitchen where name like :name", Kitchen.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
}
