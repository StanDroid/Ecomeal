package de.ecomeal.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.ecomeal.R;
import de.ecomeal.model.Product;

/**
 * Created by LS on 31.05.2016.
 */
public class ViewHolderProduct extends RecyclerView.ViewHolder {
    private TextView tvProductTitle;
    private ImageView imageViewProduct;

    public ViewHolderProduct(View pItemView) {
        super(pItemView);
        tvProductTitle = (TextView) pItemView.findViewById(R.id.tv_procduct_title);
        imageViewProduct = (ImageView) pItemView.findViewById(R.id.iv_product);
    }

    public void bind(Context context, Product model) {
        tvProductTitle.setText(model.getName());
        tvProductTitle.setTextColor(Color.parseColor(model.getTextColor()));
        Picasso.with(context).load(model.getImage()).into(imageViewProduct);
    }
}

