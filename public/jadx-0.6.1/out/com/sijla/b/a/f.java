package com.sijla.b.a;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class f extends e {
    public static final Creator<f> CREATOR = new Creator<f>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public f a(Parcel parcel) {
            return new f(parcel);
        }

        public f[] a(int i) {
            return new f[i];
        }
    };
    private final String[] a;

    public static f a(int i) {
        return new f(String.format("/proc/%d/stat", new Object[]{Integer.valueOf(i)}));
    }

    private f(String str) {
        super(str);
        this.a = this.b.split("\\s+");
    }

    private f(Parcel parcel) {
        super(parcel);
        this.a = parcel.createStringArray();
    }

    public String a() {
        return this.a[1].replace("(", "").replace(")", "");
    }

    public int b() {
        return Integer.parseInt(this.a[40]);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeStringArray(this.a);
    }
}
