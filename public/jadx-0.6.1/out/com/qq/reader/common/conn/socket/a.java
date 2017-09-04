package com.qq.reader.common.conn.socket;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: IPushService */
public interface a extends IInterface {

    /* compiled from: IPushService */
    public static abstract class a extends Binder implements a {
        public a() {
            attachInterface(this, "com.qq.reader.common.conn.socket.IPushService");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.qq.reader.common.conn.socket.IPushService");
                    a(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.qq.reader.common.conn.socket.IPushService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void a(String str) throws RemoteException;
}
