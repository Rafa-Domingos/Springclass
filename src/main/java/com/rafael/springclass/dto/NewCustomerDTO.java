package com.rafael.springclass.dto;

import com.rafael.springclass.domain.enums.CustomerType;
import com.rafael.springclass.services.validation.CustomerInsert;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@CustomerInsert
public class NewCustomerDTO implements Serializable {

    private static final long serialVersionUID = -3439027659428530872L;

    @Getter
    @Setter
    @NotEmpty(message = "Name required")
    @Length(min = 5, max = 120, message = "Name's size must be between 5 and 120 characters")
    private String name;

    @Getter
    @Setter
    @NotEmpty(message = "E-mail required")
    @Email(message = "Invalid e-mail")
    private String email;

    @Getter
    @Setter
    @NotEmpty(message = "Document required")
    private String document;

    @Getter
    @Setter
    private Integer customerType;

    @Getter
    @Setter
    @NotEmpty(message = "Place required")
    private String place;

    @Getter
    @Setter
    @NotEmpty(message = "Number required")
    private String number;

    @Getter
    @Setter
    private String complement;

    @Getter
    @Setter
    private String neighborhood;

    @Getter
    @Setter
    @NotEmpty(message = "Zip code required")
    private String zipCode;

    @Getter
    @Setter
    private Integer cityId;

    @Getter
    @Setter
    @NotEmpty(message = "Phone number required")
    private String phoneNumber1;

    @Getter
    @Setter
    private String phoneNumber2;

    @Getter
    @Setter
    private String phoneNumber3;

    public NewCustomerDTO() {
    }

    public boolean isNatural() {
        return this.customerType != null && this.customerType.equals(CustomerType.NATURAL.getCode());
    }

    public boolean isLegal() {
        return this.customerType != null && this.customerType.equals(CustomerType.LEGAL.getCode());
    }
}
