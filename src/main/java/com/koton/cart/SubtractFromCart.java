package com.koton.cart;

import com.koton.domain.ProductEntity;
import com.koton.utils.BaseEntityList;

/**
 * Created by MacbookPro on 21/08/16.
 */
public class SubtractFromCart implements CartOperationStrategy{

    private int amount;
    private boolean removeAll=false;


    public SubtractFromCart(boolean removeAll){
        this.removeAll = removeAll;
    }

    public SubtractFromCart(int amount){
        this.amount = amount;
    }

    @Override
    public BaseEntityList<CartElement> updateList(ProductEntity p, BaseEntityList<CartElement> list) {
        CartElement ce = list.get(p.getId());

        if(ce!=null) {
            if (removeAll || ce.getAmount()==amount || ce.getAmount()<amount)
                list.remove(ce.getId());
            else
                ce.setAmount(ce.getAmount() - amount);
        }

        return list;
    }
}
