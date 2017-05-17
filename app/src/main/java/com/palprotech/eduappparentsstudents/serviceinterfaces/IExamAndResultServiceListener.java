package com.palprotech.eduappparentsstudents.serviceinterfaces;

import org.json.JSONObject;

/**
 * Created by Admin on 17-05-2017.
 */

public interface IExamAndResultServiceListener {

    void onExamAndResultResponse(JSONObject response);

    void onExamAndResultError(String error);
}
