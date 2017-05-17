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
import android.widget.Switch;

import com.google.gson.Gson;
import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.adapter.ClassTestListAdapter;
import com.palprotech.eduappparentsstudents.bean.dashboard.ClassTest;
import com.palprotech.eduappparentsstudents.bean.dashboard.ClassTestList;
import com.palprotech.eduappparentsstudents.helper.AlertDialogHelper;
import com.palprotech.eduappparentsstudents.helper.ProgressDialogHelper;
import com.palprotech.eduappparentsstudents.servicehelpers.ClassTestServiceHelper;
import com.palprotech.eduappparentsstudents.serviceinterfaces.IClassTestServiceListener;
import com.palprotech.eduappparentsstudents.utils.CommonUtils;
import com.palprotech.eduappparentsstudents.utils.EduAppConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Narendar on 07/04/17.
 */

public class ClassTestHomeworkActivity extends AppCompatActivity implements IClassTestServiceListener, AdapterView.OnItemClickListener {

    private static final String TAG = "ClassTestHomeworkActivity";
    ListView loadMoreListView;
    View view;
    ClassTestListAdapter classTestListAdapter;
    ClassTestServiceHelper classTestServiceHelper;
    ArrayList<ClassTest> classTestArrayList;
    int pageNumber = 0, totalCount = 0;
    protected ProgressDialogHelper progressDialogHelper;
    protected boolean isLoadingForFirstTime = true;
    Handler mHandler = new Handler();
    private SearchView mSearchView = null;
    private Switch switch_test, switch_homwork;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classtest_homework);
        loadMoreListView = (ListView) findViewById(R.id.listView_events);
//        loadMoreListView.setOnLoadMoreListener(this);
        loadMoreListView.setOnItemClickListener(this);
        classTestArrayList = new ArrayList<>();
        classTestServiceHelper = new ClassTestServiceHelper(this);
        classTestServiceHelper.setClassTestServiceListener(this);
        progressDialogHelper = new ProgressDialogHelper(this);

        callGetFilterService();
        ImageView bckbtn = (ImageView) findViewById(R.id.back_res);
        bckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        testsort();


    }

    public void callGetFilterService() {
        /*if(eventsListAdapter != null){
            eventsListAdapter.clearSearchFlag();
        }*/
        if (classTestArrayList != null)
            classTestArrayList.clear();

        if (CommonUtils.isNetworkAvailable(this)) {
            progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
            new HttpAsyncTask().execute("");
        } else {
            AlertDialogHelper.showSimpleAlertDialog(this, getString(R.string.no_connectivity));
        }

    }

    public void testsort() {
        if (switch_test.isChecked()){
            classTestArrayList.clear();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Log.d(TAG, "onEvent list item clicked" + position);
//        ClassTest classTest = null;
//        if ((classTestListAdapter != null) && (classTestListAdapter.ismSearching())) {
//            Log.d(TAG, "while searching");
//            int actualindex = classTestListAdapter.getActualEventPos(position);
//            Log.d(TAG, "actual index" + actualindex);
//            classTest = classTestArrayList.get(actualindex);
//        } else {
//            classTest = classTestArrayList.get(position);
//        }
//        Intent intent = new Intent(this, EventDetailActivity.class);
//        intent.putExtra("eventObj", event);
        // intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        startActivity(intent);
    }

    @Override
    public void onClassTestResponse(final JSONObject response) {

        Log.d("ajazFilterresponse : ", response.toString());

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                progressDialogHelper.hideProgressDialog();
//                loadMoreListView.onLoadMoreComplete();

                Gson gson = new Gson();
                ClassTestList classTestsList = gson.fromJson(response.toString(), ClassTestList.class);
                if (classTestsList.getClassTest() != null && classTestsList.getClassTest().size() > 0) {
                    totalCount = classTestsList.getCount();
                    isLoadingForFirstTime = false;
                    updateListAdapter(classTestsList.getClassTest());
                }
            }
        });
    }

    protected void updateListAdapter(ArrayList<ClassTest> classTestArrayList) {
        this.classTestArrayList.addAll(classTestArrayList);
        if (classTestListAdapter == null) {
            classTestListAdapter = new ClassTestListAdapter(this, this.classTestArrayList);
            loadMoreListView.setAdapter(classTestListAdapter);
        } else {
            classTestListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClassTestError(final String error) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                progressDialogHelper.hideProgressDialog();
//                loadMoreListView.onLoadMoreComplete();
                AlertDialogHelper.showSimpleAlertDialog(ClassTestHomeworkActivity.this, error);
            }
        });
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
            classTestServiceHelper.makeGetClassTestServiceCall(jsonObject.toString());

            return null;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Void result) {
            progressDialogHelper.cancelProgressDialog();
        }
    }

}
