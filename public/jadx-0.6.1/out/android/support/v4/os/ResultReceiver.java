package android.support.v4.os;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ResultReceiver implements Parcelable {
    public static final Creator<ResultReceiver> CREATOR = new Creator<ResultReceiver>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ResultReceiver a(Parcel parcel) {
            return new ResultReceiver(parcel);
        }

        public ResultReceiver[] a(int i) {
            return new ResultReceiver[i];
        }
    };
    final boolean c = false;
    final Handler d = null;
    c e;

    class a extends android.support.v4.os.c.a {
        final /* synthetic */ ResultReceiver a;

        a(ResultReceiver resultReceiver) {
            this.a = resultReceiver;
        }

        public void a(int i, Bundle bundle) {
            if (this.a.d != null) {
                this.a.d.post(new b(this.a, i, bundle));
            } else {
                this.a.a(i, bundle);
            }
        }
    }

    class b implements Runnable {
        final int a;
        final Bundle b;
        final /* synthetic */ ResultReceiver c;

        b(ResultReceiver resultReceiver, int i, Bundle bundle) {
            this.c = resultReceiver;
            this.a = i;
            this.b = bundle;
        }

        public void run() {
            this.c.a(this.a, this.b);
        }
    }

    protected void a(int i, Bundle bundle) {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            if (this.e == null) {
                this.e = new a(this);
            }
            parcel.writeStrongBinder(this.e.asBinder());
        }
    }

    ResultReceiver(Parcel parcel) {
        this.e = android.support.v4.os.c.a.a(parcel.readStrongBinder());
    }
}
