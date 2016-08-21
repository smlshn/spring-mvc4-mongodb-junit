package com.koton.service;

import com.koton.domain.BaseEntity;
import com.koton.repository.BaseReporsitory;
import com.koton.utils.BaseEntityList;

import java.util.List;

/**
 * Created by MacbookPro on 20/08/16.
 */
public abstract class BaseService<T extends BaseEntity, R extends BaseReporsitory<T>> {

    public T findById(Long id){
        return getRepo().findOne(id);
    }

    public BaseEntityList<T> findAll(){
        return toBaseList(getRepo().findAll());
    }

    public BaseEntityList<T> toBaseList(List<T> list){
        return new BaseEntityList<T>(list);
    }
    public void deleteAll(){
        getRepo().deleteAll();
    }

    public List<T> save(List<T> entity){
        return getRepo().save(entity);
    }

    abstract R getRepo();
}
