package com.palprotech.eduappparentsstudents.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.helper.AlertDialogHelper;
import com.palprotech.eduappparentsstudents.helper.ProgressDialogHelper;
import com.palprotech.eduappparentsstudents.interfaces.DialogClickListener;
import com.palprotech.eduappparentsstudents.servicehelpers.TimeTableServiceHelper;
import com.palprotech.eduappparentsstudents.serviceinterfaces.ITimeTableServiceListener;
import com.palprotech.eduappparentsstudents.utils.CommonUtils;
import com.palprotech.eduappparentsstudents.utils.EduAppConstants;
import com.palprotech.eduappparentsstudents.utils.PreferenceStorage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Narendar on 18/04/17.
 */

public class TimeTableActivity extends AppCompatActivity implements ITimeTableServiceListener, DialogClickListener {
    private static final String TAG = TimeTableActivity.class.getName();
    LinearLayout layout_all;
    private ProgressDialogHelper progressDialogHelper;
    private TimeTableServiceHelper timeTableServiceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        timeTableServiceHelper = new TimeTableServiceHelper(this);
        timeTableServiceHelper.setSignUpServiceListener(this);
        progressDialogHelper = new ProgressDialogHelper(this);
        GetTimeTableData();
        ImageView bckbtn = (ImageView) findViewById(R.id.back_res);
        bckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetTimeTableData() {
        if (CommonUtils.isNetworkAvailable(this)) {

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(EduAppConstants.PARAMS_CLASS_ID, PreferenceStorage.getStudentClassIdPreference(getApplicationContext()));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
            timeTableServiceHelper.getStudentTimeTableServiceCall(jsonObject.toString());


        } else {
            AlertDialogHelper.showSimpleAlertDialog(this, "No Network connection");
        }
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

    @Override
    public void onAlertPositiveClicked(int tag) {

    }

    @Override
    public void onAlertNegativeClicked(int tag) {

    }

    @Override
    public void onTimeTableResponse(JSONObject response) {

        progressDialogHelper.hideProgressDialog();
        if (validateSignInResponse(response)) {

            try {
                JSONArray getData = response.getJSONArray("timeTable");
                JSONObject userData = getData.getJSONObject(0);
int getLength = getData.length();
                String subjectName = null;
                Log.d(TAG, "userData dictionary" + userData.toString());
                layout_all = (LinearLayout) findViewById(R.id.layout_timetable);
                TableLayout layout = new TableLayout(this);
                layout.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                layout_all.setScrollbarFadingEnabled(false);
                layout.setPadding(0, 80,0, 80);

                TableLayout.LayoutParams rowLp = new TableLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                TableRow.LayoutParams cellLp = new TableRow.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);

                cellLp.setMargins(2, 2, 2, 2);
                int i = 0;
                for (int f = 1; f <= 5; f++) {

                    TableRow tr = new TableRow(this);

                    tr.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                    tr.setBackgroundColor(Color.BLACK);
                    tr.setPadding(0, 0, 0, 1);

                    TableRow.LayoutParams llp = new
                            TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
                    llp.setMargins(1, 1, 1, 1);//2px right-margin

                    for (int c1 = 1; c1 <= 8; c1++) {

                        LinearLayout cell = new LinearLayout(this);
                        cell.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));
                        TextView b = new TextView(this);

                        final String name = getData.getJSONObject(i).getString("subject_name") + "";

                        cell.setBackgroundColor(Color.WHITE);//argb(255,104,53,142)

                        b.setText(name);
                        b.setTextSize(15.0f);
                        b.setTypeface(null, Typeface.BOLD);
                        b.setAllCaps(true);
                        b.setTextColor(Color.parseColor("#FF68358E"));
                        b.setGravity(Gravity.CENTER);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub

                            }
                        });
                        b.setPressed(true);

                        b.setHeight(150);
                        b.setWidth(150);
                        b.setPadding(1, 0, 2, 0);
                        cell.addView(b);
                        cell.setLayoutParams(llp);//2px border on the right for the cell

                        tr.addView(cell, cellLp);
                        i++;
                    } // for
                    layout.addView(tr, rowLp);
                }
                // for

                layout_all.addView(layout);

//                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            Log.d(TAG, "Error while sign In");
        }
    }

    @Override
    public void onTimeTableError(String error) {

    }
}
