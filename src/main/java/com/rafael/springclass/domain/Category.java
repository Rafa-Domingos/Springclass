package com.rafael.springclass.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
    @Setter
    private Integer id;

    /**
     * Name of category.
     */
    @Getter
    @Setter
    private String name;

    /**
     * Standard constructor.
     */
    public Category() {}

    /**
     * Constructor with parameters.
     *
     * @param id    Id of category
     * @param name  Name of category
     */
    public Category(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }
}
