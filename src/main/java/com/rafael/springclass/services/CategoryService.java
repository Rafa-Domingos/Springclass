package com.rafael.springclass.services;

import com.rafael.springclass.domain.Category;
import com.rafael.springclass.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getById(final Integer id) {
        return this.categoryRepository.findOne(id);
    }
}
