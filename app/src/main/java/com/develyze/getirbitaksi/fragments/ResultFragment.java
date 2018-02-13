package com.develyze.getirbitaksi.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.develyze.getirbitaksi.R;
import com.develyze.getirbitaksi.adapters.ResultListAdapter;
import com.develyze.getirbitaksi.models.Record;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class ResultFragment extends Fragment {
    @BindView(R.id.listView)
    ListView listView;

    public static final String TAG = ResultFragment.class.getSimpleName();

    private ArrayList<Record> dataList = new ArrayList<>();

    public ResultFragment() {}

    public ResultFragment(ArrayList<Record> dataList) {
        this.dataList = dataList;
    }


    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_results, container, false);
        ButterKnife.bind(this, fragment);
        listView.setAdapter( new ResultListAdapter(getContext(), R.id.listView, dataList));

        return fragment;
    }



    @Override
    public void onResume() {
        super.onResume();
    }
}
