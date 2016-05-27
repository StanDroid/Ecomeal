package de.ecomeal.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import de.ecomeal.activity.BaseToolbarActivity;


public class BaseToolbarFragment extends Fragment {

    void setBackArrowOnToolbar(){
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setToolbarTitle(int titleResId){
        ((BaseToolbarActivity) getActivity()).setToolbarTitle(titleResId);
    }

    void setToolbarTitle(String title){
        ((BaseToolbarActivity) getActivity()).setToolbarTitle(title);
    }
}