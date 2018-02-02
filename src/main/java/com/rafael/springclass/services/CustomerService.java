package com.rafael.springclass.services;

import com.rafael.springclass.domain.Customer;
import com.rafael.springclass.repositories.CustomerRepository;
import com.rafael.springclass.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getById(final Integer id) {
        final Customer customer = this.customerRepository.findOne(id);

        if (customer == null) {
            throw new ObjectNotFoundException("Objeto não encontrado. Id = " + id + ", tipo = " + Customer.class.getName());
        }

        return customer;
    }
}
