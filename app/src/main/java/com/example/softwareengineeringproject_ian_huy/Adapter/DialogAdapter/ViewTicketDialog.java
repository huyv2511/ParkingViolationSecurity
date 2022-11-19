package com.example.softwareengineeringproject_ian_huy.Adapter.DialogAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.softwareengineeringproject_ian_huy.Adapter.IMainActivity;
import com.example.softwareengineeringproject_ian_huy.Object.Ticket;
import com.example.softwareengineeringproject_ian_huy.R;
import com.squareup.picasso.Picasso;

public class ViewTicketDialog extends DialogFragment implements View.OnClickListener {
    private static final String TAG  = "ViewTicketDialog";
    TextView tv_TicketID, tv_ticketDate, tv_licensePlate, tv_carState, tv_carColor, tv_violationType,tv_carModel;
    ImageView row_carPic;

    private IMainActivity mainActivity;
    private Ticket mTicket;

    public static ViewTicketDialog newInstance(Ticket t){
        ViewTicketDialog dialog = new ViewTicketDialog();
        Bundle args = new Bundle();
        args.putParcelable("note", t);
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int style = DialogFragment.STYLE_NORMAL;
        int theme = android.R.style.Theme_Holo_Light_Dialog;
        setStyle(style, theme);

        mTicket = getArguments().getParcelable("note");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_view_ticket ,  container, false);
//        TextView tv_TicketID, tv_ticketDate, tv_licensePlate, tv_carState, tv_carColor, tv_violationType,tv_carModel;
//        ImageView row_carPic;

        tv_TicketID = view.findViewById(R.id.row_ticketID);
        tv_ticketDate = view.findViewById(R.id.row_ticketDate);
        tv_licensePlate = view.findViewById(R.id.row_licensePlate);
        tv_carState = view.findViewById(R.id.row_carState);
        tv_carColor = view.findViewById(R.id.row_carColor);
        row_carPic = view.findViewById(R.id.imageView_carPic);
        tv_carModel = view.findViewById(R.id.row_carModel);
        tv_violationType= view.findViewById(R.id.row_violationType);

        getDialog().setTitle("View Ticket");

        setInitialProperties();

        return view;
    }

    private void setInitialProperties() {
       tv_TicketID.setText(mTicket.getTicketID());
       tv_licensePlate.setText(mTicket.getLicensePlate());
       tv_ticketDate.setText(mTicket.getDate());
       tv_carColor.setText(mTicket.getcarColor());
       tv_carState.setText(mTicket.getcarState());
       tv_violationType.setText(mTicket.getviolationType());
        tv_carModel.setText(mTicket.getCarModel());
        Picasso.get()
                .load(mTicket.getImageUri())
                .into(row_carPic);

    }

    @Override
    public void onClick(View view) {

    }
}
