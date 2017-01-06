package com.jason.traderecorder.model;

import java.util.List;

/**
 * Created by Jason on 2016/12/29.
 */

public class Product extends Material{

    List<Composite> composites;

    public Product(String strName, List<Composite> composites) {
        super(strName);
        this.composites = composites;
        this.curCost = Calculate();
    }

    private CostRecord Calculate() {
        double totalPrice = 0.0;
        for (Composite c :
                composites) {
            totalPrice += c.material.getCurCost().price * c.nums;
        }
        return new CostRecord(totalPrice);
    }


    public class Composite{
        Product material;
        int nums;

        public Composite(Product material, int nums) {
            this.material = material;
            this.nums = nums;
        }
    }

    public class ProductItem{
        public String getProductName(){
            return getStrName();
        }

        public Double getProductCurPrice(){
            return getCurSale().price;
        }
    }

}
