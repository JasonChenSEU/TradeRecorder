package com.jason.traderecorder.model;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Jason on 2016/12/29.
 */

public class Material {
    protected String strID;
    protected String strName;
    protected CostRecord curCost = null;
    protected CostRecord curSale = null;
    protected Map<String, Double> preSales = new TreeMap<>();

    public Material(String strName){
        this.strName = strName;
    }

    public Material(String strName, double curSale) {
        //Make sure that current cost price is equal to current sale price for materials
        this.strName = strName;
        this.curSale = new CostRecord(curSale);
        this.curCost = this.curSale;
    }

    public String getStrName() {
        return strName;
    }

    public CostRecord getCurCost() {
        return curCost;
    }

    public CostRecord getCurSale() {
        return curSale;
    }

    public Map<String, Double> getPreSales() {
        return preSales;
    }

    public void updateRecord(CostRecord curSale){
        if(curSale != null){
            preSales.put(curSale.recordTime, curSale.price);
        }
        this.curSale = curSale;
    }
}
