package com.tencent.qalsdk.base.remote;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.qalsdk.base.remote.IMsfServiceCallbacker.Stub;

public class MsfServiceBindInfo implements Parcelable {
    public static final Creator<MsfServiceBindInfo> CREATOR = new b();
    private int appid;
    private String bootBoradcastName;
    private IMsfServiceCallbacker msfServiceCallbacker;
    private String processName;

    public int describeContents() {
        return 0;
    }

    public MsfServiceBindInfo(int i, String str, String str2, IMsfServiceCallbacker iMsfServiceCallbacker) {
        this.appid = i;
        this.processName = str;
        this.bootBoradcastName = str2;
        this.msfServiceCallbacker = iMsfServiceCallbacker;
    }

    public MsfServiceBindInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    private void readFromParcel(Parcel parcel) {
        this.appid = parcel.readInt();
        this.processName = parcel.readString();
        this.bootBoradcastName = parcel.readString();
        this.msfServiceCallbacker = Stub.asInterface(parcel.readStrongBinder());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.appid);
        parcel.writeString(this.processName);
        parcel.writeString(this.bootBoradcastName);
        parcel.writeStrongInterface(this.msfServiceCallbacker);
    }

    public IMsfServiceCallbacker getMsfServiceCallbacker() {
        return this.msfServiceCallbacker;
    }

    public void setMsfSericeCallbacker(IMsfServiceCallbacker iMsfServiceCallbacker) {
        this.msfServiceCallbacker = iMsfServiceCallbacker;
    }

    public String getProcessName() {
        return this.processName;
    }

    public void setProcessName(String str) {
        this.processName = str;
    }

    public String getBootBoradcastName() {
        return this.bootBoradcastName;
    }

    public void setBootBoradcastName(String str) {
        this.bootBoradcastName = str;
    }

    public int getAppid() {
        return this.appid;
    }

    public void setAppid(int i) {
        this.appid = i;
    }
}
