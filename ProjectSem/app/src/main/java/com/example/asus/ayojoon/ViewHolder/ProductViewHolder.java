package com.example.asus.ayojoon.ViewHolder;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.ayojoon.Interfaces.ItemClickListener;
import com.example.asus.ayojoon.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtname,txtdesc ;
    public ImageView imageView ;
    public ItemClickListener listner ;

    public ProductViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.im_pho) ;
        txtname= (TextView) itemView.findViewById(R.id.text_pho) ;
        txtdesc= (TextView) itemView.findViewById(R.id.text_desc_pho) ;



    }

    public void setItemClickListener (ItemClickListener listener)
    {
        this.listner = listner ;
    }

    @Override
    public void onClick(View view) {

        listner.onClick(view,getAdapterPosition(),false);
    }
}
