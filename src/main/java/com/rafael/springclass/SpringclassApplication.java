package com.rafael.springclass;

import com.rafael.springclass.domain.*;
import com.rafael.springclass.domain.enums.CustomerType;
import com.rafael.springclass.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
public class SpringclassApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringclassApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
	    final Category category1 = new Category("Computing");
        final Category category2 = new Category("Office");

        final Product product1 = new Product("Laptop", new BigDecimal("100.00"));
        final Product product2 = new Product("Mouse", new BigDecimal("200.00"));
        final Product product3 = new Product("Pen", new BigDecimal("300.00"));

        category1.getProducts().addAll(Arrays.asList(product1, product2, product3));
        category2.getProducts().add(product2);

        product1.getCategories().add(category1);
        product1.getCategories().addAll(Arrays.asList(category1, category2));
        product3.getCategories().add(category1);

		this.categoryRepository.save(Arrays.asList(category1, category2));
		this.productRepository.save(Arrays.asList(product1, product2, product3));

		final State state1 = new State("Minas Gerais");
        final State state2 = new State("São Paulo");

        final City city1 = new City("Uberlândia", state1);
        final City city2 = new City("São Paulo", state2);
        final City city3 = new City("Campinas", state2);

        state1.getCities().add(city1);
        state2.getCities().addAll(Arrays.asList(city2, city3));

        this.stateRepository.save(Arrays.asList(state1, state2));
        this.cityRepository.save(Arrays.asList(city1, city2, city3));

        final Customer customer1 = new Customer("Maria", "maria@gmail.com", "3661666188", CustomerType.NATURAL);
        customer1.getPhoneNumbers().addAll(Arrays.asList("26754432", "943760008"));

        final Address address1 = new Address("Rua Flores", "300", "Apto 2", "Jardim", "08765-321", customer1, city1);
        final Address address2 = new Address("Av. Matos", "99", null, "Centro", "07532-921", customer1, city2);

        customer1.getAddresses().addAll(Arrays.asList(address1, address2));

        this.customerRepository.save(customer1);
        this.addressRepository.save(Arrays.asList(address1, address2));
	}
}
