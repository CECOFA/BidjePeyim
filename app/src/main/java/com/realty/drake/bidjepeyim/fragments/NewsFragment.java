package com.realty.drake.bidjepeyim.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.realty.drake.bidjepeyim.R;
import com.realty.drake.bidjepeyim.models.News;

/**
 * Created by drake on 8/8/18
 */
public class NewsFragment extends Fragment{
    //todo Implement the fragment related to the News
    private DatabaseReference NewsRef;
    private RecyclerView rvNews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater
                .inflate(R.layout.fragment_news, container, false);
        rvNews = rootView.findViewById(R.id.rv_News);
        return rootView;
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NewsRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("News");
        NewsRef.keepSynced(true);

        // Read from the database
        NewsRef.child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                News news = dataSnapshot.getValue(News.class);
                assert news != null;
                Toast.makeText(getContext(), news.getAuteur(), Toast.LENGTH_SHORT).show();
                TextView textView = view.findViewById(R.id.tvAbout);
                textView.setText(news.getAuteur());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
