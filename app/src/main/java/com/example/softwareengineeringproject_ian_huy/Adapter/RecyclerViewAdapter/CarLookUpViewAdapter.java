package com.example.softwareengineeringproject_ian_huy.Adapter.RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softwareengineeringproject_ian_huy.Object.Car;
import com.example.softwareengineeringproject_ian_huy.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

    public class CarLookUpViewAdapter extends FirestoreRecyclerAdapter<Car, CarLookUpViewAdapter.CarHolder > {
    private OnItemClickListener listener;

    public CarLookUpViewAdapter(@NonNull @NotNull FirestoreRecyclerOptions options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull CarHolder holder, int position, @NonNull Car model) {
        holder.carColor_tv.setText(model.getCarColor());
        holder.carModel_tv.setText(model.getCarModel());
        holder.carState_tv.setText(model.getCarState());
        holder.licensePlate_tv.setText(model.getLicensePlate());
    }

    @NonNull
    @NotNull
    @Override
    public CarHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_card_holder,parent,false);
        return new CarHolder(v);
    }


    class CarHolder extends RecyclerView.ViewHolder{
        TextView licensePlate_tv, carState_tv, carModel_tv, carColor_tv;
        public CarHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            licensePlate_tv = itemView.findViewById(R.id.tv_licensePlate_lookUp);
            carState_tv = itemView.findViewById(R.id.tv_carState_lookUp);
            carModel_tv = itemView.findViewById(R.id.tv_carModel_lookUp);
            carColor_tv = itemView.findViewById(R.id.tv_carColor_lookUp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(pos),pos);
                    }
                }
            });
        }
    }

    public void updateCarList(ArrayList<Car> newList){

    }

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
