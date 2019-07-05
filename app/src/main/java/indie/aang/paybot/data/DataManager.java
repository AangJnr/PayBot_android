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


import indie.aang.paybot.data.db.entity.User;
import indie.aang.paybot.data.prefs.PreferencesHelper;

public interface DataManager extends PreferencesHelper {

    void updateApiHeader(Long userId, String accessToken);

    void setUserAsLoggedOut();

    void clearAllTablesFromDb();

    int backupRestoreDatabase(boolean shouldBackup);

    void updateUserInfo(
            String accessToken,
            int userId,
            String name,
            String email,
            Boolean active,
            String phone,

            String type,
            String location,
            String image);


    void updateUserInfo(User user);

      enum LoggedInMode {

          LOGGED_OUT(0),
          LOGGED_IN(1),
          SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }

}
