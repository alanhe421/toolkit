package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

/* compiled from: BackStackRecord */
final class BackStackState implements Parcelable {
    public static final Creator<BackStackState> CREATOR = new Creator<BackStackState>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public BackStackState a(Parcel parcel) {
            return new BackStackState(parcel);
        }

        public BackStackState[] a(int i) {
            return new BackStackState[i];
        }
    };
    final int[] a;
    final int b;
    final int c;
    final String d;
    final int e;
    final int f;
    final CharSequence g;
    final int h;
    final CharSequence i;
    final ArrayList<String> j;
    final ArrayList<String> k;

    public BackStackState(e eVar) {
        int i = 0;
        for (a aVar = eVar.c; aVar != null; aVar = aVar.a) {
            if (aVar.i != null) {
                i += aVar.i.size();
            }
        }
        this.a = new int[(i + (eVar.e * 7))];
        if (eVar.l) {
            i = 0;
            for (a aVar2 = eVar.c; aVar2 != null; aVar2 = aVar2.a) {
                int i2 = i + 1;
                this.a[i] = aVar2.c;
                int i3 = i2 + 1;
                this.a[i2] = aVar2.d != null ? aVar2.d.mIndex : -1;
                int i4 = i3 + 1;
                this.a[i3] = aVar2.e;
                i2 = i4 + 1;
                this.a[i4] = aVar2.f;
                i4 = i2 + 1;
                this.a[i2] = aVar2.g;
                i2 = i4 + 1;
                this.a[i4] = aVar2.h;
                if (aVar2.i != null) {
                    int size = aVar2.i.size();
                    i4 = i2 + 1;
                    this.a[i2] = size;
                    i2 = 0;
                    while (i2 < size) {
                        i3 = i4 + 1;
                        this.a[i4] = ((Fragment) aVar2.i.get(i2)).mIndex;
                        i2++;
                        i4 = i3;
                    }
                    i = i4;
                } else {
                    i = i2 + 1;
                    this.a[i2] = 0;
                }
            }
            this.b = eVar.j;
            this.c = eVar.k;
            this.d = eVar.n;
            this.e = eVar.p;
            this.f = eVar.q;
            this.g = eVar.r;
            this.h = eVar.s;
            this.i = eVar.t;
            this.j = eVar.u;
            this.k = eVar.v;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public BackStackState(Parcel parcel) {
        this.a = parcel.createIntArray();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.h = parcel.readInt();
        this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.j = parcel.createStringArrayList();
        this.k = parcel.createStringArrayList();
    }

    public e a(l lVar) {
        e eVar = new e(lVar);
        int i = 0;
        int i2 = 0;
        while (i2 < this.a.length) {
            a aVar = new a();
            int i3 = i2 + 1;
            aVar.c = this.a[i2];
            if (l.a) {
                Log.v("FragmentManager", "Instantiate " + eVar + " op #" + i + " base fragment #" + this.a[i3]);
            }
            int i4 = i3 + 1;
            i2 = this.a[i3];
            if (i2 >= 0) {
                aVar.d = (Fragment) lVar.f.get(i2);
            } else {
                aVar.d = null;
            }
            i3 = i4 + 1;
            aVar.e = this.a[i4];
            i4 = i3 + 1;
            aVar.f = this.a[i3];
            i3 = i4 + 1;
            aVar.g = this.a[i4];
            int i5 = i3 + 1;
            aVar.h = this.a[i3];
            i4 = i5 + 1;
            int i6 = this.a[i5];
            if (i6 > 0) {
                aVar.i = new ArrayList(i6);
                i3 = 0;
                while (i3 < i6) {
                    if (l.a) {
                        Log.v("FragmentManager", "Instantiate " + eVar + " set remove fragment #" + this.a[i4]);
                    }
                    i5 = i4 + 1;
                    aVar.i.add((Fragment) lVar.f.get(this.a[i4]));
                    i3++;
                    i4 = i5;
                }
            }
            eVar.a(aVar);
            i++;
            i2 = i4;
        }
        eVar.j = this.b;
        eVar.k = this.c;
        eVar.n = this.d;
        eVar.p = this.e;
        eVar.l = true;
        eVar.q = this.f;
        eVar.r = this.g;
        eVar.s = this.h;
        eVar.t = this.i;
        eVar.u = this.j;
        eVar.v = this.k;
        eVar.a(1);
        return eVar;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        TextUtils.writeToParcel(this.g, parcel, 0);
        parcel.writeInt(this.h);
        TextUtils.writeToParcel(this.i, parcel, 0);
        parcel.writeStringList(this.j);
        parcel.writeStringList(this.k);
    }
}
