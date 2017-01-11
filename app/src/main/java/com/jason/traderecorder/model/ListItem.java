package com.jason.traderecorder.model;

/**
 * Created by Jason on 2017/1/11.
 */

public class ListItem {
    String strName = "Undefined";
    double dbCurCost = 0.0;
    double dbCurSale = 0.0;

    public ListItem(String strName, double dbCurCost, double dbCurSale) {
        this.strName = strName;
        this.dbCurCost = dbCurCost;
        this.dbCurSale = dbCurSale;
    }

    public String getStrName() {
        return strName;
    }

    public double getDbCurCost() {
        return dbCurCost;
    }

    public double getDbCurSale() {
        return dbCurSale;
    }
}
