package com.kayhan.sampleactivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.kayhan.real_ui.adapter.AutoCompleteAdapter;
import com.kayhan.real_ui.models.BaseModel;
import com.kayhan.real_ui.viewmodels.AutoCompleteViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;

@EFragment(R.layout.fragment_search)
public class SearchFragment extends Fragment {


    @ViewById
    AutoCompleteTextView search;


    AutoCompleteViewModel viewModel;



    @FragmentArg
    String collection;



    @AfterViews
    public void init(){
//        viewModel = new AutoCompleteViewModel(
//                FirebaseDatabase.getInstance()
//                        .getReference()
//                        .child(collection)
//                        .orderByChild("name")
//
//                ,
//                collection
//
//        );

        search.addTextChangedListener(watcher);
    }


    AutoCompleteAdapter adapter;
    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            if(s.length()>1){
//                Query query =FirebaseDatabase.getInstance().getReference().child("lab_tests")
//                        .orderByChild("name")
//                        .startAt(s.toString()).endAt(String.valueOf(s.toString()+'\uf88f'));
//
//                viewModel.setQuery(query);
//                viewModel.getLiveData().observe(SearchFragment.this, new Observer<DataSnapshot>() {
//                    @Override
//                    public void onChanged(DataSnapshot snapshot) {
//                        Log.i("Snap: ",snapshot.toString());
//                        List<BaseModel> models = snapshot.getValue(new GenericTypeIndicator<List<BaseModel>>() {});
//                        adapter = new AutoCompleteAdapter(getActivity(), R.layout.row_model,
//                                R.id.text1,models);
//                        search.setAdapter(adapter);
//                        search.showDropDown();
//
//                    }
//                });
//            }
            if(s.length()>1){
                FirebaseDatabase.getInstance().getReference()
                        .child(collection)
                        .orderByValue()
                        .startAt(s.toString())
                        .endAt(s.toString()+'\uf8ff')

                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    HashMap<String,BaseModel> models =
                                            snapshot.getValue(new GenericTypeIndicator<HashMap<String,BaseModel>>() {});
                                    if(models!=null){
                                        if(adapter!=null){
                                            adapter.clear();adapter.notifyDataSetChanged();
                                        }
                                        adapter = new AutoCompleteAdapter(search.getContext(),
                                                R.layout.row_model,R.id.text1,new ArrayList<>(models.values()));

                                        search.setAdapter(adapter);

                                        search.showDropDown();
                                    }
                                } catch (Exception e) {
                                    Log.e("Error: ",e.toString());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
