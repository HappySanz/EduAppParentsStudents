package com.palprotech.eduappparentsstudents.activity;

import android.content.Intent;
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
import com.palprotech.eduappparentsstudents.bean.database.SQLiteHelper;
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

import java.util.HashMap;

/**
 * Created by Admin on 22-03-2017.
 */

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener, ISignUpServiceListener, DialogClickListener {

    private static final String TAG = UserLoginActivity.class.getName();

    private SignUpServiceHelper signUpServiceHelper;
    private ProgressDialogHelper progressDialogHelper;

    private EditText inputUsername, inputPassword;
    private Button btnLogin;
    private TextView txtInsName, txtForgotPassword;
    private ImageView mProfileImage = null;
    SQLiteHelper database;

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
        txtForgotPassword = (TextView) findViewById(R.id.txtForgotPassword);
        txtForgotPassword.setOnClickListener(this);
        txtInsName.setText(PreferenceStorage.getInstituteName(getApplicationContext()));
        database = new SQLiteHelper(getApplicationContext());
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
            if (v == txtForgotPassword) {
                Intent intent = new Intent(this, ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
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

    public static void longInfo(String str) {
        if (str.length() > 4000) {
            Log.d("Data From", str.substring(0, 4000));
            longInfo(str.substring(4000));
        } else
            Log.d("Data To", str);
        String New;
    }

    @Override
    public void onSignUp(JSONObject response) {

        progressDialogHelper.hideProgressDialog();
        if (validateSignInResponse(response)) {

            String repo = response.toString();

            longInfo(repo);

            try {
                JSONArray getData = response.getJSONArray("userData");
                JSONObject userData = getData.getJSONObject(0);
                String user_id = null;

                JSONArray getStudentData = response.getJSONArray("enrollDetails");
                JSONObject studentData = getStudentData.getJSONObject(0);

                try {
//                    JSONArray json = new JSONArray(getStudentData);
                    database.deleteLocal();

                    for (int i = 0; i < getStudentData.length(); i++) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        JSONObject jsonobj = getStudentData.getJSONObject(i);


                        System.out.println("enroll_id : " + i + " = " + jsonobj.getString("enroll_id"));
                        System.out.println("admission_id : " + i + " = " + jsonobj.getString("admission_id"));
                        System.out.println("admisn_no : " + i + " = " + jsonobj.getString("admisn_no"));
                        System.out.println("class_id : " + i + " = " + jsonobj.getString("class_id"));
                        System.out.println("name : " + i + " = " + jsonobj.getString("name"));
                        System.out.println("class_name : " + i + " = " + jsonobj.getString("class_name"));
                        System.out.println("sec_name : " + i + " = " + jsonobj.getString("sec_name"));

                        String v1 = jsonobj.getString("enroll_id"),
                                v2 = jsonobj.getString("admission_id"),
                                v3 = jsonobj.getString("admisn_no"),
                                v4 = jsonobj.getString("class_id"),
                                v5 = jsonobj.getString("name"),
                                v6 = jsonobj.getString("class_name"),
                                v7 = jsonobj.getString("sec_name");

                        database.student_details_insert(v1, v2, v3, v4, v5, v6, v7);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                JSONArray getParentData = response.getJSONArray("parentProfile");
                JSONObject parentData = getParentData.getJSONObject(0);

                Log.d(TAG, "userData dictionary" + userData.toString());
                if (userData != null) {
                    user_id = userData.getString("user_id") + "";

                    PreferenceStorage.saveUserId(this, user_id);

                    Log.d(TAG, "created user id" + user_id);

                    //need to re do this
                    Log.d(TAG, "sign in response is" + response.toString());

                    String Name = userData.getString("name");
                    String UserName = userData.getString("user_name");
                    String UserImage = userData.getString("user_pic");
                    String UserPicUrl = PreferenceStorage.getUserDynamicAPI(this) + EduAppConstants.USER_IMAGE_API + UserImage;
                    String UserType = userData.getString("user_type");
                    String UserTypeName = userData.getString("user_type_name");
                    String forgotPasswordStatus = userData.getString("password_status");

                    String StudentPreferenceEnrollId = studentData.getString("enroll_id");
                    String StudentPreferenceAdmissionId = studentData.getString("admission_id");
                    String StudentPreferenceAdmissionNo = studentData.getString("admisn_no");
                    String StudentPreferenceClassId = studentData.getString("class_id");
                    String StudentPreferenceName = studentData.getString("name");
                    String StudentPreferenceClassName = studentData.getString("class_name");
                    String StudentPreferenceSectionName = studentData.getString("sec_name");

                    String FatherPhone = parentData.getString("home_phone");
                    String FatherMail = parentData.getString("email");
                    String Address = parentData.getString("address");

                    // User Preference - Name
                    if ((Name != null) && !(Name.isEmpty()) && !Name.equalsIgnoreCase("null")) {
                        PreferenceStorage.saveName(this, Name);
                    }

                    // User Preference - Username
                    if ((UserName != null) && !(UserName.isEmpty()) && !UserName.equalsIgnoreCase("null")) {
                        PreferenceStorage.saveUserName(this, UserName);
                    }

                    // User Preference - ProfilePic
                    if ((UserPicUrl != null) && !(UserPicUrl.isEmpty()) && !UserPicUrl.equalsIgnoreCase("null")) {
                        PreferenceStorage.saveUserPicture(this, UserPicUrl);
                    }

                    // User Preference - Usertype
                    if ((UserType != null) && !(UserType.isEmpty()) && !UserType.equalsIgnoreCase("null")) {
                        PreferenceStorage.saveUserType(this, UserType);
                    }

                    // User Preference - UsertypeName
                    if ((UserTypeName != null) && !(UserTypeName.isEmpty()) && !UserTypeName.equalsIgnoreCase("null")) {
                        PreferenceStorage.saveUserTypeName(this, UserTypeName);
                    }

                    // Forgot Password Reset Status
                    if ((forgotPasswordStatus != null) && !(forgotPasswordStatus.isEmpty()) && !forgotPasswordStatus.equalsIgnoreCase("null")) {
                        PreferenceStorage.saveForgotPasswordStatus(this, forgotPasswordStatus);
                    }
////
////
//                    // Student Preference - EnrollId
//                    if ((StudentPreferenceEnrollId != null) && !(StudentPreferenceEnrollId.isEmpty()) && !StudentPreferenceEnrollId.equalsIgnoreCase("null")) {
//                        PreferenceStorage.saveStudentEnrollIdPreference(this, StudentPreferenceEnrollId);
//                    }
//                    // Student Preference - AdmissionId
//                    if ((StudentPreferenceAdmissionId != null) && !(StudentPreferenceAdmissionId.isEmpty()) && !StudentPreferenceAdmissionId.equalsIgnoreCase("null")) {
//                        PreferenceStorage.saveStudentAdmissionIdPreference(this, StudentPreferenceAdmissionId);
//                    }
//
//                    // Student Preference - AdmissionNo
//                    if ((StudentPreferenceAdmissionNo != null) && !(StudentPreferenceAdmissionNo.isEmpty()) && !StudentPreferenceAdmissionNo.equalsIgnoreCase("null")) {
//                        PreferenceStorage.saveStudentAdmissionNoPreference(this, StudentPreferenceAdmissionNo);
//                    }
//
//                    // Student Preference - ClassId
//                    if ((StudentPreferenceClassId != null) && !(StudentPreferenceClassId.isEmpty()) && !StudentPreferenceClassId.equalsIgnoreCase("null")) {
//                        PreferenceStorage.saveStudentClassIdPreference(this, StudentPreferenceClassId);
//                    }
//
//                    // Student Preference - Name
//                    if ((StudentPreferenceName != null) && !(StudentPreferenceName.isEmpty()) && !StudentPreferenceName.equalsIgnoreCase("null")) {
//                        PreferenceStorage.saveStudentNamePreference(this, StudentPreferenceName);
//                    }
//
//                    // Student Preference - ClassName
//                    if ((StudentPreferenceClassName != null) && !(StudentPreferenceClassName.isEmpty()) && !StudentPreferenceClassName.equalsIgnoreCase("null")) {
//                        PreferenceStorage.saveStudentClassNamePreference(this, StudentPreferenceClassName);
//                    }
//
//                    // Student Preference - SectionName
//                    if ((StudentPreferenceSectionName != null) && !(StudentPreferenceSectionName.isEmpty()) && !StudentPreferenceSectionName.equalsIgnoreCase("null")) {
//                        PreferenceStorage.saveStudentSectionNamePreference(this, StudentPreferenceSectionName);
//                    }
////
////
                    // Parents Preference - Father's Phone
                    if ((FatherPhone != null) && !(FatherPhone.isEmpty()) && !FatherPhone.equalsIgnoreCase("null")) {
                        PreferenceStorage.saveHomePhone(this, FatherPhone);
                    }

                    // Parents Preference - Father's Mail
                    if ((FatherMail != null) && !(FatherMail.isEmpty()) && !FatherMail.equalsIgnoreCase("null")) {
                        PreferenceStorage.saveEmail(this, FatherMail);
                    }

                    // Parents Preference - Address
                    if ((Address != null) && !(Address.isEmpty()) && !Address.equalsIgnoreCase("null")) {
                        PreferenceStorage.saveAddress(this, Address);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (PreferenceStorage.getForgotPasswordStatus(getApplicationContext()).equalsIgnoreCase("1")) {
                Intent intent = new Intent(this, StudentInfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, ResetPasswordActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
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