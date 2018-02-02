package com.rafael.springclass.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@EqualsAndHashCode(of = {"id"})
public class City implements Serializable {

    private static final long serialVersionUID = -6872717222919628944L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    public City() {
        this.state = new State();
    }

    public City(final String name, final State state) {
        this.name = name;
        this.state = state;
    }
}
