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

package indie.aang.paybot.data;


import android.content.Context;
import android.os.Environment;

 import indie.aang.paybot.data.db.entity.User;
import indie.aang.paybot.data.network.ApiService;
import indie.aang.paybot.data.prefs.PreferencesHelper;
import indie.aang.paybot.di.Scope.ApplicationContext;
import indie.aang.paybot.utilities.AppConstants;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.inject.Inject;
import javax.inject.Singleton;

import indie.aang.paybot.data.db.AppDatabase;
import io.reactivex.disposables.CompositeDisposable;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final AppDatabase mAppDatabase;
    private final PreferencesHelper mPreferencesHelper;


    @Inject
    CompositeDisposable compositeDisposable;

    @Inject
    ApiService apiService;


    @Inject
    public AppDataManager(@ApplicationContext Context context, AppDatabase appDatabase, PreferencesHelper preferencesHelper) {
        mContext = context;
        mAppDatabase = appDatabase;
        mPreferencesHelper = preferencesHelper;
     }




    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);

    }

    @Override
    public void clearPreferences() {
        mPreferencesHelper.clearPreferences();
    }


    @Override
    public int getUserLoggedInMode() {
        return mPreferencesHelper.getUserLoggedInMode();
    }

    @Override
    public void setUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setUserLoggedInMode(mode);
    }

    @Override
    public int getUserId() {
        return mPreferencesHelper.getUserId();
    }

    @Override
    public void setUserId(int userId) {
        mPreferencesHelper.setUserId(userId);
    }

    @Override
    public String getUserName() {
        return mPreferencesHelper.getUserName();
    }

    @Override
    public void setUserName(String name) {
        mPreferencesHelper.setUserName(name);
    }


    @Override
    public String getUserEmail() {
        return mPreferencesHelper.getUserEmail();
    }

    @Override
    public void setUserEmail(String email) {
        mPreferencesHelper.setUserEmail(email);
    }

    @Override
    public String getUserPhoneNumber() {
        return mPreferencesHelper.getUserPhoneNumber(); }

    @Override
    public void setUserPhoneNumber(String phoneNumber) {
        mPreferencesHelper.setUserPhoneNumber(phoneNumber);
    }

    @Override
    public String getUserRole() {
        return mPreferencesHelper.getUserRole();
    }

    @Override
    public void setUserRole(String role) {
        mPreferencesHelper.setUserRole(role); }


    @Override
    public Boolean getUserIsActive() {
        return mPreferencesHelper.getUserIsActive();
    }

    @Override
    public void setUserIsActive(boolean isActive) {
        mPreferencesHelper.setUserIsActive(isActive);
    }

    @Override
    public String getUserProfilePicUrl() {
        return mPreferencesHelper.getUserProfilePicUrl();
    }

    @Override
    public void setUserProfilePicUrl(String profilePicUrl) {
        mPreferencesHelper.setUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public String getUserLocation() {
        return mPreferencesHelper.getUserLocation();
    }

    @Override
    public void setUserLocation(String location) {
        mPreferencesHelper.setUserLocation(location);

    }

    @Override
    public String getUserType() {
        return mPreferencesHelper.getUserType();
    }

    @Override
    public void setUserType(String type) {
        mPreferencesHelper.setUserType(type);
    }


    @Override
    public void setAssignedCommunities(String value) {

        mPreferencesHelper.setAssignedCommunities(value);

    }

    @Override
    public String getAssignedCommunities() {
        return mPreferencesHelper.getAssignedCommunities();
    }

    @Override
    public void clearSecurePreferences() {
        mPreferencesHelper.clearSecurePreferences();
    }


    @Override
    public void setStringValue(String key, String value) {
        mPreferencesHelper.setStringValue(key, value);
    }

    @Override
    public boolean getBooleanValue(String key) {
        return mPreferencesHelper.getBooleanValue(key);
    }

    @Override
    public void setBooleanValue(String key, boolean value) {
        mPreferencesHelper.setBooleanValue(key, value);

    }

    @Override
    public String getStringValue(String key) {
        return mPreferencesHelper.getStringValue(key);
    }

    @Override
    public  void updateUserInfo(
            String accessToken,
            int userId,
            String name,
            String email,
            Boolean active,
            String phone,
            String type,
            String location,
            String image) {

        mPreferencesHelper.setAccessToken(accessToken);
        mPreferencesHelper.setUserId(userId);
        mPreferencesHelper.setUserName(name);
        mPreferencesHelper.setUserEmail(email);
        mPreferencesHelper.setUserIsActive(active);
        mPreferencesHelper.setUserPhoneNumber(phone);
         mPreferencesHelper.setUserType(type);
        mPreferencesHelper.setUserLocation(location);
        mPreferencesHelper.setUserProfilePicUrl(image);

    }

    @Override
    public void updateUserInfo(User user) {

        mPreferencesHelper.setUserId(user.getId());
        mPreferencesHelper.setUserName(user.getName());
        mPreferencesHelper.setUserEmail(user.getEmail());
        mPreferencesHelper.setUserIsActive(user.isActivated());
        mPreferencesHelper.setUserPhoneNumber(user.getPhoneNumber());
         mPreferencesHelper.setUserType(user.getType());
        mPreferencesHelper.setUserLocation(user.getLocation());
        mPreferencesHelper.setUserProfilePicUrl(user.getLocation());

    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {

    }

    @Override
    public void setUserAsLoggedOut() {

       updateUserInfo(new User(
               -1,
               "",
               "",
               false,
               null,
               "",
               ""));

        clearPreferences();
        clearSecurePreferences();
        clearAllTablesFromDb();
    }

    @Override
    public void clearAllTablesFromDb() {
        getDatabaseManager().clearAllTables();
    }


    public AppDatabase getDatabaseManager() {
        return mAppDatabase;
    }


    @Override
    public int backupRestoreDatabase(boolean shouldbackup) {

        String oldPath;
        String newPath;

        if (shouldbackup) {
            oldPath = Environment.getDataDirectory().getPath() + "/data/" + mContext.getPackageName() + "/databases/" + AppConstants.DATABASE_NAME;
            newPath = AppConstants.DATABASE_BACKUP_DIR + "/" + AppConstants.DATABASE_NAME;

        } else {

            oldPath = AppConstants.DATABASE_BACKUP_DIR + "/" + AppConstants.DATABASE_NAME;
            newPath = Environment.getDataDirectory().getPath() + "/data/" + mContext.getPackageName() + "/databases/" + AppConstants.DATABASE_NAME;
        }


        int value;

        File currentDB = new File(oldPath);

        File backupDB = new File(newPath);

        if (!shouldbackup && !currentDB.exists())
            return 0;

        FileChannel source;
        FileChannel destination;

        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();

            value = 1;
        } catch (IOException e) {
            e.printStackTrace();
            value = -1;
        }

        return value;

    }


    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public ApiService getApiService() {
        return apiService;
    }


    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }


    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }
}
