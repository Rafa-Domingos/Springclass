package com.rafael.springclass.dto;

import com.rafael.springclass.domain.Customer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = -3128594235538662668L;

    @Getter
    @Setter
    private Integer id;

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

    public CustomerDTO() {
    }

    public CustomerDTO(final Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
    }
}
