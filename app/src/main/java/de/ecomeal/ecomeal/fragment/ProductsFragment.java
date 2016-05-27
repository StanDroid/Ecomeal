package de.ecomeal.ecomeal.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.ecomeal.ecomeal.R;
import de.ecomeal.ecomeal.adapter.ProductAdapter;
import de.ecomeal.ecomeal.interfaces.OnListItemClickListener;
import de.ecomeal.ecomeal.model.Product;

/**
 * Created by LS on 26.05.2016.
 */
public class ProductsFragment extends BaseToolbarFragment implements OnListItemClickListener<Product> {

    //List
    private RecyclerView rvChildren;
    private List<Product> childrenList = new ArrayList<>();
    private ProductAdapter productAdapter;

    public static ProductsFragment newInstance() {
        ProductsFragment fragment = new ProductsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
//        setToolbarTitle(R.string.children);
        initList(view);
        getProducts();
        return view;
    }

    private void initList(View view) {
        rvChildren = (RecyclerView) view.findViewById(R.id.rv_product);
        rvChildren.setLayoutManager(new LinearLayoutManager(getActivity()));
        productAdapter = new ProductAdapter(childrenList, this);
        rvChildren.setAdapter(productAdapter);
    }

    private void getProducts() {
        Product product1 = new Product("Желтая мука", ContextCompat.getDrawable(getActivity(),R.drawable.yellow_paket_500),ContextCompat.getColor(getActivity(),R.color.md_yellow_800)
                ,"Продукт создан из пшеничной муки высшего сорта с добавлением натурального желтого экстракта семян дерева Аннато. Этот экстракт богат на антиоксиданты, а по своей структуре и функциям в организме является схожими с витамином Е.");
        childrenList.add(product1);
        product1 = new Product("Голубая мука", ContextCompat.getDrawable(getActivity(),R.drawable.blue_paket_500),ContextCompat.getColor(getActivity(),R.color.md_blue_500)
                ,"Продукт создан из пшеничной муки высшего сорта с добавлением натурального желтого экстракта семян дерева Аннато. Этот экстракт богат на антиоксиданты, а по своей структуре и функциям в организме является схожими с витамином Е.");
        childrenList.add(product1);
        product1 = new Product("Красная мука", ContextCompat.getDrawable(getActivity(),R.drawable.red_paket_500),ContextCompat.getColor(getActivity(),R.color.md_red_300)
                ,"Продукт создан из пшеничной муки высшего сорта с добавлением натурального желтого экстракта семян дерева Аннато. Этот экстракт богат на антиоксиданты, а по своей структуре и функциям в организме является схожими с витамином Е.");
        childrenList.add(product1);
        product1 = new Product("Черная мука", ContextCompat.getDrawable(getActivity(),R.drawable.black_paket_500),ContextCompat.getColor(getActivity(),R.color.md_black_1000)
                ,"Продукт создан из пшеничной муки высшего сорта с добавлением натурального желтого экстракта семян дерева Аннато. Этот экстракт богат на антиоксиданты, а по своей структуре и функциям в организме является схожими с витамином Е.");
        childrenList.add(product1);
        product1 = new Product("Зеленая мука", ContextCompat.getDrawable(getActivity(),R.drawable.green_paket_500),ContextCompat.getColor(getActivity(),R.color.md_green_500)
                ,"Продукт создан из пшеничной муки высшего сорта с добавлением натурального желтого экстракта семян дерева Аннато. Этот экстракт богат на антиоксиданты, а по своей структуре и функциям в организме является схожими с витамином Е.");
        childrenList.add(product1);
    }

    @Override
    public void onItemClick(int position, Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(product.getName())
                .setMessage(product.getDetailDescriprtion())
                .setIcon(product.getImage())
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}