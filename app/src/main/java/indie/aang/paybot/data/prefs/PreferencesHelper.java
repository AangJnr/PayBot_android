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


import indie.aang.paybot.data.DataManager;

/**
 * Created by janisharali on 27/01/17.
 */

public interface PreferencesHelper {

    int getUserLoggedInMode();

    void setUserLoggedInMode(DataManager.LoggedInMode mode);

    int getUserId();

    void setUserId(int userId);

    String getUserName();
    void setUserName(String name);

    String getUserEmail();

    void setUserEmail(String email);

    String getUserPhoneNumber();

    void setUserPhoneNumber(String email);


    String getUserRole();

    void setUserRole(String role);


    Boolean getUserIsActive();

    void setUserIsActive(boolean isActive);


    String getUserProfilePicUrl();

    void setUserProfilePicUrl(String profilePicUrl);

    String getAssignedCommunities();

    void setAssignedCommunities(String value);

    String getUserLocation();

    void setUserLocation(String location);

    String getUserType();

    void setUserType(String type);

    String getAccessToken();

    void setAccessToken(String accessToken);

    void clearPreferences();

    void clearSecurePreferences();


    String getStringValue(String key);

    void setStringValue(String key, String value);


    boolean getBooleanValue(String key);

    void setBooleanValue(String key, boolean value);
 

}
