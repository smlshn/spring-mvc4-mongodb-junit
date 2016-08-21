package com.koton.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by MacbookPro on 20/08/16.
 */
@Document
public class CategoryEntity extends BaseEntity{


    private String name;

    public CategoryEntity() {
        super();
    }

    public CategoryEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
