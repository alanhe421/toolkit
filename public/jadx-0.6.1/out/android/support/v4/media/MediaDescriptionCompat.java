package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;

public final class MediaDescriptionCompat implements Parcelable {
    public static final Creator<MediaDescriptionCompat> CREATOR = new Creator<MediaDescriptionCompat>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public MediaDescriptionCompat a(Parcel parcel) {
            if (VERSION.SDK_INT < 21) {
                return new MediaDescriptionCompat(parcel);
            }
            return MediaDescriptionCompat.a(a.a(parcel));
        }

        public MediaDescriptionCompat[] a(int i) {
            return new MediaDescriptionCompat[i];
        }
    };
    private final String a;
    private final CharSequence b;
    private final CharSequence c;
    private final CharSequence d;
    private final Bitmap e;
    private final Uri f;
    private final Bundle g;
    private final Uri h;
    private Object i;

    public static final class a {
        private String a;
        private CharSequence b;
        private CharSequence c;
        private CharSequence d;
        private Bitmap e;
        private Uri f;
        private Bundle g;
        private Uri h;

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a a(CharSequence charSequence) {
            this.b = charSequence;
            return this;
        }

        public a b(CharSequence charSequence) {
            this.c = charSequence;
            return this;
        }

        public a c(CharSequence charSequence) {
            this.d = charSequence;
            return this;
        }

        public a a(Bitmap bitmap) {
            this.e = bitmap;
            return this;
        }

        public a a(Uri uri) {
            this.f = uri;
            return this;
        }

        public a a(Bundle bundle) {
            this.g = bundle;
            return this;
        }

        public a b(Uri uri) {
            this.h = uri;
            return this;
        }

        public MediaDescriptionCompat a() {
            return new MediaDescriptionCompat(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
        }
    }

    private MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.a = str;
        this.b = charSequence;
        this.c = charSequence2;
        this.d = charSequence3;
        this.e = bitmap;
        this.f = uri;
        this.g = bundle;
        this.h = uri2;
    }

    private MediaDescriptionCompat(Parcel parcel) {
        this.a = parcel.readString();
        this.b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.c = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.e = (Bitmap) parcel.readParcelable(null);
        this.f = (Uri) parcel.readParcelable(null);
        this.g = parcel.readBundle();
        this.h = (Uri) parcel.readParcelable(null);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (VERSION.SDK_INT < 21) {
            parcel.writeString(this.a);
            TextUtils.writeToParcel(this.b, parcel, i);
            TextUtils.writeToParcel(this.c, parcel, i);
            TextUtils.writeToParcel(this.d, parcel, i);
            parcel.writeParcelable(this.e, i);
            parcel.writeParcelable(this.f, i);
            parcel.writeBundle(this.g);
            return;
        }
        a.a(a(), parcel, i);
    }

    public String toString() {
        return this.b + ", " + this.c + ", " + this.d;
    }

    public Object a() {
        if (this.i != null || VERSION.SDK_INT < 21) {
            return this.i;
        }
        Object a = a.a();
        a.a(a, this.a);
        a.a(a, this.b);
        a.b(a, this.c);
        a.c(a, this.d);
        a.a(a, this.e);
        a.a(a, this.f);
        a.a(a, this.g);
        if (VERSION.SDK_INT >= 23) {
            a.b(a, this.h);
        }
        this.i = a.a(a);
        return this.i;
    }

    public static MediaDescriptionCompat a(Object obj) {
        if (obj == null || VERSION.SDK_INT < 21) {
            return null;
        }
        a aVar = new a();
        aVar.a(a.a(obj));
        aVar.a(a.b(obj));
        aVar.b(a.c(obj));
        aVar.c(a.d(obj));
        aVar.a(a.e(obj));
        aVar.a(a.f(obj));
        aVar.a(a.g(obj));
        if (VERSION.SDK_INT >= 23) {
            aVar.b(b.h(obj));
        }
        MediaDescriptionCompat a = aVar.a();
        a.i = obj;
        return a;
    }
}
