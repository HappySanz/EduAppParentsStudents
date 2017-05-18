package com.palprotech.eduappparentsstudents.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Admin on 03-04-2017.
 */

public class PreferenceStorage {

    // School Id Login Preferences
    // InstituteId
    public static void saveInstituteId(Context context, String instituteId) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_INSTITUTE_ID, instituteId);
        editor.commit();
    }

    public static String getInstituteId(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String instituteId = sharedPreferences.getString(EduAppConstants.KEY_INSTITUTE_ID, "");
        return instituteId;
    }

    // InstituteName
    public static void saveInstituteName(Context context, String instituteName) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_INSTITUTE_NAME, instituteName);
        editor.commit();
    }

    public static String getInstituteName(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String instituteName = sharedPreferences.getString(EduAppConstants.KEY_INSTITUTE_NAME, "");
        return instituteName;
    }

    // InstituteCode
    public static void saveInstituteCode(Context context, String instituteCode) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_INSTITUTE_CODE, instituteCode);
        editor.commit();
    }

    public static String getInstituteCode(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String instituteCode = sharedPreferences.getString(EduAppConstants.KEY_INSTITUTE_CODE, "");
        return instituteCode;
    }

    // InstituteLogoPic
    public static void saveInstituteLogoPic(Context context, String url) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_INSTITUTE_LOGO, url);
        editor.commit();

    }

    public static String getInstituteLogoPicUrl(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String url = sharedPreferences.getString(EduAppConstants.KEY_INSTITUTE_LOGO, "");
        return url;

    }

    // User Login Preferences
    // User Dynamic API
    public static void saveUserDynamicAPI(Context context, String userDynamicAPI) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_USER_DYNAMIC_API, userDynamicAPI);
        editor.commit();
    }

    public static String getUserDynamicAPI(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String userDynamicAPI = sharedPreferences.getString(EduAppConstants.KEY_USER_DYNAMIC_API, "");
        return userDynamicAPI;
    }

    // UserId
    public static void saveUserId(Context context, String userId) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_USER_ID, userId);
        editor.commit();
    }

    public static String getUserId(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String userId = sharedPreferences.getString(EduAppConstants.KEY_USER_ID, "");
        return userId;
    }

    // Name
    public static void saveName(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_NAME, name);
        editor.commit();
    }

    public static String getName(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String name = sharedPreferences.getString(EduAppConstants.KEY_NAME, "");
        return name;
    }

    // User Name
    public static void saveUserName(Context context, String userName) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String userName = sharedPreferences.getString(EduAppConstants.KEY_USER_NAME, "");
        return userName;
    }

    // User Image
    public static void saveUserPicture(Context context, String userPicture) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_USER_IMAGE, userPicture);
        editor.commit();
    }

    public static String getUserPicture(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String userPicture = sharedPreferences.getString(EduAppConstants.KEY_USER_IMAGE, "");
        return userPicture;
    }

    // User Type
    public static void saveUserType(Context context, String userType) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_USER_TYPE, userType);
        editor.commit();
    }

    public static String getUserType(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String userType = sharedPreferences.getString(EduAppConstants.KEY_USER_TYPE, "");
        return userType;
    }

    // User Type Name
    public static void saveUserTypeName(Context context, String userTypeName) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_USER_TYPE_NAME, userTypeName);
        editor.commit();
    }

    public static String getUserTypeName(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String userTypeName = sharedPreferences.getString(EduAppConstants.KEY_USER_TYPE_NAME, "");
        return userTypeName;
    }

    // Student Preference Data
    // Get Student Enroll Id
    public static void saveStudentEnrollIdPreference(Context context, String studentPrefEnrollID) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_STUDENT_ENROLL_ID_PREFERENCES, studentPrefEnrollID);
        editor.commit();
    }

    public static String getStudentEnrollIdPreference(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String studentPrefEnrollID = sharedPreferences.getString(EduAppConstants.KEY_STUDENT_ENROLL_ID_PREFERENCES, "");
        return studentPrefEnrollID;
    }

    // Get Student Admission Id
    public static void saveStudentAdmissionIdPreference(Context context, String studentPrefAdmissionID) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_STUDENT_ADMISSION_ID_PREFERENCES, studentPrefAdmissionID);
        editor.commit();
    }

    public static String getStudentAdmissionIdPreference(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String studentPrefAdmissionID = sharedPreferences.getString(EduAppConstants.KEY_STUDENT_ADMISSION_ID_PREFERENCES, "");
        return studentPrefAdmissionID;
    }

    // Get Student Admission No
    public static void saveStudentAdmissionNoPreference(Context context, String studentPrefAdmissionNo) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_STUDENT_ADMISSION_NO_PREFERENCES, studentPrefAdmissionNo);
        editor.commit();
    }

    public static String getStudentAdmissionNoPreference(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String studentPrefAdmissionNo = sharedPreferences.getString(EduAppConstants.KEY_STUDENT_ADMISSION_NO_PREFERENCES, "");
        return studentPrefAdmissionNo;
    }

    // Get Student Class Id
    public static void saveStudentClassIdPreference(Context context, String studentPrefclassId) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_STUDENT_CLASS_ID_PREFERENCES, studentPrefclassId);
        editor.commit();
    }

    public static String getStudentClassIdPreference(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String studentPrefclassId = sharedPreferences.getString(EduAppConstants.KEY_STUDENT_CLASS_ID_PREFERENCES, "");
        return studentPrefclassId;
    }

    // Get Student Name
    public static void saveStudentNamePreference(Context context, String studentPrefName) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_STUDENT_NAME_PREFERENCES, studentPrefName);
        editor.commit();
    }

    public static String getStudentNamePreference(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String studentPrefName = sharedPreferences.getString(EduAppConstants.KEY_STUDENT_NAME_PREFERENCES, "");
        return studentPrefName;
    }

    // Get Student Class Name
    public static void saveStudentClassNamePreference(Context context, String studentPrefClassName) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_STUDENT_CLASS_NAME_PREFERENCES, studentPrefClassName);
        editor.commit();
    }

    public static String getStudentClassNamePreference(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String studentPrefClassName = sharedPreferences.getString(EduAppConstants.KEY_STUDENT_CLASS_NAME_PREFERENCES, "");
        return studentPrefClassName;
    }

    // Get Student Section Name
    public static void saveStudentSectionNamePreference(Context context, String studentPrefSectionName) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_STUDENT_SECTION_NAME_PREFERENCES, studentPrefSectionName);
        editor.commit();
    }

    public static String getStudentSectionNamePreference(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String studentPrefSectionName = sharedPreferences.getString(EduAppConstants.KEY_STUDENT_SECTION_NAME_PREFERENCES, "");
        return studentPrefSectionName;
    }


    ////////////////////////////////////

    public static void saveParentID(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.PARENT_ID, name);
        editor.commit();
    }

    public static String getParentID(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String parentID = sharedPreferences.getString(EduAppConstants.PARENT_ID, "");
        return parentID;
    }

    public static void saveFatherName(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.FATHER_NAME, name);
        editor.commit();
    }

    public static String getFatherName(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String fatherName = sharedPreferences.getString(EduAppConstants.FATHER_NAME, "");
        return fatherName;
    }

    public static void saveMotherName(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.MOTHER_NAME, name);
        editor.commit();
    }

    public static String getMotherName(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String motherName = sharedPreferences.getString(EduAppConstants.PARENT_ID, "");
        return motherName;
    }

    public static void saveGuardnName(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.GUARDN_NAME, name);
        editor.commit();
    }

    public static String getGuardnName(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String guardnName = sharedPreferences.getString(EduAppConstants.GUARDN_NAME, "");
        return guardnName;
    }

    public static void saveOccupation(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.OCCUPATION, name);
        editor.commit();
    }

    public static String getOccupation(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String occupation = sharedPreferences.getString(EduAppConstants.OCCUPATION, "");
        return occupation;
    }

    public static void saveAddress(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.ADDRESS, name);
        editor.commit();
    }

    public static String getAddress(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String address = sharedPreferences.getString(EduAppConstants.ADDRESS, "");
        return address;
    }

    public static void saveEmail(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.EMAIL, name);
        editor.commit();
    }

    public static String getEmail(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String email = sharedPreferences.getString(EduAppConstants.EMAIL, "");
        return email;
    }

    public static void saveHomePhone(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.HOME_PHONE, name);
        editor.commit();
    }

    public static String getHomePhone(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String homePhone = sharedPreferences.getString(EduAppConstants.HOME_PHONE, "");
        return homePhone;
    }

    public static void saveOfficePhone(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.OFFICE_PHONE, name);
        editor.commit();
    }

    public static String getOfficePhone(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String officePhone = sharedPreferences.getString(EduAppConstants.OFFICE_PHONE, "");
        return officePhone;
    }

    public static void saveMobileOne(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.MOBILE_ONE, name);
        editor.commit();
    }

    public static String getMobileOne(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String mobileOne = sharedPreferences.getString(EduAppConstants.MOBILE_ONE, "");
        return mobileOne;
    }

    public static void saveMobileTwo(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.MOBILE_TWO, name);
        editor.commit();
    }

    public static String getMobileTwo(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String mobileTwo = sharedPreferences.getString(EduAppConstants.MOBILE_TWO, "");
        return mobileTwo;
    }

    public static void saveFatherImg(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.FATHER_IMAGE, name);
        editor.commit();
    }

    public static String getFatherImg(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String fatherImg = sharedPreferences.getString(EduAppConstants.FATHER_IMAGE, "");
        return fatherImg;
    }

    public static void saveMotherImg(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.MOTHER_IMAGE, name);
        editor.commit();
    }

    public static String getMotherImg(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String motherImg = sharedPreferences.getString(EduAppConstants.MOTHER_IMAGE, "");
        return motherImg;
    }

    public static void saveGuardnImg(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.GUARDN_IMAGE, name);
        editor.commit();
    }

    public static String getGuardnImg(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String guardnImg = sharedPreferences.getString(EduAppConstants.GUARDN_IMAGE, "");
        return guardnImg;
    }
}
