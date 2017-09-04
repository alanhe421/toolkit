package com.sijla.b.a;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class g extends e {
    public static final Creator<g> CREATOR = new Creator<g>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public g a(Parcel parcel) {
            return new g(parcel);
        }

        public g[] a(int i) {
            return new g[i];
        }
    };

    public static g a(int i) {
        return new g(String.format("/proc/%d/status", new Object[]{Integer.valueOf(i)}));
    }

    private g(String str) {
        super(str);
    }

    private g(Parcel parcel) {
        super(parcel);
    }

    public String a(String str) {
        for (String str2 : this.b.split("\n")) {
            if (str2.startsWith(str + ":")) {
                return str2.split(str + ":")[1].trim();
            }
        }
        return null;
    }

    public int a() {
        try {
            return Integer.parseInt(a("Uid").split("\\s+")[0]);
        } catch (Exception e) {
            return -1;
        }
    }
}
