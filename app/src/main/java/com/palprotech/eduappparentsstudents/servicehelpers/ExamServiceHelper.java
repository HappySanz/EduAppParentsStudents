package com.palprotech.eduappparentsstudents.servicehelpers;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.activity.ExamsResultActivity;
import com.palprotech.eduappparentsstudents.app.AppController;
import com.palprotech.eduappparentsstudents.serviceinterfaces.IExamAndResultServiceListener;
import com.palprotech.eduappparentsstudents.utils.EduAppConstants;
import com.palprotech.eduappparentsstudents.utils.PreferenceStorage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Admin on 17-05-2017.
 */

public class ExamServiceHelper {

    private String TAG = ExamsResultActivity.class.getSimpleName();
    private Context context;
    IExamAndResultServiceListener examAndResultServiceListener;

    public ExamServiceHelper(Context context) {
        this.context = context;
    }

    public void setClassTestServiceListener(IExamAndResultServiceListener examAndResultServiceListener) {
        this.examAndResultServiceListener = examAndResultServiceListener;
    }

    public void makeGetExamAndResultServiceCall(String params) {
        Log.d(TAG, "making sign in request" + params);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                EduAppConstants.BASE_URL + PreferenceStorage.getInstituteCode(context) + EduAppConstants.GET_EXAM_API, params,
                new com.android.volley.Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        examAndResultServiceListener.onExamAndResultResponse(response);
                    }
                }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    Log.d(TAG, "error during sign up" + error.getLocalizedMessage());

                    try {
                        String responseBody = new String(error.networkResponse.data, "utf-8");
                        JSONObject jsonObject = new JSONObject(responseBody);
                        examAndResultServiceListener.onExamAndResultError(jsonObject.getString(EduAppConstants.PARAM_MESSAGE));
                        String status = jsonObject.getString("status");
                        Log.d(TAG, "signup status is" + status);
                    } catch (UnsupportedEncodingException e) {
                        examAndResultServiceListener.onExamAndResultError(context.getResources().getString(R.string.error_occured));
                        e.printStackTrace();
                    } catch (JSONException e) {
                        examAndResultServiceListener.onExamAndResultError(context.getResources().getString(R.string.error_occured));
                        e.printStackTrace();
                    }

                } else {
                    examAndResultServiceListener.onExamAndResultError(context.getResources().getString(R.string.error_occured));
                }
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }
}
