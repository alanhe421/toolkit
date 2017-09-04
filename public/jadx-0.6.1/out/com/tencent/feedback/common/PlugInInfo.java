package com.tencent.feedback.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

/* compiled from: RQDSRC */
public class PlugInInfo implements Parcelable, Serializable {
    public static final Creator<PlugInInfo> CREATOR = new 1();
    private static final long serialVersionUID = 1;
    public final String plugInId;
    public final String plugInUUID;
    public final String plugInVersion;

    public PlugInInfo(String str, String str2, String str3) {
        this.plugInId = str;
        this.plugInVersion = str2;
        this.plugInUUID = str3;
    }

    public String toString() {
        return "plid:" + this.plugInId + " plV:" + this.plugInVersion + " plUUID:" + this.plugInUUID;
    }

    public PlugInInfo(Parcel parcel) {
        this.plugInId = parcel.readString();
        this.plugInVersion = parcel.readString();
        this.plugInUUID = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.plugInId);
        parcel.writeString(this.plugInVersion);
        parcel.writeString(this.plugInUUID);
    }
}
