package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class MediaBrowserCompat {

    public static class MediaItem implements Parcelable {
        public static final Creator<MediaItem> CREATOR = new Creator<MediaItem>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public MediaItem a(Parcel parcel) {
                return new MediaItem(parcel);
            }

            public MediaItem[] a(int i) {
                return new MediaItem[i];
            }
        };
        private final int a;
        private final MediaDescriptionCompat b;

        private MediaItem(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            this.b.writeToParcel(parcel, i);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("MediaItem{");
            stringBuilder.append("mFlags=").append(this.a);
            stringBuilder.append(", mDescription=").append(this.b);
            stringBuilder.append('}');
            return stringBuilder.toString();
        }
    }

    public static abstract class a {
        public void a(MediaItem mediaItem) {
        }

        public void a(String str) {
        }
    }

    static class b {
    }
}
