package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class UnderstanderResult implements Parcelable {
    public static final Creator<UnderstanderResult> CREATOR = new l();
    private String a = "";

    public UnderstanderResult(Parcel parcel) {
        this.a = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
    }
}
