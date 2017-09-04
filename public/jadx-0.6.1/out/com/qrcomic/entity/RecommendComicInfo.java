package com.qrcomic.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RecommendComicInfo implements Parcelable {
    public static final Creator<RecommendComicInfo> CREATOR = new Creator() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public RecommendComicInfo a(Parcel parcel) {
            RecommendComicInfo recommendComicInfo = new RecommendComicInfo();
            recommendComicInfo.a = parcel.readString();
            recommendComicInfo.b = parcel.readString();
            recommendComicInfo.c = parcel.readString();
            recommendComicInfo.d = parcel.readInt();
            recommendComicInfo.e = parcel.readInt();
            recommendComicInfo.f = parcel.readInt();
            recommendComicInfo.g = parcel.readInt();
            return recommendComicInfo;
        }

        public RecommendComicInfo[] a(int i) {
            return new RecommendComicInfo[i];
        }
    };
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;
    public int g;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
    }
}
