package android.support.v4.app;

import android.app.RemoteInput;
import android.app.RemoteInput.Builder;
import android.support.v4.app.ae.a;

/* compiled from: RemoteInputCompatApi20 */
class ad {
    static RemoteInput[] a(a[] aVarArr) {
        if (aVarArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[aVarArr.length];
        for (int i = 0; i < aVarArr.length; i++) {
            a aVar = aVarArr[i];
            remoteInputArr[i] = new Builder(aVar.a()).setLabel(aVar.b()).setChoices(aVar.c()).setAllowFreeFormInput(aVar.d()).addExtras(aVar.e()).build();
        }
        return remoteInputArr;
    }
}
