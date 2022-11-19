package com.example.softwareengineeringproject_ian_huy.Adapter.DialogAdapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.softwareengineeringproject_ian_huy.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class ViewMoreTicketDialog extends AppCompatDialogFragment {
    public ViewMoreTicketDialog(String carColor, String carState, String carModel, String date, String imageUri, String licensePlate, String violationType) {
        this.carColor = carColor;
        this.carState = carState;
        this.carModel = carModel;
        this.date = date;
        this.imageUri = imageUri;
        this.licensePlate = licensePlate;
        this.violationType = violationType;
    }

    private String carColor, carState, carModel, date, imageUri, licensePlate, violationType;
    private TextView carColor_tv, carState_tv, carModel_Tv, date_tv, licensePlate_tv, violationType_tv;
    private ImageView imageView;


    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.card_view_ticket_alertdialog,null);
        builder.setView(view)
                .setTitle("Ticket Info");

        carColor_tv = view.findViewById(R.id.ticketDialog_carColor);
        carState_tv = view.findViewById(R.id.ticketDialog_carState);
        carModel_Tv = view.findViewById(R.id.ticketDialog_carModel);
        date_tv = view.findViewById(R.id.ticketDialog_Date);
        licensePlate_tv = view.findViewById(R.id.ticketDialog_licensePlate);
        violationType_tv = view.findViewById(R.id.ticketDialog_violationType);
        imageView = view.findViewById(R.id.ticketDialog_image);

        carColor_tv.setText(carColor);
        carState_tv.setText(carState);
        carModel_Tv.setText(carModel);
        date_tv.setText(date);
        licensePlate_tv.setText(licensePlate);
        violationType_tv.setText(violationType);

        Picasso.get().load(imageUri).into(imageView);
        return builder.create();
    }
}
