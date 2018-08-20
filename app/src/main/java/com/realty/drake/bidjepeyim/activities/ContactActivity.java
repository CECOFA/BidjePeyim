package com.realty.drake.bidjepeyim.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.realty.drake.bidjepeyim.R;
import com.realty.drake.bidjepeyim.adapters.ContactsAdapter;
import com.realty.drake.bidjepeyim.models.Contacts;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

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
}
