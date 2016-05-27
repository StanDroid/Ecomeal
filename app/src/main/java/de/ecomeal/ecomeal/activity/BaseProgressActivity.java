package de.ecomeal.ecomeal.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseProgressActivity extends AppCompatActivity{

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgressDialog();
    }

    private void initProgressDialog(){
        mProgressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
    }

    public void showProgress(int progressMessageResId){
        mProgressDialog.setMessage(getString(progressMessageResId));
        mProgressDialog.show();
    }

    public void cancelProgress(){
        mProgressDialog.cancel();
    }

    public ProgressDialog getProgressDialog(){
        return mProgressDialog;
    }
}
