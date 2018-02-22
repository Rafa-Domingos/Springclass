package com.rafael.springclass.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(exclude = {"name"})
public class Category implements Serializable {

    private static final long serialVersionUID = 3220223973492650939L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    private String name;

    /**
     * List of products owned by category.
     *
     * The Many to Many relationship is mapped by the list {@link Product#categories}
     */
    @Getter
    @Setter
    @ManyToMany(mappedBy = "categories")
    private List<Product> products;

    public Category() {
        this.products = new ArrayList<>();
    }

    public Category(final String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public Category(final Integer id, final String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<>();
    }
}
