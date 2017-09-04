package com.dynamicload;

import android.content.Context;
import com.dynamicload.internal.f;
import com.dynamicload.internal.g;

public class DLProxyFragmentActivitySingleIns extends DLProxyFragmentActivity {
    protected f a(Context context) {
        return new g(context);
    }
}
