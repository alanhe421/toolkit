package com.qq.reader.qurl;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class JumpActivityParameter implements Parcelable {
    public static final Creator<JumpActivityParameter> CREATOR = new 1();
    private int a = 0;
    private int b;
    private String c = "";
    private Object d = null;

    public JumpActivityParameter(Parcel parcel) {
        a(parcel);
    }

    public JumpActivityParameter a(Object obj) {
        this.d = obj;
        return this;
    }

    public JumpActivityParameter a(String str) {
        if (str != null) {
            this.c = str;
        }
        return this;
    }

    public JumpActivityParameter a(int i) {
        this.b = i;
        return this;
    }

    public JumpActivityParameter b(int i) {
        this.a = i;
        return this;
    }

    public Object a() {
        return this.d;
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeString(this.c);
    }

    public void a(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readString();
    }
}
