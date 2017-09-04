package com.tencent.qalsdk.base.remote;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: MsfServiceBindInfo */
final class b implements Creator<MsfServiceBindInfo> {
    b() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public MsfServiceBindInfo a(Parcel parcel) {
        return new MsfServiceBindInfo(parcel);
    }

    public MsfServiceBindInfo[] a(int i) {
        return new MsfServiceBindInfo[i];
    }
}
