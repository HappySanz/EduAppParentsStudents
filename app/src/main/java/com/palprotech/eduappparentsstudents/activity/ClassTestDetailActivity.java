package com.palprotech.eduappparentsstudents.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.bean.dashboard.ClassTest;
import com.palprotech.eduappparentsstudents.helper.AlertDialogHelper;
import com.palprotech.eduappparentsstudents.helper.ProgressDialogHelper;
import com.palprotech.eduappparentsstudents.interfaces.DialogClickListener;
import com.palprotech.eduappparentsstudents.servicehelpers.ClassTestServiceHelper;
import com.palprotech.eduappparentsstudents.serviceinterfaces.IClassTestServiceListener;
import com.palprotech.eduappparentsstudents.utils.CommonUtils;
import com.palprotech.eduappparentsstudents.utils.EduAppConstants;
import com.palprotech.eduappparentsstudents.utils.PreferenceStorage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 17-05-2017.
 */

public class ClassTestDetailActivity extends AppCompatActivity implements IClassTestServiceListener, DialogClickListener, View.OnClickListener {
    private static final String TAG = ClassTestDetailActivity.class.getName();
    private ClassTest classTest;
    private TextView txtTitle, txtHomeWorkDate, txtHomeWorkDetails, txtHomeWorkSubject, tvTitleText, txtViewMarks;
    private LinearLayout llMarkView;
    private ClassTestServiceHelper classTestServiceHelper;
    private ProgressDialogHelper progressDialogHelper;
    String txtHomeWorkType = "0";
    String markStatus = "0";
    Button btnmark;
    String mStatus;

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
        txtViewMarks = (TextView) findViewById(R.id.viewmarks);
        llMarkView = (LinearLayout) findViewById(R.id.llMarkView);

        classTestServiceHelper = new ClassTestServiceHelper(this);
        classTestServiceHelper.setClassTestServiceListener(this);
        progressDialogHelper = new ProgressDialogHelper(this);
    }

    private void populateData() {
        txtTitle.setText(classTest.getHwTitle());
        txtHomeWorkDetails.setText(classTest.getHwDatails());
        txtHomeWorkSubject.setText(classTest.getHwSubjectName());
        txtHomeWorkType = (classTest.getHwType());
        if (txtHomeWorkType.equalsIgnoreCase("HW")) {
            tvTitleText.setText("HOMEWORK");
        } else {
            tvTitleText.setText("CLASS TEST");
        }

        mStatus = classTest.getHwMarkStatus();
        if (mStatus.equalsIgnoreCase("1")) {
            llMarkView.setVisibility(View.VISIBLE);

            if (CommonUtils.isNetworkAvailable(this)) {


                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put(EduAppConstants.PARAM_HOMEWORK_ID, classTest.getHwId());
                    jsonObject.put(EduAppConstants.PARM_ENROLL_ID, PreferenceStorage.getStudentEnrollIdPreference(getApplicationContext()));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
                classTestServiceHelper.makeGetClassTestMarkServiceCall(jsonObject.toString());


            } else {
                AlertDialogHelper.showSimpleAlertDialog(this, "No Network connection");
            }

        } else {
            llMarkView.setVisibility(View.GONE);
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
        progressDialogHelper.hideProgressDialog();
        if (validateSignInResponse(response)) {

            String repo = response.toString();

//            longInfo(repo);

            try {
                JSONArray getData = response.getJSONArray("ctestmarkDetails");
                JSONObject marksData = getData.getJSONObject(0);
                String user_id = null;


                Log.d(TAG, "userData dictionary" + marksData.toString());
                if (marksData != null) {
                    user_id = marksData.getString("marks") + "";
                    txtViewMarks.setText(user_id);
                    //


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            Log.d(TAG, "Error while sign In");
        }
    }

    @Override
    public void onClassTestError(String error) {
        progressDialogHelper.hideProgressDialog();
        AlertDialogHelper.showSimpleAlertDialog(this, error);
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

    private boolean validateSignInResponse(JSONObject response) {
        boolean signInsuccess = false;
        if ((response != null)) {
            try {
                String status = response.getString("status");
                String msg = response.getString(EduAppConstants.PARAM_MESSAGE);
                Log.d(TAG, "status val" + status + "msg" + msg);

                if ((status != null)) {
                    if (((status.equalsIgnoreCase("activationError")) || (status.equalsIgnoreCase("alreadyRegistered")) ||
                            (status.equalsIgnoreCase("notRegistered")) || (status.equalsIgnoreCase("error")))) {
                        signInsuccess = false;
                        Log.d(TAG, "Show error dialog");
                        AlertDialogHelper.showSimpleAlertDialog(this, msg);

                    } else {
                        signInsuccess = true;

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return signInsuccess;
    }
}
