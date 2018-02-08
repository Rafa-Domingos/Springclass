package com.rafael.springclass.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    /**
     * Generated serial version.
     */
    private static final long serialVersionUID = 3220223973492650939L;

    /**
     * Id of category.
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Name of category.
     */
    @Getter
    @Setter
    private String name;

    /**
     * List of products owned by category.
     *
     * The Many to Many relationship is mapped by the list {@link Product#categories}
     *
     * The annotation {@link JsonManagedReference} says it's ok to load the list when the category is requested.
     */
    @Getter
    @Setter
    @ManyToMany(mappedBy = "categories")

    private List<Product> products;

    /**
     * Standard constructor.
     */
    public Category() {
        this.products = new ArrayList<>();
    }

    /**
     * Constructor with parameters.
     *
     * @param name Name of category
     */
    public Category(final String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }
}
