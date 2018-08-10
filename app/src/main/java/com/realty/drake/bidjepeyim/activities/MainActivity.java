package com.realty.drake.bidjepeyim.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.realty.drake.bidjepeyim.R;
import com.realty.drake.bidjepeyim.adapters.BidjePagerAdapter;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;

    //The {@link ViewPager} that will host the section contents.
    private BidjePagerAdapter bidjePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        bidjePagerAdapter = new BidjePagerAdapter
                (getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
//        viewPager = findViewById(R.id.container);
//        viewPager.setAdapter(mSectionsPagerAdapter);
//
//        TabLayout tabLayout = findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//
//
//        viewPager.addOnPageChangeListener(new TabLayout
//                .TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout
//                .ViewPagerOnTabSelectedListener(viewPager));
    }
}
