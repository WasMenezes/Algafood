package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository {
    List<PaymentMethod> list();
    PaymentMethod byId(Long id);
    PaymentMethod save(PaymentMethod PaymentMethod);
    void remove(PaymentMethod PaymentMethod);
}
