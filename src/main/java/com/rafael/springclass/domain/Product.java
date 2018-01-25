package com.rafael.springclass.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode
public class Product implements Serializable {

    /**
     * Generated serial version.
     */
    private static final long serialVersionUID = 6277858260523055744L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private BigDecimal price;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "product_has_category", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public Product() {
        this.categories = new ArrayList<>();
    }

    public Product(final String name, final BigDecimal price) {
        this.name = name;
        this.price = price;
        this.categories = new ArrayList<>();
    }
}
