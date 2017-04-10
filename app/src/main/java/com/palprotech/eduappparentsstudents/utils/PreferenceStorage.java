package com.palprotech.eduappparentsstudents.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Admin on 03-04-2017.
 */

public class PreferenceStorage {

    // School Id Login Preferences
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

    public static void saveInstituteCodeId(Context context, String instituteCodeId) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EduAppConstants.KEY_INSTITUTE_CODE_ID, instituteCodeId);
        editor.commit();
    }

    public static String getInstituteCodeId(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String instituteCodeId = sharedPreferences.getString(EduAppConstants.KEY_INSTITUTE_CODE_ID, "");
        return instituteCodeId;
    }

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


}
