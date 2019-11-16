package com.example.asus.ayojoon.ViewHolder;

import android.content.ClipData;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.ayojoon.Interfaces.ItemClickListener;
import com.example.asus.ayojoon.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textproname ,txtproprice,txtproquantity ;
    private ItemClickListener itemClickListener ;



    public CartViewHolder(@NonNull View itemView) {
        super(itemView);


        textproname = itemView.findViewById(R.id.cart_product_name) ;
        txtproprice = itemView.findViewById(R.id.cart_product_price) ;

        txtproquantity= itemView.findViewById(R.id.cart_product_quantity) ;






    }

    public void setItemClickListener (ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);
    }


}
