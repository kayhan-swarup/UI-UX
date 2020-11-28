package com.kayhan.real_ui.repositaries;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;

public class  BaseModelLiveData extends LiveData<DataSnapshot> {

    private Query query;

    private ModelEventListener listener = new ModelEventListener();

    public BaseModelLiveData(Query query) {
        this.query = query;
        
    }

    public Query getQuery() {
        return query;
    }


    public void setQuery(Query query) {
        this.query = query;
    }

    private class ModelEventListener implements ChildEventListener{

        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    }



}
