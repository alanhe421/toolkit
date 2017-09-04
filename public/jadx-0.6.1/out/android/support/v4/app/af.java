package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.app.ae.a;

/* compiled from: RemoteInputCompatJellybean */
class af {
    static Bundle a(a aVar) {
        Bundle bundle = new Bundle();
        bundle.putString("resultKey", aVar.a());
        bundle.putCharSequence("label", aVar.b());
        bundle.putCharSequenceArray("choices", aVar.c());
        bundle.putBoolean("allowFreeFormInput", aVar.d());
        bundle.putBundle("extras", aVar.e());
        return bundle;
    }

    static Bundle[] a(a[] aVarArr) {
        if (aVarArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[aVarArr.length];
        for (int i = 0; i < aVarArr.length; i++) {
            bundleArr[i] = a(aVarArr[i]);
        }
        return bundleArr;
    }
}
