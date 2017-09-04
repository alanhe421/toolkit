package com.tencent.sharp.jni;

import android.content.Context;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class TraeAudioSessionHost {
    private ArrayList<SessionInfo> _sessionInfoList = new ArrayList();
    private ReentrantLock mLock = new ReentrantLock();

    public class SessionInfo {
        public long sessionId;
    }

    public SessionInfo find(long j) {
        SessionInfo sessionInfo;
        this.mLock.lock();
        for (int i = 0; i < this._sessionInfoList.size(); i++) {
            sessionInfo = (SessionInfo) this._sessionInfoList.get(i);
            if (sessionInfo.sessionId == j) {
                break;
            }
        }
        sessionInfo = null;
        this.mLock.unlock();
        return sessionInfo;
    }

    public void add(long j, Context context) {
        if (find(j) == null) {
            SessionInfo sessionInfo = new SessionInfo();
            sessionInfo.sessionId = j;
            this.mLock.lock();
            this._sessionInfoList.add(sessionInfo);
            this.mLock.unlock();
        }
    }

    public void remove(long j) {
        this.mLock.lock();
        for (int i = 0; i < this._sessionInfoList.size(); i++) {
            if (((SessionInfo) this._sessionInfoList.get(i)).sessionId == j) {
                this._sessionInfoList.remove(i);
                break;
            }
        }
        this.mLock.unlock();
    }
}
