package com.qq.reader.common.web.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: IGameAidlInterface */
public interface a extends IInterface {

    /* compiled from: IGameAidlInterface */
    public static abstract class a extends Binder implements a {

        /* compiled from: IGameAidlInterface */
        private static class a implements a {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public int a(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    obtain.writeString(str);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int b(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    obtain.writeString(str);
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String b() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String c() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String d() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    this.a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String e() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    this.a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String f() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    this.a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int g() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    this.a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int h() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    this.a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int i() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    this.a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    obtain.writeInt(i);
                    this.a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    obtain.writeInt(i);
                    this.a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean j() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    this.a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void k() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    this.a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.qq.reader.common.web.aidl.IGameAidlInterface");
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof a)) {
                return new a(iBinder);
            }
            return (a) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int a;
            String a2;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    a = a(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 2:
                    parcel.enforceInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    a = b(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 3:
                    parcel.enforceInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    a2 = a();
                    parcel2.writeNoException();
                    parcel2.writeString(a2);
                    return true;
                case 4:
                    parcel.enforceInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    a2 = b();
                    parcel2.writeNoException();
                    parcel2.writeString(a2);
                    return true;
                case 5:
                    parcel.enforceInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    a2 = c();
                    parcel2.writeNoException();
                    parcel2.writeString(a2);
                    return true;
                case 6:
                    parcel.enforceInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    a2 = d();
                    parcel2.writeNoException();
                    parcel2.writeString(a2);
                    return true;
                case 7:
                    parcel.enforceInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    a2 = e();
                    parcel2.writeNoException();
                    parcel2.writeString(a2);
                    return true;
                case 8:
                    parcel.enforceInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    a2 = f();
                    parcel2.writeNoException();
                    parcel2.writeString(a2);
                    return true;
                case 9:
                    parcel.enforceInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    a = g();
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 10:
                    parcel.enforceInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    a = h();
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 11:
                    parcel.enforceInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    a = i();
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 12:
                    parcel.enforceInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    a(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    b(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    boolean j = j();
                    parcel2.writeNoException();
                    parcel2.writeInt(j ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    k();
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.qq.reader.common.web.aidl.IGameAidlInterface");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    int a(String str) throws RemoteException;

    String a() throws RemoteException;

    void a(int i) throws RemoteException;

    int b(String str) throws RemoteException;

    String b() throws RemoteException;

    void b(int i) throws RemoteException;

    String c() throws RemoteException;

    String d() throws RemoteException;

    String e() throws RemoteException;

    String f() throws RemoteException;

    int g() throws RemoteException;

    int h() throws RemoteException;

    int i() throws RemoteException;

    boolean j() throws RemoteException;

    void k() throws RemoteException;
}
