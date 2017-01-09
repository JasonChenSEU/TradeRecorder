package com.jason.traderecorder.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jason.traderecorder.R;
import com.jason.traderecorder.model.RecyclerViewItemClickListener;

/**
 * Created by asus1 on 2017/1/9.
 */
class ProductItemView extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvName;
    TextView tvCurPrice;

    private RecyclerViewItemClickListener mListener = null;

    public ProductItemView(View itemView, RecyclerViewItemClickListener listener) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.item_product_name);
        tvCurPrice = (TextView) itemView.findViewById(R.id.item_product_price);
        this.mListener = listener;
        itemView.setOnClickListener(this);

    }

    public TextView getTvName() {
        return tvName;
    }

    public TextView getTvCurPrice() {
        return tvCurPrice;
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onItemClick(view, getAdapterPosition());
        }
    }
}
