package com.tencent.qalsdk.base.remote;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBaseService extends IInterface {

    public static abstract class Stub extends Binder implements IBaseService {
        private static final String DESCRIPTOR = "com.tencent.qalsdk.base.remote.IBaseService";
        static final int TRANSACTION_sendSyncToServiceMsg = 1;
        static final int TRANSACTION_sendToServiceMsg = 2;

        private static class a implements IBaseService {
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

            public FromServiceMsg sendSyncToServiceMsg(ToServiceMsg toServiceMsg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    FromServiceMsg fromServiceMsg;
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (toServiceMsg != null) {
                        obtain.writeInt(1);
                        toServiceMsg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        fromServiceMsg = (FromServiceMsg) FromServiceMsg.CREATOR.createFromParcel(obtain2);
                    } else {
                        fromServiceMsg = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return fromServiceMsg;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int sendToServiceMsg(ToServiceMsg toServiceMsg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (toServiceMsg != null) {
                        obtain.writeInt(1);
                        toServiceMsg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBaseService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IBaseService)) {
                return new a(iBinder);
            }
            return (IBaseService) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            ToServiceMsg toServiceMsg = null;
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        toServiceMsg = (ToServiceMsg) ToServiceMsg.CREATOR.createFromParcel(parcel);
                    }
                    FromServiceMsg sendSyncToServiceMsg = sendSyncToServiceMsg(toServiceMsg);
                    parcel2.writeNoException();
                    if (sendSyncToServiceMsg != null) {
                        parcel2.writeInt(1);
                        sendSyncToServiceMsg.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        toServiceMsg = (ToServiceMsg) ToServiceMsg.CREATOR.createFromParcel(parcel);
                    }
                    int sendToServiceMsg = sendToServiceMsg(toServiceMsg);
                    parcel2.writeNoException();
                    parcel2.writeInt(sendToServiceMsg);
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    FromServiceMsg sendSyncToServiceMsg(ToServiceMsg toServiceMsg) throws RemoteException;

    int sendToServiceMsg(ToServiceMsg toServiceMsg) throws RemoteException;
}
