package com.jason.traderecorder.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jason.traderecorder.R;
import com.jason.traderecorder.model.CostRecord;
import com.jason.traderecorder.model.ListItem;
import com.jason.traderecorder.model.Material;
import com.jason.traderecorder.model.Product;
import com.jason.traderecorder.model.RecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by asus1 on 2017/1/9.
 */
class ProductItemAdapter extends RecyclerView.Adapter {

    List<ListItem> products = new ArrayList<>();

    private RecyclerViewItemClickListener mitemClickListener = null;

    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        mitemClickListener = listener;
    }

    public void add(ListItem p) {
        products.add(p);
        notifyDataSetChanged();
    }

    public void clear() {
        products.clear();
        notifyDataSetChanged();
    }

    public void remove(String proName) {
        Iterator<ListItem> it = products.iterator();
        while (it.hasNext()) {
            ListItem temp = it.next();
            if (temp.getStrName().equals(proName))
                it.remove();
        }
        notifyDataSetChanged();
    }

    public ListItem getItem(int position) {
        return products.get(position);
    }

    @Override
    public ProductItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductItemView(view, mitemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductItemView productItemView = (ProductItemView) holder;

        productItemView.getTvName().setText(products.get(position).getStrName());
        productItemView.getTvCostPrice().setText(String.valueOf(products.get(position).getDbCurCost()));
        productItemView.getTvSalePrice().setText(String.valueOf(products.get(position).getDbCurSale()));
    }


    @Override
    public int getItemCount() {
        return products.size();
    }
}
