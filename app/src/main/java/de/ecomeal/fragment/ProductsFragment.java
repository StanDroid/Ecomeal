package de.ecomeal.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import de.ecomeal.R;
import de.ecomeal.adapter.ViewHolderProduct;
import de.ecomeal.interfaces.OnListItemClickListener;
import de.ecomeal.model.Product;

/**
 * Created by LS on 26.05.2016.
 */
public class ProductsFragment extends BaseToolbarFragment{

    //List
    private RecyclerView rvProducts;
    private FirebaseRecyclerAdapter<Product, ViewHolderProduct> mAdapter;

    public static ProductsFragment newInstance() {
        ProductsFragment fragment = new ProductsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
//        setToolbarTitle(R.string.children);
        initList(view);
        initRecyclerAdapter();
        return view;
    }

    private void initRecyclerAdapter() {
        Query productsQuery = FirebaseDatabase.getInstance().getReference().child("Product");
        mAdapter = new FirebaseRecyclerAdapter<Product, ViewHolderProduct>(Product.class, R.layout.item_product,
                ViewHolderProduct.class, productsQuery) {
            @Override
            protected void populateViewHolder(final ViewHolderProduct viewHolder, final Product model, final int position) {

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch Details
                        ImageView imageView = new ImageView(getActivity());
                        Picasso.with(getActivity()).load(model.getImage()).into(imageView);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle(model.getName())
                                .setMessage(model.getDetailDescriprtion())
                                .setIcon(imageView.getDrawable())
                                .setPositiveButton("Ok",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
                viewHolder.bind(getActivity(), model);
            }
        };
        rvProducts.setAdapter(mAdapter);
    }

    private void initList(View view) {
        rvProducts = (RecyclerView) view.findViewById(R.id.recycler);
        rvProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}