package com.realty.drake.bidjepeyim.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.realty.drake.bidjepeyim.R;

/**
 * Created by drake on 8/8/18
 */
public class NewsFragment extends Fragment{
    //todo Implement the fragment related to the News
    //todo replace layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater
                .inflate(R.layout.activity_about, container, false);
    }

}
