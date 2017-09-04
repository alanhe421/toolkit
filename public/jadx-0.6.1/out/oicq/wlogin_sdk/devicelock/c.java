package oicq.wlogin_sdk.devicelock;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: DevlockInfo */
final class c implements Creator<DevlockInfo> {
    c() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public DevlockInfo a(Parcel parcel) {
        return new DevlockInfo(parcel, null);
    }

    public DevlockInfo[] a(int i) {
        return new DevlockInfo[i];
    }
}
