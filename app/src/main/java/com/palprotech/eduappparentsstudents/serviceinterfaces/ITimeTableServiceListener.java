package com.palprotech.eduappparentsstudents.serviceinterfaces;

import org.json.JSONObject;

/**
 * Created by Admin on 10-05-2017.
 */

public interface ITimeTableServiceListener {

    void onTimeTableResponse(JSONObject response);

    void onTimeTableError(String error);
}
