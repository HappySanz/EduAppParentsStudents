package com.palprotech.eduappparentsstudents.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.helper.AlertDialogHelper;
import com.palprotech.eduappparentsstudents.helper.ProgressDialogHelper;
import com.palprotech.eduappparentsstudents.interfaces.DialogClickListener;
import com.palprotech.eduappparentsstudents.servicehelpers.SignUpServiceHelper;
import com.palprotech.eduappparentsstudents.serviceinterfaces.ISignUpServiceListener;
import com.palprotech.eduappparentsstudents.utils.CommonUtils;
import com.palprotech.eduappparentsstudents.utils.EduAppConstants;
import com.palprotech.eduappparentsstudents.utils.PreferenceStorage;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 22-03-2017.
 */

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener, ISignUpServiceListener, DialogClickListener {

    private static final String TAG = UserLoginActivity.class.getName();

    private SignUpServiceHelper signUpServiceHelper;
    private ProgressDialogHelper progressDialogHelper;

    private EditText inputUsername, inputPassword;
    private Button btnLogin;
    private TextView txtInsName;
    private ImageView mProfileImage = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
//        mProfileImage = (ImageView) findViewById(R.id.image_institute_pic);
        SetUI();
    }

    private void SetUI() {

        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        mProfileImage = (ImageView) findViewById(R.id.image_institute_pic);
        txtInsName = (TextView) findViewById(R.id.txtInstituteName);
        txtInsName.setText(PreferenceStorage.getInstituteName(getApplicationContext()));

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        signUpServiceHelper = new SignUpServiceHelper(this);
        signUpServiceHelper.setSignUpServiceListener(this);
        progressDialogHelper = new ProgressDialogHelper(this);

        String url = PreferenceStorage.getInstituteLogoPicUrl(this);
        if ((url == null) || (url.isEmpty())) {
           /* if ((loginMode == 1) || (loginMode == 3)) {
                url = PreferenceStorage.getSocialNetworkProfileUrl(this);
            } */
        }
        if (((url != null) && !(url.isEmpty()))) {
            Picasso.with(this).load(url).placeholder(R.drawable.profile_pic).error(R.drawable.profile_pic).into(mProfileImage);
        }

    }


    @Override

    public void onClick(View v) {

        if (CommonUtils.isNetworkAvailable(this)) {
            if (v == btnLogin) {

                if (btnLogin == null) {
                    btnLogin = (Button) findViewById(v.getId());
                } else {
                    btnLogin.setBackgroundResource(R.drawable.round_btn_color);
                    btnLogin.setTextColor(getResources().getColor(R.color.white));
                    btnLogin = (Button) findViewById(v.getId());
                }

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put(EduAppConstants.PARAMS_USER_NAME, inputUsername.getText().toString());
                    jsonObject.put(EduAppConstants.PARAMS_PASSWORD, inputPassword.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
                signUpServiceHelper.makeUserLoginServiceCall(jsonObject.toString());
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
                JSONArray getData = response.getJSONArray("userData");
                JSONObject userData = getData.getJSONObject(0);
                String user_id = null;
                Log.d(TAG, "userData dictionary" + userData.toString());
                if (userData != null) {
                    user_id = userData.getString("user_id");
                    String SchoolId = userData.getString("school_id");
                    String Name = userData.getString("name");
                    String UserName = userData.getString("user_name");
                    String UserImage = userData.getString("user_pic");
                    String UserType = userData.getString("user_type");


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
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

