package com.palprotech.eduappparentsstudents.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.helper.AlertDialogHelper;
import com.palprotech.eduappparentsstudents.helper.ProgressDialogHelper;
import com.palprotech.eduappparentsstudents.interfaces.DialogClickListener;
import com.palprotech.eduappparentsstudents.servicehelpers.SignUpServiceHelper;
import com.palprotech.eduappparentsstudents.serviceinterfaces.ISignUpServiceListener;
import com.palprotech.eduappparentsstudents.utils.AppValidator;
import com.palprotech.eduappparentsstudents.utils.CommonUtils;
import com.palprotech.eduappparentsstudents.utils.EduAppConstants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 25-05-2017.
 */

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener, ISignUpServiceListener, DialogClickListener {

    EditText edtUserName, edtPassword;
    Button btnConfirm;
    private SignUpServiceHelper signUpServiceHelper;
    private ProgressDialogHelper progressDialogHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        edtPassword = (EditText) findViewById(R.id.edtnewpassword);
        edtUserName = (EditText) findViewById(R.id.edtusername);
        btnConfirm = (Button) findViewById(R.id.confirm);
        btnConfirm.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (CommonUtils.isNetworkAvailable(this)) {
            if (v == btnConfirm) {
                if (validateFields()) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put(EduAppConstants.PARAMS_FP_USER_NAME, edtUserName.getText().toString());
                        jsonObject.put(EduAppConstants.PARAMS_PASSWORD, edtPassword.getText().toString());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    progressDialogHelper.showProgressDialog(getString(R.string.progress_loading));
                    signUpServiceHelper.makeForgotPasswordServiceCall(jsonObject.toString());
                    //PreferenceStorage.saveLoginMode(this, 2);
                }
            } else {
                AlertDialogHelper.showSimpleAlertDialog(this, "No Network connection");
            }
        }
    }

    private boolean validateFields() {
        if (!AppValidator.checkNullString(this.edtUserName.getText().toString().trim())) {
            AlertDialogHelper.showSimpleAlertDialog(this, this.getResources().getString(R.string.enter_user_name));
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void onSignUp(JSONObject response) {

    }

    @Override
    public void onSignUpError(String error) {

    }

    @Override
    public void onAlertPositiveClicked(int tag) {

    }

    @Override
    public void onAlertNegativeClicked(int tag) {

    }
}
