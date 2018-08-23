package com.realty.drake.bidjepeyim.activities;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.realty.drake.bidjepeyim.GlideApp;
import com.realty.drake.bidjepeyim.R;
import com.realty.drake.bidjepeyim.models.News;

import org.parceler.Parcels;

import java.util.Objects;

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

        Toolbar toolbar = findViewById(R.id.bidje_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        Objects.requireNonNull(actionbar).setDisplayHomeAsUpEnabled(true);

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            startActivity(new Intent(this, MainActivity.class));
        }
        return true;
    }
}
