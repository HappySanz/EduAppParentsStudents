package com.palprotech.eduappparentsstudents.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.bean.dashboard.ClassTest;
import com.palprotech.eduappparentsstudents.interfaces.DialogClickListener;
import com.palprotech.eduappparentsstudents.serviceinterfaces.IClassTestServiceListener;

import org.json.JSONObject;

/**
 * Created by Admin on 17-05-2017.
 */

public class ClassTestDetailActivity extends AppCompatActivity implements IClassTestServiceListener, DialogClickListener, View.OnClickListener {

    private ClassTest classTest;
    private TextView txtHomeWorkType, txtTitle, txtHomeWorkDate, txtHomeWorkDetails, txtHomeWorkSubject, txtHomeWorkMarkStatus;
    private String markStatus = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classtest_details);
        classTest = (ClassTest) getIntent().getSerializableExtra("eventObj");
        initializeViews();
        populateData();


    }

    private void initializeViews() {
        txtHomeWorkType = (TextView) findViewById(R.id.txtHomeWorkType);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtHomeWorkDate = (TextView) findViewById(R.id.txtHomeWorkDate);
        txtHomeWorkDetails = (TextView) findViewById(R.id.txtHomeWorkDetails);
        txtHomeWorkSubject = (TextView) findViewById(R.id.txtHomeWorkSubject);
        txtHomeWorkMarkStatus = (TextView) findViewById(R.id.txtHomeWorkMarkStatus);
    }

    private void populateData() {
        txtHomeWorkType.setText(classTest.getHwType());
        txtTitle.setText(classTest.getHwTitle());
        txtHomeWorkDate.setText(classTest.getHwTestDate());
        txtHomeWorkDetails.setText(classTest.getHwDatails());
        txtHomeWorkSubject.setText(classTest.getHwSubjectName());
        txtHomeWorkMarkStatus.setText(classTest.getHwMarkStatus());

    }

    @Override
    public void onClassTestResponse(JSONObject response) {

    }

    @Override
    public void onClassTestError(String error) {

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
