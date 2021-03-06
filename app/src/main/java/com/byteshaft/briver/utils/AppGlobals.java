package com.byteshaft.briver.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by fi8er1 on 26/04/2016.
 */
public class AppGlobals extends Application {


    private static Context sContext;
    private static SharedPreferences sPreferences;
    private static final String LOGGED_IN = "logged_in";
    private static final String USER_NAME = "user_name";
    private static final String NAME = "student_name";
    private static final String STUDENT_TYPE = "student_type";
    private static final String TOKEN = "token";
    private static final String GCM_TOKEN = "gcm_token";
    private static final String USER_DATA = "user_data";

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        sPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static Context getContext() {
        return sContext;
    }

    public static void setLoggedIn(boolean loggedIn) {
        sPreferences.edit().putBoolean(LOGGED_IN, loggedIn).apply();
    }

    public static boolean isLoggedIn() {
        return sPreferences.getBoolean(LOGGED_IN, true);
    }

    public static void putUsername(String username) {
        sPreferences.edit().putString(USER_NAME, username).apply();
    }

    public static String getUsername() {
        return sPreferences.getString(USER_NAME, null);
    }


    public static void saveUserDataForPushNotifications(String userData) {
        sPreferences.edit().putString(USER_DATA, userData).apply();
    }

    public static String getUserDataForPushNotifications() {
        return sPreferences.getString(USER_DATA, null);
    }

    public static void putToken(String token) {
        sPreferences.edit().putString(TOKEN, token).apply();
    }

    public static String getToken() {
        return sPreferences.getString(TOKEN, null);
    }

    public static void putGcmToken(String gcmToken) {
        sPreferences.edit().putString(GCM_TOKEN, gcmToken).apply();
    }

    public static String getGcmToken() {
        return sPreferences.getString(GCM_TOKEN, null);
    }

    public static String getName() {
        return sPreferences.getString(NAME, null);
    }
    public static void putName(String name) {
        sPreferences.edit().putString(NAME, name).apply();
    }
    public static int getUserType() {
        return sPreferences.getInt(STUDENT_TYPE, 0);
    }

    public static void putUserType(int userType) {
        sPreferences.edit().putInt(STUDENT_TYPE, userType).apply();
    }
}
