package com.qrcomic.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public class ComicRecommendPageInfo implements Parcelable {
    public static final Creator<ComicRecommendPageInfo> CREATOR = new Creator() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ComicRecommendPageInfo a(Parcel parcel) {
            ComicRecommendPageInfo comicRecommendPageInfo = new ComicRecommendPageInfo();
            comicRecommendPageInfo.a = parcel.readString();
            comicRecommendPageInfo.b = parcel.readInt();
            comicRecommendPageInfo.c = parcel.readInt();
            comicRecommendPageInfo.d = parcel.readString();
            if (comicRecommendPageInfo.e == null) {
                comicRecommendPageInfo.e = new ArrayList();
            }
            comicRecommendPageInfo.e.clear();
            parcel.readList(comicRecommendPageInfo.e, ComicRecommendPageInfo.class.getClassLoader());
            return comicRecommendPageInfo;
        }

        public ComicRecommendPageInfo[] a(int i) {
            return new ComicRecommendPageInfo[i];
        }
    };
    public String a;
    public int b;
    public int c;
    public String d;
    public List<RecommendComicInfo> e;

    public boolean a() {
        if (TextUtils.isEmpty(this.a) || TextUtils.isEmpty(this.d) || this.c <= 0 || this.e == null || this.e.size() < 3) {
            return false;
        }
        return true;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeList(this.e);
    }
}
