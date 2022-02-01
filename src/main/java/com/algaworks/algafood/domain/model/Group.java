package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name="permission_group", joinColumns = @JoinColumn(name="group_id"),
            inverseJoinColumns = @JoinColumn(name="permission_id"))
    private List<Permission> permissions = new ArrayList<>();
}
