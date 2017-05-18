package com.palprotech.eduappparentsstudents.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.adapter.EventListAdapter;
import com.palprotech.eduappparentsstudents.bean.dashboard.Event;
import com.palprotech.eduappparentsstudents.bean.dashboard.EventList;
import com.palprotech.eduappparentsstudents.helper.AlertDialogHelper;
import com.palprotech.eduappparentsstudents.helper.ProgressDialogHelper;
import com.palprotech.eduappparentsstudents.servicehelpers.EventServiceHelper;
import com.palprotech.eduappparentsstudents.serviceinterfaces.IEventServiceListener;
import com.palprotech.eduappparentsstudents.utils.CommonUtils;
import com.palprotech.eduappparentsstudents.utils.EduAppConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Narendar on 18/04/17.
 */

public class EventsActivity extends AppCompatActivity implements IEventServiceListener, AdapterView.OnItemClickListener{
    private static final String TAG = "EventsActivity";
    ListView loadMoreListView;
    View view;
    EventListAdapter eventListAdapter;
    EventServiceHelper eventServiceHelper;
    ArrayList<Event> eventArrayList;
    int pageNumber = 0, totalCount = 0;
    protected ProgressDialogHelper progressDialogHelper;
    protected boolean isLoadingForFirstTime = true;
    Handler mHandler = new Handler();


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        loadMoreListView = (ListView) findViewById(R.id.listView_events);
//        loadMoreListView.setOnLoadMoreListener(this);
        loadMoreListView.setOnItemClickListener(this);
        eventArrayList = new ArrayList<>();
        eventServiceHelper = new EventServiceHelper(this);
        eventServiceHelper.setEventServiceListener(this);
        progressDialogHelper = new ProgressDialogHelper(this);

        callGetEventService();
        ImageView bckbtn = (ImageView) findViewById(R.id.back_res);
        bckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void callGetEventService() {
        /*if(eventsListAdapter != null){
            eventsListAdapter.clearSearchFlag();
        }*/
        if (eventArrayList != null)
            eventArrayList.clear();

        if (CommonUtils.isNetworkAvailable(this)) {
            progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
            new HttpAsyncTask().execute("");
        } else {
            AlertDialogHelper.showSimpleAlertDialog(this, getString(R.string.no_connectivity));
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
    public void onEventResponse(final JSONObject response) {

        Log.d("ajazFilterresponse : ", response.toString());

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                progressDialogHelper.hideProgressDialog();
//                loadMoreListView.onLoadMoreComplete();

                Gson gson = new Gson();
                EventList eventList = gson.fromJson(response.toString(), EventList.class);
                if (eventList.getEvents() != null && eventList.getEvents().size() > 0) {
                    totalCount = eventList.getCount();
                    isLoadingForFirstTime = false;
                    updateListAdapter(eventList.getEvents());
                }
            }
        });
    }

    protected void updateListAdapter(ArrayList<Event> eventArrayList) {
        this.eventArrayList.addAll(eventArrayList);
        if (eventListAdapter == null) {
            eventListAdapter = new EventListAdapter(this, this.eventArrayList);
            loadMoreListView.setAdapter(eventListAdapter);
        } else {
            eventListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onEventError(final String error) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                progressDialogHelper.hideProgressDialog();
//                loadMoreListView.onLoadMoreComplete();
                AlertDialogHelper.showSimpleAlertDialog(EventsActivity.this, error);
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
            eventServiceHelper.makeGetEventServiceCall(jsonObject.toString());

            return null;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Void result) {
            progressDialogHelper.cancelProgressDialog();
        }
    }

}
