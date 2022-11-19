package com.example.softwareengineeringproject_ian_huy.Adapter.RecyclerViewAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softwareengineeringproject_ian_huy.Adapter.IMainActivity;
import com.example.softwareengineeringproject_ian_huy.Object.Ticket;
import com.example.softwareengineeringproject_ian_huy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import android.content.Context;


public class ticketManagementAdapter extends RecyclerView.Adapter<ticketManagementAdapter.TicketMangeViewHolder> {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private IMainActivity iMainActivity;
    ArrayList<Ticket> ticketList;
    Context context;
    final  String TAG  = "ADAPTER";
    private OnItemClickListener listener;
    public ticketManagementAdapter(ArrayList<Ticket> ticketList, Context context){
        this.ticketList = ticketList;
        this.context = context;
    }



    @NonNull
    @Override
    public TicketMangeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_ticket_management, parent, false);
        return new TicketMangeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketMangeViewHolder holder, int position) {
        Ticket t = ticketList.get(position);
        holder.tv_TicketID.setText(t.getTicketID());
        holder.tv_licensePlate.setText(t.getLicensePlate());
        holder.tv_ticketDate.setText(t.getDate());
//        holder.tv_carColor.setText(t.getcarColor());
//        holder.tv_carState.setText(t.getcarState());
//
//        holder.tv_violationType.setText(t.getviolationType());
//
//        holder.tv_carModel.setText(t.getCarModel());
//        Picasso.get()
//                .load(t.getImageUri())
//                .into(holder.row_carPic);
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ticket t = ticketList.get(holder.getAdapterPosition());
                deleteTicket(t);
            }
        });

    }

    private void deleteTicket(Ticket t) {
        DocumentReference documentReference = db.collection("tickets")
                .document(t.getTicketID());
        Log.i(TAG," okay"+ t.getTicketID());

        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete( Task<Void> task) {
                if(task.isSuccessful()){
                    ticketList.remove(t);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        iMainActivity = (IMainActivity) recyclerView.getContext();
    }

    public class TicketMangeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_TicketID, tv_ticketDate, tv_licensePlate, tv_carState, tv_carColor, tv_violationType,tv_carModel;
        ImageView row_carPic;
        Button deleteBtn, viewMorebtn;
        public TicketMangeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_TicketID = itemView.findViewById(R.id.row_ticketID);
            tv_ticketDate = itemView.findViewById(R.id.row_ticketDate);
            tv_licensePlate = itemView.findViewById(R.id.row_licensePlate);
            viewMorebtn = itemView.findViewById(R.id.viewTicket_btn);
//            tv_carState = itemView.findViewById(R.id.row_carState);
//            tv_carColor = itemView.findViewById(R.id.row_carColor);
//            row_carPic = itemView.findViewById(R.id.imageView_carPic);
//            tv_carModel = itemView.findViewById(R.id.row_carModel);
//            tv_violationType= itemView.findViewById(R.id.row_violationType);
           deleteBtn= itemView.findViewById(R.id.row_delete);
           viewMorebtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   int selectedIndex = getAdapterPosition();
//                   iMainActivity = (IMainActivity) context;
               }
           });
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   int pos = getAbsoluteAdapterPosition();
                   if(pos!= RecyclerView.NO_POSITION){
                       listener.onItemClick(getAdapterPosition());
                   }

               }
           });
        }

        @Override
        public void onClick(View view) {
            int selectedIndex = getAdapterPosition();
//            iMainActivity = (IMainActivity) context;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
