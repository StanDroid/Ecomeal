package de.ecomeal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.ecomeal.R;
import de.ecomeal.interfaces.OnListItemClickListener;
import de.ecomeal.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;
    private final OnListItemClickListener<Product> onItemClickListener;

    public ProductAdapter(List<Product> childrenList, OnListItemClickListener<Product> onItemClickListener) {
        this.productList = childrenList;
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<Product> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        ////FIXME
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final Product product = productList.get(position);

        viewHolder.tvProductTitle.setText(product.getName());
        viewHolder.tvProductTitle.setTextColor(product.getTextColor());
        viewHolder.imageViewProduct.setImageDrawable(product.getImage());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(position, product);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvProductTitle;
        private ImageView imageViewProduct;

        public ViewHolder(View pItemView) {
            super(pItemView);
            tvProductTitle = (TextView) itemView.findViewById(R.id.tv_procduct_title);
            imageViewProduct = (ImageView ) pItemView.findViewById(R.id.iv_product);
        }
    }
}