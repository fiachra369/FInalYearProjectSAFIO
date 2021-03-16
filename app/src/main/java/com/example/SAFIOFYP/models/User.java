package com.example.SAFIOFYP.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Spinner;


public class User implements Parcelable{

    private String email;
    private String user_id;
    private String name;
    private String number;
    private String condition;
    private String date;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User(String email, String user_id, String name, String number, String condition, String date) {
        this.email = email;
        this.user_id = user_id;
        this.name = name;
        this.number = number;
        this.condition = condition;
        this.date = date;
    }

    public User() {

    }

    protected User(Parcel in) {
        email = in.readString();
        user_id = in.readString();
        name = in.readString();
        number = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number+ '\'' + ", condition ='" + condition + '\'' + ", DOB='" + date + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(user_id);
        dest.writeString(name);
        dest.writeString(String.valueOf(number));
        dest.writeString(date);
        dest.writeString(condition);
    }
}

