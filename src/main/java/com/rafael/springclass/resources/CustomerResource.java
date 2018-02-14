package com.rafael.springclass.resources;

import com.rafael.springclass.domain.Customer;
import com.rafael.springclass.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

    /**
     * {@link CustomerService} injection.
     */
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getById(@PathVariable final Integer id) {
        return ResponseEntity.ok().body(this.customerService.getById(id));
    }
}
