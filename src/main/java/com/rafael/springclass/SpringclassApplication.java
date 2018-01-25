package com.rafael.springclass;

import com.rafael.springclass.domain.Category;
import com.rafael.springclass.domain.Product;
import com.rafael.springclass.repositories.CategoryRepository;
import com.rafael.springclass.repositories.ProductRepository;
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
	}
}
