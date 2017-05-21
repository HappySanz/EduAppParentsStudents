package com.palprotech.eduappparentsstudents.serviceinterfaces;

import org.json.JSONObject;

/**
 * Created by Admin on 21-05-2017.
 */

public interface IAttendanceServiceListener {

    void onAttendanceResponse(JSONObject response);

    void onAttendanceError(String error);
}
