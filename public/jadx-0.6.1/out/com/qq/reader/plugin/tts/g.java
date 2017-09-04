package com.qq.reader.plugin.tts;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: ITtsFakeService */
public interface g extends IInterface {

    /* compiled from: ITtsFakeService */
    public static abstract class a extends Binder implements g {

        /* compiled from: ITtsFakeService */
        private static class a implements g {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }
        }

        public a() {
            attachInterface(this, "com.qq.reader.plugin.tts.ITtsFakeService");
        }

        public static g a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.qq.reader.plugin.tts.ITtsFakeService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof g)) {
                return new a(iBinder);
            }
            return (g) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1598968902:
                    parcel2.writeString("com.qq.reader.plugin.tts.ITtsFakeService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }
}
