package com.example.android.restapp.restPack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.restapp.R;

import java.util.ArrayList;

/**
 * Created by Android on 6/7/2017.
 */

public class ResultAdapter extends BaseAdapter {
    private ArrayList<ContactClass> contacts;
    private Context context;

    public ResultAdapter(ArrayList<ContactClass> results, Context context) {
        this.contacts = results;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ContactClass currentResult = (ContactClass) getItem(position);
        viewHolder.resultName.setText(currentResult.getName());
        viewHolder.resultLocation.setText(currentResult.getLocation());
        viewHolder.resultEmail.setText(currentResult.getEmail());
        return convertView;

    }
    private class ViewHolder{
        TextView resultName;
        TextView resultEmail;
        TextView resultLocation;
        public ViewHolder(View view){

            resultName = (TextView) view.findViewById(R.id.tv_name);
            resultEmail = (TextView) view.findViewById(R.id.tv_email);
            resultLocation = (TextView) view.findViewById(R.id.tv_location);
        }
    }
}
