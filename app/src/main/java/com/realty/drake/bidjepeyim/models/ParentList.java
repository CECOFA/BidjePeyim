package com.realty.drake.bidjepeyim.models;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by drake on 8/15/18
 */
public class ParentList extends ExpandableGroup<ChildList> {



    public ParentList(String title, List<ChildList> items) {
        super(title, items);
    }

}
