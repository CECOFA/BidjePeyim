package com.realty.drake.bidjepeyim.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.realty.drake.bidjepeyim.R;
import com.realty.drake.bidjepeyim.adapters.BidjePagerAdapter;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        BidjePagerAdapter bidjePagerAdapter = new BidjePagerAdapter
                (getSupportFragmentManager());

        //The {@link ViewPager} that will host the section contents.
        // Set up the ViewPager with the sections adapter.
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(bidjePagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout
                .TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout
                .ViewPagerOnTabSelectedListener(viewPager));
    }
}