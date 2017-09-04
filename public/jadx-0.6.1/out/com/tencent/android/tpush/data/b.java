package com.tencent.android.tpush.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: ProGuard */
final class b implements Creator {
    b() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public StorageEntity a(Parcel parcel) {
        return new StorageEntity(parcel);
    }

    public StorageEntity[] a(int i) {
        return new StorageEntity[i];
    }
}
