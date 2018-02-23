package com.rafael.springclass.services;

import com.rafael.springclass.domain.Address;
import com.rafael.springclass.domain.Customer;
import com.rafael.springclass.domain.enums.CustomerType;
import com.rafael.springclass.dto.CustomerDTO;
import com.rafael.springclass.dto.NewCustomerDTO;
import com.rafael.springclass.repositories.AddressRepository;
import com.rafael.springclass.repositories.CityRepository;
import com.rafael.springclass.repositories.CustomerRepository;
import com.rafael.springclass.services.exceptions.DataIntegrityException;
import com.rafael.springclass.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Customer getById(final Integer id) {
        final Customer customer = this.customerRepository.findOne(id);

        if (customer == null) {
            throw new ObjectNotFoundException("Object not found. Id = " + id + ", type = " + Customer.class.getName());
        }

        return customer;
    }

    public Customer save(final Customer category) {
        final Customer customer = this.customerRepository.save(category);
        this.addressRepository.save(customer.getAddresses());
        return customer;
    }

    public Customer update(final Customer customer) {
        final Customer updatedCustomer = this.updateData(customer);
        return this.customerRepository.save(updatedCustomer);
    }

    public void delete(final Integer id) {
        this.getById(id);
        try {
            this.customerRepository.delete(id);
        } catch (final DataIntegrityViolationException e) {
            throw new DataIntegrityException("Cannot delete customer with orders");
        }
    }

    public List<Customer> list() {
        return this.customerRepository.findAll();
    }

    public Page<Customer> listWithPagination(final Integer page, final Integer linesPerPage, final String orderBy,
                                             final String direction) {
        final PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return this.customerRepository.findAll(pageRequest);
    }

    public Customer fromDTO(final CustomerDTO customerDTO) {
        return new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getEmail());
    }

    public Customer fromDTO(final NewCustomerDTO newCustomerDTO) {
        final Customer customer = new Customer(newCustomerDTO.getName(), newCustomerDTO.getEmail(),
                                               newCustomerDTO.getDocument(),
                                               CustomerType.toEnum(newCustomerDTO.getCustomerType()));

        final Address address = new Address(newCustomerDTO.getPlace(), newCustomerDTO.getNumber(),
                                            newCustomerDTO.getComplement(), newCustomerDTO.getNeighborhood(),
                                            newCustomerDTO.getZipCode(), customer,
                                            this.cityRepository.findOne(newCustomerDTO.getCityId()));

        customer.getAddresses().add(address);
        customer.getPhoneNumbers().add(newCustomerDTO.getPhoneNumber1());

        if (newCustomerDTO.getPhoneNumber2() != null) {
            customer.getPhoneNumbers().add(newCustomerDTO.getPhoneNumber1());
        }

        if (newCustomerDTO.getPhoneNumber3() != null) {
            customer.getPhoneNumbers().add(newCustomerDTO.getPhoneNumber3());
        }

        return customer;
    }

    private Customer updateData(final Customer customer) {
        final Customer customerDB = this.getById(customer.getId());

        customerDB.setName(customer.getName());
        customerDB.setEmail(customer.getEmail());

        return customerDB;
    }
}
