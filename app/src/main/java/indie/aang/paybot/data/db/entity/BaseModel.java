package indie.aang.paybot.data.db.entity;


import androidx.annotation.NonNull;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import indie.aang.paybot.utilities.TimeUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BaseModel {

    @Expose(serialize = false)
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int base_id;

    @SerializedName("created_at")
    String createdAt = TimeUtils.getCurrentDateTime();

    @SerializedName("updated_at")
    String updatedAt;

    @SerializedName("sync_status")
    int syncStatus = 1;

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }

    public int getSyncStatus() {
        return syncStatus;
    }

    public void setBase_id(@NonNull int base_id) {
        this.base_id = base_id;
    }

    @NonNull
    public int getBase_id() {
        return base_id;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }


    @Ignore
    public Calendar getCreatedDate(){
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat(getCreatedAt(), Locale.getDefault());
        Date date;
        try {
            date = sdf.parse(createdAt);
            cal.setTime(date);
        } catch (ParseException e) {e.printStackTrace();}
        return cal;
        }

    @Ignore
    public Calendar getCreatedDate(String s){
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date;
        try {
            date = sdf.parse(s.substring(0, 10));
            cal.setTime(date);
        } catch (ParseException e) {e.printStackTrace();}
        return cal;
    }





}
