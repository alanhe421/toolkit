package com.dynamicload.Lib;

import android.os.Bundle;

public abstract class DLBaseIpcOperator implements IDLPluginInterCommunication {
    public int handleSyncRequest(int i, Bundle bundle, Bundle bundle2) {
        return 502;
    }

    public int handleAddOuterCallBack(DLBasePluginCallBack dLBasePluginCallBack) {
        return 502;
    }

    public int handleRemoveOuterCallBack(DLBasePluginCallBack dLBasePluginCallBack) {
        return 502;
    }
}
