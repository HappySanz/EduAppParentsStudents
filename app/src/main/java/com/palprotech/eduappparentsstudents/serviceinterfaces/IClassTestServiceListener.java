package com.palprotech.eduappparentsstudents.serviceinterfaces;

import org.json.JSONObject;

/**
 * Created by Admin on 15-05-2017.
 */

public interface IClassTestServiceListener {

    void onClassTestResponse(JSONObject response);

    void onClassTestError(String error);
}
