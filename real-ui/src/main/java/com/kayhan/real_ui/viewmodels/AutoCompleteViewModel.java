package com.kayhan.real_ui.viewmodels;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.kayhan.real_ui.models.BaseModel;
import com.kayhan.real_ui.repositaries.BaseModelLiveData;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteViewModel  extends ViewModel{
    List<BaseModel> list = new ArrayList<>();

    public BaseModelLiveData getBaseModelLiveData() {
        return baseModelLiveData;
    }

    public void setBaseModelLiveData(BaseModelLiveData baseModelLiveData) {
        this.baseModelLiveData = baseModelLiveData;
    }

    public void setLiveData(Query query){

    }

    public Query getQuery() {
        return query;

    }

    public void setQuery(Query query) {
        this.query = query;
        baseModelLiveData =new BaseModelLiveData(query);
    }

    BaseModelLiveData baseModelLiveData ;
    MutableLiveData<DataSnapshot> liveData =new MutableLiveData<DataSnapshot>();
    Query query ;
    String collection;
    DatabaseReference reference;
    String input;

    public AutoCompleteViewModel(Query query,String collection) {
        reference = FirebaseDatabase.getInstance().getReference().child(collection);



        this.query = query;






    }

    public MutableLiveData<DataSnapshot> getLiveData() {

        return liveData;

    }


    public void  onQueryChange(LifecycleOwner owner,String input){
        this.input = input;
        this.query = reference.startAt(input).endAt(input+'\uf8ff');
        liveData.observe(owner, new Observer<DataSnapshot>() {
                    @Override
                    public void onChanged(DataSnapshot snapshot) {
                        liveData.setValue(snapshot);
                    }
                }
        );
    }


    public List<BaseModel> getList() {
        return list;
    }

    public void setList(List<BaseModel> list) {
        this.list = list;
    }
}
