package com.palprotech.eduappparentsstudents.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.adapter.ExamMarkViewListAdapter;
import com.palprotech.eduappparentsstudents.bean.dashboard.ExamDetailsViewList;
import com.palprotech.eduappparentsstudents.bean.dashboard.ExamMark;
import com.palprotech.eduappparentsstudents.bean.dashboard.ExamMarkList;
import com.palprotech.eduappparentsstudents.bean.dashboard.Exams;
import com.palprotech.eduappparentsstudents.helper.AlertDialogHelper;
import com.palprotech.eduappparentsstudents.helper.ProgressDialogHelper;
import com.palprotech.eduappparentsstudents.interfaces.DialogClickListener;
import com.palprotech.eduappparentsstudents.servicehelpers.ExamDetailViewServiceHelper;
import com.palprotech.eduappparentsstudents.serviceinterfaces.IExamDetailViewServiceListener;
import com.palprotech.eduappparentsstudents.utils.CommonUtils;
import com.palprotech.eduappparentsstudents.utils.EduAppConstants;
import com.palprotech.eduappparentsstudents.utils.PreferenceStorage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Admin on 25-05-2017.
 */

public class ExamMarksActivity extends AppCompatActivity implements IExamDetailViewServiceListener, AdapterView.OnItemClickListener, DialogClickListener {

    private static final String TAG = "ExamMarksActivity";
    ListView loadMoreListView;
    View view;
    ExamMarkViewListAdapter examMarkViewListAdapter;
    ExamDetailViewServiceHelper examDetailViewServiceHelper;
    ArrayList<ExamMark> examMarkArrayList;
    int pageNumber = 0, totalCount = 0;
    protected ProgressDialogHelper progressDialogHelper;
    protected boolean isLoadingForFirstTime = true;
    Handler mHandler = new Handler();
    private SearchView mSearchView = null;
    private Exams exams;
    String ExamId;
    TextView txtTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_marks);
        loadMoreListView = (ListView) findViewById(R.id.listView_events);
//        loadMoreListView.setOnLoadMoreListener(this);
        loadMoreListView.setOnItemClickListener(this);
        examMarkArrayList = new ArrayList<>();
        examDetailViewServiceHelper = new ExamDetailViewServiceHelper(this);
        examDetailViewServiceHelper.setExamDetailViewServiceListener(this);
        progressDialogHelper = new ProgressDialogHelper(this);
        txtTotal = (TextView) findViewById(R.id.txtTotal);
        exams = (Exams) getIntent().getSerializableExtra("eventObj");

        callGetExamDetailViewService();
        ImageView bckbtn = (ImageView) findViewById(R.id.back_res);
        bckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void callGetExamDetailViewService() {

        ExamId = exams.getExamId();

        /*if(eventsListAdapter != null){
            eventsListAdapter.clearSearchFlag();
        }*/
        if (examMarkArrayList != null)
            examMarkArrayList.clear();

        if (CommonUtils.isNetworkAvailable(this)) {
            progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
            //    eventServiceHelper.makeRawRequest(FindAFunConstants.GET_ADVANCE_SINGLE_SEARCH);
            new HttpAsyncTask().execute("");
        } else {
//            AlertDialogHelper.showSimpleAlertDialog(this, getString(R.string.no_connectivity));
            AlertDialogHelper.showSimpleAlertDialog(this, "No Network connection");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onAlertPositiveClicked(int tag) {

    }

    @Override
    public void onAlertNegativeClicked(int tag) {

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
    public void onExamDetailViewResponse(final JSONObject response) {
        if (validateSignInResponse(response)) {
            Log.d("ajazFilterresponse : ", response.toString());
            try {
                String totalMark = response.getString("totalMarks");
                txtTotal.setText( totalMark);

            } catch (Exception ex) {
            }

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressDialogHelper.hideProgressDialog();
//                loadMoreListView.onLoadMoreComplete();

                    Gson gson = new Gson();
                    ExamMarkList examMarkList = gson.fromJson(response.toString(), ExamMarkList.class);
                    if (examMarkList.getExamMarkView() != null && examMarkList.getExamMarkView().size() > 0) {
                        totalCount = examMarkList.getCount();
                        isLoadingForFirstTime = false;
                        updateListAdapter(examMarkList.getExamMarkView());
                    }
                }
            });
        } else {
            Log.d(TAG, "Error while sign In");
        }
    }

    protected void updateListAdapter(ArrayList<ExamMark> examDetailsViewArrayList) {
        this.examMarkArrayList.addAll(examDetailsViewArrayList);
        if (examMarkViewListAdapter == null) {
            examMarkViewListAdapter = new ExamMarkViewListAdapter(this, this.examMarkArrayList);
            loadMoreListView.setAdapter(examMarkViewListAdapter);
        } else {
            examMarkViewListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onExamDetailViewError(final String error) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                progressDialogHelper.hideProgressDialog();
//                loadMoreListView.onLoadMoreComplete();
                AlertDialogHelper.showSimpleAlertDialog(ExamMarksActivity.this, error);
            }
        });
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... urls) {

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(EduAppConstants.PARAM_EXAM_ID, ExamId);
                jsonObject.put(EduAppConstants.PARAM_STUDENT_ID, PreferenceStorage.getStudentEnrollIdPreference(getApplicationContext()));


            } catch (JSONException e) {
                e.printStackTrace();
            }

            progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
            examDetailViewServiceHelper.makeGetExamMarkViewServiceCall(jsonObject.toString());

            return null;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Void result) {
            progressDialogHelper.cancelProgressDialog();
        }
    }
}
