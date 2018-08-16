package com.realty.drake.bidjepeyim.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by drake on 8/15/18
 */
public class ChildList implements Parcelable {

    private String ministry;
    private String credit;

    public ChildList(String ministry, String credit) {
        this.ministry = ministry;
        this.credit = credit;
    }

    protected ChildList(Parcel in) {
        ministry = in.readString();
        credit = in.readString();
    }

    public static final Creator<ChildList> CREATOR = new Creator<ChildList>() {
        @Override
        public ChildList createFromParcel(Parcel in) {
            return new ChildList(in);
        }

        @Override
        public ChildList[] newArray(int size) {
            return new ChildList[size];
        }
    };

    public String getMinistry() {
        return ministry;
    }

    public void setMinistry(String Title) {
        this.ministry = Title;
    }
    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ministry);
        parcel.writeString(credit);
    }
}

