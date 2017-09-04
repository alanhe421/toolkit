package com.tencent.android.tpush.rpc;

import android.os.IBinder;
import android.os.Parcel;

/* compiled from: ProGuard */
class f implements d {
    private IBinder a;

    f(IBinder iBinder) {
        this.a = iBinder;
    }

    public IBinder asBinder() {
        return this.a;
    }

    public void a() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.tencent.android.tpush.rpc.ITaskCallback");
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
