package android.support.v4.media.session;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import java.util.List;

public class MediaButtonReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent("android.intent.action.MEDIA_BUTTON");
        intent2.setPackage(context.getPackageName());
        List queryIntentServices = context.getPackageManager().queryIntentServices(intent2, 0);
        if (queryIntentServices.size() != 1) {
            throw new IllegalStateException("Expected 1 Service that handles android.intent.action.MEDIA_BUTTON, found " + queryIntentServices.size());
        }
        ResolveInfo resolveInfo = (ResolveInfo) queryIntentServices.get(0);
        intent.setComponent(new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name));
        context.startService(intent);
    }
}
