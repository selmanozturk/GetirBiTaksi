package com.develyze.getirbitaksi.activities;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.develyze.getirbitaksi.GetirBiTaksiApplication;
import com.develyze.getirbitaksi.R;
import com.develyze.getirbitaksi.adapters.PaginationAdapter;
import com.develyze.getirbitaksi.fragments.ResultFragment;
import com.develyze.getirbitaksi.models.Record;
import com.develyze.getirbitaksi.models.RecordInfo;
import com.develyze.getirbitaksi.models.ResultObject;
import com.develyze.getirbitaksi.utils.ApiCaller;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.layoutDots)
    LinearLayout dotsLayout;
    @BindView(R.id.btn_prev)
    Button btnPrev;
    @BindView(R.id.btn_next)
    Button btnNext;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    private TextView[] dots;

    private PaginationAdapter myViewPagerAdapter;

    long min, max;
    String startDate, endDate;

    ApiCaller apiCaller;

    public static int TOTAL_NUM_ITEMS=0;
    public static int ITEMS_PER_PAGE=10;
    public static int ITEMS_REMAINING=TOTAL_NUM_ITEMS % ITEMS_PER_PAGE;
    public static int TOTAL_PAGES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();

        startDate = extras.getString("startDate");
        endDate = extras.getString("endDate");
        min = extras.getLong("min");
        max = extras.getLong("max");

        progressBar.setVisibility(View.VISIBLE);
//        startDate = "2017-02-01";
//        endDate = "2017-02-02";
//        min = 1;
//        max = 50000;

        myViewPagerAdapter = new PaginationAdapter(getSupportFragmentManager());

        apiCaller = new ApiCaller((GetirBiTaksiApplication)getApplication());

        apiCaller.getResult(startDate, endDate, min, max, new Callback<ResultObject>() {
            @Override
            public void onResponse(Call<ResultObject> call, Response<ResultObject> response) {
                Log.d("BUNDLE_",""+call.request().url());
                ResultObject result = response.body();
                List<Record> records = result.getRecords();
                if(records.size()<1){
                    Toasty.error(getApplicationContext(),
                            getResources().getString(R.string.no_record_fount),
                            Toast.LENGTH_LONG, true).show();
                    Record noneRecord = new Record();
                    RecordInfo recordInfo = new RecordInfo();
                    recordInfo.setId("");
                    recordInfo.setKey(getResources().getString(R.string.no_record_fount));
                    recordInfo.setValue(getResources().getString(R.string.info_go_back_retry));
                    recordInfo.setCreatedAt("");
                    noneRecord.setTotalCount(0);
                    noneRecord.setRecordInfo(recordInfo);
                    records.add(noneRecord);
                    generatePages(myViewPagerAdapter, records);
                    viewPager.setAdapter(myViewPagerAdapter);
                }else{
                    Toasty.success(getApplicationContext(),
                            getResources().getString(R.string.info_content_listing),
                            Toast.LENGTH_LONG, true).show();


                    generatePages(myViewPagerAdapter, records);
                    viewPager.setAdapter(myViewPagerAdapter);
                    viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ResultObject> call, Throwable t) {
                Toasty.error(getApplicationContext(),
                        getResources().getString(R.string.error_getting_results),
                        Toast.LENGTH_LONG, true).show();
                progressBar.setVisibility(View.GONE);
            }
        });


    }
    private void addBottomDots(int currentPage, int size) {
        dots = new TextView[size];

        int colorsActive = getResources().getColor(R.color.dot_light_screen);
        int colorsInactive = getResources().getColor(R.color.dot_dark_screen);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position , TOTAL_PAGES);

            if (position == TOTAL_PAGES - 1) {
                // last page. make button text to GOT IT
                btnNext.setVisibility(View.INVISIBLE);
            } else if(position == 0) {
                // still pages are left
                btnPrev.setVisibility(View.INVISIBLE);
            }else{
                btnNext.setVisibility(View.VISIBLE);
                btnPrev.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };


    public void generatePages(PaginationAdapter adapter, List<Record> records){

        TOTAL_NUM_ITEMS = records.size();
        ITEMS_REMAINING=TOTAL_NUM_ITEMS % ITEMS_PER_PAGE;
        if(ITEMS_REMAINING==0){
            TOTAL_PAGES = TOTAL_NUM_ITEMS/ITEMS_PER_PAGE;
        }else{
            TOTAL_PAGES = TOTAL_NUM_ITEMS/ITEMS_PER_PAGE +1;
        }

        for(int i=0 ; i < TOTAL_PAGES ; i++){
            ArrayList<Record> subList = new ArrayList<>();
            if(i == TOTAL_PAGES -1){
                for (int j = 0 ; j < ITEMS_REMAINING ; j++){
                    subList.add( records.get(i*ITEMS_PER_PAGE +j));
                }
                myViewPagerAdapter.addFragment(new ResultFragment(subList), "Sayfa "+i);
            }else{
                for (int j = 0 ; j < ITEMS_PER_PAGE ; j++){
                    subList.add( records.get(i*ITEMS_PER_PAGE +j));
                }
                myViewPagerAdapter.addFragment(new ResultFragment(subList), "Sayfa "+i);
            }

        }
        addBottomDots(0 , TOTAL_PAGES);
    }


    @OnClick(R.id.btn_prev)
    void prev(){
        int current = getItem(-1);
        if (current >= 0 ) {
            viewPager.setCurrentItem(current);
        }
    }
    @OnClick(R.id.btn_next)
    void next(){
        int current = getItem(+1);
        if (current < TOTAL_PAGES ) {
            viewPager.setCurrentItem(current);
        }
    }

}
