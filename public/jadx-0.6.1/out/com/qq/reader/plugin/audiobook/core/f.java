package com.qq.reader.plugin.audiobook.core;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* compiled from: IQQPlayerService */
public interface f extends IInterface {

    /* compiled from: IQQPlayerService */
    public static abstract class a extends Binder implements f {

        /* compiled from: IQQPlayerService */
        private static class a implements f {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public boolean a() throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void e() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void f() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void g() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void h() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long i() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long j() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long a(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    obtain.writeLong(j);
                    this.a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int k() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long l() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long m() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    obtain.writeInt(i);
                    this.a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int n() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public SongInfo o() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    SongInfo songInfo;
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        songInfo = (SongInfo) SongInfo.CREATOR.createFromParcel(obtain2);
                    } else {
                        songInfo = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return songInfo;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(SongInfo[] songInfoArr, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    obtain.writeTypedArray(songInfoArr, 0);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(SongInfo[] songInfoArr, Bundle bundle, SongInfo songInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    obtain.writeTypedArray(songInfoArr, 0);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (songInfo != null) {
                        obtain.writeInt(1);
                        songInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public SongInfo[] p() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    SongInfo[] songInfoArr = (SongInfo[]) obtain2.createTypedArray(SongInfo.CREATOR);
                    return songInfoArr;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int q() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int r() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    obtain.writeInt(i);
                    this.a.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(SongInfo[] songInfoArr, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    obtain.writeTypedArray(songInfoArr, 0);
                    obtain.writeInt(i);
                    this.a.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    obtain.writeInt(i);
                    this.a.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(SongInfo songInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    if (songInfo != null) {
                        obtain.writeInt(1);
                        songInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.a.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int s() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void t() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    this.a.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    obtain.writeInt(i);
                    this.a.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.qq.reader.plugin.audiobook.core.IQQPlayerService");
        }

        public static f a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof f)) {
                return new a(iBinder);
            }
            return (f) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int i3 = 0;
            long i4;
            SongInfo o;
            SongInfo[] songInfoArr;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    boolean a = a();
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 2:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    b();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    c();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    d();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    e();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    f();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    g();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    h();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    i4 = i();
                    parcel2.writeNoException();
                    parcel2.writeLong(i4);
                    return true;
                case 10:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    i4 = j();
                    parcel2.writeNoException();
                    parcel2.writeLong(i4);
                    return true;
                case 11:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    i4 = a(parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeLong(i4);
                    return true;
                case 12:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    i3 = k();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 13:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    i4 = l();
                    parcel2.writeNoException();
                    parcel2.writeLong(i4);
                    return true;
                case 14:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    i4 = m();
                    parcel2.writeNoException();
                    parcel2.writeLong(i4);
                    return true;
                case 15:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    a(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    i3 = n();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 17:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    o = o();
                    parcel2.writeNoException();
                    if (o != null) {
                        parcel2.writeInt(1);
                        o.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 18:
                    Bundle bundle;
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    songInfoArr = (SongInfo[]) parcel.createTypedArray(SongInfo.CREATOR);
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    a(songInfoArr, bundle);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    Bundle bundle2;
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    songInfoArr = (SongInfo[]) parcel.createTypedArray(SongInfo.CREATOR);
                    if (parcel.readInt() != 0) {
                        bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle2 = null;
                    }
                    if (parcel.readInt() != 0) {
                        o = (SongInfo) SongInfo.CREATOR.createFromParcel(parcel);
                    } else {
                        o = null;
                    }
                    a(songInfoArr, bundle2, o);
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    Parcelable[] p = p();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(p, 1);
                    return true;
                case 21:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    i3 = q();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 22:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    i3 = r();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 23:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    b(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    a((SongInfo[]) parcel.createTypedArray(SongInfo.CREATOR), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    c(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    SongInfo songInfo;
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    if (parcel.readInt() != 0) {
                        songInfo = (SongInfo) SongInfo.CREATOR.createFromParcel(parcel);
                    } else {
                        songInfo = null;
                    }
                    a(songInfo);
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    i3 = s();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 28:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    t();
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    d(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.qq.reader.plugin.audiobook.core.IQQPlayerService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    long a(long j) throws RemoteException;

    void a(int i) throws RemoteException;

    void a(SongInfo songInfo) throws RemoteException;

    void a(SongInfo[] songInfoArr, int i) throws RemoteException;

    void a(SongInfo[] songInfoArr, Bundle bundle) throws RemoteException;

    void a(SongInfo[] songInfoArr, Bundle bundle, SongInfo songInfo) throws RemoteException;

    boolean a() throws RemoteException;

    void b() throws RemoteException;

    void b(int i) throws RemoteException;

    void c() throws RemoteException;

    void c(int i) throws RemoteException;

    void d() throws RemoteException;

    void d(int i) throws RemoteException;

    void e() throws RemoteException;

    void f() throws RemoteException;

    void g() throws RemoteException;

    void h() throws RemoteException;

    long i() throws RemoteException;

    long j() throws RemoteException;

    int k() throws RemoteException;

    long l() throws RemoteException;

    long m() throws RemoteException;

    int n() throws RemoteException;

    SongInfo o() throws RemoteException;

    SongInfo[] p() throws RemoteException;

    int q() throws RemoteException;

    int r() throws RemoteException;

    int s() throws RemoteException;

    void t() throws RemoteException;
}
