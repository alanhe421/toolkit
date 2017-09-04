package tencent.tls.platform;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class TLSErrInfo$1 implements Creator<TLSErrInfo> {
    TLSErrInfo$1() {
    }

    public TLSErrInfo createFromParcel(Parcel parcel) {
        return new TLSErrInfo(parcel, null);
    }

    public TLSErrInfo[] newArray(int i) {
        return new TLSErrInfo[i];
    }
}
