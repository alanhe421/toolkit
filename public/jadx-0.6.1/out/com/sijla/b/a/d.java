package com.sijla.b.a;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class d implements Parcelable {
    public static final Creator<d> CREATOR = new Creator<d>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public d a(Parcel parcel) {
            return new d(parcel);
        }

        public d[] a(int i) {
            return new d[i];
        }
    };
    public final int a;
    public final String b;
    public final String c;

    protected d(String str) {
        String[] split = str.split(":");
        this.a = Integer.parseInt(split[0]);
        this.b = split[1];
        this.c = split[2];
    }

    protected d(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }

    public String toString() {
        return String.format("%d:%s:%s", new Object[]{Integer.valueOf(this.a), this.b, this.c});
    }
}
