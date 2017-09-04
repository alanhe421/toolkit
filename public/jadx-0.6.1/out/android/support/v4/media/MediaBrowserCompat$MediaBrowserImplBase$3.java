package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import android.support.v4.media.MediaBrowserCompat.a;
import android.support.v4.os.ResultReceiver;

class MediaBrowserCompat$MediaBrowserImplBase$3 extends ResultReceiver {
    final /* synthetic */ a a;
    final /* synthetic */ String b;

    protected void a(int i, Bundle bundle) {
        if (i == 0 && bundle != null && bundle.containsKey("media_item")) {
            Parcelable parcelable = bundle.getParcelable("media_item");
            if (parcelable instanceof MediaItem) {
                this.a.a((MediaItem) parcelable);
                return;
            } else {
                this.a.a(this.b);
                return;
            }
        }
        this.a.a(this.b);
    }
}
