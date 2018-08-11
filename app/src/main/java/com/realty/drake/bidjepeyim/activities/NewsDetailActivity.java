package com.realty.drake.bidjepeyim.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.realty.drake.bidjepeyim.R;
import com.realty.drake.bidjepeyim.models.News;

import org.parceler.Parcels;

public class NewsDetailActivity extends AppCompatActivity {
    News news;
    TextView titreActualite;
    TextView contenuActualite;
    TextView motsCles;
    TextView datePublication;
    TextView auteur;
    ImageView imageActualite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        //Get Parcel
        news = Parcels
                .unwrap(getIntent()
                        .getParcelableExtra("news"));

        titreActualite= findViewById(R.id.tvNewsTitleDetail);
        contenuActualite = findViewById(R.id.tvNewsTextDetail);
        motsCles = findViewById(R.id.tvTag);
        datePublication = findViewById(R.id.tvPublicationDate);
        auteur = findViewById(R.id.tvAuthor);
        imageActualite = findViewById(R.id.ivNewsImageDetail);
    }
}
