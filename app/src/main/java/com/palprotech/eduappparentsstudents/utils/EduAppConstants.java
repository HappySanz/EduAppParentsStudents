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
    public static final String USER_IMAGE_API = "/assets/admin/profile/";

    // STUDENT TIMETABLE URL
    public static final String GET_STUDENT_TIME_TABLE_API = "/api/stud_timetable/studTimetable/";

    // CLASS TEST & HOMEWORK URL
    public static final String GET_STUDENT_CLASSTEST_AND_HOMEWORK_API = "/api/disp_Homework/dispHomework/";

    // EXAM & RESULT URL
    public static final String GET_EXAM_API = "/api/disp_Exams/dispExams/";
    public static final String GET_RESULT_API = "/api/disp_Exammarks/dispMarkdetails/";

    //

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
    public static final String KEY_INSTITUTE_LOGO = "institute_logo";

    // User Login Preferences
    public static final String KEY_USER_DYNAMIC_API = "user_dynamic_api";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_USER_IMAGE = "user_pic";
    public static final String KEY_USER_TYPE = "user_type";
    public static final String KEY_USER_TYPE_NAME = "user_type_name";

    // Student Preferences Data
    public static final String KEY_STUDENT_CLASS_ID_PREFERENCES = "student_class_id";

    // Get Student Time table
    public static final String PARAMS_CLASS_ID = "class_id";


    //User Profile details
    public static final String PARENT_ID = "parent_id";
    public static final String FATHER_NAME = "father_name";
    public static final String MOTHER_NAME = "mother_name";
    public static final String GUARDN_NAME = "guardn_name";
    public static final String OCCUPATION = "occupation";
    public static final String ADDRESS = "address";
    public static final String EMAIL = "email";
    public static final String HOME_PHONE = "home_phone";
    public static final String OFFICE_PHONE = "office_phone";
    public static final String MOBILE_ONE = "mobile";
    public static final String MOBILE_TWO = "mobile1";
    public static final String FATHER_IMAGE = "father_pic";
    public static final String MOTHER_IMAGE = "mother_pic";
    public static final String GUARDN_IMAGE = "guardn_pic";

    //Class Test & Homework
    public static final String PARAM_CLASS_ID = "class_id";

}
