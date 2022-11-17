package com.example.softwareengineeringproject_ian_huy.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.softwareengineeringproject_ian_huy.R;

import org.jetbrains.annotations.NotNull;

public class ViewCarInfoDialog extends AppCompatDialogFragment {
    private TextView carOwnerName_tv, carOwnerPhoneNumber_tv, carOwnerEmail_tv;

    public ViewCarInfoDialog(String carOwnerName, String carOwnerPhoneNumber, String carOwnerEmail) {
        this.carOwnerName = carOwnerName;
        this.carOwnerPhoneNumber = carOwnerPhoneNumber;
        this.carOwnerEmail = carOwnerEmail;
    }

    private String carOwnerName, carOwnerPhoneNumber, carOwnerEmail;


    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.car_owner_info_dialog,null);
        builder.setView(view)
                .setTitle("Car Owner Info");
        carOwnerEmail_tv = view.findViewById(R.id.tv_CarOwner_email);
        carOwnerName_tv = view.findViewById(R.id.tv_CarOwner_name);
        carOwnerPhoneNumber_tv = view.findViewById(R.id.tv_CarOwner_phoneNumber);

        carOwnerPhoneNumber_tv.setText(carOwnerPhoneNumber);
        carOwnerEmail_tv.setText(carOwnerEmail);
        carOwnerName_tv.setText(carOwnerName);
        return builder.create();
    }


}
