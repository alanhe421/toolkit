package com.sijla.b.a;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.io.IOException;

public class b implements Parcelable {
    public static final Creator<b> CREATOR = new Creator<b>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public b a(Parcel parcel) {
            return new b(parcel);
        }

        public b[] a(int i) {
            return new b[i];
        }
    };
    public final String c;
    public final int d;

    static String a(int i) {
        String str = null;
        try {
            str = e.b(String.format("/proc/%d/cmdline", new Object[]{Integer.valueOf(i)})).trim();
        } catch (IOException e) {
        }
        if (TextUtils.isEmpty(str)) {
            return f.a(i).a();
        }
        return str;
    }

    public b(int i) {
        this.d = i;
        this.c = a(i);
    }

    public c b() {
        return c.a(this.d);
    }

    public f c() {
        return f.a(this.d);
    }

    public g d() {
        return g.a(this.d);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
    }

    protected b(Parcel parcel) {
        this.c = parcel.readString();
        this.d = parcel.readInt();
    }
}
