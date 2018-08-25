package com.realty.drake.bidjepeyim.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
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
    private DrawerLayout mDrawerLayout;
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
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    // set item as selected to persist highlight
                    menuItem.setChecked(true);
                    // close drawer when item is tapped
                    mDrawerLayout.closeDrawers();
                    // Add code here to update the UI based on the item selected
                    // For example, swap UI fragments here
                    switch (menuItem.getItemId()) {
                        case R.id.nav_about: {
                            startActivity(new Intent
                                    (this, AboutActivity.class));
                            break;
                        }
                        case R.id.nav_contacts: {
                            startActivity(new Intent
                                    (this, ContactActivity.class));
                            break;
                        }

                    }
                    return true;
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
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
