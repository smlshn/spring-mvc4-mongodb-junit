package com.koton.service;

import com.koton.domain.CategoryEntity;
import com.koton.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MacbookPro on 20/08/16.
 */
@Service
@Transactional
public class CategoryService extends BaseService<CategoryEntity,CategoryRepository>{

    @Autowired
    private CategoryRepository categoryRepository;



    public CategoryEntity create(CategoryEntity category){
        return categoryRepository.save(category);
    }

    public void deleteAll(){
        categoryRepository.deleteAll();
    }

    protected CategoryRepository getRepo(){
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
