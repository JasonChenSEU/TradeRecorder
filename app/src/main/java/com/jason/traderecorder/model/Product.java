package com.jason.traderecorder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Jason on 2016/12/29.
 */

public class Product implements Serializable{

    Map<String, Integer> mapComposites = new HashMap<>();
    List<Product> lsCompProducts = new ArrayList<>();
    List<Material> lsCompMaterials = new ArrayList<>();

    protected String strName;

    protected CostRecord curCost = null;
    protected CostRecord curSale = null;
    protected Map<String, Double> preSales = new TreeMap<>();

    public Product(String strName) {
        this.strName = strName;
        GlobalData.productMap.put(strName,this);
//        composites = new ArrayList<>();
    }

    public Product(String strName, double curSale) {
        this.strName = strName;
        this.curSale = new CostRecord(curSale);
        GlobalData.productMap.put(strName,this);
//        composites = new ArrayList<>();
    }

    public String getStrName() {
        return strName;
    }

    public CostRecord getCurSale() {
        return curSale;
    }

    public CostRecord getCurCost() {
        return curCost;
    }

    public Map<String, Double> getPreSales() {
        return preSales;
    }

    /**
     * 更新Sale的记录
     * @param newSale 参数为可自定义时间的CostRecord
     */
    public void updateRecord(CostRecord newSale){
        if(curSale != null){
            preSales.put(curSale.recordTime, curSale.price);
        }
        this.curSale = newSale;
    }

    /**
     * 更新Sale的记录
     * @param newSale double即可
     */
    public void updateRecord(double newSale){
        if(curSale != null){
            preSales.put(curSale.recordTime, curSale.price);
        }
        this.curSale = new CostRecord(newSale);
    }

    /**
     * 增加Material的同时会更新cost数值，component数组
     * @param material 材料
     * @param num 数量
     * @return
     */
    public void addMaterial(Material material, int num) {
        if(curCost == null)
            curCost = new CostRecord(material.getCurSale().price * num);
        else
            curCost = new CostRecord(curCost.getPrice() + material.getCurSale().price * num);
        mapComposites.put(material.getStrName(),num);
        lsCompMaterials.add(material);
    }

    public void addProduct(Product product, int num){
        if(curCost == null)
            curCost = new CostRecord(product.getCurCost().price * num);
        else
            curCost = new CostRecord(curCost.getPrice() + product.getCurCost().price * num);
        mapComposites.put(product.getStrName(),num);
        lsCompProducts.add(product);
    }

    public CostRecord Calculate() {
        double totalPrice = 0.0;
        for (Material c :
                lsCompMaterials) {
            int nums = mapComposites.get(c.getStrName());
            totalPrice += c.getCurSale().price * nums;
        }
        for (Product p :
                lsCompProducts) {
            int nums = mapComposites.get(p.getStrName());
            totalPrice += p.getCurCost().price * nums;
        }
        //CurCost: only update
        curCost = new CostRecord(totalPrice);
        return curCost;
    }
}
