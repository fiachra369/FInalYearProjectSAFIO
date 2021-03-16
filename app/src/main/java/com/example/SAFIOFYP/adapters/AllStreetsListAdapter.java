package com.example.SAFIOFYP.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.SAFIOFYP.R;
import com.example.SAFIOFYP.models.AllStreetsModel;
import com.example.SAFIOFYP.utils.Constants;

import java.util.List;

public class AllStreetsListAdapter extends RecyclerView.Adapter<AllStreetsListAdapter.ViewHolder> {

    public List<AllStreetsModel> allStreetsList;

    Context context;




    public AllStreetsListAdapter(List<AllStreetsModel> allStreetsList, Context context) {

        this.context = context;
        this.allStreetsList = allStreetsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_street_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        AllStreetsModel singleStreet = allStreetsList.get(position);


        holder.indicatorStreetName.setText(singleStreet.getStreetName());

        if (position % 2 == 0)
        {
            holder.indicatorIndicatorBg.setBackgroundColor(context.getResources().getColor(R.color.lightGray));
        }
        else
        {
            holder.indicatorIndicatorBg.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

        int area = findArea(singleStreet.getStreetWidth(), singleStreet.getStreetLength());
        double streetRisk = findStreetRisk(singleStreet.getStreetAverage(), area);
        boolean safeStatus = streetStatus(streetRisk);

        if (safeStatus)
        {
            holder.indicatorText.setText("SAFE");
            holder.indicatorTextBg.setBackgroundColor(context.getResources().getColor(R.color.green));
        }
        else
        {
            holder.indicatorText.setText("NOT SAFE");
            holder.indicatorTextBg.setBackgroundColor(context.getResources().getColor(R.color.red));
        }



//        if (singleStreet.getStreetAverage() == 0 || singleStreet.getStreetThresh() == 0)
//        {
//            holder.indicatorText.setText("N/A");
//            holder.indicatorTextBg.setBackgroundColor(context.getResources().getColor(R.color.lightGray));
//        }
//        else
//        {
//            if (singleStreet.getStreetAverage() > singleStreet.getStreetThresh())
//            {
//                holder.indicatorTextBg.setBackgroundColor(context.getResources().getColor(R.color.red));
//                holder.indicatorText.setText("NOT SAFE");
//            }
//            else
//            {
//                holder.indicatorTextBg.setBackgroundColor(context.getResources().getColor(R.color.green));
//                holder.indicatorText.setText("SAFE");
//            }
//        }


    }

    @Override
    public int getItemCount() {
        return allStreetsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        TextView indicatorText;
        TextView indicatorStreetName;
        View indicatorIndicatorBg;
        View indicatorTextBg;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            indicatorText = mView.findViewById(R.id.indicatortext);
            indicatorStreetName = mView.findViewById(R.id.indicator_street_name);
            indicatorTextBg = mView.findViewById(R.id.indicator_text_bg);
            indicatorIndicatorBg = mView.findViewById(R.id.indicator_indicator_bg);

        }
    }

    private int findArea(int width, int length)
    {
        return width*length;
    }

    private double findStreetRisk(int average, int area)
    {
        return (average*1.0)/area;
    }

    private boolean streetStatus(double streetRisk)
    {
        int userRisk = Constants.USER_RISK_SCORE;
        double finalRisk = userRisk * streetRisk;
        if (finalRisk >= 1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


}
