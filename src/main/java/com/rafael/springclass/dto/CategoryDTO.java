package com.rafael.springclass.dto;

import com.rafael.springclass.domain.Category;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class CategoryDTO  implements Serializable {

    /**
     * Generated serial version.
     */
    private static final long serialVersionUID = -5827024510147750800L;

    @Getter
    @Setter
    private Integer id;


    @Getter
    @Setter
    @NotEmpty(message = "Name required")
    @Length(min = 5, max = 80, message = "Name's size must be between 5 and 80 characters")
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(final Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
