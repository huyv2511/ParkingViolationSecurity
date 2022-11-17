package com.example.softwareengineeringproject_ian_huy.Adapter;

import com.example.softwareengineeringproject_ian_huy.Object.Ticket;

public interface IMainActivity {

    void createNewTicket(String title, String content);

    void updateTicket(Ticket t);

    void onTicketSelected(Ticket t);

}