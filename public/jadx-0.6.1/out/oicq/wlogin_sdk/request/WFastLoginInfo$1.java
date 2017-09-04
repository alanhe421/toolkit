package oicq.wlogin_sdk.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class WFastLoginInfo$1 implements Creator<WFastLoginInfo> {
    WFastLoginInfo$1() {
    }

    public WFastLoginInfo createFromParcel(Parcel parcel) {
        return new WFastLoginInfo(parcel, null);
    }

    public WFastLoginInfo[] newArray(int i) {
        return new WFastLoginInfo[i];
    }
}
