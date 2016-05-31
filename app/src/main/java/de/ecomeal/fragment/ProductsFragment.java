package de.ecomeal.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.ecomeal.R;
import de.ecomeal.adapter.ProductAdapter;
import de.ecomeal.interfaces.OnListItemClickListener;
import de.ecomeal.model.Product;

/**
 * Created by LS on 26.05.2016.
 */
public class ProductsFragment extends BaseToolbarFragment implements OnListItemClickListener<Product> {

    //List
    private RecyclerView rvChildren;
    private List<Product> childrenList = new ArrayList<>();
    private ProductAdapter productAdapter;
    private Product product1;
    private DatabaseReference reference;
    private String TAG =" sds";

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
        getProducts();
        initList(view);
        return view;
    }

    private void initList(View view) {
        rvChildren = (RecyclerView) view.findViewById(R.id.rv_product);
        rvChildren.setLayoutManager(new LinearLayoutManager(getActivity()));
        productAdapter = new ProductAdapter(getActivity(),childrenList, this);
        rvChildren.setAdapter(productAdapter);
    }

    private void getProducts() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("Product");
        myref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Product",dataSnapshot.child("Product").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        reference = FirebaseDatabase.getInstance().getReference();
//        reference.child("Product").addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        product1 = dataSnapshot.getValue(Product.class);
//                        childrenList.add(product1);
//                        int ik = 1;
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
//                    }
//                });
//
//        product1 = new Product("Желтая мука", "https://firebasestorage.googleapis.com/v0/b/ecomeal-b7b8c.appspot.com/o/yellow_paket_500.png?alt=media&token=21202eff-a1d3-4b51-a58b-7d12c8e196a7", getString(R.color.md_yellow_800)
//                , "Продукт создан из пшеничной муки высшего сорта с добавлением натурального желтого экстракта семян дерева Аннато. Этот экстракт богат на антиоксиданты, а по своей структуре и функциям в организме является схожими с витамином Е.");
//        childrenList.add(product1);
//        product1 = new Product("Голубая мука", ""+R.drawable.blue_paket_500, ""+R.color.md_blue_500
//                , "Продукт создан из пшеничной муки высшего сорта с добавлением натурального голубого экстракта водоросли Спирулины. Содержит много белка и аминокислот. По количеству витаминов, превосходит большинство растений. В состав входят витамины E, B1, B2, B6, K, PP, C, D, пантотеновая и фолиевая кислоты, биотин.");
//        childrenList.add(product1);
//        product1 = new Product("Красная мука", ""+R.drawable.red_paket_500, "" + R.color.md_red_300
//                , "Продукт создан из пшеничной муки высшего сорта с добавлением натурального красного пигмента получаемого из природной карминовой кислоты. Этот пигмент известен еще со времен индейцев Майя.");
//        childrenList.add(product1);
//        product1 = new Product("Черная мука", ""+ R.drawable.black_paket_500, ""+R.color.md_black_1000
//                , "Продукт создан из пшеничной муки высшего сорта с добавлением натурального Бамбукового активированного угля. Растительный уголь - это проверенное средство для очищения кишечника и мощнейший антиоксидант. Выводит токсины и соли тяжелых металлов. Применяется при детокс-диетах.");
//        childrenList.add(product1);
//        product1 = new Product("Зеленая мука", ""+R.drawable.green_paket_500, ""+R.color.md_green_500
//                , "Продукт создан из пшеничной муки высшего сорта с добавлением натурального зеленого экстракта «хлорофилла медных комплексов». Хлорофилл и его медные комплексы отлично подходят в качестве биологически-активных добавок, восстанавливающих уровень гемоглобина.");
//        childrenList.add(product1);
    }

    private void saveProduct() {
        reference = FirebaseDatabase.getInstance().getReference();
        for (Product p: childrenList) {
            reference.child("Product").push().setValue(p);
        }
    }

    @Override
    public void onItemClick(int position, Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(product.getName())
                .setMessage(product.getDetailDescriprtion())
                .setIcon(ContextCompat.getDrawable(getActivity(), Color.parseColor(product.getImage())))
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
//        saveProduct();
    }

}