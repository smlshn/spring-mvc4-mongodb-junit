package com.koton.repository;

import com.koton.domain.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by MacbookPro on 20/08/16.
 */
public interface BaseReporsitory<T extends BaseEntity> extends MongoRepository<T, Long> {


}
