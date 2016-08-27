package de.ecomeal.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import java.util.ArrayList;

import de.ecomeal.R;
import de.ecomeal.adapter.AddressAdapter;
import de.ecomeal.fragment.BaseToolbarFragment;
import de.ecomeal.model.Address;

/**
 * Created by LS on 26.05.2016.
 */
public class AddressesActivity extends BaseToolbarActivity implements OnMapReadyCallback {

    //List
    private RecyclerView rvAddresses;
    private FirebaseRecyclerAdapter<Address, AddressAdapter.ViewHolderAddress> mAdapter;
    private GoogleMap googleMap;
    private DatabaseReference databaseReference;
    private ArrayList<Address> list;
    private Drawer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_address);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        initList();
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        initRecyclerAdapter();
        result = new DrawerBuilder()
                .withActivity(this)
                .withDrawerGravity(Gravity.END)
                .withSliderBackgroundColor(ContextCompat.getColor(this, R.color.red_ecomeal))
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("TEST").withTextColor(ContextCompat.getColor(this, R.color.white_ecomeal))).build();

    }

    private void initRecyclerAdapter() {
        Query productsQuery = databaseReference.child("Address");
        mAdapter = new AddressAdapter(Address.class, R.layout.item_address, AddressAdapter.ViewHolderAddress.class, productsQuery);
        rvAddresses.setAdapter(mAdapter);

    }

    private void setMapMarkers() {
        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            googleMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.common_plus_signin_btn_icon_light_pressed))
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(new LatLng(12.886, i)));
        }
    }

    private void initList() {
        rvAddresses = (RecyclerView) findViewById(R.id.recycler);
        rvAddresses.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        setMapMarkers();
        googleMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.common_plus_signin_btn_icon_light_pressed))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(41.889, -87.622)));
    }

}