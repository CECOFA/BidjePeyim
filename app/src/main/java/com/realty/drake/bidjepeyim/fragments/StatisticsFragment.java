package com.realty.drake.bidjepeyim.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.realty.drake.bidjepeyim.R;
import com.realty.drake.bidjepeyim.activities.NewsDetailActivity;
import com.realty.drake.bidjepeyim.models.Statistic;

import org.parceler.Parcels;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by drake on 8/8/18
 */
public class StatisticsFragment extends Fragment{
    private RecyclerView rvStatistics;
    FirebaseRecyclerAdapter<Statistic, statisticsViewHolder> statisticsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater
                .inflate(R.layout.fragment_statistic, container, false);
        rvStatistics = rootView.findViewById(R.id.rvStat);
        return rootView;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvStatistics.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvStatistics.hasFixedSize();

        //Loading bar when content are not yet available
        //final ProgressBar progressBar = view.findViewById(R.id.progressBar);
        // progressBar.setVisibility(View.VISIBLE);

        final LottieAnimationView lottieAnimationView = view.findViewById(R.id.animation_view1);
        lottieAnimationView.setVisibility(View.VISIBLE);

        DatabaseReference newsRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("Ministry");
        newsRef.keepSynced(true);

        FirebaseRecyclerOptions<Statistic> options =
                new FirebaseRecyclerOptions.Builder<Statistic>()
                        .setQuery(newsRef, Statistic.class)
                        .build();

        statisticsAdapter = new FirebaseRecyclerAdapter<Statistic,
                statisticsViewHolder>(options) {


            @Override
            // Bind the Property object to the ViewHolder PropertyHolder
            public void onBindViewHolder(@NonNull statisticsViewHolder holder,
                                         final int position,
                                         @NonNull final Statistic model) {
                holder.setMinistry(model.getMinistry());
                holder.setBalance(model.getBalance());
                holder.setCredit(model.getCredit());
                holder.setExpense(model.getExpense());
                holder.setAbsorption(model.getCredit(), model.getExpense());

                //This Intent send Parcelable to NewsDetail
                // holder.itemView.setOnClickListener(view1 -> getActivity()
                //         .startActivity(new Intent(getActivity(), MinistryDetailActivity.class)
                //                 .putExtra("ministry", Parcels.wrap(model.getMinistry()))));
            }

            @Override
            public statisticsViewHolder
            onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.news_card for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_stats, parent, false);
                return new statisticsViewHolder(view);
            }

            @Override
            public void onDataChanged() {
                // Called each time there is a new data snapshot.
                // You may want to use this method
                // to hide a loading spinner or check for the "no documents" state and update your UI.

                lottieAnimationView.setVisibility(View.GONE);
            }

            //TODO Implement onError
            @Override
            public void onError(@NonNull DatabaseError e) {
                // Called when there is an error getting data. You may want to update
                // your UI to display an error message to the user.
                // ...
                lottieAnimationView.setVisibility(View.GONE);
                Toast.makeText(getActivity(),
                        "DatabaseError", Toast.LENGTH_SHORT).show();
            }
        };
        rvStatistics.setAdapter(statisticsAdapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        statisticsAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        statisticsAdapter.stopListening();
    }

    //Format double in HT currency
    String inCurrencyHT(double money){
        NumberFormat df = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol("HTG ");
        dfs.setGroupingSeparator(',');
        dfs.setMonetaryDecimalSeparator('.');
        ((DecimalFormat) df).setDecimalFormatSymbols(dfs);
        return (df.format(money));
    }

    public class statisticsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        statisticsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setMinistry(String ministry){
            TextView tvMinistry = mView.findViewById(R.id.tvMinistry);
            tvMinistry.setText(ministry);
        }

        public void setCredit(double credit){
            TextView tvCredit = mView.findViewById(R.id.tvCreditAmount);
            tvCredit.setText(String.valueOf(inCurrencyHT(credit)));
        }

        public void setExpense(double expense){
            TextView tvExpense = mView.findViewById(R.id.tvExpenseAmount);
            tvExpense.setText(String.valueOf(inCurrencyHT(expense)));
        }

        public void setBalance(double balance){
            TextView tvBalance = mView.findViewById(R.id.tvBalanceAmount);
            tvBalance.setText(String.valueOf(inCurrencyHT(balance)));
        }

        public void setAbsorption(double credit, double expense){
            TextView tvAbsorption = mView.findViewById(R.id.tvAbsorbtionAmnt);
            double percentage = (expense/credit);
            NumberFormat defaultFormat = NumberFormat.getPercentInstance();
            defaultFormat.setMinimumFractionDigits(1);
            tvAbsorption.setText(defaultFormat.format(percentage));
        }
    }


}
