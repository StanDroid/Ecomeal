package de.ecomeal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import de.ecomeal.R;
import de.ecomeal.model.Address;

/**
 * Created by LS on 20.06.2016.
 */
public class AddressAdapter extends FirebaseRecyclerAdapter<Address, AddressAdapter.ViewHolderAddress> {

    public AddressAdapter(Class<Address> modelClass, int modelLayout, Class<AddressAdapter.ViewHolderAddress> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(AddressAdapter.ViewHolderAddress viewHolder, final Address model, int position) {
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Launch Details
//                ImageView imageView = new ImageView(context);
//                Picasso.with(context).load(model.getImage()).into(imageView);
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle(model.getName())
//                        .setMessage(model.getDetailDescriprtion())
//                        .setIcon(imageView.getDrawable())
//                        .setPositiveButton("Ok",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//                AlertDialog alert = builder.create();
//                alert.show();
//            }
//        });
        viewHolder.tvAddressLine.setText(model.getLine());
        viewHolder.tvObl.setText(model.getObl());
        viewHolder.tvIsEat.setText(String.format("Is Eat: %s", model.isEat()));
        viewHolder.tvIsBuy.setText(String.format("Is Pay: %s", model.isBuy()));
    }

    public static class ViewHolderAddress extends RecyclerView.ViewHolder {
        private TextView tvAddressLine;
        private TextView tvObl;
        private TextView tvIsBuy;
        private TextView tvIsEat;

        public ViewHolderAddress(View pItemView) {
            super(pItemView);
            tvAddressLine = (TextView) pItemView.findViewById(R.id.tv_address_line);
            tvObl = (TextView) pItemView.findViewById(R.id.tv_address_obl);
            tvIsEat = (TextView) pItemView.findViewById(R.id.tv_address_isEat);
            tvIsBuy = (TextView) pItemView.findViewById(R.id.tv_address_isBuy);
        }
    }
}
