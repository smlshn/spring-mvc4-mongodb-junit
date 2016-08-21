package com.koton.cart;

import com.koton.domain.ProductEntity;
import com.koton.utils.BaseEntityList;

/**
 * Created by MacbookPro on 21/08/16.
 */
public interface CartOperationStrategy {
    BaseEntityList<CartElement> updateList(ProductEntity p, BaseEntityList<CartElement> list);
}
