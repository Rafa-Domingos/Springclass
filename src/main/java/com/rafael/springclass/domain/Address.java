package com.rafael.springclass.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Address implements Serializable {

    private static final long serialVersionUID = -1748771620704816673L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    private String place;

    @Getter
    @Setter
    private String number;

    @Getter
    @Setter
    private String complement;

    @Getter
    @Setter
    private String neighborhood;

    @Getter
    @Setter
    private String zipCode;

    @Getter
    @Setter
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Address(final String place, final String number, final String complement, final String neighborhood,
                   final String zipCode, final Customer customer, final City city) {
        this.place = place;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.customer = customer;
        this.city = city;
    }
}
