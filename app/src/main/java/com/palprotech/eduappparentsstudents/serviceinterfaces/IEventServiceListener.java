package com.palprotech.eduappparentsstudents.serviceinterfaces;

import org.json.JSONObject;

/**
 * Created by Narendar on 17/05/17.
 */

public interface IEventServiceListener {

    void onEventResponse(JSONObject response);

    void onEventError(String error);
}
