package com.rafael.springclass.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class NewCustomerDTO implements Serializable {

    private static final long serialVersionUID = -3439027659428530872L;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String document;

    @Getter
    @Setter
    private Integer customerType;

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
    private Integer cityId;

    @Getter
    @Setter
    private String phoneNumber1;

    @Getter
    @Setter
    private String phoneNumber2;

    @Getter
    @Setter
    private String phoneNumber3;

    public NewCustomerDTO() {
    }


}
