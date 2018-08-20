package com.realty.drake.bidjepeyim.fragments;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.realty.drake.bidjepeyim.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by drake on 8/15/18
 */
public class MyParentViewHolder extends GroupViewHolder {

    public TextView listGroup;
    private ImageView arrow;

    public MyParentViewHolder(View itemView) {
        super(itemView);
        listGroup = itemView.findViewById(R.id.listParent);
        arrow = itemView.findViewById(R.id.list_item_genre_arrow);
    }

    public void setParentTitle(ExpandableGroup group) {
        listGroup.setText(group.getTitle());
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360,
                        180, RELATIVE_TO_SELF,
                        0.5f, RELATIVE_TO_SELF,
                        0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180,
                        360, RELATIVE_TO_SELF,
                        0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }


}

