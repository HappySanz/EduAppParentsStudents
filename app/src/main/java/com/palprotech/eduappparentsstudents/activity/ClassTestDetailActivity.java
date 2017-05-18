package com.palprotech.eduappparentsstudents.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
    private TextView txtHomeWorkType, txtTitle, txtHomeWorkDate, txtHomeWorkDetails, txtHomeWorkSubject;
    public  String txtHomeWorkMarkStatus;
    private String markStatus = "0";
    private Button btnmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classtest_details);
        classTest = (ClassTest) getIntent().getSerializableExtra("eventObj");
        initializeViews();
        populateData();


    }

    private void initializeViews() {
        txtHomeWorkDate = (TextView) findViewById(R.id.txtHomeWorkDate);
        txtHomeWorkDetails = (TextView) findViewById(R.id.txtHomeWorkDetails);
        txtHomeWorkSubject = (TextView) findViewById(R.id.txtHomeWorkSubject);
    }

    private void populateData() {
        txtHomeWorkDate.setText(classTest.getHwTestDate());
        txtHomeWorkDetails.setText(classTest.getHwDatails());
        txtHomeWorkSubject.setText(classTest.getHwSubjectName());
//        txtHomeWorkMarkStatus = classTest.getHwMarkStatus();
//        if(txtHomeWorkMarkStatus.equals(1)){
//            btnmark.setVisibility(View.VISIBLE);
//        }
//        else{
//            btnmark.setVisibility(View.GONE);
//        }

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
