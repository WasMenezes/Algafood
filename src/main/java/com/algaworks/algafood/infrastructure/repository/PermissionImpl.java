package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Permission;
import com.algaworks.algafood.domain.repository.PermissionRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PermissionImpl implements PermissionRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permission> list() {
        TypedQuery<Permission> query = manager.createQuery("from Permission", Permission.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Permission save (Permission Permission) {
        return manager.merge(Permission);
    }

    @Override
    public Permission byId(Long id) {
        return manager.find(Permission.class, id);
    }

    @Transactional
    @Override
    public void remove(Permission Permission) {
        Permission = byId(Permission.getId());
        manager.remove(Permission);
    }
}
