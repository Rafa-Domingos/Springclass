package com.rafael.springclass.resources;

import com.rafael.springclass.domain.Category;
import com.rafael.springclass.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    /**
     * {@link CategoryService} injection.
     */
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable final Integer id) {
        return ResponseEntity.ok().body(this.categoryService.getById(id));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody final Category category) {
        final Category savedCategory = this.categoryService.save(category);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCategory.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
