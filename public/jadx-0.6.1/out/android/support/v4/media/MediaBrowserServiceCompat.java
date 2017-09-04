package android.support.v4.media;

import android.app.Service;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.util.a;

public abstract class MediaBrowserServiceCompat extends Service {
    private final a<IBinder, Object> a = new a();
    private final Handler b = new Handler();
}
