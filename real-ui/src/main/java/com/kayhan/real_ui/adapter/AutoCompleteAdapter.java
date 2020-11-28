package com.kayhan.real_ui.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;

import com.kayhan.real_ui.models.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteAdapter extends ArrayAdapter<BaseModel> implements Filterable {
    Context context;

    public AutoCompleteAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<BaseModel> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        models = objects;
    }
    List<BaseModel> models=new ArrayList<>();


//    @Override
//    public int getCount() {
//        return models.size();
//    }

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        if(convertView == null){
//            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_model,parent,false);
//
//        }
//        TextView text1 = convertView.findViewById(R.id.text1);
//        text1.setText(models.get(position).getName());
//
//        return convertView;
//    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null) {
                filterResults.count = getCount();

            }


            // do some other stuff


            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence contraint, FilterResults results) {
            if (results != null && results.count > 0) {

                notifyDataSetChanged();
            }
        }
    };

    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }
}
