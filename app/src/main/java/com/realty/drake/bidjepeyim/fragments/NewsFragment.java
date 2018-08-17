package com.realty.drake.bidjepeyim.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.realty.drake.bidjepeyim.GlideApp;
import com.realty.drake.bidjepeyim.R;
import com.realty.drake.bidjepeyim.activities.NewsDetailActivity;
import com.realty.drake.bidjepeyim.models.News;

import org.parceler.Parcels;

/**
 * Created by drake on 8/8/18
 */
public class NewsFragment extends Fragment{
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
        final ProgressBar progressBar = view.findViewById(R.id.progressBar);
        rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNews.hasFixedSize();

        DatabaseReference newsRef = FirebaseDatabase.getInstance()
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
                //If there's no image, default image will be load
                if ((model.getImageActualite())!= null) {
                    holder.setImageActualite(model.getImageActualite());
                }

                //Set Date on cardView
                holder.setDateActualite(model.getDatePublication());

                //This Intent send Parcelable to NewsDetail
                holder.itemView.setOnClickListener(view1 -> getActivity()
                        .startActivity(new Intent(getActivity(), NewsDetailActivity.class)
                                .putExtra("news", Parcels.wrap(model))));
            }

            @Override
            public newsViewHolder
            onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.news_card for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.news_card, parent, false);
                return new newsViewHolder(view);
            }

            @Override
            public void onDataChanged() {
                // Called each time there is a new data snapshot.
                // You may want to use this method
                // to hide a loading spinner or check for the "no documents" state and update your UI.

                //progressBar.setVisibility(View.GONE);
            }

            //TODO Implement onError
            @Override
            public void onError(@NonNull DatabaseError e) {
                // Called when there is an error getting data. You may want to update
                // your UI to display an error message to the user.
                // ...
                progressBar.setVisibility(View.GONE);
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

        newsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitreActualite(String titreActualite){
            TextView tvTitreActualite = mView.findViewById(R.id.tvNewsTitle);
            tvTitreActualite.setText(Html.fromHtml(titreActualite));
        }

        public void setImageActualite(String imageActualite){
            ImageView ivImageActualite = mView.findViewById(R.id.iv_News);
            //Loading circle for placeholder, ColorAccent has been used
            CircularProgressDrawable progressDrawable =
                    new CircularProgressDrawable(getContext());
            progressDrawable.setStrokeWidth(5f);
            progressDrawable.setCenterRadius(30f);
            progressDrawable.setColorSchemeColors(Color.argb(1,255,145,0));
            progressDrawable.start();

            GlideApp.with(getContext())
                    .load(imageActualite)
                    .placeholder(progressDrawable)
                    .into(ivImageActualite);
        }

        public void setDateActualite(String dateActualite){
            TextView tvDate = mView.findViewById(R.id.tvDate);
            tvDate.setText(dateActualite);
        }
    }

}


