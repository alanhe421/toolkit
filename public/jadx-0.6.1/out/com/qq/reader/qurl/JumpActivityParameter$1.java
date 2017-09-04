package com.qq.reader.qurl;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class JumpActivityParameter$1 implements Creator<JumpActivityParameter> {
    JumpActivityParameter$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public JumpActivityParameter a(Parcel parcel) {
        return new JumpActivityParameter(parcel);
    }

    public JumpActivityParameter[] a(int i) {
        return new JumpActivityParameter[i];
    }
}
