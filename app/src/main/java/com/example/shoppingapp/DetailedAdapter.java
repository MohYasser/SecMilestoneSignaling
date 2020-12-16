package com.example.shoppingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DetailedAdapter extends RecyclerView.Adapter<DetailedAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<Shop_enhanced> fds;

    public DetailedAdapter (Context context, List<Shop_enhanced> fds){
        this.mContext = context;
        this.fds = fds;

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mName;
        TextView mSp_offers;
        TextView mPrice;
        TextView mDistance;

        public MyViewHolder (View view){
            super(view);

            mName = view.findViewById(R.id.product_name);
            mSp_offers = view.findViewById(R.id.sp_offers);
            mPrice = view.findViewById(R.id.price);
            mDistance = view.findViewById(R.id.distance);
        }
    }


    public DetailedAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.shops_list_item_layout,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull DetailedAdapter.MyViewHolder holder, int position) {
        final Shop_enhanced shop = fds.get(position);

        holder.mName.setText(shop.getShop_name());
        holder.mSp_offers.setText(shop.getShop_sp_offers());
        holder.mPrice.setText((int) shop.getShop_price());
        holder.mDistance.setText(shop.getShop_distance());
    }

    @Override
    public int getItemCount() {
        return fds.size();
    }
}