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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.realty.drake.bidjepeyim.R;
import com.realty.drake.bidjepeyim.models.ChildList;
import com.realty.drake.bidjepeyim.models.ParentList;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by drake on 8/8/18
 */
public class BudgetFragment extends Fragment {
    private RecyclerView recycler_view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater
                .inflate(R.layout.fragment_budget, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Define recycleview
        recycler_view = view.findViewById(R.id.recycler_Expand);
        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));

        //Initialize your Firebase app
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Reference to your entire Firebase database
        DatabaseReference parentReference = database.getReference("/Budget");

        //reading data from firebase
        parentReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<ParentList> Parent = new ArrayList<>();
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()){
                    final String ParentKey = snapshot.getKey().toString();
                    snapshot.child("Parent").getValue();
                    DatabaseReference childReference =
                            FirebaseDatabase.getInstance()
                                    .getReference("/Budget").child(ParentKey);
                    childReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            final List<ChildList> Child = new ArrayList<>();
                            //numberOnline = 0;

                            for (DataSnapshot snapshot1:dataSnapshot.getChildren())
                            {
                                final String ChildValue1 =
                                        Objects.requireNonNull(snapshot1.getValue()).toString();
                                final String ChildValue2 =
                                        snapshot1.getKey();

                                snapshot1.child("title").getValue();

                                Child.add(new ChildList(ChildValue1, ChildValue2));
                            }

                            Parent.add(new ParentList(ParentKey, Child));

                            DocExpandableRecyclerAdapter adapter =
                                    new DocExpandableRecyclerAdapter(Parent);
                            recycler_view.setAdapter(adapter);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Failed to read value
                            System.out.println("Failed to read value." + error.toException());
                        }

                    });}}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public class DocExpandableRecyclerAdapter extends
            ExpandableRecyclerViewAdapter<MyParentViewHolder,
                    MyChildViewHolder> {

        public DocExpandableRecyclerAdapter(List<ParentList> groups) {
            super(groups);
        }

        @Override
        public MyParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_parent, parent, false);
            return new MyParentViewHolder(view);
        }

        @Override
        public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_child, parent, false);
            return new MyChildViewHolder(view);
        }

        @Override
        public void onBindChildViewHolder(MyChildViewHolder holder,
                                          int flatPosition,
                                          ExpandableGroup group,
                                          int childIndex) {

            final ChildList childItem = ((ParentList) group).getItems().get(childIndex);
            holder.onBind(childItem.getMinistry(), childItem.getCredit());
            final String TitleChild=group.getTitle();
            holder.listMinistry.setOnClickListener(view -> {
                Toast toast = Toast.makeText(getContext(), TitleChild, Toast.LENGTH_SHORT);
                toast.show();
            });

        }

        @Override
        public void onBindGroupViewHolder(MyParentViewHolder holder,
                                          int flatPosition,
                                          final ExpandableGroup group) {
            holder.setParentTitle(group);

            if(group.getItems()==null)
            {
                holder.listGroup.setOnClickListener(view -> {
                    Toast toast = Toast.makeText(getContext(),
                            group.toString(), Toast.LENGTH_SHORT);
                    toast.show();
                });

            }
        }
    }
}