package com.palprotech.eduappparentsstudents.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.helper.AlertDialogHelper;
import com.palprotech.eduappparentsstudents.helper.ProgressDialogHelper;
import com.palprotech.eduappparentsstudents.interfaces.DialogClickListener;
import com.palprotech.eduappparentsstudents.servicehelpers.SignUpServiceHelper;
import com.palprotech.eduappparentsstudents.serviceinterfaces.ISignUpServiceListener;
import com.palprotech.eduappparentsstudents.utils.CommonUtils;
import com.palprotech.eduappparentsstudents.utils.EduAppConstants;
import com.palprotech.eduappparentsstudents.utils.PreferenceStorage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 22-03-2017.
 */

public class SchoolIdLoginActivity extends AppCompatActivity implements View.OnClickListener, ISignUpServiceListener, DialogClickListener {

    private static final String TAG = SchoolIdLoginActivity.class.getName();

    private SignUpServiceHelper signUpServiceHelper;
    private ProgressDialogHelper progressDialogHelper;

    private EditText inputInstituteId;
    private ImageView btnSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_id_login);

        inputInstituteId = (EditText) findViewById(R.id.inputInsId);
        btnSubmit = (ImageView) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        signUpServiceHelper = new SignUpServiceHelper(this);
        signUpServiceHelper.setSignUpServiceListener(this);
        progressDialogHelper = new ProgressDialogHelper(this);
    }

    @Override
    public void onClick(View v) {
        if (CommonUtils.isNetworkAvailable(this)) {
            if (v == btnSubmit) {

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put(EduAppConstants.PARAMS_FUNC_NAME, EduAppConstants.SIGN_IN_PARAMS_FUNC_NAME);
                    jsonObject.put(EduAppConstants.PARAMS_INSTITUTE_ID, inputInstituteId.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
                signUpServiceHelper.makeInstituteLoginServiceCall(jsonObject.toString());
            }
        } else {
            AlertDialogHelper.showSimpleAlertDialog(this, "No Network connection");
        }
    }

    @Override
    public void onAlertPositiveClicked(int tag) {

    }

    @Override
    public void onAlertNegativeClicked(int tag) {

    }

    private boolean validateSignInResponse(JSONObject response) {
        boolean signInsuccess = false;
        if ((response != null)) {
            try {
                String status = response.getString("status");
                String msg = response.getString(EduAppConstants.PARAM_MESSAGE);
                Log.d(TAG, "status val" + status + "msg" + msg);

                if ((status != null)) {
                    if (((status.equalsIgnoreCase("activationError")) || (status.equalsIgnoreCase("alreadyRegistered")) ||
                            (status.equalsIgnoreCase("notRegistered")) || (status.equalsIgnoreCase("error")))) {
                        signInsuccess = false;
                        Log.d(TAG, "Show error dialog");
                        AlertDialogHelper.showSimpleAlertDialog(this, msg);

                    } else {
                        signInsuccess = true;

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return signInsuccess;
    }

    @Override
    public void onSignUp(JSONObject response) {
        progressDialogHelper.hideProgressDialog();
        if (validateSignInResponse(response)) {
            try {
                JSONObject userData = response.getJSONObject("userData");
                String ins_id = null;

                Log.d(TAG, "userData dictionary" + userData.toString());
                if (userData != null) {
                    ins_id = userData.getString("institute_id")+"";

                    PreferenceStorage.saveInstituteId(this, ins_id);

                    Log.d(TAG, "created user id" + ins_id);

                    //need to re do this
                    Log.d(TAG, "sign in response is" + response.toString());

                    String instituteName = userData.getString("institute_name");
                    String instituteCode = userData.getString("institute_code");
                    String instituteLogo = userData.getString("institute_logo");
                    String instituteLogoPicUrl = EduAppConstants.GET_SCHOOL_LOGO + instituteLogo;
                    String userDynamicAPI = EduAppConstants.BASE_URL + instituteCode;

                    if ((instituteName != null) && !(instituteName.isEmpty()) && !instituteName.equalsIgnoreCase("null")) {
                        PreferenceStorage.saveInstituteName(this, instituteName);
                    }
                    if ((instituteCode != null) && !(instituteCode.isEmpty()) && !instituteCode.equalsIgnoreCase("null")) {
                        PreferenceStorage.saveInstituteCode(this, instituteCode);
                    }
                    if ((instituteLogoPicUrl != null) && !(instituteLogoPicUrl.isEmpty()) && !instituteLogoPicUrl.equalsIgnoreCase("null")) {
                        PreferenceStorage.saveInstituteLogoPic(this, instituteLogoPicUrl);
                    }
                    if ((userDynamicAPI != null) && !(userDynamicAPI.isEmpty()) && !userDynamicAPI.equalsIgnoreCase("null")) {
                        PreferenceStorage.saveUserDynamicAPI(this, userDynamicAPI);
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Intent intent = new Intent(this, UserLoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        } else {
            Log.d(TAG, "Error while sign In");
        }
    }

    @Override
    public void onSignUpError(String error) {
        progressDialogHelper.hideProgressDialog();
        AlertDialogHelper.showSimpleAlertDialog(this, error);
    }
}
