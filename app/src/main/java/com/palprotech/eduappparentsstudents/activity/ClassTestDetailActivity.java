package com.palprotech.eduappparentsstudents.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    private TextView txtTitle, txtHomeWorkDate, txtHomeWorkDetails, txtHomeWorkSubject,tvTitleText;
    String txtHomeWorkType ="0";
    String markStatus = "0";
    Button btnmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classtest_details);
        classTest = (ClassTest) getIntent().getSerializableExtra("eventObj");
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
        txtTitle = (TextView) findViewById(R.id.txtClassTestTitle1);
        tvTitleText = (TextView) findViewById(R.id.tvtitletext);
        txtHomeWorkDetails = (TextView) findViewById(R.id.txthomeworkdetails);
        txtHomeWorkSubject = (TextView) findViewById(R.id.txthomeworksubject);
    }

    private void populateData() {
        txtTitle.setText(classTest.getHwTitle());
        txtHomeWorkDetails.setText(classTest.getHwDatails());
        txtHomeWorkSubject.setText(classTest.getHwSubjectName());
        txtHomeWorkType = (classTest.getHwType());
        if (txtHomeWorkType.equalsIgnoreCase("HW")) {
            tvTitleText.setText("HOMEWORK");
        }
        else {
            tvTitleText.setText("CLASS TEST");
        }
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
