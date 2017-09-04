package com.iflytek.cloud;

import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import com.iflytek.speech.SynthesizerListener.Stub;

class j extends Stub {
    final /* synthetic */ c a;
    final /* synthetic */ a b;

    j(a aVar, c cVar) {
        this.b = aVar;
        this.a = cVar;
    }

    public void onBufferProgress(int i, int i2, int i3, String str) throws RemoteException {
        if (this.b.b != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("percent", i);
            bundle.putInt("begpos", i2);
            bundle.putInt("endpos", i3);
            bundle.putString("spellinfo", "");
            if (this.b.b != null) {
                Message.obtain(this.b.d, 2, bundle).sendToTarget();
            }
        }
    }

    public void onCompleted(int i) throws RemoteException {
        if (this.b.b != null) {
            Message.obtain(this.b.d, 6, i == 0 ? null : new SpeechError(i)).sendToTarget();
        }
    }

    public void onEvent(int i, int i2, int i3, Bundle bundle) throws RemoteException {
        if (this.b.b != null) {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.arg1 = 0;
            obtain.arg2 = 0;
            obtain.obj = bundle;
            Message.obtain(this.b.d, 7, 0, 0, obtain).sendToTarget();
        }
    }

    public void onSpeakBegin() throws RemoteException {
        if (this.b.b != null) {
            Message.obtain(this.b.d, 1).sendToTarget();
        }
    }

    public void onSpeakPaused() throws RemoteException {
        if (this.b.b != null) {
            Message.obtain(this.b.d, 3).sendToTarget();
        }
    }

    public void onSpeakProgress(int i, int i2, int i3) throws RemoteException {
        if (this.b.b != null) {
            Message.obtain(this.b.d, 5, i, i2, Integer.valueOf(i3)).sendToTarget();
        }
    }

    public void onSpeakResumed() throws RemoteException {
        if (this.b.b != null) {
            Message.obtain(this.b.d, 4, 0, 0, null).sendToTarget();
        }
    }
}
