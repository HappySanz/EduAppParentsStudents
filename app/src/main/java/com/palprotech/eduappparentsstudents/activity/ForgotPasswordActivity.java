package com.palprotech.eduappparentsstudents.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.helper.AlertDialogHelper;
import com.palprotech.eduappparentsstudents.helper.ProgressDialogHelper;
import com.palprotech.eduappparentsstudents.interfaces.DialogClickListener;
import com.palprotech.eduappparentsstudents.servicehelpers.SignUpServiceHelper;
import com.palprotech.eduappparentsstudents.serviceinterfaces.IForgotPasswordServiceListener;
import com.palprotech.eduappparentsstudents.utils.AppValidator;
import com.palprotech.eduappparentsstudents.utils.CommonUtils;
import com.palprotech.eduappparentsstudents.utils.EduAppConstants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Narendar on 25/05/17.
 */

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener, IForgotPasswordServiceListener, DialogClickListener {
    private static final String TAG = ForgotPasswordActivity.class.getName();
    private Button btnReset;
    private EditText edtUsername, edtLastRememberPassword, edtNewPassword, edtRetypePassword;
    private ProgressDialogHelper progressDialogHelper;
    private SignUpServiceHelper signUpServiceHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgot_password);
        initializeViews();
        signUpServiceHelper = new SignUpServiceHelper(this);
        signUpServiceHelper.setForgotPasswordServiceListener(this);
        progressDialogHelper = new ProgressDialogHelper(this);
    }

    // Initialize Views
    private void initializeViews() {
        btnReset = (Button) findViewById(R.id.confirm);
        btnReset.setOnClickListener(this);
        edtUsername = (EditText) findViewById(R.id.edtusername);
    }

    @Override
    public void onClick(View view) {
        if (CommonUtils.isNetworkAvailable(this)) {
            if (view == btnReset) {
                if (validateFields()) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put(EduAppConstants.PARAMS_FP_USER_NAME, edtUsername.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
                    signUpServiceHelper.makeForgotPasswordServiceCall(jsonObject.toString());
                }
            }
        } else {
            AlertDialogHelper.showSimpleAlertDialog(this, "No Network connection available");
        }
    }

    private boolean validateFields() {
        if (!AppValidator.checkNullString(this.edtUsername.getText().toString().trim())) {
            AlertDialogHelper.showSimpleAlertDialog(this, this.getResources().getString(R.string.enter_user_name));
            return false;
        }
        else {
            return true;
        }
    }

    private boolean validateForgotPasswordResponse(JSONObject response) {
        boolean forgotPasswordsuccess = false;
        if ((response != null)) {
            try {
                String status = response.getString("status");
                String msg = response.getString(EduAppConstants.PARAM_MESSAGE);
                Log.d(TAG, "status val" + status + "msg" + msg);

                if ((status != null)) {
                    if (((status.equalsIgnoreCase("notRegistered")) || (status.equalsIgnoreCase("error")))) {
                        forgotPasswordsuccess = false;
                        if (status.equalsIgnoreCase("notRegistered")) {

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                            //alertDialogBuilder.setTitle("Registration Successful");
                            alertDialogBuilder.setMessage(msg);
                            alertDialogBuilder.setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            Intent intent = new Intent(ForgotPasswordActivity.this, SplashScreenActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });

                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();

                        } else {
                            Log.d(TAG, "Show error dialog");
                            AlertDialogHelper.showSimpleAlertDialog(this, msg);
                        }

                    } else {
                        forgotPasswordsuccess = true;

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return forgotPasswordsuccess;
    }

    @Override
    public void onForgotPassword(JSONObject response) {
        progressDialogHelper.hideProgressDialog();
        if (validateForgotPasswordResponse(response)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Password reset");
            alertDialogBuilder.setMessage("Password reset link sent to your Email Id successfully");
            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent intent = new Intent(getApplicationContext(), SplashScreenActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            /*Intent intent = new Intent(this, SelectCityActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            this.finish();*/
        }
    }

    @Override
    public void onForgotPasswordError(String error) {
        progressDialogHelper.hideProgressDialog();
        AlertDialogHelper.showSimpleAlertDialog(this, error);
    }

    @Override
    public void onAlertPositiveClicked(int tag) {

    }

    @Override
    public void onAlertNegativeClicked(int tag) {

    }
}
