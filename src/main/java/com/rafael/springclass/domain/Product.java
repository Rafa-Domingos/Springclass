package com.rafael.springclass.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@EqualsAndHashCode(of = {"id"})
public class Product implements Serializable {

    /**
     * Generated serial version.
     */
    private static final long serialVersionUID = 6277858260523055744L;

    /**
     * Product's id.
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Product's name.
     */
    @Getter
    @Setter
    private String name;

    /**
     * Product's price.
     */
    @Getter
    @Setter
    private BigDecimal price;

    /**
     * List of categories who owns the product.
     *
     * The Many to Many relationship is made, in database, by the table product_has_category which has the columns
     * category_id and product_id referencing {@link Category#id} and {@link Product#id} respectively.
     *
     * The annotation {@link JsonBackReference} says to not load the list when the product is requested.
     * The idea is avoid cyclic references.
     */
    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "product_has_category", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonBackReference
    private List<Category> categories;

    @Getter
    @Setter
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items;

    /**
     * Standard contructor.
     */
    public Product() {
        this.categories = new ArrayList<>();
        this.items = new HashSet<>();
    }

    /**
     * Constructor with parameters.
     *
     * @param name  Product's name
     * @param price Product's price
     */
    public Product(final String name, final BigDecimal price) {
        this.name = name;
        this.price = price;
        this.categories = new ArrayList<>();
        this.items = new HashSet<>();
    }

    public List<PurchaseOrder> getPurchaseOrders() {
        return this.items.stream().map(OrderItem::getPurchaseOrder).collect(Collectors.toList());
    }
}
