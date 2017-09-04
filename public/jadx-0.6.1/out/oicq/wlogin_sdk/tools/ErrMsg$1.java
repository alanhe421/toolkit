package oicq.wlogin_sdk.tools;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class ErrMsg$1 implements Creator<ErrMsg> {
    ErrMsg$1() {
    }

    public ErrMsg createFromParcel(Parcel parcel) {
        return new ErrMsg(parcel, null);
    }

    public ErrMsg[] newArray(int i) {
        return new ErrMsg[i];
    }
}
