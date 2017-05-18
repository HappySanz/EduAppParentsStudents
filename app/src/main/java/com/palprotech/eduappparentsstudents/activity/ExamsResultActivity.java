package com.palprotech.eduappparentsstudents.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.adapter.ExamListAdapter;
import com.palprotech.eduappparentsstudents.bean.dashboard.ExamList;
import com.palprotech.eduappparentsstudents.bean.dashboard.Exams;
import com.palprotech.eduappparentsstudents.helper.AlertDialogHelper;
import com.palprotech.eduappparentsstudents.helper.ProgressDialogHelper;
import com.palprotech.eduappparentsstudents.servicehelpers.ExamServiceHelper;
import com.palprotech.eduappparentsstudents.serviceinterfaces.IExamAndResultServiceListener;
import com.palprotech.eduappparentsstudents.utils.CommonUtils;
import com.palprotech.eduappparentsstudents.utils.EduAppConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Narendar on 18/04/17.
 */

public class ExamsResultActivity extends AppCompatActivity implements IExamAndResultServiceListener, AdapterView.OnItemClickListener{

    private static final String TAG = "ExamsResultActivity";
    ListView loadMoreListView;
    View view;
    ExamListAdapter examListAdapter;
    ExamServiceHelper examServiceHelper;
    ArrayList<Exams> examsArrayList;
    int pageNumber = 0, totalCount = 0;
    protected ProgressDialogHelper progressDialogHelper;
    protected boolean isLoadingForFirstTime = true;
    Handler mHandler = new Handler();
    private SearchView mSearchView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams_result);
        loadMoreListView = (ListView) findViewById(R.id.listView_events);
//        loadMoreListView.setOnLoadMoreListener(this);
        loadMoreListView.setOnItemClickListener(this);
        examsArrayList = new ArrayList<>();
        examServiceHelper = new ExamServiceHelper(this);
        examServiceHelper.setClassTestServiceListener(this);
        progressDialogHelper = new ProgressDialogHelper(this);

        callGetExamResultService();
        ImageView bckbtn = (ImageView) findViewById(R.id.back_res);
        bckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void callGetExamResultService() {
        /*if(eventsListAdapter != null){
            eventsListAdapter.clearSearchFlag();
        }*/
        if (examsArrayList != null)
            examsArrayList.clear();

        if (CommonUtils.isNetworkAvailable(this)) {
            progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
            //    eventServiceHelper.makeRawRequest(FindAFunConstants.GET_ADVANCE_SINGLE_SEARCH);
            new ExamsResultActivity.HttpAsyncTask().execute("");
        } else {
            AlertDialogHelper.showSimpleAlertDialog(this, getString(R.string.no_connectivity));
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onExamAndResultResponse(final JSONObject response) {
        Log.d("ajazFilterresponse : ", response.toString());

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                progressDialogHelper.hideProgressDialog();
//                loadMoreListView.onLoadMoreComplete();

                Gson gson = new Gson();
                ExamList examList = gson.fromJson(response.toString(), ExamList.class);
                if (examList.getExams() != null && examList.getExams().size() > 0) {
                    totalCount = examList.getCount();
                    isLoadingForFirstTime = false;
                    updateListAdapter(examList.getExams());
                }
            }
        });
    }

    protected void updateListAdapter(ArrayList<Exams> examsArrayList) {
        this.examsArrayList.addAll(examsArrayList);
        if (examListAdapter == null) {
            examListAdapter = new ExamListAdapter(this, this.examsArrayList);
            loadMoreListView.setAdapter(examListAdapter);
        } else {
            examListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onExamAndResultError(String error) {

    }

    private class HttpAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... urls) {

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(EduAppConstants.PARAM_CLASS_ID, "1");


            } catch (JSONException e) {
                e.printStackTrace();
            }

            progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
            examServiceHelper.makeGetExamAndResultServiceCall(jsonObject.toString());

            return null;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Void result) {
            progressDialogHelper.cancelProgressDialog();
        }
    }

}
