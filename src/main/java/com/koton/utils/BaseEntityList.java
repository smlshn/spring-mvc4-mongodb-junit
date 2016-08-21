package com.koton.utils;

import com.koton.domain.BaseEntity;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by MacbookPro on 20/08/16.
 */
public class BaseEntityList<T extends BaseEntity> extends ArrayList<T> {

    public BaseEntityList() {
        super();
    }

    public BaseEntityList(Collection<? extends T> c) {
        super(c);
    }


    public T get(Long entityId){
        int ind = indexOf(entityId);

        if(ind>-1)
            return get(ind);

        return null;

    }

    public boolean remove(Long id){
        int ind = indexOf(id);
        if(ind>-1){
            remove(ind);
            return true;
        }


        return false;
    }

    public int indexOf(Long entityId){
        return indexOf(new BaseEntity(entityId));
    }
}
