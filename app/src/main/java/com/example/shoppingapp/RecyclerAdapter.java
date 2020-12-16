package com.example.shoppingapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context mContext;
    List<Product> products;

    private static final String TAG = RecyclerAdapter.class.getSimpleName();

    public RecyclerAdapter (Context context, List<Product> products){
        this.mContext = context;
        this.products = products;
    }


    public RecyclerAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.products_list_item_layout,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyViewHolder holder, int position) {

        Product product = products.get(position);

        Log.d(TAG, "5555555555555555555555555555555555555555555555555555555555555555555555555: "+product.getProduct_name());

        holder.mDescription.setText(product.getDescription());
        holder.mName.setText(product.getProduct_name());
        Glide.with(mContext).load(product.getImage_URL()).into(holder.mImageView);

        holder.mContainer.setOnClickListener(v -> {

            Intent intent = new Intent(mContext, ShopList.class);

            intent.putExtra("id",product.getId());

            mContext.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mName;
        TextView mDescription;
        ImageView mImageView;
        RelativeLayout mContainer;

        public MyViewHolder (View view){
            super(view);

            mName = view.findViewById(R.id.product_name);
            mImageView = view.findViewById(R.id.product_image);
            mDescription = view.findViewById(R.id.description);
            mContainer = view.findViewById(R.id.product_container);
        }
    }
}