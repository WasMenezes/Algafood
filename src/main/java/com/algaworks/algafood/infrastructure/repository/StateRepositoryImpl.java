package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class StateRepositoryImpl implements StateRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<State> list() {
        TypedQuery<State> query = manager.createQuery("from State", State.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public State save (State state) {
        return manager.merge(state);
    }

    @Override
    public State byId(Long id) {
        return manager.find(State.class, id);
    }

    @Transactional
    @Override
    public void remove(Long stateId) {
        State state = byId(stateId);
        if (state == null) throw new EmptyResultDataAccessException(1);
        manager.remove(state);
    }
}
