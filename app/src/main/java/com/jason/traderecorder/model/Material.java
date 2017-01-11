package com.jason.traderecorder.model;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Jason on 2016/12/29.
 */

public class Material{
    protected String strName;
    protected CostRecord curSale = null;
    protected Map<String, Double> preSales = new TreeMap<>();

    public Material(String strName, double curSale) {
        //Make sure that current cost price is equal to current sale price for materials
        this.strName = strName;
        this.curSale = new CostRecord(curSale);
        GlobalData.materialMap.put(strName,this);
    }

    public String getStrName() {
        return strName;
    }

    public CostRecord getCurSale() {
        return curSale;
    }

    public Map<String, Double> getPreSales() {
        return preSales;
    }

    public ListItem getItemForItem(){
        return new ListItem(this.strName,this.curSale.getPrice(),this.curSale.getPrice());
    }

    public void updateRecord(CostRecord curSale){
        if(curSale != null){
            preSales.put(curSale.recordTime, curSale.price);
        }
        this.curSale = curSale;
    }

    @Override
    public String toString() {
        return "Material{" +
                "strName='" + strName + '\'' +
                ", curSale=" + curSale +
                ", preSales=" + preSales +
                '}';
    }
}
