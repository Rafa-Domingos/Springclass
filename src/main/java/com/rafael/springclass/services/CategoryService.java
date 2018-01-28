package com.rafael.springclass.services;

import com.rafael.springclass.domain.Category;
import com.rafael.springclass.repositories.CategoryRepository;
import com.rafael.springclass.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getById(final Integer id) {
        final Category category = this.categoryRepository.findOne(id);

        if (category == null) {
            throw new ObjectNotFoundException("Objeto não encontrado. Id = " + id + ", tipo = " + Category.class.getName());
        }

        return category;
    }
}
