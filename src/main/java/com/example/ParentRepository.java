package com.example;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by Marian at 16.02.2022
 */
@ApplicationScoped
public class ParentRepository implements PanacheRepository<Parent> {
}
