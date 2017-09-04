package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class l implements Creator<UnderstanderResult> {
    l() {
    }

    public UnderstanderResult a(Parcel parcel) {
        return new UnderstanderResult(parcel);
    }

    public UnderstanderResult[] a(int i) {
        return new UnderstanderResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
