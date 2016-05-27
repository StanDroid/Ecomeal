package de.ecomeal.ecomeal.activity;

import android.widget.TextView;

import de.ecomeal.ecomeal.R;


public class BaseToolbarActivity extends BaseProgressActivity {

    public void setToolbarTitle(int titleResId){
        ((TextView)findViewById(R.id.tv_toolbar_title)).setText(titleResId);
    }

    public void setToolbarTitle(String title){
        ((TextView)findViewById(R.id.tv_toolbar_title)).setText(title);
    }
}
