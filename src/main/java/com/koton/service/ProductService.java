package com.koton.service;

import com.koton.domain.ProductEntity;
import com.koton.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MacbookPro on 20/08/16.
 */
@Service
@Transactional
public class ProductService extends BaseService<ProductEntity,ProductRepository>{

    @Autowired
    private ProductRepository productRepository;


    public List<ProductEntity> findByName(String name){
        return productRepository.findByName(name);
    }

    public List<ProductEntity> findByCategoryId(Long id){
        return productRepository.findByCategoryId(id);
    }


    protected ProductRepository getRepo() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
