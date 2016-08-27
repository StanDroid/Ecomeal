package de.ecomeal.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
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
import de.ecomeal.model.Address;

/**
 * Created by LS on 26.05.2016.
 */
public class AddressesFragment extends BaseToolbarFragment implements OnMapReadyCallback {

    //List
    private RecyclerView rvAddresses;
    private FirebaseRecyclerAdapter<Address, AddressAdapter.ViewHolderAddress> mAdapter;
    private GoogleMap googleMap;
    private DatabaseReference databaseReference;
    private ArrayList<Address> list;
    private Drawer result;

    public static AddressesFragment newInstance() {
        AddressesFragment fragment = new AddressesFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        initList(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        initRecyclerAdapter();
        result = new DrawerBuilder()
                .withActivity(getActivity())
                .withDrawerGravity(Gravity.END)
//                .withSliderBackgroundColor(ContextCompat.getColor(getActivity(), R.color.red_ecomeal))
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("TEST").withTextColor(ContextCompat.getColor(getActivity(), R.color.red_ecomeal))).build();
    }

    private void initRecyclerAdapter() {
        Query productsQuery = databaseReference.child("Address");
        mAdapter = new AddressAdapter(Address.class, R.layout.item_address, AddressAdapter.ViewHolderAddress.class, productsQuery);
        rvAddresses.setAdapter(mAdapter);
    }

    private void setMapMarkers() {
        mAdapter.notifyDataSetChanged();
        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            googleMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.common_plus_signin_btn_icon_light_pressed))
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(new LatLng(12.886, i)));
        }
    }

    private void initList(View view) {
        rvAddresses = (RecyclerView) view.findViewById(R.id.recycler);
        rvAddresses.setLayoutManager(new LinearLayoutManager(getActivity()));
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

    @Override
    public void onDestroy() {
        //get the DrawerLayout from the Drawer
        DrawerLayout drawerLayout = result.getDrawerLayout();
        //do whatever you want with the Drawer. Like locking it.
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        super.onDestroy();
    }
}