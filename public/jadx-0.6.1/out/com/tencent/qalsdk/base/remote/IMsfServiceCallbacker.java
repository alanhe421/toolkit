package com.tencent.qalsdk.base.remote;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMsfServiceCallbacker extends IInterface {

    public static abstract class Stub extends Binder implements IMsfServiceCallbacker {
        private static final String DESCRIPTOR = "com.tencent.qalsdk.base.remote.IMsfServiceCallbacker";
        static final int TRANSACTION_onRecvPushResp = 2;
        static final int TRANSACTION_onResponse = 1;

        private static class a implements IMsfServiceCallbacker {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public String a() {
                return Stub.DESCRIPTOR;
            }

            public void onResponse(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (toServiceMsg != null) {
                        obtain.writeInt(1);
                        toServiceMsg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (fromServiceMsg != null) {
                        obtain.writeInt(1);
                        fromServiceMsg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onRecvPushResp(FromServiceMsg fromServiceMsg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (fromServiceMsg != null) {
                        obtain.writeInt(1);
                        fromServiceMsg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMsfServiceCallbacker asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMsfServiceCallbacker)) {
                return new a(iBinder);
            }
            return (IMsfServiceCallbacker) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            FromServiceMsg fromServiceMsg;
            switch (i) {
                case 1:
                    ToServiceMsg toServiceMsg;
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        toServiceMsg = (ToServiceMsg) ToServiceMsg.CREATOR.createFromParcel(parcel);
                    } else {
                        toServiceMsg = null;
                    }
                    if (parcel.readInt() != 0) {
                        fromServiceMsg = (FromServiceMsg) FromServiceMsg.CREATOR.createFromParcel(parcel);
                    } else {
                        fromServiceMsg = null;
                    }
                    onResponse(toServiceMsg, fromServiceMsg);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        fromServiceMsg = (FromServiceMsg) FromServiceMsg.CREATOR.createFromParcel(parcel);
                    } else {
                        fromServiceMsg = null;
                    }
                    onRecvPushResp(fromServiceMsg);
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void onRecvPushResp(FromServiceMsg fromServiceMsg) throws RemoteException;

    void onResponse(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) throws RemoteException;
}
