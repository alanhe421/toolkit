package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RecognizerResult implements Parcelable {
    public static final Creator<RecognizerResult> CREATOR = new h();
    private String a = "";

    public RecognizerResult(Parcel parcel) {
        this.a = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
    }
}
