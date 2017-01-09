package com.jason.traderecorder.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jason.traderecorder.R;
import com.jason.traderecorder.model.CostRecord;
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

    List<Product> products = new ArrayList<>();

    private RecyclerViewItemClickListener mitemClickListener = null;

    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        mitemClickListener = listener;
    }

    public void add(Product p) {
        products.add(p);
        notifyDataSetChanged();
    }

    public void clear() {
        products.clear();
        notifyDataSetChanged();
    }

    public void remove(String proName) {
        Iterator<Product> it = products.iterator();
        while (it.hasNext()) {
            Product temp = it.next();
            if (temp.getStrName().equals(proName))
                it.remove();
        }
        notifyDataSetChanged();
    }

    public Product getItem(int position) {
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
        CostRecord temp = products.get(position).getCurSale();
        if (temp == null)
            productItemView.getTvCurPrice().setText("0.0");
        else
            productItemView.getTvCurPrice().setText(String.valueOf(temp.getPrice()));
    }


    @Override
    public int getItemCount() {
        return products.size();
    }
}
