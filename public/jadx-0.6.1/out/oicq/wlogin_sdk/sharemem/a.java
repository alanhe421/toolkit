package oicq.wlogin_sdk.sharemem;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: WloginLoginInfo */
final class a implements Creator<WloginLoginInfo> {
    a() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public WloginLoginInfo a(Parcel parcel) {
        return new WloginLoginInfo(parcel, null);
    }

    public WloginLoginInfo[] a(int i) {
        return new WloginLoginInfo[i];
    }
}
