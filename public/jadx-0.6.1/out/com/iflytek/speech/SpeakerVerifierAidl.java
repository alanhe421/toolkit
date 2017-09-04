package com.iflytek.speech;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;
import com.iflytek.cloud.a;
import com.iflytek.speech.aidl.ISpeakerVerifier;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import java.util.ArrayList;

public class SpeakerVerifierAidl extends SpeechModuleAidl<ISpeakerVerifier> {
    public static String IVP_PARAM_CONSISTTHRESHOLD = "consistThreshold";
    public static String IVP_PARAM_DTW_CHECK_THRESHOLD = "checkThreshold";
    public static String IVP_SENTENCE_CNT = ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT;
    public static String USER_NAME = "name";
    public static String VID = "vid";
    private final String TAG = "SpeakerVerifier";

    public interface DownloadListener {
        void onData(ArrayList<PassWord> arrayList);

        void onError(int i);
    }

    public class PassWord {
        public String pwdt = "";
        public String pwid = "";
        public String pwtext = "";
    }

    public SpeakerVerifierAidl(Context context, a aVar) {
        super(context, aVar, UtilityConfig.ACTION_SPEAKER_VERIFIER);
    }

    public /* bridge */ /* synthetic */ boolean destory() {
        return super.destory();
    }

    public void endSpeak() {
        try {
            Log.d("SpeakerVerifier", "SpeakerVerifier | endSpeak");
            ((ISpeakerVerifier) this.mService).endSpeak();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public /* bridge */ /* synthetic */ Intent getIntent() {
        return super.getIntent();
    }

    public String getParameter(String str) {
        return null;
    }

    public int getPasswordList(Context context, DownloadListener downloadListener, String str) {
        return 0;
    }

    public int identify(String str, String str2, VerifierListener verifierListener) {
        return 0;
    }

    public /* bridge */ /* synthetic */ boolean isActionInstalled(Context context, String str) {
        return super.isActionInstalled(context, str);
    }

    public /* bridge */ /* synthetic */ boolean isAvailable() {
        return super.isAvailable();
    }

    public int register(String str, String str2, VerifierListener verifierListener) {
        Log.d("SpeakerVerifier", "SpeakerVerifier | register");
        if (this.mService == null) {
            return 21003;
        }
        if (verifierListener == null) {
            return 20012;
        }
        try {
            ((ISpeakerVerifier) this.mService).register(str, str2, verifierListener);
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int setParameter(String str, String str2) {
        return 0;
    }

    public void stopSpeak() {
        try {
            Log.d("SpeakerVerifier", "SpeakerVerifier | stopSpeak");
            ((ISpeakerVerifier) this.mService).stopSpeak();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public int verify(String str, String str2, VerifierListener verifierListener) {
        Log.d("SpeakerVerifier", "SpeakerVerifier | verify");
        if (this.mService == null) {
            return 21003;
        }
        if (verifierListener == null) {
            return 20012;
        }
        try {
            ((ISpeakerVerifier) this.mService).verify(str, str2, verifierListener);
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }
}
