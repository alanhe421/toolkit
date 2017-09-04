package oicq.wlogin_sdk.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class TransReqContext$1 implements Creator<TransReqContext> {
    TransReqContext$1() {
    }

    public TransReqContext createFromParcel(Parcel parcel) {
        return new TransReqContext(parcel, null);
    }

    public TransReqContext[] newArray(int i) {
        return new TransReqContext[i];
    }
}
