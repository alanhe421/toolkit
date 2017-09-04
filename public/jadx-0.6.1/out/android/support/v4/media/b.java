package android.support.v4.media;

import android.media.MediaDescription;
import android.media.MediaDescription.Builder;
import android.net.Uri;

/* compiled from: MediaDescriptionCompatApi23 */
class b extends a {

    /* compiled from: MediaDescriptionCompatApi23 */
    static class a extends a {
        public static void b(Object obj, Uri uri) {
            ((Builder) obj).setMediaUri(uri);
        }
    }

    public static Uri h(Object obj) {
        return ((MediaDescription) obj).getMediaUri();
    }
}
