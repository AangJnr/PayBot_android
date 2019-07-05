package indie.aang.paybot.data.db.entity;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import indie.aang.paybot.utilities.AppConstants;

/**
 * Created by AangJnr on 09, December, 2018 @ 11:54 AM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */


@Entity(tableName = "users", indices = @Index(value = "id", unique = true))
public class User extends BaseModel{

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("phone_number")
    @Expose
    public String phoneNumber;


    @SerializedName("password")
    @Expose
    public String password;


    @SerializedName("role_id")
    @Expose
    public int roleId;


    @SerializedName("gender")
    @Expose
    public String gender;



    @SerializedName("location")
    @Expose
    public String location;

    @SerializedName("profile_picture")
    @Expose
    public String profileImage;


    @SerializedName("status")
    @Expose
    public String status = AppConstants.USER_DEACTIVATED;

    @SerializedName("country_id")
    @Expose
    public int countryId;

    @Ignore
    @SerializedName("token")
    @Expose
    String accessToken;



    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }




    @Ignore
    public User(int id, String name, String email, boolean isActivated, String phoneNumber, String type, String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = (isActivated) ? AppConstants.USER_ACTIVATED : AppConstants.USER_DEACTIVATED;
        this.phoneNumber = phoneNumber;

        this.type = type;
        this.location = location;
    }

    /**
     *
     *
     */


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isActivated(){
        return getStatus().equalsIgnoreCase(AppConstants.USER_ACTIVATED);


    }


}