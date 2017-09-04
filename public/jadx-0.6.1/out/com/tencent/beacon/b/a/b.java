package com.tencent.beacon.b.a;

import java.io.Serializable;

/* compiled from: ProGuard */
public final class b implements Serializable {
    public long a;
    public String b;
    public int c;
    public boolean d;
    public boolean e;

    public final boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass() || !(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (bVar.b == null || !bVar.b.equals(this.b)) {
            return false;
        }
        return true;
    }
}
