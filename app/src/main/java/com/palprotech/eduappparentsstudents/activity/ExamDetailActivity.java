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
import android.widget.ListView;

import com.google.gson.Gson;
import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.adapter.ExamDetailViewListAdapter;
import com.palprotech.eduappparentsstudents.bean.dashboard.ExamDetailsView;
import com.palprotech.eduappparentsstudents.bean.dashboard.ExamDetailsViewList;
import com.palprotech.eduappparentsstudents.bean.dashboard.Exams;
import com.palprotech.eduappparentsstudents.helper.AlertDialogHelper;
import com.palprotech.eduappparentsstudents.helper.ProgressDialogHelper;
import com.palprotech.eduappparentsstudents.servicehelpers.ExamDetailViewServiceHelper;
import com.palprotech.eduappparentsstudents.serviceinterfaces.IExamDetailViewServiceListener;
import com.palprotech.eduappparentsstudents.utils.CommonUtils;
import com.palprotech.eduappparentsstudents.utils.EduAppConstants;
import com.palprotech.eduappparentsstudents.utils.PreferenceStorage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Admin on 18-05-2017.
 */

public class ExamDetailActivity extends AppCompatActivity implements IExamDetailViewServiceListener, AdapterView.OnItemClickListener {

    private static final String TAG = "ExamDetailActivity";
    ListView loadMoreListView;
    View view;
    ExamDetailViewListAdapter examDetailViewListAdapter;
    ExamDetailViewServiceHelper examDetailViewServiceHelper;
    ArrayList<ExamDetailsView> examDetailsViewArrayList;
    int pageNumber = 0, totalCount = 0;
    protected ProgressDialogHelper progressDialogHelper;
    protected boolean isLoadingForFirstTime = true;
    Handler mHandler = new Handler();
    private SearchView mSearchView = null;
    private Exams exams;
    String ExamId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_detail);
        loadMoreListView = (ListView) findViewById(R.id.listView_events);
//        loadMoreListView.setOnLoadMoreListener(this);
        loadMoreListView.setOnItemClickListener(this);
        examDetailsViewArrayList = new ArrayList<>();
        examDetailViewServiceHelper = new ExamDetailViewServiceHelper(this);
        examDetailViewServiceHelper.setExamDetailViewServiceListener(this);
        progressDialogHelper = new ProgressDialogHelper(this);
        exams = (Exams) getIntent().getSerializableExtra("eventObj");

        callGetExamDetailViewService();

    }

    private void callGetExamDetailViewService() {

        ExamId = exams.getExamId();

        /*if(eventsListAdapter != null){
            eventsListAdapter.clearSearchFlag();
        }*/
        if (examDetailsViewArrayList != null)
            examDetailsViewArrayList.clear();

        if (CommonUtils.isNetworkAvailable(this)) {
            progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
            //    eventServiceHelper.makeRawRequest(FindAFunConstants.GET_ADVANCE_SINGLE_SEARCH);
            new HttpAsyncTask().execute("");
        } else {
            AlertDialogHelper.showSimpleAlertDialog(this, getString(R.string.no_connectivity));
        }
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... urls) {

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(EduAppConstants.PARAM_CLASS_ID, PreferenceStorage.getStudentClassIdPreference(getApplicationContext()));
                jsonObject.put(EduAppConstants.PARAM_EXAM_ID, ExamId);
                jsonObject.put(EduAppConstants.PARAM_STUDENT_ID, PreferenceStorage.getStudentEnrollIdPreference(getApplicationContext()));



            } catch (JSONException e) {
                e.printStackTrace();
            }

            progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
            examDetailViewServiceHelper.makeGetExamDetailViewServiceCall(jsonObject.toString());

            return null;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Void result) {
            progressDialogHelper.cancelProgressDialog();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onExamDetailViewResponse(final JSONObject response) {
        Log.d("ajazFilterresponse : ", response.toString());

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                progressDialogHelper.hideProgressDialog();
//                loadMoreListView.onLoadMoreComplete();

                Gson gson = new Gson();
                ExamDetailsViewList examDetailsViewList = gson.fromJson(response.toString(), ExamDetailsViewList.class);
                if (examDetailsViewList.getExamDetailView() != null && examDetailsViewList.getExamDetailView().size() > 0) {
                    totalCount = examDetailsViewList.getCount();
                    isLoadingForFirstTime = false;
                    updateListAdapter(examDetailsViewList.getExamDetailView());
                }
            }
        });
    }

    protected void updateListAdapter(ArrayList<ExamDetailsView> examDetailsViewArrayList) {
        this.examDetailsViewArrayList.addAll(examDetailsViewArrayList);
        if (examDetailViewListAdapter == null) {
            examDetailViewListAdapter = new ExamDetailViewListAdapter(this, this.examDetailsViewArrayList);
            loadMoreListView.setAdapter(examDetailViewListAdapter);
        } else {
            examDetailViewListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onExamDetailViewError(final String error) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                progressDialogHelper.hideProgressDialog();
//                loadMoreListView.onLoadMoreComplete();
                AlertDialogHelper.showSimpleAlertDialog(ExamDetailActivity.this, error);
            }
        });
    }
}


