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
import com.palprotech.eduappparentsstudents.serviceinterfaces.ISignUpServiceListener;
import com.palprotech.eduappparentsstudents.utils.AppValidator;
import com.palprotech.eduappparentsstudents.utils.CommonUtils;
import com.palprotech.eduappparentsstudents.utils.EduAppConstants;
import com.palprotech.eduappparentsstudents.utils.PreferenceStorage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 25-05-2017.
 */

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener, IForgotPasswordServiceListener, DialogClickListener {

    private static final String TAG = ChangePasswordActivity.class.getName();
    EditText edtCurrentName, edtNewPassword, edtConfirmPassword;
    Button btnConfirm;
    private SignUpServiceHelper signUpServiceHelper;
    private ProgressDialogHelper progressDialogHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        edtCurrentName = (EditText) findViewById(R.id.edtcurrentpassword);
        edtNewPassword = (EditText) findViewById(R.id.edtnewpassword);
        edtConfirmPassword = (EditText) findViewById(R.id.edtconfirmpassword);
        btnConfirm = (Button) findViewById(R.id.confirm);
        btnConfirm.setOnClickListener(this);

        signUpServiceHelper = new SignUpServiceHelper(this);
        signUpServiceHelper.setForgotPasswordServiceListener(this);
        progressDialogHelper = new ProgressDialogHelper(this);


    }

    @Override
    public void onClick(View v) {
        if (CommonUtils.isNetworkAvailable(this)) {
            if (v == btnConfirm) {
                if (validateFields()) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put(EduAppConstants.PARAMS_FP_USER_ID, PreferenceStorage.getUserId(this));
                        jsonObject.put(EduAppConstants.PARAMS_CP_CURRENT_PASSWORD, edtCurrentName.getText().toString());
                        jsonObject.put(EduAppConstants.PARAMS_PASSWORD, edtNewPassword.getText().toString());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
                    signUpServiceHelper.makeChangePasswordServiceCall(jsonObject.toString());
                    //PreferenceStorage.saveLoginMode(this, 2);
                }
            } else {
                AlertDialogHelper.showSimpleAlertDialog(this, "No Network connection");
            }
        }
    }

    private boolean validateFields() {
        int minChar = 8;
        if (!AppValidator.checkNullString(this.edtCurrentName.getText().toString().trim())) {
            AlertDialogHelper.showSimpleAlertDialog(this, this.getResources().getString(R.string.enter_password));
            return false;
        } else if (!AppValidator.checkNullString(this.edtNewPassword.getText().toString().trim())) {
            AlertDialogHelper.showSimpleAlertDialog(this, this.getResources().getString(R.string.enter_password));
            return false;
        } else if (!AppValidator.checkNullString(this.edtConfirmPassword.getText().toString().trim())) {
            AlertDialogHelper.showSimpleAlertDialog(this, this.getResources().getString(R.string.enter_password));
            return false;
        } else if (!AppValidator.checkStringMinLength(minChar, this.edtNewPassword.getText().toString().trim())) {
            AlertDialogHelper.showSimpleAlertDialog(this, this.getResources().getString(R.string.min_pass_length));
            return false;
        } else if (!this.edtNewPassword.getText().toString().trim().contentEquals(this.edtConfirmPassword.getText().toString().trim())) {
            AlertDialogHelper.showSimpleAlertDialog(this, this.getResources().getString(R.string.password_mismatch));
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onAlertPositiveClicked(int tag) {

    }

    @Override
    public void onAlertNegativeClicked(int tag) {

    }

    @Override
    public void onForgotPassword(JSONObject response) {
        progressDialogHelper.hideProgressDialog();
        if (validateForgotPasswordResponse(response)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Password changed");
            alertDialogBuilder.setMessage("Password changed successfully");
            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
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
    public void onForgotPasswordError(String error) {
        progressDialogHelper.hideProgressDialog();
        AlertDialogHelper.showSimpleAlertDialog(this, error);
    }
}
