package com.qq.reader.common.emotion;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.t.d;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.f;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.PostTopicTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreTwoLevelActivity;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONObject;

/* compiled from: CommentPublisher */
public abstract class a {
    private static String b = null;
    private Handler a = null;
    private PostTopicTask c = null;

    /* compiled from: CommentPublisher */
    public static class a implements c {
        private Handler a;

        public a(Handler handler) {
            this.a = handler;
        }

        public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
            int i = 0;
            i.a("event_C60", true, 0, 0, null, i, i, ReaderApplication.getApplicationImp());
            Bundle bundle = new Bundle();
            try {
                i = ((PostTopicTask) readerProtocolTask).getCtype();
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("CommentPublisher", "post topic task not a PostTopicTask");
            }
            bundle.putInt("CTYPE", i);
            bundle.putString(SocialConstants.PARAM_URL, readerProtocolTask.getUrl());
            bundle.putString("book_name", a.b);
            try {
                JSONObject jSONObject = new JSONObject(str);
                int optInt = jSONObject.optInt("code");
                String optString = jSONObject.optString("message");
                if (optInt == 0) {
                    a.b(ReaderApplication.getApplicationImp(), (byte) 30, bundle);
                    if (this.a != null) {
                        f.b().b((ReaderTask) readerProtocolTask);
                        Message obtainMessage = this.a.obtainMessage();
                        obtainMessage.what = 6000014;
                        obtainMessage.obj = jSONObject;
                        obtainMessage.sendToTarget();
                        return;
                    }
                    return;
                }
                a(this.a, readerProtocolTask, optString);
                a.b(ReaderApplication.getApplicationImp(), (byte) 31, bundle);
            } catch (Exception e2) {
                a(this.a, readerProtocolTask, "");
                a.b(ReaderApplication.getApplicationImp(), (byte) 31, bundle);
            }
        }

        public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            int i = 0;
            i.a("event_C60", i, 0, 0, null, true, i, ReaderApplication.getApplicationImp());
            a(readerProtocolTask.getUrl());
            Bundle bundle = new Bundle();
            bundle.putString(SocialConstants.PARAM_URL, readerProtocolTask.getUrl());
            bundle.putString("book_name", a.b);
            try {
                i = ((PostTopicTask) readerProtocolTask).getCtype();
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("CommentPublisher", "post topic task not a PostTopicTask");
            }
            bundle.putInt("CTYPE", i);
            a.b(ReaderApplication.getApplicationImp(), (byte) 31, bundle);
        }

        void a(Handler handler, ReaderProtocolTask readerProtocolTask, String str) {
            ((PostTopicTask) readerProtocolTask).setErrorMessage(str);
            f.b().a((ReaderTask) readerProtocolTask);
            a(readerProtocolTask.getUrl());
        }

        private void a(String str) {
            String a = a.e(str);
            if (this.a != null) {
                Message obtainMessage = this.a.obtainMessage(6000015);
                obtainMessage.obj = a;
                obtainMessage.sendToTarget();
            }
        }
    }

    public abstract void a(String str, String str2);

    public a(Handler handler, String str) {
        this.a = handler;
        b = str;
    }

    private PostTopicTask a(long j, int i) {
        if (this.c == null) {
            this.c = new PostTopicTask(new a(this.a), String.valueOf(j), i);
        }
        return this.c;
    }

    public void a(Intent intent) {
        g.a().a(new CommentPublisher$1(this, intent));
    }

    private static int b(String str) {
        String c = c(str);
        int d = d(str);
        ArrayList arrayList = new ArrayList();
        ArrayList c2 = f.b().c(new PostTopicTask(new a(null), c, d));
        if (c2 != null) {
            return c2.size();
        }
        return 1;
    }

    private static String c(String str) {
        Matcher matcher = Pattern.compile("\\bbid=\\b\\d*").matcher(str);
        if (matcher.find()) {
            return str.substring("bid=".length() + matcher.start(), matcher.end());
        }
        return null;
    }

    private static int d(String str) {
        try {
            Matcher matcher = Pattern.compile("\\bctype=\\b\\d*").matcher(str);
            String str2 = null;
            if (matcher.find()) {
                str2 = str.substring("ctype=".length() + matcher.start(), matcher.end());
            }
            return Integer.parseInt(str2);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static String e(String str) {
        Matcher matcher = Pattern.compile("\\bsignal=\\b\\d*").matcher(str);
        if (matcher.find()) {
            return str.substring("signal=".length() + matcher.start(), matcher.end());
        }
        return null;
    }

    public static Bundle a(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("KEY_JUMP_PAGENAME", "bookclubmain");
        bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.bookclubindex));
        bundle.putInt("function_type", 0);
        bundle.putBoolean("newactivitywithresult", true);
        bundle.putString("PARA_TYPE_BOOK_NAME", b);
        String string = bundle.getString(SocialConstants.PARAM_URL);
        if (!TextUtils.isEmpty(string)) {
            c(string);
            long parseLong = Long.parseLong(c(string));
            bundle.putLong("URL_BUILD_PERE_BOOK_ID", parseLong);
            if (parseLong <= 3) {
                String[] strArr = new String[]{"书荒求助", "原创交流", "大神沙龙"};
                int i = ((int) parseLong) - 1;
                if (i >= 0 && i <= 2) {
                    bundle.putString("LOCAL_STORE_IN_TITLE", strArr[i]);
                }
                bundle.putInt("CTYPE", 4);
                bundle.putString("KEY_JUMP_PAGENAME", "discovery_comment_detail");
            }
        }
        return bundle;
    }

    private static void b(Context context, byte b, Bundle bundle) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        Intent intent = new Intent();
        CharSequence charSequence = null;
        String string = bundle.getString(SocialConstants.PARAM_URL);
        String string2 = bundle.getString("book_name");
        long parseLong = Long.parseLong(c(string));
        notificationManager.cancel((int) parseLong);
        CharSequence charSequence2 = "书友圈:" + string2;
        switch (b) {
            case (byte) 30:
                String str = "书评发送成功";
                return;
            case (byte) 31:
                charSequence = b(string) + "条书评发送失败";
                break;
        }
        intent.putExtras(a(bundle));
        intent.setClass(context, NativeBookStoreTwoLevelActivity.class);
        intent.setFlags(335544320);
        PendingIntent activity = PendingIntent.getActivity(context, 25, intent, SigType.WLOGIN_PT4Token);
        d y = ao.y(context);
        y.c(charSequence);
        y.a(charSequence2);
        y.b(charSequence);
        y.a(activity);
        Notification a = y.a();
        a.flags |= 16;
        notificationManager.notify((int) parseLong, a);
    }
}
