package com.rafael.springclass;

import com.rafael.springclass.domain.Category;
import com.rafael.springclass.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringclassApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringclassApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		this.categoryRepository.save(Arrays.asList(new Category(null, "Computing"),
				new Category(null, "Office")));
	}
}
