package com.realty.drake.bidjepeyim.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.realty.drake.bidjepeyim.R;
import com.realty.drake.bidjepeyim.models.News;

/**
 * Created by drake on 8/8/18
 */
public class NewsFragment extends Fragment{
    //todo Implement the fragment related to the News
    private DatabaseReference newsRef;
    private RecyclerView rvNews;
    FirebaseRecyclerAdapter<News, newsViewHolder> newsAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater
                .inflate(R.layout.fragment_news, container, false);
        rvNews = rootView.findViewById(R.id.rv_News);
        return rootView;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNews.hasFixedSize();

        newsRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("News");
        newsRef.keepSynced(true);

        FirebaseRecyclerOptions<News> options =
                new FirebaseRecyclerOptions.Builder<News>()
                        .setQuery(newsRef, News.class)
                        .build();

        newsAdapter = new FirebaseRecyclerAdapter<News,
                newsViewHolder>(options) {


            @Override
            // Bind the Property object to the ViewHolder PropertyHolder
            public void onBindViewHolder(@NonNull newsViewHolder holder,
                                         final int position,
                                         @NonNull final News model) {
                holder.setTitreActualite(model.getTitreActualite());

                //This Intent send Parcelable from Property to PropertyDetail
                //holder.itemView.setOnClickListener(view1 -> getActivity()
                //        .startActivity(new Intent(getActivity(), PropertyDetail.class)
                //                .putExtra("Property", model)));
            }

            @Override
            public newsViewHolder
            onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.property_card for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.news_card, parent, false);
                return new newsViewHolder(view);
            }

            @Override
            public void onDataChanged() {
                // Called each time there is a new data snapshot.
                // You may want to use this method
                // to hide a loading spinner or check for the "no documents" state and update your UI.
                // ...
                //progressBar.setVisibility(View.GONE);
            }

            //TODO Implement onError
            @Override
            public void onError(@NonNull DatabaseError e) {
                // Called when there is an error getting data. You may want to update
                // your UI to display an error message to the user.
                // ...
                //progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(),
                        "DatabaseError", Toast.LENGTH_SHORT).show();
            }
        };
        rvNews.setAdapter(newsAdapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        newsAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        newsAdapter.stopListening();
    }

    public class newsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public newsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitreActualite(String titreActualite){
            TextView tvTitreActualite = mView.findViewById(R.id.tvNewsTitle);
            tvTitreActualite.setText(titreActualite);
        }

        public void setImageActualite(String imageActualite){
            ImageView ivImageActualite = mView.findViewById(R.id.iv_News);

        }
    }

}


