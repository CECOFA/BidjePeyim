package com.realty.drake.bidjepeyim.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.realty.drake.bidjepeyim.PDF_Viewer;
import com.realty.drake.bidjepeyim.R;

/**
 * Created by drake on 8/8/18
 */
public class RegulatoryFrameworkFragment extends Fragment {
    //todo Implement RegulatoryFrameworkFragment here
    //todo Integrate firebase storage
    //todo replace layout
    private Button pdfButton;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater
                .inflate(R.layout.fragment_regulatory_framework, container, false);
        pdfButton = rootview.findViewById(R.id.pdfButt);
        pdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),PDF_Viewer.class);
                startActivity(intent);
            }
        });
        return rootview;

    }

}
