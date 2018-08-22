package com.realty.drake.bidjepeyim.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;

import com.realty.drake.bidjepeyim.R;

import java.util.Objects;

public class AboutActivity extends AppCompatActivity {

    private TextView textView;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        textView = (TextView) findViewById(R.id.about);
        String htmlText = "<p>BidjePeyim est une application mobile dédiée à la publication d’au moins des cinq\n" + "      " +
                "  dernières lois de finances de la République d’Haïti plus celle en vigueur. Elle diffusera aussi " +
                "des rapports\n" + "        financiers et des statistiques relatifs aux autorisations de dépenses " +
                "mensuelles de fonctionnement et d’investissement\n" + "        et au niveau d’emploi dans l’administration " +
                "d’Etat. Elle fournira enfin des dashboards de la situation financière des\n" + "       " +
                " institutions de l’Etat et se voudra un outil de travail principalement pour les" +
                " techniciens en finances publiques du\n" + "        Ministère de l’Economie et des Finances.</p>Les données " +
                "publiées seront tirées de la base de données du système de gestion des dépenses budgétaires de l’Etat\n" + "     " +
                "   (SYSDEP) dont la gouvernance est assurée par la Direction des Systèmes d’Information (DSI) du Ministère de\n" + "         " +
                "   l’Economie et des Finances (MEF).<p><p>Cette application BidjePeyim,  développée par l’équipe composée des" +
                " sieurs Joseph Colin, Célande Pierre et\n" + "        Fabrice Ledan, constitue le projet de ladite équipe dans le" +
                " cadre du bootcamp codepath tenue du 3 juillet au\n" + "         " +
                "   25 août 2018 à St-Louis de Conzague à Port-au-Prince, Haiti. <p>";
        textView.setText(Html.fromHtml(htmlText));
        Toolbar toolbar = findViewById(R.id.bidje_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        Objects.requireNonNull(actionbar).setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to Tab Activity...
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
