package com.qq.reader.module.question;

import android.app.Activity;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.k;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.question.data.AudioData;
import com.tencent.feedback.proguard.R;

/* compiled from: QAHelper */
public class b {

    /* compiled from: QAHelper */
    public static class a {
        public String a = "";
        public int b = 0;
    }

    public static a a(int i) {
        a aVar = new a();
        switch (i) {
            case 0:
            case 4:
                aVar.a = ReaderApplication.getApplicationImp().getString(R.string.common_waiting_for_answer);
                aVar.b = R.drawable.audio_question_status_waiting;
                break;
            case 1:
                aVar.a = ReaderApplication.getApplicationImp().getString(R.string.common_answered_already);
                aVar.b = R.drawable.audio_question_status_already;
                break;
            case 3:
                aVar.a = ReaderApplication.getApplicationImp().getString(R.string.common_refused_to_answer);
                aVar.b = R.drawable.audio_question_status_refuses;
                break;
            case 8:
                aVar.a = ReaderApplication.getApplicationImp().getString(R.string.common_refused_censoring);
                aVar.b = R.drawable.audio_question_status_platform_censoring;
                break;
            default:
                aVar.a = ReaderApplication.getApplicationImp().getString(R.string.common_refused_to_answer);
                aVar.b = R.drawable.audio_question_status_refuses;
                break;
        }
        c.e("getQAState", "audioStates = " + i + " --- " + aVar.a);
        return aVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.qq.reader.module.question.b.a a(com.qq.reader.module.question.data.AudioData r5) {
        /*
        r2 = 1;
        r1 = new com.qq.reader.module.question.b$a;
        r1.<init>();
        r0 = r5.b();	 Catch:{ Exception -> 0x0027 }
        r0 = r0.e();	 Catch:{ Exception -> 0x0027 }
        switch(r0) {
            case 1: goto L_0x002c;
            case 2: goto L_0x0077;
            case 3: goto L_0x0017;
            default: goto L_0x0011;
        };	 Catch:{ Exception -> 0x0027 }
    L_0x0011:
        r0 = 2130837527; // 0x7f020017 float:1.728001E38 double:1.052773619E-314;
        r1.b = r0;	 Catch:{ Exception -> 0x0027 }
    L_0x0016:
        return r1;
    L_0x0017:
        r0 = 2130837529; // 0x7f020019 float:1.7280015E38 double:1.05277362E-314;
        r1.b = r0;	 Catch:{ Exception -> 0x0027 }
        r0 = r5.b();	 Catch:{ Exception -> 0x0027 }
        r0 = r0.k();	 Catch:{ Exception -> 0x0027 }
        r1.a = r0;	 Catch:{ Exception -> 0x0027 }
        goto L_0x0016;
    L_0x0027:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0016;
    L_0x002c:
        r0 = r5.b();	 Catch:{ Exception -> 0x0027 }
        r2 = r0.m();	 Catch:{ Exception -> 0x0027 }
        r0 = d(r2);	 Catch:{ Exception -> 0x0027 }
        if (r0 == 0) goto L_0x0067;
    L_0x003a:
        r0 = com.qq.reader.ReaderApplication.getApplicationImp();	 Catch:{ Exception -> 0x0027 }
        r0 = r0.getResources();	 Catch:{ Exception -> 0x0027 }
        r2 = 2131296753; // 0x7f0901f1 float:1.8211432E38 double:1.0530005068E-314;
        r0 = r0.getString(r2);	 Catch:{ Exception -> 0x0027 }
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0027 }
        r3 = 0;
        r4 = r5.b();	 Catch:{ Exception -> 0x0027 }
        r4 = r4.i();	 Catch:{ Exception -> 0x0027 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x0027 }
        r2[r3] = r4;	 Catch:{ Exception -> 0x0027 }
        r0 = java.lang.String.format(r0, r2);	 Catch:{ Exception -> 0x0027 }
        r1.a = r0;	 Catch:{ Exception -> 0x0027 }
        r0 = 2130837527; // 0x7f020017 float:1.728001E38 double:1.052773619E-314;
        r1.b = r0;	 Catch:{ Exception -> 0x0027 }
        goto L_0x0016;
    L_0x0067:
        r0 = r5.b();	 Catch:{ Exception -> 0x0027 }
        r0 = r0.k();	 Catch:{ Exception -> 0x0027 }
        r1.a = r0;	 Catch:{ Exception -> 0x0027 }
        r0 = 2130837529; // 0x7f020019 float:1.7280015E38 double:1.05277362E-314;
        r1.b = r0;	 Catch:{ Exception -> 0x0027 }
        goto L_0x0016;
    L_0x0077:
        r0 = r5.b();	 Catch:{ Exception -> 0x0027 }
        r0 = r0.n();	 Catch:{ Exception -> 0x0027 }
        if (r0 != r2) goto L_0x008c;
    L_0x0081:
        r0 = 2130837528; // 0x7f020018 float:1.7280013E38 double:1.0527736195E-314;
        r1.b = r0;	 Catch:{ Exception -> 0x0027 }
        r0 = "点击播放";
        r1.a = r0;	 Catch:{ Exception -> 0x0027 }
        goto L_0x0016;
    L_0x008c:
        r0 = 2130837527; // 0x7f020017 float:1.728001E38 double:1.052773619E-314;
        r1.b = r0;	 Catch:{ Exception -> 0x0027 }
        r0 = r5.b();	 Catch:{ Exception -> 0x0027 }
        r0 = r0.k();	 Catch:{ Exception -> 0x0027 }
        r1.a = r0;	 Catch:{ Exception -> 0x0027 }
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.question.b.a(com.qq.reader.module.question.data.AudioData):com.qq.reader.module.question.b$a");
    }

    public static String a(long j) {
        return k.c(j);
    }

    public static String b(long j) {
        return String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.common_qa_listen_count), new Object[]{j + ""});
    }

    public static String c(long j) {
        long j2 = 1000 * 60;
        long j3 = 60 * j2;
        long currentTimeMillis = j - System.currentTimeMillis();
        if (currentTimeMillis > 0 && currentTimeMillis <= 12 * j3) {
            if (((int) (currentTimeMillis / j3)) > 0) {
                return String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.audio_out_of_time_hour), new Object[]{Integer.valueOf(r4)});
            }
            if (((int) (currentTimeMillis / j2)) > 0) {
                return String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.audio_out_of_time_min), new Object[]{Integer.valueOf(r0)});
            }
        }
        return "";
    }

    public static void a(Activity activity, AudioData audioData, boolean z) {
        if (audioData != null && audioData.b() != null) {
            if (!com.qq.reader.common.login.c.b()) {
                z = false;
            }
            o.a(activity, audioData.a().g(), z);
        }
    }

    public static boolean d(long j) {
        return j < System.currentTimeMillis();
    }
}
