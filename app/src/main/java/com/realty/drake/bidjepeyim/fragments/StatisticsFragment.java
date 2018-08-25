package com.realty.drake.bidjepeyim.fragments;

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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.realty.drake.bidjepeyim.R;
import com.realty.drake.bidjepeyim.models.Statistic;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by drake on 8/8/18
 */
public class StatisticsFragment extends Fragment{
    private RecyclerView rvStatistics;
    FirebaseRecyclerAdapter<Statistic, statisticsViewHolder> statisticsAdapter;
    public String uid;
    public String author;
    public String title1;
    public String body;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();





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

        DatabaseReference statisticsRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("Ministry");
        statisticsRef.keepSynced(true);

        FirebaseRecyclerOptions<Statistic> options =
                new FirebaseRecyclerOptions.Builder<Statistic>()
                        .setQuery(statisticsRef, Statistic.class)
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
                holder.barchart();

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
    private float randRange(int min, int max) {
        Random r = new Random();
        int x = min + r.nextInt(max - min);
        float res = (float) x;
        return res;
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
        public void barchart(){
            //BarChart barChart;
            BarChart barChart = mView.findViewById(R.id.chart);
            barChart.setDrawBarShadow(false);
            barChart.setDrawValueAboveBar(true);
            barChart.setMaxVisibleValueCount(100000);
            barChart.setPinchZoom(false);
            barChart.setDrawGridBackground(true);
            barChart.animateXY(3100, 3100);

            //myRef.setValue("Hello, World!");
            ArrayList<BarEntry> barEntries = new ArrayList<>();


            // barEntries.add(new BarEntry(0,350f));
            barEntries.add(new BarEntry(1,randRange(5000,100000)));
            barEntries.add(new BarEntry(2,randRange(5000,100000)));
            barEntries.add(new BarEntry(3,randRange(5000,100000)));
            barEntries.add(new BarEntry(4,randRange(5000,100000)));
            barEntries.add(new BarEntry(5,randRange(5000,100000)));
            barEntries.add(new BarEntry(6,randRange(5000,100000)));
            barEntries.add(new BarEntry(7,randRange(5000,100000)));
            barEntries.add(new BarEntry(8,randRange(5000,100000)));
            barEntries.add(new BarEntry(9,randRange(5000,100000)));
            barEntries.add(new BarEntry(10,randRange(5000,100000)));
            barEntries.add(new BarEntry(11,randRange(5000,100000)));
            barEntries.add(new BarEntry(12,randRange(5000,100000)));


            final BarDataSet barDataSet = new BarDataSet(barEntries,"MONTH");


            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

            BarData data = new BarData(barDataSet);


            data.setBarWidth(0.35f);
            barChart.setData(data);



            final String[] months = new String[]{"","Jan","Fev","Mar","Av","Mai","Juin","Jun","Aout","Sept","Oct","Nov","Dec"};
            XAxis xAxis = barChart.getXAxis();
            xAxis.setValueFormatter(new MyXAxisValueFormatter(months));
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
          //  xAxis.setAxisLineWidth(0.5f);
            //xAxis.setGranularity(1);
            //  xAxis.setAxisMaximum(14);
            //  xAxis.setCenterAxisLabels(true);
        }
        public class MyXAxisValueFormatter implements IAxisValueFormatter {

            private String[] mValues;
            public MyXAxisValueFormatter(String[] values) {

                this.mValues = values;
            }

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mValues[(int)value];
            }
        }

        public void setExpense(double expense){
            TextView tvExpense = mView.findViewById(R.id.tvExpenseAmount);
            tvExpense.setText(String.valueOf("- "+inCurrencyHT(expense)));
        }

        public void setBalance(double balance){
            TextView tvBalance = mView.findViewById(R.id.tvBalanceAmount);
            tvBalance.setText(String.valueOf(inCurrencyHT(balance)));
        }

        public void setAbsorption(double credit, double expense){
            TextView tvAbsorption = mView.findViewById(R.id.tvAbsorbtionAmount);
            double percentage = (expense/credit);
            NumberFormat defaultFormat = NumberFormat.getPercentInstance();
            defaultFormat.setMinimumFractionDigits(1);
            tvAbsorption.setText(defaultFormat.format(percentage));
        }
    }



}



