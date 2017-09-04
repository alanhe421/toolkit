package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;

/* compiled from: Fragment */
final class FragmentState implements Parcelable {
    public static final Creator<FragmentState> CREATOR = new Creator<FragmentState>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public FragmentState a(Parcel parcel) {
            return new FragmentState(parcel);
        }

        public FragmentState[] a(int i) {
            return new FragmentState[i];
        }
    };
    final String a;
    final int b;
    final boolean c;
    final int d;
    final int e;
    final String f;
    final boolean g;
    final boolean h;
    final Bundle i;
    Bundle j;
    Fragment k;

    public FragmentState(Fragment fragment) {
        this.a = fragment.getClass().getName();
        this.b = fragment.mIndex;
        this.c = fragment.mFromLayout;
        this.d = fragment.mFragmentId;
        this.e = fragment.mContainerId;
        this.f = fragment.mTag;
        this.g = fragment.mRetainInstance;
        this.h = fragment.mDetached;
        this.i = fragment.mArguments;
    }

    public FragmentState(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.a = parcel.readString();
        this.b = parcel.readInt();
        this.c = parcel.readInt() != 0;
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readString();
        if (parcel.readInt() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.g = z;
        if (parcel.readInt() == 0) {
            z2 = false;
        }
        this.h = z2;
        this.i = parcel.readBundle();
        this.j = parcel.readBundle();
    }

    public Fragment a(j jVar, Fragment fragment) {
        if (this.k != null) {
            return this.k;
        }
        Context i = jVar.i();
        if (this.i != null) {
            this.i.setClassLoader(i.getClassLoader());
        }
        this.k = Fragment.instantiate(i, this.a, this.i);
        if (this.j != null) {
            this.j.setClassLoader(i.getClassLoader());
            this.k.mSavedFragmentState = this.j;
        }
        this.k.setIndex(this.b, fragment);
        this.k.mFromLayout = this.c;
        this.k.mRestored = true;
        this.k.mFragmentId = this.d;
        this.k.mContainerId = this.e;
        this.k.mTag = this.f;
        this.k.mRetainInstance = this.g;
        this.k.mDetached = this.h;
        this.k.mFragmentManager = jVar.d;
        if (l.a) {
            Log.v("FragmentManager", "Instantiated fragment " + this.k);
        }
        return this.k;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c ? 1 : 0);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
        if (this.g) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (!this.h) {
            i3 = 0;
        }
        parcel.writeInt(i3);
        parcel.writeBundle(this.i);
        parcel.writeBundle(this.j);
    }
}
