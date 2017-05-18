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
import com.palprotech.eduappparentsstudents.adapter.CommunicationListAdapter;
import com.palprotech.eduappparentsstudents.bean.dashboard.Communication;
import com.palprotech.eduappparentsstudents.bean.dashboard.CommunicationList;
import com.palprotech.eduappparentsstudents.helper.AlertDialogHelper;
import com.palprotech.eduappparentsstudents.helper.ProgressDialogHelper;
import com.palprotech.eduappparentsstudents.servicehelpers.CommunicationServiceHelper;
import com.palprotech.eduappparentsstudents.serviceinterfaces.ICommunicationServiceListener;
import com.palprotech.eduappparentsstudents.utils.CommonUtils;
import com.palprotech.eduappparentsstudents.utils.EduAppConstants;
import com.palprotech.eduappparentsstudents.utils.PreferenceStorage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Admin on 18-05-2017.
 */

public class ParentsCommunicationActivity extends AppCompatActivity implements ICommunicationServiceListener, AdapterView.OnItemClickListener {

    private static final String TAG = "ParentsCommunication";
    ListView loadMoreListView;
    View view;
    CommunicationListAdapter communicationListAdapter;
    CommunicationServiceHelper communicationServiceHelper;
    ArrayList<Communication> communicationArrayList;
    int pageNumber = 0, totalCount = 0;
    protected ProgressDialogHelper progressDialogHelper;
    protected boolean isLoadingForFirstTime = true;
    Handler mHandler = new Handler();
    private SearchView mSearchView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_communication);
        loadMoreListView = (ListView) findViewById(R.id.listView_events);
//        loadMoreListView.setOnLoadMoreListener(this);
        loadMoreListView.setOnItemClickListener(this);
        communicationArrayList = new ArrayList<>();
        communicationServiceHelper = new CommunicationServiceHelper(this);
        communicationServiceHelper.setClassTestServiceListener(this);
        progressDialogHelper = new ProgressDialogHelper(this);

        callGetClassTestService();
    }

    public void callGetClassTestService() {
        /*if(eventsListAdapter != null){
            eventsListAdapter.clearSearchFlag();
        }*/
        if (communicationArrayList != null)
            communicationArrayList.clear();

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


            } catch (JSONException e) {
                e.printStackTrace();
            }

            progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
            communicationServiceHelper.makeGetCommunicationServiceCall(jsonObject.toString());

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
    public void onCommunicationResponse(final JSONObject response) {

        Log.d("ajazFilterresponse : ", response.toString());

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                progressDialogHelper.hideProgressDialog();
//                loadMoreListView.onLoadMoreComplete();

                Gson gson = new Gson();
                CommunicationList communicationList = gson.fromJson(response.toString(), CommunicationList.class);
                if (communicationList.getCommunication() != null && communicationList.getCommunication().size() > 0) {
                    totalCount = communicationList.getCount();
                    isLoadingForFirstTime = false;
                    updateListAdapter(communicationList.getCommunication());
                }
            }
        });
    }

    protected void updateListAdapter(ArrayList<Communication> communicationArrayList) {
        this.communicationArrayList.addAll(communicationArrayList);
        if (communicationListAdapter == null) {
            communicationListAdapter = new CommunicationListAdapter(this, this.communicationArrayList);
            loadMoreListView.setAdapter(communicationListAdapter);
        } else {
            communicationListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCommunicationError(final String error) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                progressDialogHelper.hideProgressDialog();
//                loadMoreListView.onLoadMoreComplete();
                AlertDialogHelper.showSimpleAlertDialog(ParentsCommunicationActivity.this, error);
            }
        });
    }
}
