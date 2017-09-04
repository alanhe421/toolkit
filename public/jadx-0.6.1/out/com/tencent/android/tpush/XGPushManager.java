package com.tencent.android.tpush;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.b.b;
import com.tencent.android.tpush.b.i;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.XGPushService;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.d.f;
import com.tencent.open.SocialConstants;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.tencent.upload.log.trace.TracerConfig;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class XGPushManager {
    public static final String ENABLE_SERVICE_SUFFIX = ".enableService";
    public static final int OPERATION_FAIL = 1;
    public static final int OPERATION_REQ_REGISTER = 100;
    public static final int OPERATION_REQ_UNREGISTER = 101;
    public static final int OPERATION_SUCCESS = 0;
    private static final String a = XGPushManager.class.getSimpleName();
    private static Context b = null;
    private static XGPushNotifactionCallback c = null;
    private static Map d = new ConcurrentHashMap();
    public static int enableService = -1;

    private XGPushManager() {
    }

    public static Context getContext() {
        return b;
    }

    public static void setContext(Context context) {
        if (b == null && context != null) {
            b = context.getApplicationContext();
        }
    }

    public static void startPushService(Context context) {
        if (context != null) {
            setContext(context);
            if (XGPushConfig.enableDebug) {
                a.e(a, context.getPackageName() + "call start Push Service");
            }
            p.g(context);
            if (p.d(context) == 0) {
                p.e(context);
            }
        }
    }

    static void a(Context context) {
        if (context != null) {
            if (XGPushConfig.enableDebug) {
                a.e(a, context.getPackageName() + " call stop Push Service");
            }
            Intent intent = new Intent();
            intent.setClass(context.getApplicationContext(), XGPushService.class);
            context.stopService(intent);
        }
    }

    public static int getServiceStatus(Context context) {
        if (context != null) {
            return p.d(context);
        }
        return 0;
    }

    public static void registerPush(Context context) {
        registerPush(context, new s());
    }

    public static void registerPush(Context context, XGIOperateCallback xGIOperateCallback) {
        if (xGIOperateCallback == null) {
            throw new IllegalArgumentException("The callback parameter can not be null!");
        }
        a(context, null, null, -1, null, xGIOperateCallback, -1, null);
    }

    public static void registerPush(Context context, String str, XGIOperateCallback xGIOperateCallback) {
        if (xGIOperateCallback == null) {
            throw new IllegalArgumentException("The callback parameter can not be null!");
        }
        registerPush(context, str, "0", 0, null, xGIOperateCallback);
    }

    public static void registerPush(Context context, String str) {
        if (context == null || str == null) {
            a.i(Constants.LogTag, "the parameter context or account of registerPush is invalid.");
        } else {
            registerPush(context, str, new t());
        }
    }

    public static void registerPush(Context context, String str, String str2, int i, String str3, XGIOperateCallback xGIOperateCallback) {
        if (xGIOperateCallback == null) {
            throw new IllegalArgumentException("The callback parameter can not be null!");
        } else if (context == null || p.b(str) || p.b(str2) || i < 0) {
            xGIOperateCallback.onFail(null, 10001, "The context, account, ticket or ticketType is(are) invalid!");
        } else {
            a(context, str, str2, i, str3, xGIOperateCallback, -1, null);
        }
    }

    public static void unregisterPush(Context context) {
        if (context == null) {
            a.h(Constants.LogTag, "the context of unregisterPush is null");
        } else {
            unregisterPush(context, new u());
        }
    }

    public static void unregisterPush(Context context, XGIOperateCallback xGIOperateCallback) {
        a(context, xGIOperateCallback, XGPushConfig.getAccessId(context), XGPushConfig.getAccessKey(context));
    }

    static void a(Context context, XGIOperateCallback xGIOperateCallback, long j, String str) {
        if (context == null) {
            setContext(context);
            if (xGIOperateCallback != null) {
                xGIOperateCallback.onFail(null, 10001, "The context parameter can not be null!");
                return;
            }
            throw new IllegalArgumentException("The context parameter can not be null!");
        }
        g.a().a(new v(context.getApplicationContext(), xGIOperateCallback, j, str));
    }

    public static void setTag(Context context, String str) {
        if (XGPushConfig.enableDebug) {
            a.e(a, "Action -> setTag with tag = " + str);
        }
        a(context, str, 1, -1);
    }

    public static void setKeyValueTag(Context context, String str, String str2) {
        if (context == null || str == null || str.trim().length() == 0 || str2 == null || str2.trim().length() == 0) {
            a.i(a, "setKeyValueTag context or tagKey or tagValue invalid.");
            return;
        }
        String str3 = str + "::::" + str2;
        a.e(a, "Action -> setKeyValueTag with tag = " + str3);
        a(context, str3, 3, -1);
    }

    public static void deleteKeyValueTag(Context context, String str, String str2) {
        if (context == null || str == null || str.trim().length() == 0) {
            a.i(a, "deleteKeyValueTag context or tagKey invalid.");
            return;
        }
        String str3 = str + "::::" + str2;
        a.e(a, "Action -> deleteKeyValueTag with tag = " + str3);
        a(context, str3, 4, -1);
    }

    public static void deleteTag(Context context, String str) {
        if (XGPushConfig.enableDebug) {
            a.e(a, "Action -> deleteTag with tag = " + str);
        }
        a(context, str, 2, -1);
    }

    static void a(Context context, String str, int i, long j) {
        if (context == null) {
            throw new IllegalArgumentException("The context parameter can not be null!");
        } else if (p.a(context) <= 0) {
            if (str == null) {
                throw new IllegalArgumentException("The tagName parameter can not be null!");
            }
            if (j <= 0) {
                j = XGPushConfig.getAccessId(context);
            }
            if (j < 0) {
                throw new IllegalArgumentException("The accessId not set!");
            }
            Intent intent = new Intent("com.tencent.android.tpush.action.TAG");
            intent.putExtra("accId", j);
            intent.putExtra(Constants.FLAG_PACK_NAME, Rijndael.encrypt(context.getPackageName()));
            intent.putExtra(Constants.FLAG_TAG_TYPE, i);
            intent.putExtra(Constants.FLAG_TAG_NAME, Rijndael.encrypt(str));
            context.sendBroadcast(intent);
        }
    }

    static XGPushClickedResult a(Activity activity) {
        if (XGPushConfig.enableDebug) {
            a.e(Constants.LogTag, ">>> onActivityStarted activity=" + activity);
        }
        if (activity == null || activity.getIntent() == null) {
            return null;
        }
        Intent intent = activity.getIntent();
        String stringExtra = intent.getStringExtra(Constants.TAG_TPUSH_MESSAGE);
        if (stringExtra == null || !stringExtra.equalsIgnoreCase("true") || p.a(activity.getApplicationContext()) > 0) {
            return null;
        }
        XGPushClickedResult xGPushClickedResult = new XGPushClickedResult();
        xGPushClickedResult.parseIntent(intent);
        intent.removeExtra(Constants.TAG_TPUSH_MESSAGE);
        g.a().a(new w(activity, intent));
        return xGPushClickedResult;
    }

    public static XGPushClickedResult onActivityStarted(Activity activity) {
        if (XGPushConfig.enableDebug) {
            a.e(Constants.PushMessageLogTag, ">>> onActivityStarted " + activity);
        }
        if (activity == null || activity.getIntent() == null || !p.h(activity)) {
            return null;
        }
        Intent intent = activity.getIntent();
        if (intent != null) {
            try {
                if (intent.hasExtra(Constants.TAG_TPUSH_NOTIFICATION)) {
                    Serializable serializableExtra = intent.getSerializableExtra(Constants.TAG_TPUSH_NOTIFICATION);
                    intent.removeExtra(Constants.TAG_TPUSH_NOTIFICATION);
                    if (serializableExtra != null && (serializableExtra instanceof XGPushClickedResult)) {
                        XGPushClickedResult xGPushClickedResult = (XGPushClickedResult) serializableExtra;
                        xGPushClickedResult.parseIntent(intent);
                        return xGPushClickedResult;
                    }
                }
            } catch (Throwable e) {
                a.c(a, "onActivityStarted", e);
            }
        }
        return null;
    }

    public static long addLocalNotification(Context context, XGLocalMessage xGLocalMessage) {
        return a(context, xGLocalMessage, -1);
    }

    static long a(Context context, XGLocalMessage xGLocalMessage, long j) {
        if (context == null || xGLocalMessage == null) {
            try {
                a.h(a, "addLocalNotification context == null or msg == null");
                return -1;
            } catch (Throwable th) {
                a.c(Constants.LogTag, "addLocalNotification ", th);
                return 0;
            }
        } else if (!TpnsSecurity.checkTpnsSecurityLibSo(context)) {
            return -1;
        } else {
            Intent intent = new Intent("com.tencent.android.tpush.action.CUSTOM_NOTIFICATION");
            intent.putExtra("appPkgName", context.getPackageName());
            if (j <= 0) {
                j = XGPushConfig.getAccessId(context);
            }
            intent.putExtra("accessId", j);
            intent.putExtra("type", xGLocalMessage.getType());
            intent.putExtra("title", xGLocalMessage.getTitle());
            intent.putExtra(MessageKey.MSG_CONTENT, a(xGLocalMessage.getContent()));
            intent.putExtra("custom_content", xGLocalMessage.getCustom_content());
            intent.putExtra(MessageKey.MSG_DATE, xGLocalMessage.getDate());
            intent.putExtra(MessageKey.MSG_ACCEPT_TIME_HOUR, xGLocalMessage.getHour());
            intent.putExtra(MessageKey.MSG_ACCEPT_TIME_MIN, xGLocalMessage.getMin());
            intent.putExtra("builderId", xGLocalMessage.getBuilderId());
            intent.putExtra(MessageKey.MSG_RING, xGLocalMessage.getRing());
            intent.putExtra(MessageKey.MSG_VIBRATE, xGLocalMessage.getVibrate());
            intent.putExtra(MessageKey.MSG_LIGHTS, xGLocalMessage.getLights());
            intent.putExtra(MessageKey.MSG_RING_RAW, xGLocalMessage.getRing_raw());
            intent.putExtra(MessageKey.MSG_ICON_TYPE, xGLocalMessage.getIcon_type());
            intent.putExtra(MessageKey.MSG_ICON_RES, xGLocalMessage.getIcon_res());
            intent.putExtra(MessageKey.MSG_STYLE_ID, xGLocalMessage.getStyle_id());
            intent.putExtra(MessageKey.MSG_SMALL_ICON, xGLocalMessage.getSmall_icon());
            intent.putExtra(Constants.FLAG_ACTION_TYPE, xGLocalMessage.getAction_type());
            intent.putExtra(Constants.FLAG_ACTIVITY_NAME, xGLocalMessage.getActivity());
            intent.putExtra(SocialConstants.PARAM_URL, xGLocalMessage.getUrl());
            intent.putExtra("intent", xGLocalMessage.getIntent());
            intent.putExtra(Constants.FLAG_PACKAGE_DOWNLOAD_URL, xGLocalMessage.getPackageDownloadUrl());
            intent.putExtra(Constants.FLAG_PACKAGE_NAME, xGLocalMessage.getPackageName());
            long currentTimeMillis = System.currentTimeMillis();
            intent.putExtra(MessageKey.MSG_ID, -currentTimeMillis);
            intent.putExtra("timeUs", currentTimeMillis);
            intent.putExtra(MessageKey.MSG_NOTIFY_ID, xGLocalMessage.getNotificationId());
            intent.putExtra("ttl", xGLocalMessage.getTtl());
            context.sendBroadcast(intent);
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append(j).append(-currentTimeMillis).append(context.getPackageName()).append(TextUtils.isEmpty(xGLocalMessage.getTitle()) ? "" : xGLocalMessage.getTitle()).append(TextUtils.isEmpty(xGLocalMessage.getContent()) ? "" : xGLocalMessage.getContent());
            String custom_content = xGLocalMessage.getCustom_content();
            if (TextUtils.isEmpty(custom_content) || new JSONObject(custom_content).length() == 0) {
                custom_content = "";
            }
            stringBuilder.append(custom_content);
            if (xGLocalMessage.getType() == 1) {
                StringBuilder append = stringBuilder.append(TextUtils.isEmpty(xGLocalMessage.getUrl()) ? "" : xGLocalMessage.getUrl());
                if (TextUtils.isEmpty(xGLocalMessage.getIntent())) {
                    custom_content = "";
                } else {
                    custom_content = xGLocalMessage.getIntent();
                }
                append = append.append(custom_content);
                if (TextUtils.isEmpty(xGLocalMessage.getActivity())) {
                    custom_content = "";
                } else {
                    custom_content = xGLocalMessage.getActivity();
                }
                append.append(custom_content);
            }
            custom_content = stringBuilder.toString();
            String str = Constants.LOCAL_MESSAGE_FLAG + com.tencent.android.tpush.encrypt.a.a(custom_content);
            long expirationTimeMs = xGLocalMessage.getExpirationTimeMs();
            m.b(context, str, expirationTimeMs);
            a.d(Constants.LogTag, custom_content + ",tag:" + str + ",exp:" + expirationTimeMs);
            return -currentTimeMillis;
        }
    }

    public static XGPushNotifactionCallback getNotifactionCallback() {
        return c;
    }

    public static void setNotifactionCallback(XGPushNotifactionCallback xGPushNotifactionCallback) {
        c = xGPushNotifactionCallback;
    }

    public static void clearLocalNotifications(Context context) {
        if (context == null) {
            a.h(a, "clearLocalNotifications  context==null.");
        } else if (p.a(context) <= 0) {
            com.tencent.android.tpush.service.b.a.a().b(context, context.getPackageName());
            Intent intent = new Intent("com.tencent.android.tpush.action.CLEAR_CUSTOM_NOTIFICATION");
            intent.putExtra("appPkgName", context.getPackageName());
            context.sendBroadcast(intent);
        }
    }

    private static void c(Context context, Intent intent) {
        if (intent.getLongExtra(MessageKey.MSG_ID, -1) > 0) {
            Intent intent2 = new Intent("com.tencent.android.tpush.action.PUSH_CLICK.RESULT");
            intent2.putExtras(intent);
            intent2.putExtra(Constants.FLAG_PACK_NAME, context.getPackageName());
            intent2.putExtra(Constants.FLAG_CLICK_TIME, System.currentTimeMillis() / 1000);
            context.sendBroadcast(intent2);
        }
    }

    public static void onMessageClicked(Context context, XGPushTextMessage xGPushTextMessage) {
        a(context, xGPushTextMessage.a(), "com.tencent.android.tpush.action.PUSH_CLICK.RESULT");
    }

    public static void onMessageCleared(Context context, XGPushTextMessage xGPushTextMessage) {
        a(context, xGPushTextMessage.a(), "com.tencent.android.tpush.action.PUSH_CANCELLED.RESULT");
    }

    private static void a(Context context, Intent intent, String str) {
        if (context != null && intent != null && str != null) {
            Intent intent2 = new Intent(str);
            intent2.putExtras(intent);
            if ("com.tencent.android.tpush.action.PUSH_CANCELLED.RESULT".equals(str)) {
                intent2.putExtra("action", 2);
            }
            intent2.putExtra(Constants.FLAG_PACK_NAME, context.getPackageName());
            intent2.putExtra(Constants.FLAG_CLICK_TIME, System.currentTimeMillis() / 1000);
            context.sendBroadcast(intent2);
        }
    }

    private static void d(Context context, Intent intent) {
        if (intent != null) {
            Intent intent2 = new Intent(Constants.ACTION_FEEDBACK);
            intent2.setPackage(context.getPackageName());
            intent2.putExtra(Constants.FEEDBACK_TAG, 4);
            intent2.putExtra(Constants.FEEDBACK_ERROR_CODE, 0);
            intent2.putExtras(intent);
            context.sendBroadcast(intent2);
        }
    }

    public static void onActivityStoped(Activity activity) {
        if (activity != null) {
        }
    }

    public static void setPushNotificationBuilder(Context context, int i, XGPushNotificationBuilder xGPushNotificationBuilder) {
        if (context == null) {
            throw new IllegalArgumentException("context is null.");
        } else if (i < 1 || i > 4096) {
            throw new IllegalArgumentException("notificationBulderId不在范围[1, 4096].");
        } else if (xGPushNotificationBuilder != null) {
            b.a(context, i, xGPushNotificationBuilder);
        }
    }

    public static void setDefaultNotificationBuilder(Context context, XGPushNotificationBuilder xGPushNotificationBuilder) {
        if (context != null && xGPushNotificationBuilder != null) {
            b.a(context, 0, xGPushNotificationBuilder);
        }
    }

    public static XGPushNotificationBuilder getDefaultNotificationBuilder(Context context) {
        XGPushNotificationBuilder notificationBuilder = getNotificationBuilder(context, 0);
        if (notificationBuilder == null) {
            return b.a(context);
        }
        return notificationBuilder;
    }

    public static XGPushNotificationBuilder getNotificationBuilder(Context context, int i) {
        if (context != null) {
            return b.a(context, i);
        }
        Log.e(Constants.LogTag, "getNotificationBuilder  context == null");
        return null;
    }

    public static void cancelNotifaction(Context context, int i) {
        try {
            ((NotificationManager) context.getSystemService("notification")).cancel(i);
        } catch (Exception e) {
        }
    }

    public static void cancelAllNotifaction(Context context) {
        try {
            ((NotificationManager) context.getSystemService("notification")).cancelAll();
        } catch (Exception e) {
        }
    }

    public static void msgAck(Context context, i iVar) {
        if (context != null && iVar != null) {
            if (XGPushConfig.enableDebug) {
                a.a(a, "Action -> msgAck(" + context.getPackageName() + "," + iVar.b() + ")");
            }
            a.a(3, iVar.b());
            com.tencent.android.tpush.service.b.a.a().b(context, context.getPackageName(), iVar.b());
            if (iVar.b() > 0) {
                Intent intent = new Intent("com.tencent.android.tpush.action.MSG_ACK");
                intent.putExtra(MessageKey.MSG_ID, iVar.b());
                intent.putExtra(Constants.FLAG_PACK_NAME, context.getPackageName());
                context.sendBroadcast(intent);
            }
        }
    }

    public static String getServiceTag(Context context) {
        if (TpnsSecurity.checkTpnsSecurityLibSo(context)) {
            return Rijndael.encrypt(XGPushConfig.getAccessId(context) + "," + "xg_service_enable");
        }
        return "xg_service_enable";
    }

    public static void enableService(Context context, boolean z) {
        if (context != null) {
            if (!z) {
                a.h(Constants.LogTag, "XG is disable.");
                unregisterPush(context, new x(context));
            }
            enableService = z ? 1 : 0;
            if (XGPushConfig.enableDebug) {
                a.e(a, "enableService=" + enableService);
            }
            f.a(context, context.getPackageName() + ENABLE_SERVICE_SUFFIX, enableService);
        }
    }

    static void a(Context context, String str, String str2, int i, String str3, XGIOperateCallback xGIOperateCallback, long j, String str4) {
        setContext(context);
        if (context == null) {
            xGIOperateCallback.onFail(null, 10001, "The context parameter can not be null!");
            return;
        }
        g.a().a(new y(context, xGIOperateCallback, j, str4, str, str2, str3, i));
    }

    static void a(Context context, Intent intent, XGIOperateCallback xGIOperateCallback, boolean z) {
        p.e(context);
        BroadcastReceiver acVar = new ac(context, intent, xGIOperateCallback);
        context.registerReceiver(acVar, new IntentFilter("com.tencent.android.tpush.action.SERVICE_START"));
        Runnable adVar = new ad(context, intent, xGIOperateCallback);
        try {
            d.put(acVar, adVar);
            g.a().a(adVar, TracerConfig.LOG_FLUSH_DURATION);
        } catch (Throwable e) {
            a.c(Constants.LogTag, "mapTimeRunnableOfMessage error", e);
        }
    }

    private static synchronized void c(Context context, Intent intent, XGIOperateCallback xGIOperateCallback) {
        synchronized (XGPushManager.class) {
            String c = p.c(context);
            if (XGPushConfig.enableDebug) {
                a.e(a, "Action -> Register to xinge server, svcPackName:" + c);
            }
            if (!p.b(c)) {
                intent.setPackage(c);
            }
            if (xGIOperateCallback != null) {
                context.registerReceiver(new z(xGIOperateCallback), new IntentFilter("com.tencent.android.tpush.action.REGISTER.RESULT"));
            }
            context.sendBroadcast(intent);
        }
    }

    private static void d(Context context, Intent intent, XGIOperateCallback xGIOperateCallback) {
        if (XGPushConfig.enableDebug) {
            a.e(a, "Action ->UnRegister to xinge server");
        }
        String c = p.c(context);
        if (!p.b(c)) {
            intent.setPackage(c);
        }
        if (xGIOperateCallback != null) {
            context.registerReceiver(new aa(xGIOperateCallback), new IntentFilter("com.tencent.android.tpush.action.UNREGISTER.RESULT"));
        }
        context.sendBroadcast(intent);
    }

    private static String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\b':
                    stringBuilder.append("\\b");
                    break;
                case '\t':
                    stringBuilder.append("\\t");
                    break;
                case '\n':
                    stringBuilder.append("\\n");
                    break;
                case '\f':
                    stringBuilder.append("\\f");
                    break;
                case '\r':
                    stringBuilder.append("\\r");
                    break;
                case '\"':
                case '/':
                case Opcodes.IPUT_BOOLEAN /*92*/:
                    stringBuilder.append('\\').append(charAt);
                    break;
                default:
                    if (charAt > '\u001f') {
                        stringBuilder.append(charAt);
                        break;
                    }
                    stringBuilder.append(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                    break;
            }
        }
        return stringBuilder.toString();
    }
}
