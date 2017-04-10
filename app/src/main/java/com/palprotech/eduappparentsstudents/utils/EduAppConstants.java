package com.palprotech.eduappparentsstudents.utils;

/**
 * Created by Narendar on 30/03/17.
 */

public class EduAppConstants {

    // URLS
    // BASE URL
    public static final String BASE_URL = "http://happysanz.net/";

    // ADMIN URL
    public static final String ADMIN_BASE_URL = BASE_URL + "admin/admin_api/";
    public static final String ADMIN_BASE_API = "api.php";
    public static final String INSTITUTE_LOGIN_API = ADMIN_BASE_URL + ADMIN_BASE_API;
    public static final String GET_SCHOOL_LOGO = BASE_URL + "institute_logo/";

    // USERS URL
    public static final String USER_LOGIN_API = "/api/login/adminlogin/";

    //Service Params
    public static String PARAM_MESSAGE = "msg";

    // Admin login params
    public static final String PARAMS_FUNC_NAME = "func_name";
    public static final String SIGN_IN_PARAMS_FUNC_NAME = "chkInstid";
    public static final String PARAMS_INSTITUTE_ID = "InstituteID";

    // User login params
    public static final String PARAMS_USER_NAME = "username";
    public static final String PARAMS_PASSWORD = "password";

    // Alert Dialog Constants
    public static String ALERT_DIALOG_TITLE = "alertDialogTitle";
    public static String ALERT_DIALOG_MESSAGE = "alertDialogMessage";
    public static String ALERT_DIALOG_TAG = "alertDialogTag";
    public static String ALERT_DIALOG_INPUT_HINT = "alert_dialog_input_hint";
    public static String ALERT_DIALOG_POS_BUTTON = "alert_dialog_pos_button";
    public static String ALERT_DIALOG_NEG_BUTTON = "alert_dialog_neg_button";

    // Preferences
    // Institute Login Preferences
    public static final String KEY_INSTITUTE_ID = "institute_id";
    public static final String KEY_INSTITUTE_NAME = "institute_name";
    public static final String KEY_INSTITUTE_CODE = "institute_code";
    public static final String KEY_INSTITUTE_CODE_ID = "institute_code_id";
    public static final String KEY_INSTITUTE_LOGO = "institute_logo";

    // User Login Preferences
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_SCHOOL_ID = "school_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_USER_EMAIL = "user_name";


}
