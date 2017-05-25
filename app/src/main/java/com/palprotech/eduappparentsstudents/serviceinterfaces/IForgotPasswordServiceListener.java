package com.palprotech.eduappparentsstudents.serviceinterfaces;

import org.json.JSONObject;

/**
 * Created by Narendar on 25/05/17.
 */

public interface IForgotPasswordServiceListener {

    void onForgotPassword(JSONObject response);

    void onForgotPasswordError(String error);
}
