package com.rafael.springclass.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(of = {"id"})
public class State implements Serializable {

    private static final long serialVersionUID = -4971123636692166551L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @OneToMany(mappedBy = "state")
    private List<City> cities;

    public State() {
        this.cities = new ArrayList<>();
    }

    public State(final String name) {
        this.cities = new ArrayList<>();
        this.name = name;
    }
}
