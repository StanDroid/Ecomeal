package de.ecomeal.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import java.util.ArrayList;

import de.ecomeal.R;
import de.ecomeal.model.Address;

/**
 * Created by LS on 26.05.2016
 */
public class AddressesFragment extends BaseToolbarFragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private MapView mapView;
    private DatabaseReference databaseReference;
    private ArrayList<Address> list;
    private Drawer drawer;

    public static AddressesFragment newInstance() {
        AddressesFragment fragment = new AddressesFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        // Gets to GoogleMap from the MapView and does initialization stuff
        mapView.getMapAsync(this);
        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this.getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initRightDrawer() {
        DrawerBuilder drawerBuilder = new DrawerBuilder()
                .withActivity(getActivity())
                .withDrawerGravity(Gravity.END);

        for (Address address : list) {
            drawerBuilder.addDrawerItems(
                    new PrimaryDrawerItem().withName(address.getLine()).withTextColor(ContextCompat.getColor(getActivity(), R.color.red_ecomeal)));
        }
        drawer = drawerBuilder.build();
    }

    private void initData() {
        list = new ArrayList<>();
        databaseReference.child("Address").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Address address = postSnapshot.getValue(Address.class);
                            list.add(address);
                        }
                        setMapMarkers();
                        initRightDrawer();
                        // Move the camera instantly to hamburg with a zoom of 15.
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(49.9493649, 36.3063558), 9));
//                         Zoom in, animating the camera.
//                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("TAG", "getAdress:onCancelled", databaseError.toException());
                    }
                });
    }

    private void setMapMarkers() {
        for (int i = 0; i < list.size(); i++) {
            googleMap.addMarker(new MarkerOptions()
                    .title(list.get(i).getLine())
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.common_plus_signin_btn_icon_light_pressed))
//                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(new LatLng(list.get(i).getLat(), list.get(i).getLng())));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        initData();
    }

    @Override
    public void onDestroy() {
        if (drawer != null) {
            //get the DrawerLayout from the Drawer
            DrawerLayout drawerLayout = drawer.getDrawerLayout();
            //do whatever you want with the Drawer. Like locking it.
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_address, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_address) {
            drawer.openDrawer();
        }
        return super.onOptionsItemSelected(item);
    }
}