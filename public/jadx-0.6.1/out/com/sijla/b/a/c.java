package com.sijla.b.a;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.Iterator;

public final class c extends e {
    public static final Creator<c> CREATOR = new Creator<c>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public c a(Parcel parcel) {
            return new c(parcel);
        }

        public c[] a(int i) {
            return new c[i];
        }
    };
    public final ArrayList<d> a;

    public static c a(int i) {
        return new c(String.format("/proc/%d/cgroup", new Object[]{Integer.valueOf(i)}));
    }

    private c(String str) {
        super(str);
        String[] split = this.b.split("\n");
        this.a = new ArrayList();
        for (String dVar : split) {
            try {
                this.a.add(new d(dVar));
            } catch (Exception e) {
            }
        }
    }

    private c(Parcel parcel) {
        super(parcel);
        this.a = parcel.createTypedArrayList(d.CREATOR);
    }

    public d a(String str) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            d dVar = (d) it.next();
            for (String equals : dVar.b.split(",")) {
                if (equals.equals(str)) {
                    return dVar;
                }
            }
        }
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.a);
    }
}
