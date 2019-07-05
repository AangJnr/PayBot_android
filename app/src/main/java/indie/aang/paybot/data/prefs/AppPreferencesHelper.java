/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package indie.aang.paybot.data.prefs;

import android.content.SharedPreferences;

import indie.aang.paybot.data.DataManager;
import indie.aang.paybot.utilities.AppConstants;
import indie.aang.paybot.utilities.AppLogger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by janisharali on 27/01/17.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_USER_ID = "PREF_KEY_USER_ID";
    private static final String PREF_KEY_USER_NAME = "PREF_KEY_USER_NAME";
    private static final String PREF_KEY_USER_EMAIL = "PREF_KEY_USER_EMAIL";
    private static final String PREF_KEY_USER_ACTIVE = "PREF_KEY_USER_ACTIVE";
     private static final String PREF_KEY_USER_PROFILE_PIC_URL = "PREF_KEY_USER_PROFILE_PIC_URL";
     private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private static final String PREF_KEY_USER_ROLE = "PREF_KEY_USER_ROLE";
    private static final String PREF_KEY_USER_TYPE = "PREF_KEY_USER_TYPE";
    private static final String PREF_KEY_USER_PHONE = "PREF_KEY_USER_PHONE";
    private static final String PREF_KEY_USER_LOCATION = "PREF_KEY_USER_LOCATION";

    private static final String PREF_KEY_USER_COMMUNITIES = "PREF_KEY_USER_COMMUNITIES";

    private final SharedPreferences mPrefs;

    //private SecurePreferences securedPreferences;

    @Inject
    public AppPreferencesHelper(SharedPreferences preferences) {
        mPrefs = preferences;
        //securedPreferences = new SecurePreferences(mPrefs, BaseActivity.DEVICE_ID, false);
    }

    @Override
    public int getUserId() {
        return mPrefs.getInt(PREF_KEY_USER_ID, AppConstants.NULL_INDEX);
    }

    @Override
    public void setUserId(int userId) {
        mPrefs.edit().putInt(PREF_KEY_USER_ID, userId).apply();
    }

    @Override
    public String getUserName() {
        return mPrefs.getString(PREF_KEY_USER_NAME, "");
    }

    @Override
    public void setUserName(String name) {

        mPrefs.edit().putString(PREF_KEY_USER_NAME, name).apply();

    }


    @Override
    public String getUserEmail() {
        return mPrefs.getString(PREF_KEY_USER_EMAIL, null);
    }

    @Override
    public void setUserEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_USER_EMAIL, email).apply();
    }

    @Override
    public String getUserPhoneNumber() {
        return mPrefs.getString(PREF_KEY_USER_PHONE, "");
    }

    @Override
    public void setUserPhoneNumber(String phone) {
        mPrefs.edit().putString(PREF_KEY_USER_PHONE, phone).apply();


    }

    @Override
    public String getUserRole() {
        return mPrefs.getString(PREF_KEY_USER_ROLE, "");
    }

    @Override
    public void setUserRole(String role) {
        mPrefs.edit().putString(PREF_KEY_USER_ROLE, role).apply();
        }


    @Override
    public Boolean getUserIsActive() {
        return mPrefs.getBoolean(PREF_KEY_USER_ACTIVE, false);
    }

    @Override
    public void setUserIsActive(boolean isActive) {
        mPrefs.edit().putBoolean(PREF_KEY_USER_ACTIVE, isActive).apply();
    }

    @Override
    public String getUserProfilePicUrl() {
        return mPrefs.getString(PREF_KEY_USER_PROFILE_PIC_URL, null);
    }

    @Override
    public void setUserProfilePicUrl(String profilePicUrl) {
        mPrefs.edit().putString(PREF_KEY_USER_PROFILE_PIC_URL, profilePicUrl).apply();
    }

    @Override
    public String getUserLocation() {
        return mPrefs.getString(PREF_KEY_USER_LOCATION, "");
    }

    @Override
    public void setUserLocation(String location) {

        mPrefs.edit().putString(PREF_KEY_USER_LOCATION, location).apply();

    }

    @Override
    public String getUserType() {
        return mPrefs.getString(PREF_KEY_USER_TYPE, "");
    }

    @Override
    public void setUserType(String type) {

        mPrefs.edit().putString(PREF_KEY_USER_TYPE, type).apply();
    }



    @Override
    public String getAssignedCommunities() {
        return mPrefs.getString(PREF_KEY_USER_COMMUNITIES, "");
    }

    @Override
    public void setAssignedCommunities(String value) {

        mPrefs.edit().putString(PREF_KEY_USER_COMMUNITIES, value).apply();
    }


    public List<Integer> getUserAssignedCommunities(){

        AppLogger.i("ASSIGNED COMMUNIR+TIES OF USER ====>>>"  + getAssignedCommunities()
                .replace("\"", "")
                .replace("[", "")
                .replace("]", ""));
                return new ArrayList<>();
    }

    @Override
    public int getUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_OUT.getType());
    }

    @Override
    public void setUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }


    @Override
    public void clearPreferences() {
        mPrefs.edit().clear().apply();
    }

    @Override
    public void clearSecurePreferences() {
       // securedPreferences.clear();
    }

    @Override
    public String getStringValue(String key) {
        return mPrefs.getString(key, "");
    }

    @Override
    public void setStringValue(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
    }


    @Override
    public void setBooleanValue(String key, boolean value) {

        mPrefs.edit().putBoolean(key, value).apply();
    }

    @Override
    public boolean getBooleanValue(String key) {
        return mPrefs.getBoolean(key, false);
    }
}
