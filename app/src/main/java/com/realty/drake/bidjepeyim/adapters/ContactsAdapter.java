package com.realty.drake.bidjepeyim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.realty.drake.bidjepeyim.R;
import com.realty.drake.bidjepeyim.models.Contacts;

import java.util.ArrayList;

/**
 * Created by drake on 8/20/18
 */
public class ContactsAdapter extends ArrayAdapter<Contacts> {
    public ContactsAdapter(Context context, ArrayList<Contacts> contacts){
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the data item for this position
        Contacts contacts = getItem(position);

        //Check if an existing view is being reuse, otherwise inflate the view
        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_contact, parent, false);
        }
        //Lookup View for data population
        TextView tvContactName = (TextView) convertView.findViewById(R.id.tvContactName);
        TextView tvContactEmail = (TextView) convertView.findViewById(R.id.tvContactEmail);
        TextView tvContactPhone = (TextView) convertView.findViewById(R.id.tvContactPhone);
        TextView tvContactFunction = (TextView) convertView.findViewById(R.id.tvContactFunction);
        ImageView ivContactPhoto =  convertView.findViewById(R.id.ivContactPhoto);

        tvContactName.setText(contacts.getContactName());
        tvContactEmail.setText(contacts.getContactEmail());
        tvContactFunction.setText(contacts.getContactFunction());
        tvContactPhone.setText(contacts.getContactPhone());

        if(contacts.getContactName().equals("Jephte COLIN")) {
            Glide.with(getContext())
                    .load(R.drawable.jephte)
                    .into(ivContactPhoto);
        }else if(contacts.getContactName().equals("Celande PIERRE")){
            Glide.with(getContext())
                    .load(R.drawable.celande)
                    .into(ivContactPhoto);
        }else Glide.with(getContext())
                .load(R.drawable.fabrice)
                .into(ivContactPhoto);



        //return the completed View to return to screen
        return convertView;

    }
}
