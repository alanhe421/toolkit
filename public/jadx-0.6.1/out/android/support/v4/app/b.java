package android.support.v4.app;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.media.session.MediaController;
import android.os.Parcelable;
import android.view.View;
import java.util.List;
import java.util.Map;

/* compiled from: ActivityCompat21 */
class b {

    /* compiled from: ActivityCompat21 */
    public static abstract class a {
        public abstract Parcelable a(View view, Matrix matrix, RectF rectF);

        public abstract View a(Context context, Parcelable parcelable);

        public abstract void a(List<View> list);

        public abstract void a(List<String> list, List<View> list2, List<View> list3);

        public abstract void a(List<String> list, Map<String, View> map);

        public abstract void b(List<String> list, List<View> list2, List<View> list3);
    }

    /* compiled from: ActivityCompat21 */
    private static class b extends SharedElementCallback {
        private a a;

        public b(a aVar) {
            this.a = aVar;
        }

        public void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
            this.a.a((List) list, (List) list2, (List) list3);
        }

        public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
            this.a.b(list, list2, list3);
        }

        public void onRejectSharedElements(List<View> list) {
            this.a.a(list);
        }

        public void onMapSharedElements(List<String> list, Map<String, View> map) {
            this.a.a((List) list, (Map) map);
        }

        public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectF) {
            return this.a.a(view, matrix, rectF);
        }

        public View onCreateSnapshotView(Context context, Parcelable parcelable) {
            return this.a.a(context, parcelable);
        }
    }

    public static void a(Activity activity, Object obj) {
        activity.setMediaController((MediaController) obj);
    }

    public static void a(Activity activity) {
        activity.finishAfterTransition();
    }

    public static void a(Activity activity, a aVar) {
        activity.setEnterSharedElementCallback(a(aVar));
    }

    public static void b(Activity activity, a aVar) {
        activity.setExitSharedElementCallback(a(aVar));
    }

    public static void b(Activity activity) {
        activity.postponeEnterTransition();
    }

    public static void c(Activity activity) {
        activity.startPostponedEnterTransition();
    }

    private static SharedElementCallback a(a aVar) {
        if (aVar != null) {
            return new b(aVar);
        }
        return null;
    }
}
