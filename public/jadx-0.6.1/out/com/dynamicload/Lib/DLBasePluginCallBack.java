package com.dynamicload.Lib;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;

public abstract class DLBasePluginCallBack implements Callback {
    protected Bundle mBundle;
    protected int mType;

    public int getType() {
        return this.mType;
    }

    public Bundle getBundle() {
        return this.mBundle;
    }

    public boolean handleMessage(Message message) {
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }
}
