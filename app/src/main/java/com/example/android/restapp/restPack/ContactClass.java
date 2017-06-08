package com.example.android.restapp.restPack;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Android on 6/7/2017.
 */

public class ContactClass implements Parcelable {
    private String email;
    private String location;
    private String name;

    protected ContactClass(Parcel in) {
        email = in.readString();
        location = in.readString();
        name = in.readString();
    }

    public ContactClass(String email, String location, String name) {
        this.email = email;
        this.location = location;
        this.name = name;
    }

    public static final Creator<ContactClass> CREATOR = new Creator<ContactClass>() {
        @Override
        public ContactClass createFromParcel(Parcel in) {
            return new ContactClass(in);
        }

        @Override
        public ContactClass[] newArray(int size) {
            return new ContactClass[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(location);
        dest.writeString(name);

    }
}
