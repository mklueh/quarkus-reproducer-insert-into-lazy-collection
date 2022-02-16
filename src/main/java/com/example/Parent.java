package com.example;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marian at 16.02.2022
 */
@Getter
@Entity
public class Parent extends PanacheEntity {

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "parentOfLazyChildren")
    private Set<Child> lazyChildren = new HashSet<>();


    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "parentOfEagerChildren")
    private Set<Child> eagerChildren = new HashSet<>();

    public Parent addLazy(Child child) {
        this.lazyChildren.add(child);
        child.setParentOfLazyChildren(this);
        return this;
    }

    public Parent addEager(Child child) {
        this.eagerChildren.add(child);
        child.setParentOfEagerChildren(this);
        return this;
    }
}
