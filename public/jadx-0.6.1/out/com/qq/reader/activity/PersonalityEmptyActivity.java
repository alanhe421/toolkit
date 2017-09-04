package com.qq.reader.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.view.KeyEvent;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.FeedColumnCheckNeedGeneTask;
import com.qq.reader.view.m;
import org.json.JSONObject;

public class PersonalityEmptyActivity extends ReaderBaseActivity {
    protected boolean isLayoutFillWindow() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setSwipeBackEnable(false);
        disableUseAnimation();
        showPorgress("正在加载...");
        a();
    }

    public void showPorgress(String str) {
        if (!isFinishing()) {
            if (this.mProgressDialog == null) {
                if (str == null) {
                    str = "";
                }
                this.mProgressDialog = new m(this);
                this.mProgressDialog.a(str);
                this.mProgressDialog.a(new OnKeyListener(this) {
                    final /* synthetic */ PersonalityEmptyActivity a;

                    {
                        this.a = r1;
                    }

                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        switch (i) {
                            case 4:
                                this.a.progressCancel();
                                this.a.finish();
                                break;
                        }
                        return false;
                    }
                });
            }
            this.mProgressDialog.f_();
        }
    }

    private void a() {
        g.a().a(new FeedColumnCheckNeedGeneTask(new c(this) {
            final /* synthetic */ PersonalityEmptyActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                String str2 = "uniteqqreader://nativepage/infostream/individualbooklist?needGeneInfo=0";
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject != null && jSONObject.optInt("code") == 0 && jSONObject.optInt("needGene") == 1) {
                        str2 = "uniteqqreader://nativepage/infostream/individualbooklist?needGeneInfo=2";
                    }
                    if (!this.a.isFinishing()) {
                        com.qq.reader.qurl.c.a(this.a, str2);
                    }
                    this.a.finish();
                } catch (Exception e) {
                    try {
                        if (!this.a.isFinishing()) {
                            com.qq.reader.qurl.c.a(this.a, str2);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    } catch (Throwable th) {
                        this.a.finish();
                    }
                    this.a.finish();
                }
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onConnectionError(com.qq.reader.common.readertask.ordinal.ReaderProtocolTask r3, java.lang.Exception r4) {
                /*
                r2 = this;
                r0 = r2.a;	 Catch:{ Exception -> 0x0016 }
                r0 = r0.isFinishing();	 Catch:{ Exception -> 0x0016 }
                if (r0 != 0) goto L_0x0010;
            L_0x0008:
                r0 = r2.a;	 Catch:{ Exception -> 0x0016 }
                r1 = "uniteqqreader://nativepage/infostream/individualbooklist?needGeneInfo=0";
                com.qq.reader.qurl.c.a(r0, r1);	 Catch:{ Exception -> 0x0016 }
            L_0x0010:
                r0 = r2.a;
                r0.finish();
            L_0x0015:
                return;
            L_0x0016:
                r0 = move-exception;
                r0.printStackTrace();	 Catch:{ all -> 0x0020 }
                r0 = r2.a;
                r0.finish();
                goto L_0x0015;
            L_0x0020:
                r0 = move-exception;
                r1 = r2.a;
                r1.finish();
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.activity.PersonalityEmptyActivity.2.onConnectionError(com.qq.reader.common.readertask.ordinal.ReaderProtocolTask, java.lang.Exception):void");
            }
        }));
    }
}
