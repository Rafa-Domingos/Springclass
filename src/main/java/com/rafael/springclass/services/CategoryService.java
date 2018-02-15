package com.rafael.springclass.services;

import com.rafael.springclass.domain.Category;
import com.rafael.springclass.repositories.CategoryRepository;
import com.rafael.springclass.services.exceptions.DataIntegrityException;
import com.rafael.springclass.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    /**
     * {@link CategoryRepository} injection.
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Get a {@link Category} by its {@link Category#id}.
     *
     * @param id {@link Category#id} from the {@link Category} that request want to recovery.
     *
     * @return {@link Category} where {@link Category#id} is equals {@param id}
     */
    public Category getById(final Integer id) {
        final Category category = this.categoryRepository.findOne(id);

        if (category == null) {
            throw new ObjectNotFoundException("Objeto não encontrado. Id = " + id + ", tipo = " + Category.class.getName());
        }

        return category;
    }

    public Category save(final Category category) {
        return this.categoryRepository.save(category);
    }

    public Category update(final Category category) {
        this.getById(category.getId());
        return this.categoryRepository.save(category);
    }

    public void delete(final Integer id) {
        this.getById(id);
        try {
            this.categoryRepository.delete(id);
        } catch (final DataIntegrityViolationException e) {
            throw new DataIntegrityException("Esta categoria não pode ser excluída pois está relacionada a produtos");
        }
    }

    public List<Category> list() {
        return this.categoryRepository.findAll();
    }

    public Page<Category> listWithPagination(final Integer page, final Integer linesPerPage, final String orderBy,
                                   final String direction) {
        final PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return this.categoryRepository.findAll(pageRequest);
    }
}
