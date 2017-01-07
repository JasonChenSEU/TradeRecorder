package com.jason.traderecorder.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2016/12/29.
 */

public class Product extends Material{

    List<Composite> composites;

    public Product(String strName) {
        super(strName);
        composites = new ArrayList<>();
    }

    public boolean addMaterial(Material material, int num) {
        if(curCost == null)
            curCost = new CostRecord(material.getCurCost().price * num);
        else
            curCost = new CostRecord(curCost.getPrice() + material.getCurCost().price * num);
        return composites.add(new Composite(material, num));
    }

    public CostRecord Calculate() {
        double totalPrice = 0.0;
        for (Composite c :
                composites) {
            totalPrice += c.material.getCurCost().price * c.nums;
        }
        //CurCost: only update
        curCost = new CostRecord(totalPrice);
        return curCost;
    }


    public class Composite{
        Material material;
        int nums;

        public Composite(Material material, int nums) {
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
