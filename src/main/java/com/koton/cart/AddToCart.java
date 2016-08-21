package com.koton.cart;

import com.koton.domain.ProductEntity;
import com.koton.utils.BaseEntityList;

/**
 * Created by MacbookPro on 21/08/16.
 */
public class AddToCart implements CartOperationStrategy{

    private int amount;
    private boolean overrideAmount = false;

    public AddToCart(int amount, boolean overrideAmount){
        this.amount = amount;
        this.overrideAmount = overrideAmount;
    }

    public AddToCart(int amount){
        this.amount = amount;
    }

    @Override
    public BaseEntityList<CartElement> updateList(ProductEntity p, BaseEntityList<CartElement> list) {
        CartElement ce = list.get(p.getId());

        if(ce!=null){
            if(!overrideAmount)
                ce.setAmount(ce.getAmount()+amount);
            else
                ce.setAmount(amount);
        }
        else{
            ce = new CartElement(p,amount);
            list.add(ce);
        }

        return list;
    }
}
