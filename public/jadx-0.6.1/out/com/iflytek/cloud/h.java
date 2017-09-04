package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class h implements Creator<RecognizerResult> {
    h() {
    }

    public RecognizerResult a(Parcel parcel) {
        return new RecognizerResult(parcel);
    }

    public RecognizerResult[] a(int i) {
        return new RecognizerResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
