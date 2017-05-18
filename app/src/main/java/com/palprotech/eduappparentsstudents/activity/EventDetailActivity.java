package com.palprotech.eduappparentsstudents.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.bean.dashboard.Event;
import com.palprotech.eduappparentsstudents.interfaces.DialogClickListener;
import com.palprotech.eduappparentsstudents.serviceinterfaces.IEventServiceListener;

import org.json.JSONObject;

/**
 * Created by Admin on 18-05-2017.
 */

public class EventDetailActivity extends AppCompatActivity implements IEventServiceListener, DialogClickListener, View.OnClickListener {

    private Event event;
    private TextView txtEventName, txtEventDate, txtEventDetails;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        event = (Event) getIntent().getSerializableExtra("eventObj");
        initializeViews();
        populateData();
        ImageView bckbtn = (ImageView) findViewById(R.id.back_res);
        bckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initializeViews() {
        txtEventName = (TextView) findViewById(R.id.eventname);
        txtEventDate = (TextView) findViewById(R.id.eventdate);
        txtEventDetails = (TextView) findViewById(R.id.eventdetail);
    }

    private void populateData() {
        txtEventName.setText(event.getEvent_name());
        txtEventDate.setText(event.getEvent_date());
        txtEventDetails.setText(event.getEvent_details());
    }

    @Override
    public void onEventResponse(JSONObject response) {

    }

    @Override
    public void onEventError(String error) {

    }

    @Override
    public void onAlertPositiveClicked(int tag) {

    }

    @Override
    public void onAlertNegativeClicked(int tag) {

    }

    @Override
    public void onClick(View v) {

    }
}
