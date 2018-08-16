package com.realty.drake.bidjepeyim.fragments;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by drake on 8/15/18
 */

public class MyChildViewHolder extends ChildViewHolder {

    public TextView listCredit;
    public TextView listMinistry;

    public MyChildViewHolder(View itemView) {
        super(itemView);
        listMinistry = (TextView) itemView.findViewById(R.id.listChild);
        listCredit = (TextView) itemView.findViewById(R.id.listChild1);

    }

    public void onBind(String ministry, String credit) {
        listMinistry.setText(ministry);
        listCredit.setText(credit);

    }
}

