package com.palprotech.eduappparentsstudents.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import com.palprotech.eduappparentsstudents.R;

/**
 * Created by Narendar on 05/04/17.
 */

public class AttendanceActivity extends AppCompatActivity {

    CalendarView calend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        calend = (CalendarView) findViewById(R.id.calendView);

        calend.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                Toast.makeText(
//                        getBaseContext(),
//                        "Selected Date is\n\n" + dayOfMonth + " / " + month
//                                + " / " + year, Toast.LENGTH_LONG).show();
            }
        });

        ImageView bckbtn = (ImageView) findViewById(R.id.back_res);
        bckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
