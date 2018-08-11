package com.realty.drake.bidjepeyim.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.realty.drake.bidjepeyim.GlideApp;
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
        titreActualite.setText(news.getTitreActualite());
        contenuActualite = findViewById(R.id.tvNewsTextDetail);
        contenuActualite.setText(news.getContenuActualite());
        motsCles = findViewById(R.id.tvTag);
        motsCles.setText(news.getMotsCles());
        datePublication = findViewById(R.id.tvPublicationDate);
        datePublication.setText(news.getDatePublication());
        auteur = findViewById(R.id.tvAuthor);
        auteur.setText(news.getAuteur());
        imageActualite = findViewById(R.id.ivNewsImageDetail);
        if (news.getImageActualite() != null){
            GlideApp.with(getBaseContext())
                    .load(news.getImageActualite())
                    .into(imageActualite);
        }
    }
}
