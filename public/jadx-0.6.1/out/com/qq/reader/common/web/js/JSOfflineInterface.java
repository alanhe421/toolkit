package com.qq.reader.common.web.js;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.offline.OfflineRequestTask;
import com.qq.reader.common.offline.c;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.a.a.b;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class JSOfflineInterface extends b {
    private Context a;
    private Handler b;
    private String c;

    public JSOfflineInterface(Context context, Handler handler, String str) {
        this.a = context;
        this.b = handler;
        this.c = str;
    }

    public void req(String str, String str2, String str3) {
        OfflineRequestTask offlineRequestTask = new OfflineRequestTask(str, str2);
        if (str.contains("common/accesslog")) {
            offlineRequestTask.setPriority(1);
        } else {
            offlineRequestTask.setPriority(2);
        }
        offlineRequestTask.setHostName(this.c);
        if (Integer.valueOf(str3).intValue() == 1) {
            offlineRequestTask.setShouldCallBack(true);
            offlineRequestTask.setShouldCache(false);
            c.a(this.a).a(offlineRequestTask);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        com.qq.reader.common.offline.b c = c.a(this.a).c(str);
        i.a("event_offline_pagedata_load_from_local", true, System.currentTimeMillis() - currentTimeMillis, 0, null, ReaderApplication.getApplicationImp().getApplicationContext());
        String str4 = "0";
        Message obtainMessage;
        if (ao.p(this.a) || !ao.d(this.a)) {
            if (c != null) {
                obtainMessage = this.b.obtainMessage();
                c.a(str2);
                obtainMessage.obj = c;
                obtainMessage.what = 90004;
                this.b.sendMessageDelayed(obtainMessage, 100);
                str4 = c.c();
                c.a(this.a).a(this.c, false);
            }
            if (c == null || Long.valueOf(r0).longValue() < System.currentTimeMillis()) {
                if (c == null) {
                    offlineRequestTask.setShouldCallBack(true);
                }
                offlineRequestTask.setShouldCache(true);
                c.a(this.a).a(offlineRequestTask);
            }
        } else if (c == null || Long.valueOf(c.c()).longValue() < System.currentTimeMillis()) {
            offlineRequestTask.setShouldCallBack(true);
            offlineRequestTask.setShouldCache(true);
            c.a(this.a).a(offlineRequestTask);
        } else if (c != null) {
            obtainMessage = this.b.obtainMessage();
            c.a(str2);
            obtainMessage.obj = c;
            obtainMessage.what = 90004;
            this.b.sendMessageDelayed(obtainMessage, 100);
            c.c();
            c.a(this.a).a(this.c, false);
        }
    }

    public void post(String str, String str2, String str3, String str4) {
        f.a("post url", str);
        OfflineRequestTask offlineRequestTask = new OfflineRequestTask(str, str2, str4);
        if (str.contains("common/accesslog")) {
            offlineRequestTask.setPriority(1);
        } else {
            offlineRequestTask.setPriority(2);
        }
        offlineRequestTask.setHostName(this.c);
        if (Integer.valueOf(str3).intValue() == 1) {
            offlineRequestTask.setShouldCallBack(true);
            offlineRequestTask.setShouldCache(false);
            c.a(this.a).a(offlineRequestTask);
        }
    }

    public void saveErrInfo(String str) {
        File file = new File(a.cU + "errorlog/" + String.valueOf(System.currentTimeMillis()));
        if (ab.a(file.getParentFile())) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath());
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public void swtichTab() {
    }

    public String getClientInfo(String str) {
        String str2 = "";
        switch (Integer.parseInt(str)) {
            case 1000:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("qqreader_6.5.3.0888_android");
                stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV);
                stringBuilder.append(ao.h(this.a));
                stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV);
                String R = d.R(this.a);
                if (R == null || R.trim().length() == 0) {
                    R = "0";
                }
                stringBuilder.append(R);
                stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV);
                stringBuilder.append(com.qq.reader.common.utils.a.b.a());
                try {
                    return com.qq.reader.common.utils.a.d.b(stringBuilder.toString().getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return str2;
                }
            default:
                return str2;
        }
    }
}
