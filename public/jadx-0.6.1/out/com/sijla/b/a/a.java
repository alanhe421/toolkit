package com.sijla.b.a;

import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.io.File;

public class a extends b {
    public static final Creator<a> CREATOR = new Creator<a>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public a a(Parcel parcel) {
            return new a(parcel);
        }

        public a[] a(int i) {
            return new a[i];
        }
    };
    private static final boolean e = new File("/dev/cpuctl/tasks").exists();
    public final boolean a;
    public final int b;

    public static final class a extends Exception {
        public a(int i) {
            super(String.format("The process %d does not belong to any application", new Object[]{Integer.valueOf(i)}));
        }
    }

    public a(int i) {
        boolean z;
        int parseInt;
        super(i);
        if (e) {
            c b = b();
            d a = b.a("cpuacct");
            d a2 = b.a("cpu");
            if (VERSION.SDK_INT >= 21) {
                if (a2 == null || a == null || !a.c.contains("pid_")) {
                    throw new a(i);
                }
                z = !a2.c.contains("bg_non_interactive");
                try {
                    parseInt = Integer.parseInt(a.c.split("/")[1].replace("uid_", ""));
                } catch (Exception e) {
                    parseInt = d().a();
                }
                com.sijla.b.a.a("name=%s, pid=%d, uid=%d, foreground=%b, cpuacct=%s, cpu=%s", this.c, Integer.valueOf(i), Integer.valueOf(parseInt), Boolean.valueOf(z), a.toString(), a2.toString());
            } else if (a2 == null || a == null || !a2.c.contains("apps")) {
                throw new a(i);
            } else {
                z = !a2.c.contains("bg_non_interactive");
                try {
                    parseInt = Integer.parseInt(a.c.substring(a.c.lastIndexOf("/") + 1));
                } catch (Exception e2) {
                    parseInt = d().a();
                }
                com.sijla.b.a.a("name=%s, pid=%d, uid=%d foreground=%b, cpuacct=%s, cpu=%s", this.c, Integer.valueOf(i), Integer.valueOf(parseInt), Boolean.valueOf(z), a.toString(), a2.toString());
            }
        } else if (this.c.startsWith("/") || !new File("/data/data", a()).exists()) {
            throw new a(i);
        } else {
            f c = c();
            g d = d();
            if (c.b() == 0) {
                z = true;
            } else {
                z = false;
            }
            parseInt = d.a();
            com.sijla.b.a.a("name=%s, pid=%d, uid=%d foreground=%b", this.c, Integer.valueOf(i), Integer.valueOf(parseInt), Boolean.valueOf(z));
        }
        this.a = z;
        this.b = parseInt;
    }

    public String a() {
        return this.c.split(":")[0];
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeByte((byte) (this.a ? 1 : 0));
        parcel.writeInt(this.b);
    }

    protected a(Parcel parcel) {
        super(parcel);
        this.a = parcel.readByte() != (byte) 0;
        this.b = parcel.readInt();
    }
}
