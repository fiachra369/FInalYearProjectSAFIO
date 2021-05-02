package com.example.safiofyp.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.safiofyp.models.PlaceApi;
import com.example.safiofyp.models.PlaceDetailsData;

import java.util.ArrayList;

public class PlaceAutoSuggestAdapter extends ArrayAdapter implements Filterable {

    ArrayList<String> results;

    ArrayList<PlaceDetailsData> placeDetailsData;

    int resource;
    Context context;

    PlaceApi placeApi=new PlaceApi();

    public PlaceAutoSuggestAdapter(Context context, int resId){
        super(context,resId);
        this.context=context;
        this.resource=resId;

    }

    @Override
    public int getCount(){
        return results.size();
    }

    @Override
    public String getItem(int pos){
        return results.get(pos);
    }

    @Override
    public Filter getFilter(){
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults=new FilterResults();
                if(constraint!=null){
                    placeDetailsData=placeApi.autoComplete(constraint.toString());
                    results = new ArrayList<>();
                    for(int i=0;i<placeDetailsData.size();i++){
                        results.add(placeDetailsData.get(i).getAddress());
                    }

                    filterResults.values=results;
                    filterResults.count=results.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results!=null && results.count>0){
                    notifyDataSetChanged();
                }
                else{
                    notifyDataSetInvalidated();
                }

            }
        };
        return filter;
    }

    public ArrayList<PlaceDetailsData> getPlaceDetailsData() {
        return placeDetailsData;
    }
}