package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.PaymentMethod;
import com.algaworks.algafood.domain.repository.PaymentMethodRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PaymentMethodImpl implements PaymentMethodRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<PaymentMethod> list() {
        TypedQuery<PaymentMethod> query = manager.createQuery("from PaymentMethod", PaymentMethod.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public PaymentMethod save (PaymentMethod PaymentMethod) {
        return manager.merge(PaymentMethod);
    }

    @Override
    public PaymentMethod byId(Long id) {
        return manager.find(PaymentMethod.class, id);
    }

    @Transactional
    @Override
    public void remove(PaymentMethod PaymentMethod) {
        PaymentMethod = byId(PaymentMethod.getId());
        manager.remove(PaymentMethod);
    }
}
