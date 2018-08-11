package com.realty.drake.bidjepeyim.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.realty.drake.bidjepeyim.R;
import com.realty.drake.bidjepeyim.models.News;

import org.parceler.Parcels;

public class NewsDetailActivity extends AppCompatActivity {
    News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        //Get Parcel
        news = Parcels
                .unwrap(getIntent()
                        .getParcelableExtra("news"));
    }
}
