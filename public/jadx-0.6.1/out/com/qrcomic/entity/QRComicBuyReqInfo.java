package com.qrcomic.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public class QRComicBuyReqInfo implements Parcelable {
    public static final Creator<QRComicBuyReqInfo> CREATOR = new Creator() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public QRComicBuyReqInfo a(Parcel parcel) {
            QRComicBuyReqInfo qRComicBuyReqInfo = new QRComicBuyReqInfo();
            qRComicBuyReqInfo.a = parcel.readString();
            qRComicBuyReqInfo.b = parcel.createStringArrayList();
            return qRComicBuyReqInfo;
        }

        public QRComicBuyReqInfo[] a(int i) {
            return new QRComicBuyReqInfo[i];
        }
    };
    public String a;
    public List<String> b;

    public QRComicBuyReqInfo(String str, List<String> list) {
        this.a = str;
        this.b = list;
    }

    public QRComicBuyReqInfo(String str) {
        this(str, null);
    }

    public String toString() {
        return "comicId = " + this.a + " , sectionIdList = " + this.b + " hashcode = " + super.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeStringList(this.b);
    }
}
