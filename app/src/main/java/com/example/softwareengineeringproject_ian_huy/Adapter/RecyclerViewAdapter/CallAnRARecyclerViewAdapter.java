package com.example.softwareengineeringproject_ian_huy.Adapter.RecyclerViewAdapter;

import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softwareengineeringproject_ian_huy.Object.RA;
import com.example.softwareengineeringproject_ian_huy.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import android.content.Context;


import org.jetbrains.annotations.NotNull;

public class CallAnRARecyclerViewAdapter extends FirestoreRecyclerAdapter<RA, CallAnRARecyclerViewAdapter.CardHolder> {
    Context mContext;
    public CallAnRARecyclerViewAdapter(@NonNull @NotNull FirestoreRecyclerOptions<RA> options, Context context) {
        super(options);
        this.mContext = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull CardHolder holder, int position, @NonNull @NotNull RA model) {

        holder.raName_tv.setText("Name : "+ model.getName());
        holder.phoneNum_tv.setText("phone number : "+ model.getPhoneNumber());
        holder.email_tv.setText("email : "+ model.getEmail());
        holder.building_tv.setText("building : "+ model.getBuildingPos());

        holder.callRA_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNum = model.getPhoneNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phoneNum));
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @NonNull
    @NotNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_call_an_ra_rv,parent,false);

        return new CardHolder(v);
    }

    class CardHolder extends RecyclerView.ViewHolder{
        TextView raName_tv,phoneNum_tv,email_tv, building_tv;
        Button callRA_btn;
        public CardHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            raName_tv= itemView.findViewById(R.id.raName);
            phoneNum_tv= itemView.findViewById(R.id.raPhoneNumber);
            email_tv = itemView.findViewById(R.id.raEmail);
            building_tv = itemView.findViewById(R.id.raBuildingPos);
            callRA_btn = itemView.findViewById(R.id.callRA_btn);


        }
    }

}
