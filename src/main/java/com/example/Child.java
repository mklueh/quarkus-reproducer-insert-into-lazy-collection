package com.example;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Marian at 16.02.2022
 */
@Entity
@Getter
@Setter
public class Child extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "lazy_parent_id")
    private Parent parentOfLazyChildren;

    @ManyToOne
    @JoinColumn(name = "eager_parent_id")
    private Parent parentOfEagerChildren;


}
