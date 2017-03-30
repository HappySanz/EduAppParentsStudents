package com.palprotech.eduappparentsstudents.serviceinterfaces;

import org.json.JSONObject;

public interface ISignUpServiceListener {

    void onSignUp(JSONObject response);

    void onSignUpError(String error);
}