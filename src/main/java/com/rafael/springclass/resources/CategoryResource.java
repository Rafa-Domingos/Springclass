package com.rafael.springclass.resources;

import com.rafael.springclass.domain.Category;
import com.rafael.springclass.dto.CategoryDTO;
import com.rafael.springclass.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    /**
     * {@link CategoryService} injection.
     */
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> getById() {
        final List<Category> categories = this.categoryService.list();
        final List<CategoryDTO> categoryDTOList = categories.stream().map(CategoryDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoryDTOList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> getById(@PathVariable final Integer id) {
        return ResponseEntity.ok().body(this.categoryService.getById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody final Category category) {
        final Category savedCategory = this.categoryService.save(category);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCategory.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable final Integer id, @RequestBody final Category category) {
        category.setId(id);
        final Category savedCategory = this.categoryService.update(category);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> remove(@PathVariable final Integer id) {
        this.categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
