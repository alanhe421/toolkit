package com.dynamicload.Lib;

import android.os.Bundle;

public interface IDLPluginInterCommunication {
    int handleAddOuterCallBack(DLBasePluginCallBack dLBasePluginCallBack);

    int handleRemoveOuterCallBack(DLBasePluginCallBack dLBasePluginCallBack);

    int handleSyncRequest(int i, Bundle bundle, Bundle bundle2);
}
