package com.palprotech.eduappparentsstudents.serviceinterfaces;

import org.json.JSONObject;

/**
 * Created by Admin on 18-05-2017.
 */

public interface IExamDetailViewServiceListener {

    void onExamDetailViewResponse(JSONObject response);

    void onExamDetailViewError(String error);
}
