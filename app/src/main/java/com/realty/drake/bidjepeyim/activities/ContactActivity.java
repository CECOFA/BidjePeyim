package com.realty.drake.bidjepeyim.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ListView;

import com.realty.drake.bidjepeyim.R;
import com.realty.drake.bidjepeyim.adapters.ContactsAdapter;
import com.realty.drake.bidjepeyim.models.Contacts;

import java.util.ArrayList;
import java.util.Objects;

public class ContactActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

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

        //Construct the data source
        ArrayList<Contacts> arrayOfUsers = new ArrayList<Contacts>();
        //Create the adapter to covert the array to views
        ContactsAdapter adapter = new ContactsAdapter(this, arrayOfUsers);
        //Add item to the adapter
        Contacts jephte = new Contacts("Jephte COLIN",
                "Développeur Android\n" + "Project Manager",
                "(509) 3743-8713",
                "drakecolinj@gmail.com");
        adapter.add(jephte);

        Contacts celande = new Contacts("Celande PIERRE",
                "IT Instructor\n" + "Fonctionnaire",
                "(509) 3750-2564",
                "celandep@gmail.com");
        adapter.add(celande);

        Contacts fabrice = new Contacts("Fabrice LEDAN",
                "Graphiste\n" + "Développeur Android",
                "(509) 3821-7170",
                "ledanfab.93@gmail.com");
        adapter.add(fabrice);

        //Attach the adapter to the ListView
        ListView listView = (ListView) findViewById(R.id.lvItems);
        listView.setAdapter(adapter);
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

    //Override back button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            startActivity(new Intent(this, MainActivity.class));
        }
        return true;
    }
}
