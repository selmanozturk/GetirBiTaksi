package com.develyze.getirbitaksi.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.develyze.getirbitaksi.R;
import com.develyze.getirbitaksi.models.Record;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HyperX on 6.02.2018.
 */

public class ResultListAdapter extends ArrayAdapter<Record>{
    @BindView(R.id.count)
    TextView countView;

    @BindView(R.id.id)
    TextView idView;
    @BindView(R.id.key)
    TextView keyView;
    @BindView(R.id.value)
    TextView valueView;
    @BindView(R.id.created)
    TextView createdView;

    Context context;
    private ArrayList<Record> dataList = new ArrayList<>();

    public ResultListAdapter(@NonNull Context context, int resource, ArrayList<Record> dataList) {
        super(context, resource);
        this.context = context;
        //this.dataList = dataList;
        for (Record item : dataList){
            this.dataList.add(item);
        }
        for(Record rec : this.dataList){
            Log.d("ASD",""+rec.getTotalCount());
        }

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view != null) {
            return view;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_result, parent, false);
            ButterKnife.bind(this,view);

            countView.setText(""+dataList.get(position).getTotalCount());
            idView.setText("_id: "+dataList.get(position).getRecordInfo().getId());
            keyView.setText("key: "+dataList.get(position).getRecordInfo().getKey());
            valueView.setText("value: "+dataList.get(position).getRecordInfo().getValue());
            createdView.setText("created: "+dataList.get(position).getRecordInfo().getCreatedAt());

            return view;
        }
    }
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public int getViewTypeCount() {

        return dataList.size() < 1 ? 1 : dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}
