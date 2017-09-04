package com.qq.reader.module.question.loader;

import android.os.Handler;
import android.os.Message;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.module.question.data.AudioData;
import com.qq.reader.module.question.loader.RecordDownloadTask.a;
import com.tencent.open.SocialConstants;
import com.tencent.util.WeakReferenceHandler;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: RecordDataLoader */
public class c {
    private b a;

    protected c(b bVar) {
        this.a = bVar;
    }

    protected boolean a(File file, final AudioData audioData, final WeakReferenceHandler weakReferenceHandler) {
        String g = audioData.a().g();
        String h = audioData.b().h();
        if (h == null || h.length() == 0) {
            return false;
        }
        if (file == null || !file.exists()) {
            file = this.a.a(h);
        }
        if (file == null || !file.exists()) {
            String[] d = audioData.b().d();
            if (d == null || d.length <= 0) {
                g.a().a(new RecordGetDownloadUrlTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                    final /* synthetic */ c c;

                    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            switch (jSONObject.optInt("code")) {
                                case 0:
                                    JSONArray optJSONArray = jSONObject.optJSONArray("ul");
                                    JSONArray optJSONArray2 = jSONObject.optJSONArray("cl");
                                    String str2 = "";
                                    String str3 = "";
                                    if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                        jSONObject = optJSONArray2.getJSONObject(0);
                                        str2 = jSONObject.optString("cn");
                                        str3 = jSONObject.optString("vkey");
                                    }
                                    if (optJSONArray == null || str2.length() <= 0 || str3.length() <= 0) {
                                        c.a(weakReferenceHandler, audioData, 11000005);
                                        return;
                                    }
                                    String[] strArr = new String[optJSONArray.length()];
                                    for (int i = 0; i < optJSONArray.length(); i++) {
                                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                                        if (jSONObject2 != null) {
                                            String optString = jSONObject2.optString(SocialConstants.PARAM_URL);
                                            if (optString.length() != 0) {
                                                StringBuilder stringBuilder = new StringBuilder(optString);
                                                stringBuilder.append(str2);
                                                stringBuilder.append("?vkey=");
                                                stringBuilder.append(str3);
                                                strArr[i] = stringBuilder.toString();
                                            }
                                        }
                                    }
                                    audioData.b().a(strArr);
                                    this.c.a(audioData, weakReferenceHandler);
                                    return;
                                case 1010:
                                    c.a(weakReferenceHandler, audioData, 11000001);
                                    return;
                                case 1011:
                                    c.a(weakReferenceHandler, audioData, 11000008);
                                    return;
                                case 1018:
                                    c.a(weakReferenceHandler, audioData, 11000002);
                                    return;
                                case 1022:
                                    c.a(weakReferenceHandler, audioData, 11000003);
                                    return;
                                default:
                                    com.qq.reader.common.monitor.debug.c.a("Audio", "loadData obtion download url : " + jSONObject.optString(SocialConstants.PARAM_SEND_MSG));
                                    c.a(weakReferenceHandler, audioData, 11000005);
                                    return;
                            }
                        } catch (Exception e) {
                            c.a(weakReferenceHandler, audioData, 11000004);
                        }
                        c.a(weakReferenceHandler, audioData, 11000004);
                    }

                    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                        c.a(weakReferenceHandler, audioData, 11000005);
                    }
                }, g));
                return true;
            }
            a(audioData, weakReferenceHandler);
            return true;
        }
        audioData.b().a(file);
        a((Handler) weakReferenceHandler, true, audioData);
        return true;
    }

    private void a(AudioData audioData, WeakReferenceHandler weakReferenceHandler) {
        String h = audioData.b().h();
        String[] d = audioData.b().d();
        if (h == null || d == null || d.length == 0) {
            audioData.b().a(null);
            a((Handler) weakReferenceHandler, audioData, 11000005);
        }
        ReaderTask recordDownloadTask = new RecordDownloadTask(ReaderApplication.getApplicationImp(), h, d);
        recordDownloadTask.setListener(new a(this.a, h, audioData, weakReferenceHandler));
        g.a().a(recordDownloadTask);
    }

    public static void a(Handler handler, boolean z, AudioData audioData) {
        if (handler != null) {
            Message obtain = Message.obtain();
            if (z) {
                obtain.what = 11000006;
            } else {
                obtain.what = 11000007;
            }
            obtain.obj = audioData;
            handler.sendMessage(obtain);
        }
    }

    public static void a(Handler handler, AudioData audioData, int i) {
        if (handler != null) {
            Message obtain = Message.obtain();
            obtain.what = 11000000;
            obtain.arg1 = i;
            obtain.obj = audioData;
            handler.sendMessage(obtain);
        }
    }
}
