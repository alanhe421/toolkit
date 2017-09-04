package com.qq.reader.module.redpacket.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RedPacketMessage implements Parcelable {
    public static final Creator<RedPacketMessage> CREATOR = new Creator<RedPacketMessage>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public RedPacketMessage a(Parcel parcel) {
            return new RedPacketMessage(parcel);
        }

        public RedPacketMessage[] a(int i) {
            return new RedPacketMessage[i];
        }
    };
    private String a;
    private String b;
    private long c;
    private String d;
    private int e;
    private long f;

    protected RedPacketMessage(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readLong();
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeLong(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeLong(this.f);
    }

    public int describeContents() {
        return 0;
    }

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

    public long c() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public int e() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public long f() {
        return this.f;
    }

    public void b(long j) {
        this.f = j;
    }
}
