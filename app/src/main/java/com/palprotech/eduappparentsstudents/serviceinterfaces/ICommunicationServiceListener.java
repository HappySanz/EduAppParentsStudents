package com.palprotech.eduappparentsstudents.serviceinterfaces;

import org.json.JSONObject;

/**
 * Created by Admin on 18-05-2017.
 */

public interface ICommunicationServiceListener {

    void onCommunicationResponse(JSONObject response);

    void onCommunicationError(String error);
}
