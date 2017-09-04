package com.qrcomic.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class QRQueryDanmuByPage implements Parcelable {
    public static final Creator<QRQueryDanmuByPage> CREATOR = new Creator() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public QRQueryDanmuByPage a(Parcel parcel) {
            QRQueryDanmuByPage qRQueryDanmuByPage = new QRQueryDanmuByPage();
            qRQueryDanmuByPage.a = parcel.readString();
            qRQueryDanmuByPage.b = parcel.readString();
            qRQueryDanmuByPage.c = parcel.readString();
            qRQueryDanmuByPage.d = parcel.readString();
            qRQueryDanmuByPage.e = parcel.readInt();
            return qRQueryDanmuByPage;
        }

        public QRQueryDanmuByPage[] a(int i) {
            return new QRQueryDanmuByPage[i];
        }
    };
    public String a;
    public String b;
    public String c;
    public String d;
    public int e;

    public String toString() {
        return super.toString() + "comicId = " + this.a + " , sectionId = " + this.b + " , picId = " + this.c + " , lastDanmuId = " + this.d + " , num = " + this.e;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
    }
}
