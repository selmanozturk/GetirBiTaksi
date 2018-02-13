package com.develyze.getirbitaksi.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.develyze.awesome_calendar.customviews.DateRangeCalendarView;
import com.develyze.getirbitaksi.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.calendar)
    DateRangeCalendarView calendar;
    @BindView(R.id.button)
    Button getirButton;
    @BindView(R.id.min)
    EditText min;
    @BindView(R.id.max)
    EditText max;

    String startDateStr, endDateStr;
    long minNum, maxNum;
    SimpleDateFormat dateFormat ;
    boolean isButtonActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //setButtonPassive();

        Typeface typeface = Typeface.createFromAsset(getAssets(), "JosefinSans-Regular.ttf");
        calendar.setFonts(typeface);

        calendar.setCalendarListener(new DateRangeCalendarView.CalendarListener() {
            @Override
            public void onDateRangeSelected(Calendar startDate, Calendar endDate) {
                startDateStr = dateFormat.format(startDate.getTimeInMillis());
                endDateStr = dateFormat.format(endDate.getTimeInMillis());
                isButtonActive = true;
            }

            @Override
            public void onCancel() {
                isButtonActive = false;
            }
        });

    }

    @OnClick(R.id.button)
    public void getir(){
        if(min.getText().length() < 1) {
            Toasty.error(getApplicationContext(),
                    getResources().getString(R.string.warning_min_value),
                    Toast.LENGTH_SHORT, true).show();

        }else if(max.getText().length() < 1){
            Toasty.error(getApplicationContext(),
                    getResources().getString(R.string.warning_max_value),
                    Toast.LENGTH_SHORT, true).show();

        }else if(Integer.parseInt(min.getText().toString())
                > Integer.parseInt(max.getText().toString())){
            Toasty.error(getApplicationContext(),
                    getResources().getString(R.string.warning_max_must_greater_or_equal_than_min),
                    Toast.LENGTH_SHORT, true).show();

        }else if(!isButtonActive){
            Toasty.error(getApplicationContext(),
                    getResources().getString(R.string.warning_date),
                    Toast.LENGTH_SHORT, true).show();

        }
        else{
            minNum = Long.parseLong(min.getText().toString());
            maxNum = Long.parseLong(max.getText().toString());

            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("startDate", startDateStr );
            intent.putExtra("endDate", endDateStr );
            intent.putExtra("min", minNum );
            intent.putExtra("max", maxNum );

            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        }
    }

    void setButtonActive(){
        getirButton.setClickable(true);
        getirButton.setBackground(getResources().getDrawable(R.drawable.round_btn));
    }
    void setButtonPassive(){
        getirButton.setClickable(false);
        getirButton.setBackground(getResources().getDrawable(R.drawable.round_btn_passive));
    }


}