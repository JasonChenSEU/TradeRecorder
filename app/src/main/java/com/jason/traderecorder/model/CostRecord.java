package com.jason.traderecorder.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jason on 2016/12/29.
 */

public class CostRecord implements Serializable{
    double price;
    String recordTime;

    public double getPrice() {
        return price;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public CostRecord(double price) {
        this.price = price;
        this.recordTime = new Date().toString();
    }
}
