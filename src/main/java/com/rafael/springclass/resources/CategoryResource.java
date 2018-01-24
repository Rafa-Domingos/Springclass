package com.rafael.springclass.resources;

import com.rafael.springclass.domain.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> restTest() {
        final List<Category> categories = new ArrayList<>();

        categories.add(new Category(1, "Computing"));
        categories.add(new Category(2, "Office"));

        return categories;
    }
}
