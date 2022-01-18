package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Permission;

import java.util.List;

public interface PermissionRepository {
    List<Permission> list();
    Permission byId(Long id);
    Permission save(Permission Permission);
    void remove(Permission Permission);
}
