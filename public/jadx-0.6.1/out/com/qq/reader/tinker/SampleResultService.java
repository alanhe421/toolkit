package com.qq.reader.tinker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.tencent.tinker.lib.service.DefaultTinkerResultService;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.TinkerServiceInternals;
import java.io.File;

public class SampleResultService extends DefaultTinkerResultService {

    static class a {

        interface a {
            void a();
        }

        a(Context context, final a aVar) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            context.registerReceiver(new BroadcastReceiver(this) {
                final /* synthetic */ a b;

                public void onReceive(Context context, Intent intent) {
                    TinkerLog.i("Tinker.SampleResultService", "ScreenReceiver action [%s] ", intent == null ? "" : intent.getAction());
                    if ("android.intent.action.SCREEN_OFF".equals(intent == null ? "" : intent.getAction())) {
                        context.unregisterReceiver(this);
                        if (aVar != null) {
                            aVar.a();
                        }
                    }
                }
            }, intentFilter);
        }
    }

    public void onPatchResult(final PatchResult patchResult) {
        if (patchResult == null) {
            TinkerLog.e("Tinker.SampleResultService", "SampleResultService received null result!!!!", new Object[0]);
            return;
        }
        TinkerLog.i("Tinker.SampleResultService", "SampleResultService receive result: %s", patchResult.toString());
        TinkerServiceInternals.killTinkerPatchServiceProcess(getApplicationContext());
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ SampleResultService b;

            public void run() {
                b.b().a(patchResult);
            }
        });
        if (patchResult.isSuccess) {
            deleteRawPatchFile(new File(patchResult.rawPatchFilePath));
            if (!checkIfNeedKill(patchResult)) {
                TinkerLog.i("Tinker.SampleResultService", "I have already install the newly patch version!", new Object[0]);
            } else if (m.b()) {
                TinkerLog.i("Tinker.SampleResultService", "it is in background, just restart process", new Object[0]);
                a();
            } else {
                TinkerLog.i("Tinker.SampleResultService", "tinker wait screen to restart process", new Object[0]);
                a aVar = new a(getApplicationContext(), new a(this) {
                    final /* synthetic */ SampleResultService a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.a();
                    }
                });
            }
        }
    }

    private void a() {
        TinkerLog.i("Tinker.SampleResultService", "app is background now, i can kill quietly", new Object[0]);
        Process.killProcess(Process.myPid());
    }
}
