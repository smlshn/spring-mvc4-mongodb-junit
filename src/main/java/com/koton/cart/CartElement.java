package com.koton.cart;

import com.koton.domain.BaseEntity;
import com.koton.domain.ProductEntity;

/**
 * Created by MacbookPro on 21/08/16.
 */
public class CartElement extends BaseEntity {
    private ProductEntity product;
    private int amount;

    public CartElement(ProductEntity product, int amount) {
        this.product = product;
        this.amount = amount;
        this.setId(product.getId());
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
