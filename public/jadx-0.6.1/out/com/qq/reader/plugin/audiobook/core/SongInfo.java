package com.qq.reader.plugin.audiobook.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.qq.reader.module.bookchapter.online.OnlineChapter;

public class SongInfo implements Parcelable {
    public static final Creator<SongInfo> CREATOR = new Creator<SongInfo>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public SongInfo a(Parcel parcel) {
            return new SongInfo(parcel);
        }

        public SongInfo[] a(int i) {
            return new SongInfo[i];
        }
    };
    public OnlineChapter a;
    public String b;
    public boolean c = false;
    private int d;
    private String e;
    private long f;
    private long g;
    private long h;
    private int i;
    private String j;
    private long k;

    public SongInfo(String str, long j) {
        if (str == null) {
            this.e = "";
        } else {
            this.e = str;
        }
        this.d = 0;
        this.h = j;
    }

    public SongInfo(Parcel parcel) {
        a(parcel);
    }

    @Deprecated
    public String a() {
        return h();
    }

    public void a(long j) {
        this.f = j;
    }

    public long b() {
        return this.f;
    }

    public String c() {
        return this.e;
    }

    public void a(int i) {
        this.d = i;
    }

    public int d() {
        return this.d;
    }

    public void b(long j) {
        this.g = j;
    }

    public void a(Parcel parcel) {
        this.e = parcel.readString();
        if (this.e == null) {
            this.e = "";
        }
        this.d = parcel.readInt();
        this.f = parcel.readLong();
        this.g = parcel.readLong();
        this.h = parcel.readLong();
        this.j = parcel.readString();
        this.k = parcel.readLong();
        this.b = parcel.readString();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof SongInfo)) {
            return false;
        }
        if (this.k == ((SongInfo) obj).k && this.e.equals(((SongInfo) obj).e)) {
            return true;
        }
        return false;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.e);
        parcel.writeInt(this.d);
        parcel.writeLong(this.f);
        parcel.writeLong(this.g);
        parcel.writeLong(this.h);
        parcel.writeString(this.j);
        parcel.writeLong(this.k);
        parcel.writeString(this.b);
    }

    public void c(long j) {
        this.k = j;
    }

    public long e() {
        return this.k;
    }

    public int f() {
        return this.i;
    }

    public void b(int i) {
        this.i = i;
    }

    public long g() {
        return this.h;
    }

    public String h() {
        return this.j;
    }

    public String i() {
        if (this.a != null) {
            return this.a.getBookName();
        }
        return null;
    }

    public void a(String str) {
        this.j = str;
    }
}
