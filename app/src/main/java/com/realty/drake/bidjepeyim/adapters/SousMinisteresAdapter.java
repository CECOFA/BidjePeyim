package com.realty.drake.bidjepeyim.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.realty.drake.bidjepeyim.R;
import com.realty.drake.bidjepeyim.StatsSousMinisteres;

import java.util.List;

/**
 * Created by Fab on 8/21/2018.
 */

public class SousMinisteresAdapter extends ArrayAdapter<StatsSousMinisteres> {
    public SousMinisteresAdapter(@NonNull Context context, List<StatsSousMinisteres>statsSousMinisteres) {
        super(context, android.R.layout.simple_list_item_1);
    }
    public static class ViewHolder{
        TextView tvNomSous,tvCredit,tvDepenses,tvSoldes,tvTaux,tvCreditamnt,tvDepensesamnt,tvSoldesamnt,tvTauxamnt;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        StatsSousMinisteres statsSousMinisteres = getItem(position);
        ViewHolder  viewHolder;

        if (convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_sous_ministeres,parent,false);


            viewHolder.tvNomSous =(TextView) convertView.findViewById(R.id.tvSousMinisteres);

            viewHolder.tvCredit =(TextView) convertView.findViewById(R.id.tvCredit);
            viewHolder.tvCreditamnt =(TextView) convertView.findViewById(R.id.tvCreditAmntSub);
            viewHolder.tvDepenses =(TextView) convertView.findViewById(R.id.tvDpensesSub);
            viewHolder.tvDepensesamnt =(TextView) convertView.findViewById(R.id.tvDepensesAmntSub);
            viewHolder.tvSoldes =(TextView) convertView.findViewById(R.id.tvSoldeSub);
            viewHolder.tvSoldesamnt =(TextView) convertView.findViewById(R.id.tvSoldesAmntSub);
            viewHolder.tvTaux =(TextView) convertView.findViewById(R.id.tvTauxSub);
            viewHolder.tvTauxamnt =(TextView) convertView.findViewById(R.id.tvTauxAmntSub);

            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Where to set
        viewHolder.tvNomSous.setText("OK");

        return convertView;
    }
}
