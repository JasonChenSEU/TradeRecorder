package com.jason.traderecorder.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jason.traderecorder.R;
import com.jason.traderecorder.model.Product;
import com.jason.traderecorder.model.RecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        ProductItemAdapter adapter = new ProductItemAdapter();
//        mRecyclerView.setAdapter();


    }

    private class ProductItemView extends RecyclerView.ViewHolder implements View.OnClickListener{

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
            if(mListener != null){
                mListener.onItemClick(view,getAdapterPosition());
            }
        }
    }

    private class ProductItemAdapter extends RecyclerView.Adapter{

        List<Product> products = new ArrayList<>();

        private RecyclerViewItemClickListener mitemClickListener = null;

        public void setOnItemClickListener(RecyclerViewItemClickListener listener){
            mitemClickListener = listener;
        }

        public void add(Product p){
            products.add(p);
            notifyDataSetChanged();
        }

        public void clear(){
            products.clear();
            notifyDataSetChanged();
        }

        public void remove(String proName){
            Iterator<Product> it = products.iterator();
            while(it.hasNext()){
                Product temp = it.next();
                if(temp.getStrName().equals(proName))
                    it.remove();
            }
            notifyDataSetChanged();
        }

        public Product getItem(int position){
            return products.get(position);
        }

        @Override
        public ProductItemView onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
            return new ProductItemView(view,mitemClickListener);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ProductItemView productItemView = (ProductItemView) holder;

            productItemView.getTvName().setText(products.get(position).getStrName());
            productItemView.getTvCurPrice().setText(String.valueOf(products.get(position).getCurSale().getPrice()));
        }


        @Override
        public int getItemCount() {
            return products.size();
        }
    }
}
