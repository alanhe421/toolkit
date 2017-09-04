package com.tencent.qalsdk.base.remote;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: FromServiceMsg */
final class a implements Creator<FromServiceMsg> {
    a() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public FromServiceMsg a(Parcel parcel) {
        return new FromServiceMsg(parcel);
    }

    public FromServiceMsg[] a(int i) {
        return new FromServiceMsg[i];
    }
}
