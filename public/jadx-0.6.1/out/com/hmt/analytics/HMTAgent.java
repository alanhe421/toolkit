package com.hmt.analytics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.hmt.analytics.common.AssembJSONObj;
import com.hmt.analytics.common.CommonUtil;
import com.hmt.analytics.common.HMTActivityLifecycleCallbacks;
import com.hmt.analytics.common.HMTConstants;
import com.hmt.analytics.common.HMTTool;
import com.hmt.analytics.common.MD5Utility;
import com.hmt.analytics.common.MyCrashHandler;
import com.hmt.analytics.common.NetworkUitlity;
import com.hmt.analytics.dao.GetInfoFromFile;
import com.hmt.analytics.dao.SaveInfoExec;
import com.hmt.analytics.interfaces.HMTCallback;
import com.hmt.analytics.interfaces.HMTNetWorkCallback;
import com.hmt.analytics.objects.ActionController;
import com.hmt.analytics.objects.MyMessage;
import com.hmt.analytics.objects.ParamList;
import com.hmt.analytics.objects.PostObjAction;
import com.hmt.analytics.objects.PostObjActivity;
import com.hmt.analytics.util.HParams;
import com.hmt.analytics.util.HmtUtils;
import com.hmt.analytics.util.SPUtils;
import com.hmt.analytics.util.ThreadPoolUtils;
import com.tencent.android.tpush.common.Constants;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HMTAgent {
    static Context a;
    public static boolean b = false;
    private static List<PostObjActivity> c = new ArrayList();
    private static String d = null;
    private static final String e = HMTAgent.class.getSimpleName();
    private static String f = "";
    private static HMTAgent g = new HMTAgent();
    private static Handler h;
    private static boolean i = true;
    private static AtomicBoolean j = new AtomicBoolean(true);
    private static boolean k = false;

    class AnonymousClass10 implements Runnable {
        private final /* synthetic */ Context a;
        private final /* synthetic */ HMTCallback b;

        AnonymousClass10(Context context, HMTCallback hMTCallback) {
            this.a = context;
            this.b = hMTCallback;
        }

        public void run() {
            HMTAgent.sendAdvActionData(this.a, this.b);
        }
    }

    class AnonymousClass11 implements Runnable {
        private final /* synthetic */ Context a;
        private final /* synthetic */ HMTCallback b;

        AnonymousClass11(Context context, HMTCallback hMTCallback) {
            this.a = context;
            this.b = hMTCallback;
        }

        public void run() {
            HMTAgent.sendLaunchActivityData(this.a, this.b);
        }
    }

    class AnonymousClass12 implements Runnable {
        private final /* synthetic */ Context a;
        private final /* synthetic */ String[] b;

        AnonymousClass12(Context context, String[] strArr) {
            this.a = context;
            this.b = strArr;
        }

        public void run() {
            CommonUtil.setUnTracked(this.a, this.b, "client");
        }
    }

    class AnonymousClass13 implements Runnable {
        private final /* synthetic */ Context a;

        AnonymousClass13(Context context) {
            this.a = context;
        }

        public void run() {
            HMTAgent.postIRSUIDInfo(this.a);
        }
    }

    class AnonymousClass1 implements Runnable {
        private final /* synthetic */ Context a;

        AnonymousClass1(Context context) {
            this.a = context;
        }

        public void run() {
            MyCrashHandler myCrashHandler = new MyCrashHandler(this.a.getApplicationContext());
        }
    }

    class AnonymousClass2 implements Runnable {
        private final /* synthetic */ Context a;
        private final /* synthetic */ String b;
        private final /* synthetic */ HParams c;
        private final /* synthetic */ HMTCallback d;

        AnonymousClass2(Context context, String str, HParams hParams, HMTCallback hMTCallback) {
            this.a = context;
            this.b = str;
            this.c = hParams;
            this.d = hMTCallback;
        }

        public void run() {
            HMTAgent.postErrorInfo(this.a, this.b, this.c, this.d);
        }
    }

    class AnonymousClass3 implements Runnable {
        private final /* synthetic */ Context a;
        private final /* synthetic */ String b;
        private final /* synthetic */ int c;
        private final /* synthetic */ HParams d;
        private final /* synthetic */ HMTCallback e;

        AnonymousClass3(Context context, String str, int i, HParams hParams, HMTCallback hMTCallback) {
            this.a = context;
            this.b = str;
            this.c = i;
            this.d = hParams;
            this.e = hMTCallback;
        }

        public void run() {
            HMTAgent.onActionInfo(this.a, this.b, this.c, this.d, this.e);
        }
    }

    class AnonymousClass4 implements Runnable {
        private final /* synthetic */ Context a;
        private final /* synthetic */ HParams b;
        private final /* synthetic */ int c;
        private final /* synthetic */ HMTCallback d;

        AnonymousClass4(Context context, HParams hParams, int i, HMTCallback hMTCallback) {
            this.a = context;
            this.b = hParams;
            this.c = i;
            this.d = hMTCallback;
        }

        public void run() {
            HMTAgent.postOnPauseInfo(this.a, this.b, this.c, this.d);
        }
    }

    class AnonymousClass5 implements Runnable {
        private final /* synthetic */ Context a;
        private final /* synthetic */ int b;

        AnonymousClass5(Context context, int i) {
            this.a = context;
            this.b = i;
        }

        public void run() {
            HMTAgent.postonResume(this.a, this.b);
        }
    }

    class AnonymousClass6 implements Runnable {
        private final /* synthetic */ Context a;

        AnonymousClass6(Context context) {
            this.a = context;
        }

        public void run() {
            HMTAgent.updateOnlineConfigs(this.a);
        }
    }

    class AnonymousClass7 implements Runnable {
        private final /* synthetic */ Context a;
        private final /* synthetic */ HMTCallback b;

        AnonymousClass7(Context context, HMTCallback hMTCallback) {
            this.a = context;
            this.b = hMTCallback;
        }

        public void run() {
            HMTAgent.postManualClientDatas(this.a, this.b, 1);
        }
    }

    class AnonymousClass8 implements Runnable {
        private final /* synthetic */ String a;
        private final /* synthetic */ String b;
        private final /* synthetic */ HMTCallback c;

        AnonymousClass8(String str, String str2, HMTCallback hMTCallback) {
            this.a = str;
            this.b = str2;
            this.c = hMTCallback;
        }

        public void run() {
            HMTAgent.postReqInfo(HMTAgent.a, this.a, this.b, this.c);
        }
    }

    class AnonymousClass9 implements Runnable {
        private final /* synthetic */ Context a;
        private final /* synthetic */ int b;
        private final /* synthetic */ String[] c;

        AnonymousClass9(Context context, int i, String[] strArr) {
            this.a = context;
            this.b = i;
            this.c = strArr;
        }

        public void run() {
            HMTAgent.InitializeRunnableMethod(this.a, this.b, this.c);
        }
    }

    public static void setBaseURL(String str) {
        HMTConstants.f = new StringBuilder(String.valueOf(str)).append("/hmt?_t=i&_z=m").toString();
        HMTConstants.g = new StringBuilder(String.valueOf(str)).append("/imt?_t=i&_z=m").toString();
        HMTConstants.h = new StringBuilder(String.valueOf(str)).append("/hmt_pro/project/").toString();
    }

    public static void setSessionContinueMillis(long j) {
        if (j > 0) {
            HMTConstants.a = j;
        }
    }

    public static void setAutoLocation(boolean z) {
        HMTConstants.v = z;
    }

    private HMTAgent() {
        HandlerThread handlerThread = new HandlerThread("HMTAgent");
        handlerThread.start();
        h = new Handler(handlerThread.getLooper());
    }

    public static HMTAgent getHMTAgent() {
        return g;
    }

    public static Handler getHandler() {
        return h;
    }

    public static String bindMuid(Context context, String str) {
        SPUtils.put(context, "hmt_agent_online_setting", "muid", str);
        return (String) SPUtils.get(context, "hmt_agent_online_setting", "muid", "");
    }

    public static void onError(Context context) {
        h.post(new Thread(new AnonymousClass1(context)));
    }

    public static void onError(Context context, String str) {
        onError(context, str, null, null);
    }

    public static void onError(Context context, String str, HMTCallback hMTCallback) {
        onError(context, str, null, hMTCallback);
    }

    public static void onError(Context context, String str, HParams hParams) {
        onError(context, str, hParams, null);
    }

    public static void onError(Context context, String str, HParams hParams, HMTCallback hMTCallback) {
        h.post(new AnonymousClass2(context, str, hParams, hMTCallback));
    }

    private static void postErrorInfo(Context context, String str, HParams hParams, HMTCallback hMTCallback) {
        HMTTool.sendData(context, HMTTool.addProperty(AssembJSONObj.getErrorListJSONObj(str, context), hParams), HMTConstants.f, "error_list", HMTConstants.n, "error", hMTCallback);
    }

    public static void saveInfoToFileSynchro(String str, JSONObject jSONObject, Context context, String str2) {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(0, jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(str, jSONArray);
            SaveInfoExec.saveExec(context, jSONObject2, str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void onAction(Context context, String str) {
        onAction(context, str, 1, null, null);
    }

    public static void onAction(Context context, String str, HParams hParams) {
        onAction(context, str, 1, hParams, null);
    }

    public static void onAction(Context context, String str, HMTCallback hMTCallback) {
        onAction(context, str, 1, null, hMTCallback);
    }

    public static void onAction(Context context, String str, int i) {
        onAction(context, str, i, null, null);
    }

    public static void onAction(Context context, String str, int i, HParams hParams) {
        onAction(context, str, i, hParams, null);
    }

    public static void onAction(Context context, String str, int i, HMTCallback hMTCallback) {
        onAction(context, str, i, null, hMTCallback);
    }

    public static void onAction(Context context, String str, HParams hParams, HMTCallback hMTCallback) {
        onAction(context, str, 1, hParams, hMTCallback);
    }

    public static void onAction(Context context, String str, int i, HParams hParams, HMTCallback hMTCallback) {
        h.post(new AnonymousClass3(context, str, i, hParams, hMTCallback));
    }

    public static void onActionInfo(Context context, String str, int i, HParams hParams, HMTCallback hMTCallback) {
        if (h == null) {
            HandlerThread handlerThread = new HandlerThread("HMTAgent");
            handlerThread.start();
            h = new Handler(handlerThread.getLooper());
        }
        ActionController.postActionInfo(h, context, new PostObjAction(str, new StringBuilder(String.valueOf(i)).toString(), context), hParams, hMTCallback);
    }

    public static void onPauseCallbacks(Context context) {
        if (VERSION.SDK_INT >= 14) {
            onPauseExecute(context, null, 0, null);
        }
    }

    public static void onPauseAgent(Context context) {
        if (VERSION.SDK_INT < 14) {
            onPauseExecute(context, null, 0, null);
        }
    }

    public static void onPause(Context context) {
        onPause(context, null, null);
    }

    public static void onPause(Context context, HMTCallback hMTCallback) {
        onPause(context, null, hMTCallback);
    }

    public static void onPause(Context context, HParams hParams) {
        onPause(context, hParams, null);
    }

    public static void onPause(Context context, HParams hParams, HMTCallback hMTCallback) {
        onPauseExecute(context, hParams, 1, hMTCallback);
    }

    private static void onPauseExecute(Context context, HParams hParams, int i, HMTCallback hMTCallback) {
        h.post(new AnonymousClass4(context, hParams, i, hMTCallback));
    }

    private static void postOnPauseInfo(Context context, HParams hParams, int i, HMTCallback hMTCallback) {
        saveSessionTime(context);
        synchronized (HMTConstants.e) {
            int activityListIndex = getActivityListIndex(context, i, false);
            if (activityListIndex == -1) {
                return;
            }
            JSONObject jSONObject = ((PostObjActivity) c.get(activityListIndex)).getJSONObject(context, CommonUtil.getTime());
            c.remove(activityListIndex);
            HMTTool.sendData(context, HMTTool.addProperty(jSONObject, hParams), HMTConstants.f, "activity_list", HMTConstants.n, Constants.FLAG_ACTIVITY_NAME, hMTCallback);
        }
    }

    public static void onResumeCallbacks(Context context) {
        if (VERSION.SDK_INT >= 14) {
            onResumeExecute(context, 0);
        }
    }

    public static void onResumeAgent(Context context) {
        if (VERSION.SDK_INT < 14) {
            onResumeExecute(context, 0);
        }
    }

    private static void onResumeExecute(Context context, int i) {
        h.post(new AnonymousClass5(context, i));
    }

    public static void onResume(Context context) {
        onResumeExecute(context, 1);
    }

    private static void postonResume(Context context, int i) {
        isCreateNewSessionID(context);
        String activityName = CommonUtil.getActivityName(context, 1);
        try {
            if (d == null) {
                generateSeesion(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String time = CommonUtil.getTime();
        synchronized (HMTConstants.e) {
            int activityListIndex = getActivityListIndex(context, i, true);
            if (activityListIndex == -1) {
                c.add(new PostObjActivity(context, activityName, d, time, i, CommonUtil.get_mac(context), CommonUtil.get_imei(context), CommonUtil.getAndroidId(context)));
            } else {
                ((PostObjActivity) c.get(activityListIndex)).setInfo(activityName, d, time, i, CommonUtil.get_mac(context), CommonUtil.get_imei(context), CommonUtil.getAndroidId(context));
            }
        }
        if (HMTConstants.s) {
            InitializeRunnableMethodUnConfig(context, 0);
        }
    }

    public static void onFragmentTrigger(Context context) {
        if (HMTConstants.s) {
            InitializeRunnableMethodUnConfig(context, 0);
        }
    }

    private static int getActivityListIndex(Context context, int i, boolean z) {
        int size = c.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((PostObjActivity) c.get(i2)).contrast(context, i, z).booleanValue()) {
                return i2;
            }
        }
        return -1;
    }

    private static void isCreateNewSessionID(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - ((Long) SPUtils.get(context, "hmt_session_id_savetime", "session_save_time", Long.valueOf(currentTimeMillis))).longValue() > HMTConstants.a) {
            try {
                generateSeesion(context);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateOnlineConfig(Context context) {
        h.post(new AnonymousClass6(context));
    }

    private static void updateOnlineConfigs(Context context) {
        f = CommonUtil.getAppKey(context);
        if (f != null && !f.equals("")) {
            if (CommonUtil.isNetworkAvailable(context)) {
                MyMessage myMessage = NetworkUitlity.get(HMTConstants.h + f + ".config");
                try {
                    if (myMessage.isFlag()) {
                        JSONObject jSONObject = new JSONObject(myMessage.getMsg());
                        Iterator keys = jSONObject.keys();
                        while (keys.hasNext()) {
                            String str = (String) keys.next();
                            if (str.equals("deliveryType")) {
                                String string = jSONObject.getJSONObject("deliveryType").getString("code");
                                if (string == null || string.equals("null")) {
                                    CommonUtil.printLog("hmt====>", "code is null");
                                    string = "0";
                                }
                                CommonUtil.setReportPolicy(context, Integer.parseInt(string), "server");
                            }
                            if (str.equals("untracked")) {
                                JSONArray jSONArray = jSONObject.getJSONArray("untracked");
                                String[] strArr = new String[jSONArray.length()];
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    strArr[i] = jSONArray.getString(i);
                                }
                                CommonUtil.setUnTracked(context, strArr, "server");
                            }
                            if (str.equals("adactiontime")) {
                                str = jSONObject.getString("adactiontime");
                                if (HmtUtils.checkStrIsNum(str)) {
                                    SPUtils.put(context, "hmt_agent_online_setting", "hmt_adv_upload_gap_time", Long.valueOf(str));
                                }
                            }
                        }
                        return;
                    }
                    CommonUtil.printLog("error", myMessage.getMsg());
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            }
            CommonUtil.printLog("HMTAgent", " updateOnlineConfig network error");
        }
    }

    public static String getConfigParams(Context context, String str) {
        f = CommonUtil.getAppKey(context);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("_ua", f);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jSONObject2 = jSONObject.toString();
        if (CommonUtil.isNetworkAvailable(context)) {
            MyMessage post = NetworkUitlity.post(HMTConstants.h, jSONObject2);
            if (post.isFlag()) {
                try {
                    return new JSONObject(post.getMsg()).getString(str);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            } else {
                CommonUtil.printLog("error", "getConfigParams error");
            }
        } else {
            CommonUtil.printLog("NetworkError", "Network, not work");
        }
        return "";
    }

    private static String generateSeesion(Context context) throws ParseException {
        String str = "";
        String appKey = CommonUtil.getAppKey(context);
        if (appKey == null) {
            return str;
        }
        str = MD5Utility.md5Appkey(new StringBuilder(String.valueOf(appKey)).append(CommonUtil.getTime()).toString());
        SPUtils.put(context, "hmt_session_id", "session_id", str);
        saveSessionTime(context);
        d = str;
        return str;
    }

    @SuppressLint({"NewApi"})
    public static String getSessionId(Context context) {
        if (d == null || d.isEmpty()) {
            try {
                d = generateSeesion(context);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return d;
    }

    private static void saveSessionTime(Context context) {
        SPUtils.put(context, "hmt_session_id_savetime", "session_save_time", Long.valueOf(System.currentTimeMillis()));
    }

    public static void postClientData(Context context) {
        postClientData(context, null);
    }

    public static void postClientData(Context context, HMTCallback hMTCallback) {
        h.post(new AnonymousClass7(context, hMTCallback));
    }

    private static synchronized void postManualClientDatas(Context context, HMTCallback hMTCallback, int i) {
        synchronized (HMTAgent.class) {
            postClientDatas(context, hMTCallback);
        }
    }

    private static boolean postClientDatas(Context context, HMTCallback hMTCallback) {
        if (TextUtils.isEmpty(CommonUtil.getDeviceID(context))) {
            return false;
        }
        k = true;
        SPUtils.put(a, "hmt_init_savetime", "init_save_time", Long.valueOf(System.currentTimeMillis()));
        HMTTool.sendData(context, HMTTool.getClientDataJSONObject(context), HMTConstants.f, "client_data_list", HMTConstants.n, "client_data", hMTCallback);
        return true;
    }

    public static void onReq(String str, String str2, HMTCallback hMTCallback) {
        h.post(new AnonymousClass8(str, str2, hMTCallback));
    }

    public static void onReq(String str, String str2) {
        onReq(str, str2, null);
    }

    public static void onReqUrl(String str, String str2) {
        onReq(str, str2, null);
    }

    public static void onReqUrl(String str) {
        onReq(str, "", null);
    }

    public static void onReq(String str) {
        onReq(str, "", null);
    }

    public static void onReq(String str, HMTCallback hMTCallback) {
        onReq(str, "", hMTCallback);
    }

    private static void postReqInfo(Context context, String str, String str2, HMTCallback hMTCallback) {
        if (context != null && HMTTool.isTrackedurl(str)) {
            HMTTool.sendData(context, AssembJSONObj.getReqJOSNobj(str, str2, context), HMTConstants.g, "req_list", HMTConstants.o, "req", hMTCallback);
        } else if (hMTCallback != null) {
            hMTCallback.callback();
        }
    }

    public static void Initialize(Context context) {
        Initialize(context, Constants.ERRORCODE_UNKNOWN);
    }

    public static void Initialize(Context context, int i) {
        Initialize(context, i, null);
    }

    public static void Initialize(Context context, int i, String[] strArr) {
        if (j.compareAndSet(true, false)) {
            a = context.getApplicationContext();
            h.post(new AnonymousClass9(context, i, strArr));
            if (HMTConstants.s) {
                InitializeSetActivityCallback(context);
            }
        }
    }

    @SuppressLint({"NewApi"})
    private static void InitializeSetActivityCallback(Context context) {
        if (VERSION.SDK_INT >= 14 && HMTConstants.s) {
            if (context instanceof Activity) {
                ((Activity) context).getApplication().registerActivityLifecycleCallbacks(new HMTActivityLifecycleCallbacks());
            } else if (context instanceof Application) {
                ((Application) context).registerActivityLifecycleCallbacks(new HMTActivityLifecycleCallbacks());
            }
        }
    }

    private static void InitializeRunnableMethod(Context context, int i, String[] strArr) {
        CommonUtil.setUnTracked(context, strArr, "client");
        CommonUtil.setReportPolicy(context, i, "client");
        if (i) {
            i = false;
            HMTTool.setTrackedurl(context);
            if (CommonUtil.isNetworkAvailable(context)) {
                updateOnlineConfigs(context);
            }
            if (HMTConstants.s) {
                InitializeRunnableMethodUnConfig(context, 1);
            }
        }
    }

    public static synchronized void InitializeRunnableMethodUnConfig(Context context, int i) {
        synchronized (HMTAgent.class) {
            long currentTimeMillis = System.currentTimeMillis();
            long longValue = ((Long) SPUtils.get(a, "hmt_init_savetime", "init_save_time", Long.valueOf(currentTimeMillis))).longValue();
            long longValue2 = ((Long) SPUtils.get(a, "hmt_init_savetime", "upload_save_time", Long.valueOf(0))).longValue();
            Object obj = currentTimeMillis - longValue > HMTConstants.c ? 1 : null;
            boolean isToDay = HMTTool.isToDay(currentTimeMillis, longValue);
            if (obj != null || !k || !isToDay) {
                boolean postClientDatas = postClientDatas(context, null);
                if (i == 1) {
                    if (CommonUtil.isNetworkAvailable(context)) {
                        ThreadPoolUtils.getSingleInstance().execute(new GetInfoFromFile(context));
                    }
                    onIRSUID(context);
                } else if (postClientDatas) {
                    if (CommonUtil.isNetworkAvailable(context)) {
                        ThreadPoolUtils.getSingleInstance().execute(new GetInfoFromFile(context));
                    }
                }
            } else if (!HMTTool.isToDay(currentTimeMillis, longValue2) && CommonUtil.isNetworkAvailable(context)) {
                ThreadPoolUtils.getSingleInstance().execute(new GetInfoFromFile(context, 0));
            }
        }
    }

    public static void pushAllData(Context context) {
        pushAllData(context, null);
    }

    public static void pushAllData(Context context, HMTCallback hMTCallback) {
        if (CommonUtil.isNetworkAvailable(context)) {
            ThreadPoolUtils.getSingleInstance().execute(new GetInfoFromFile(context));
        }
    }

    public static void pushAdvActionData(Context context) {
        pushAdvActionData(context, null);
    }

    public static void pushAdvActionData(Context context, HMTCallback hMTCallback) {
        if (CommonUtil.isNetworkAvailable(context)) {
            h.post(new AnonymousClass10(context, hMTCallback));
        }
    }

    private static void sendAdvActionData(Context context, HMTCallback hMTCallback) {
        HMTTool.sendData(context, AssembJSONObj.getActionJOSNobj(new PostObjAction("advFirst", "1", CommonUtil.getTime(), "hmt_launch", CommonUtil.getAppVersion(context), CommonUtil.getAppKey(context)), context), HMTConstants.f, "act_list", HMTConstants.n, "client_adv", hMTCallback);
    }

    public static void pushLaunchActionData(Context context) {
        pushLaunchActionData(context, null);
    }

    public static void pushLaunchActionData(Context context, HMTCallback hMTCallback) {
        if (CommonUtil.isNetworkAvailable(context)) {
            h.post(new AnonymousClass11(context, hMTCallback));
        }
    }

    private static void sendLaunchActivityData(Context context, HMTCallback hMTCallback) {
        JSONObject jSONObject = null;
        String sessionId = getSessionId(context);
        try {
            jSONObject = ParamList.getParamList(context, Constants.FLAG_ACTIVITY_NAME);
            jSONObject.put("session_id", sessionId);
            jSONObject.put(Constants.FLAG_ACTIVITY_NAME, "hmt_launch");
            sessionId = CommonUtil.getTime();
            jSONObject.put("start_ts", sessionId);
            jSONObject.put("end_ts", sessionId);
            jSONObject.put("duration", "0");
            jSONObject.put("_activity", "hmt_launch");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HMTTool.sendData(context, jSONObject, HMTConstants.f, "activity_list", HMTConstants.n, Constants.FLAG_ACTIVITY_NAME, hMTCallback);
    }

    public static void setHMTNetWorkCallback(HMTNetWorkCallback hMTNetWorkCallback) {
        HMTConstants.t = hMTNetWorkCallback;
    }

    public static void setUnTracked(Context context, String[] strArr) {
        h.post(new AnonymousClass12(context, strArr));
    }

    public static void onIRSUID(Context context) {
        h.post(new AnonymousClass13(context));
    }

    private static void postIRSUIDInfo(Context context) {
        if (HMTTool.isSendIRSUID(context)) {
            HMTTool.isCreateNewIRSUID(context);
            getIRSUID(context);
        }
    }

    public static String getIRSUID(Context context) {
        HMTTool.isCreateNewIRSUID(context);
        return HMTTool.getIRSUID(context);
    }

    public static void setChannelId(String str) {
        if (str != null) {
            HMTConstants.u = str;
        }
    }

    public static void setIMEI(Context context, String str) {
        CommonUtil.setManualIMEI(context, str);
    }

    public static void setAppKey(Context context, String str) {
        SPUtils.put(context, "manual_setting_appkey", (Object) str);
    }
}
