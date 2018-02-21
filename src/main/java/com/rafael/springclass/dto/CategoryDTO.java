package com.rafael.springclass.dto;

import com.rafael.springclass.domain.Category;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NotEmpty(message = "required")
    @Length(min = 5, max = 80, message = "Size must be between 5 and 80")
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(final Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
