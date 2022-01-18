package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
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
    public State save (State State) {
        return manager.merge(State);
    }

    @Override
    public State byId(Long id) {
        return manager.find(State.class, id);
    }

    @Transactional
    @Override
    public void remove(State State) {
        State = byId(State.getId());
        manager.remove(State);
    }
}
