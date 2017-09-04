package com.tencent.qalsdk.base.remote;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: ToServiceMsg */
final class c implements Creator<ToServiceMsg> {
    c() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public ToServiceMsg a(Parcel parcel) {
        return new ToServiceMsg(parcel);
    }

    public ToServiceMsg[] a(int i) {
        return new ToServiceMsg[i];
    }
}
