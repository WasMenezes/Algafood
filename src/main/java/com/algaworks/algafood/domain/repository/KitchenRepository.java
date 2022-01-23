package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {
    List<Kitchen> list();
    List<Kitchen> byName(String name);
    Kitchen byId(Long id);
    Kitchen save(Kitchen kitchen);
    void remove(Long kitchenId);
}
