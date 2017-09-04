package android.support.v4.media.session;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.util.List;

public final class PlaybackStateCompat implements Parcelable {
    public static final Creator<PlaybackStateCompat> CREATOR = new Creator<PlaybackStateCompat>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public PlaybackStateCompat a(Parcel parcel) {
            return new PlaybackStateCompat(parcel);
        }

        public PlaybackStateCompat[] a(int i) {
            return new PlaybackStateCompat[i];
        }
    };
    private final int a;
    private final long b;
    private final long c;
    private final float d;
    private final long e;
    private final CharSequence f;
    private final long g;
    private List<CustomAction> h;
    private final long i;
    private final Bundle j;

    public static final class CustomAction implements Parcelable {
        public static final Creator<CustomAction> CREATOR = new Creator<CustomAction>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public CustomAction a(Parcel parcel) {
                return new CustomAction(parcel);
            }

            public CustomAction[] a(int i) {
                return new CustomAction[i];
            }
        };
        private final String a;
        private final CharSequence b;
        private final int c;
        private final Bundle d;

        private CustomAction(Parcel parcel) {
            this.a = parcel.readString();
            this.b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.c = parcel.readInt();
            this.d = parcel.readBundle();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
            TextUtils.writeToParcel(this.b, parcel, i);
            parcel.writeInt(this.c);
            parcel.writeBundle(this.d);
        }

        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "Action:mName='" + this.b + ", mIcon=" + this.c + ", mExtras=" + this.d;
        }
    }

    private PlaybackStateCompat(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readLong();
        this.d = parcel.readFloat();
        this.g = parcel.readLong();
        this.c = parcel.readLong();
        this.e = parcel.readLong();
        this.f = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.h = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.i = parcel.readLong();
        this.j = parcel.readBundle();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PlaybackState {");
        stringBuilder.append("state=").append(this.a);
        stringBuilder.append(", position=").append(this.b);
        stringBuilder.append(", buffered position=").append(this.c);
        stringBuilder.append(", speed=").append(this.d);
        stringBuilder.append(", updated=").append(this.g);
        stringBuilder.append(", actions=").append(this.e);
        stringBuilder.append(", error=").append(this.f);
        stringBuilder.append(", custom actions=").append(this.h);
        stringBuilder.append(", active item id=").append(this.i);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeLong(this.b);
        parcel.writeFloat(this.d);
        parcel.writeLong(this.g);
        parcel.writeLong(this.c);
        parcel.writeLong(this.e);
        TextUtils.writeToParcel(this.f, parcel, i);
        parcel.writeTypedList(this.h);
        parcel.writeLong(this.i);
        parcel.writeBundle(this.j);
    }
}
