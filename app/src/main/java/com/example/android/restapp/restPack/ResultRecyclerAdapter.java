package com.example.android.restapp.restPack;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.restapp.R;

import java.util.ArrayList;

/**
 * Created by Android on 6/8/2017.
 */

public class ResultRecyclerAdapter  extends RecyclerView.Adapter<ResultRecyclerAdapter.ViewHolder> {
    ArrayList<ContactClass> contacts;
    Context context;

    public ResultRecyclerAdapter(ArrayList<ContactClass> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recycle_item,parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContactClass contact = contacts.get(position);
        holder.nameTV.setText(contact.getName());
        holder.locationTV.setText(contact.getLocation());
        holder.emailTV.setText(contact.getEmail());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView nameTV;
        TextView emailTV;
        TextView locationTV;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTV = (TextView)itemView.findViewById(R.id.tv_name);
            emailTV = (TextView)itemView.findViewById(R.id.tv_email);
            locationTV = (TextView)itemView.findViewById(R.id.tv_location);
        }
    }
}
