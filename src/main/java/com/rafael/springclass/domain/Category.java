package com.rafael.springclass.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

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
    @Setter
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
