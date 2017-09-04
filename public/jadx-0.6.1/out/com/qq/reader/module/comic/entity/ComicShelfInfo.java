package com.qq.reader.module.comic.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.qrcomic.c.b.a;

public class ComicShelfInfo implements Parcelable {
    public static final Creator<ComicShelfInfo> CREATOR = new Creator<ComicShelfInfo>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ComicShelfInfo a(Parcel parcel) {
            return new ComicShelfInfo(parcel);
        }

        public ComicShelfInfo[] a(int i) {
            return new ComicShelfInfo[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private int d;
    private long e;
    private int f;
    private boolean g;

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

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public int d() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public long e() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public void b(int i) {
        this.f = i;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public static ComicShelfInfo a(h hVar) {
        int i = 0;
        if (hVar == null) {
            return null;
        }
        ComicShelfInfo comicShelfInfo = new ComicShelfInfo();
        comicShelfInfo.a(hVar.a());
        comicShelfInfo.b(hVar.b());
        comicShelfInfo.c(hVar.d());
        if (hVar.l() != null) {
            comicShelfInfo.a(hVar.l().b());
        }
        switch (hVar.m()) {
            case 2:
                i = 1;
                break;
        }
        comicShelfInfo.b(i);
        comicShelfInfo.a(hVar.g());
        return comicShelfInfo;
    }

    public void a(a aVar) {
        this.a = aVar.a();
        this.b = aVar.b();
        this.c = aVar.c();
        this.e = aVar.e();
        this.f = aVar.f();
        this.d = aVar.d();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeLong(this.e);
        parcel.writeInt(this.f);
        parcel.writeByte(this.g ? (byte) 1 : (byte) 0);
    }

    protected ComicShelfInfo(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.e = parcel.readLong();
        this.f = parcel.readInt();
        this.g = parcel.readByte() != (byte) 0;
    }
}
