package com.qq.reader.cservice.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ReaderShareRespon */
public class b {

    /* compiled from: ReaderShareRespon */
    public static class a implements IUiListener {
        private String a;
        private WeakReference<Activity> b;
        private int c = 0;

        public a(Activity activity, int i, String str) {
            this.a = str;
            this.b = new WeakReference(activity);
            this.c = i;
        }

        public void onComplete(Object obj) {
            Activity activity = (Activity) this.b.get();
            if (activity != null) {
                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "分享成功", 0).a();
                Intent intent = new Intent();
                if (this.c == 0) {
                    intent.putExtra("type", 2);
                } else {
                    intent.putExtra("type", 3);
                }
                intent.putExtra("success", true);
                intent.putExtra("sharedurl", this.a);
                intent.setAction(com.qq.reader.common.c.a.cM);
                activity.sendBroadcast(intent);
            }
        }

        public void onError(UiError uiError) {
            Activity activity = (Activity) this.b.get();
            if (activity != null) {
                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "分享失败", 0).a();
                Intent intent = new Intent();
                if (this.c == 0) {
                    intent.putExtra("type", 2);
                } else {
                    intent.putExtra("type", 3);
                }
                intent.putExtra("success", false);
                intent.putExtra("sharedurl", this.a);
                intent.setAction(com.qq.reader.common.c.a.cM);
                activity.sendBroadcast(intent);
            }
        }

        public void onCancel() {
        }
    }

    public static boolean a(int i, int i2, Intent intent) {
        if (i == 10103) {
            Tencent.onActivityResultData(i, i2, intent, null);
            return true;
        } else if (i != 10104) {
            return false;
        } else {
            Tencent.onActivityResultData(i, i2, intent, null);
            return true;
        }
    }

    public static boolean a(Activity activity, String str, int i) {
        String optString;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("share_type");
            optString = jSONObject.optString("share_targeturl");
            int i2 = optInt;
        } catch (JSONException e) {
            c.e("WXEntryActivity", e.getMessage());
            optString = null;
            boolean z = true;
        }
        if (i == 0) {
            Intent intent = new Intent();
            if (1 == i2) {
                af.a((Context) activity, activity.getResources().getString(R.string.share_success), 0).a();
                intent.putExtra("type", 1);
            } else {
                intent.putExtra("type", 0);
            }
            intent.putExtra("success", true);
            intent.putExtra("sharedurl", optString);
            intent.setAction(com.qq.reader.common.c.a.cM);
            activity.sendBroadcast(intent);
        }
        return true;
    }

    public static a a(Activity activity, String str) {
        return new a(activity, 0, str);
    }

    public static a b(Activity activity, String str) {
        return new a(activity, 1, str);
    }
}
