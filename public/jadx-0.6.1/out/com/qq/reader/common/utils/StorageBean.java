package com.qq.reader.common.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class StorageBean implements Parcelable {
    public static final Creator<StorageBean> CREATOR = new Creator<StorageBean>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public StorageBean a(Parcel parcel) {
            return new StorageBean(parcel);
        }

        public StorageBean[] a(int i) {
            return new StorageBean[i];
        }
    };
    private String a;
    private String b;
    private boolean c;
    private long d;
    private long e;

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public boolean c() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void a(long j) {
        this.d = j;
    }

    public void b(long j) {
        this.e = j;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeByte(this.c ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.d);
        parcel.writeLong(this.e);
    }

    protected StorageBean(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readByte() != (byte) 0;
        this.d = parcel.readLong();
        this.e = parcel.readLong();
    }
}
