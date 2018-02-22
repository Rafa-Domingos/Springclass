package com.rafael.springclass.resources;

import com.rafael.springclass.domain.Customer;
import com.rafael.springclass.dto.CustomerDTO;
import com.rafael.springclass.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

    /**
     * {@link CustomerService} injection.
     */
    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDTO>> list() {
        final List<Customer> categories = this.customerService.list();
        final List<CustomerDTO> customerDTOList = categories.stream().map(CustomerDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(customerDTOList);
    }

    @RequestMapping(value = "page", method = RequestMethod.GET)
    public ResponseEntity<Page<CustomerDTO>> listWithPagination(
            @RequestParam(value="page", defaultValue = "0") final Integer page,
            @RequestParam(value="linesPerPage", defaultValue = "24") final Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "name") final String orderBy,
            @RequestParam(value="direction", defaultValue = "ASC") final String direction) {
        final Page<Customer> categories = this.customerService.listWithPagination(page, linesPerPage, orderBy, direction);
        final Page<CustomerDTO> customerDTOList = categories.map(CustomerDTO::new);
        return ResponseEntity.ok().body(customerDTOList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getById(@PathVariable final Integer id) {
        return ResponseEntity.ok().body(this.customerService.getById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable final Integer id, @Valid @RequestBody final CustomerDTO customerDTO) {
        final Customer customer = this.customerService.fromDTO(customerDTO);
        customer.setId(id);
        final Customer savedCustomer = this.customerService.update(customer);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> remove(@PathVariable final Integer id) {
        this.customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
