package com.example.softwareengineeringproject_ian_huy.Adapter.DialogAdapter;



import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

;import com.example.softwareengineeringproject_ian_huy.R;

public class RegisterNewCarDialog extends AppCompatDialogFragment {
    private EditText  newCarModel_et, newCarLicensePlate_et;
    private Spinner newCarState_sp, newCarColor_sp;
//    private AddExerciseDialogListener addExerciseDialogListener;
    private registerNewCarListener resListener;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.register_new_car,null);
        builder.setView(view)
                .setTitle("Add new exercise")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                String newExercise = new_exercise_et.getText().toString();
                String newCarModel = newCarModel_et.getText().toString();
                String newCarState = newCarState_sp.getSelectedItem().toString();
                String newCarLicensePlate = newCarLicensePlate_et.getText().toString();
                String newCarColor = newCarColor_sp.getSelectedItem().toString();
                resListener.newData(newCarState,newCarLicensePlate,newCarModel,newCarColor);
            }
        });

        newCarModel_et = view.findViewById(R.id.dia_carModel);
        newCarLicensePlate_et = view.findViewById(R.id.dia_licensePlate);
        newCarState_sp = view.findViewById(R.id.dia_carState);
        newCarColor_sp = view.findViewById(R.id.dia_carColor);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            resListener = (registerNewCarListener) context;
        } catch(ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface registerNewCarListener{
        void newData(String newCarState, String newCarLicensePlate, String newCarModel, String newCarColor);
    }
}

