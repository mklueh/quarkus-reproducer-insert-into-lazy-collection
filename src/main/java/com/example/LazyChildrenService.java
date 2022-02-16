package com.example;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by Marian at 16.02.2022
 */
@Slf4j
@ApplicationScoped
public class LazyChildrenService {

    @Inject
    ParentRepository parentRepository;

    @Inject
    ChildRepository childRepository;

    public Uni<Parent> saveParentWithLazyChildren() {
        return Panache.withTransaction(() -> parentRepository.findAll().firstResult()
                .onItem().transformToUni(parent -> parentRepository.persist(parent.addLazy(new Child()))
                        .onItem().transform(unused -> parent)));
    }

    public Uni<Parent> saveLazyChild() {
        return Panache.withTransaction(() -> parentRepository.findAll().firstResult()
                .onItem().transformToUni(parent -> {
                    Child child = new Child();
                    parent.addLazy(child);
                    return childRepository.persist(child)
                            .onItem().transform(unused -> parent);
                }));
    }

    @ReactiveTransactional
    public Uni<Parent> saveParentWithLazyChildrenAndAnnotation() {
        return parentRepository.findAll().firstResult()
                .onItem().transformToUni(parent -> parentRepository.persist(parent.addLazy(new Child()))
                        .onItem().transform(unused -> parent));
    }





}
