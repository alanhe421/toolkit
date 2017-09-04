package com.tencent.qalsdk.util;

import com.tencent.qalsdk.sdk.e;
import tencent.tls.platform.TLSHelper;
import tencent.tls.platform.TLSLoginHelper;

public class GuestHelper {
    private static GuestHelper instance = new GuestHelper();
    private static final String tag = "GuestHelper";

    public static GuestHelper getInstance() {
        return instance;
    }

    public void init() {
        if (TLSLoginHelper.getInstance() == null) {
            QLog.e(tag, 4, "TLSLoginHelper.getInstance() null");
            return;
        }
        String sSOGuestIdentifier = TLSHelper.getInstance().getSSOGuestIdentifier();
        QLog.d(tag, 1, "HaveAnonymousID:" + sSOGuestIdentifier);
        if (sSOGuestIdentifier == null) {
            generateAndLoginGuest();
            return;
        }
        e.b().a(sSOGuestIdentifier);
        long TLSGetLastRefreshTime = TLSLoginHelper.getInstance().TLSGetLastRefreshTime(sSOGuestIdentifier);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long j = ((currentTimeMillis - TLSGetLastRefreshTime) / 3600) / 24;
        QLog.d(tag, "ticket interverl:" + TLSGetLastRefreshTime + ":" + currentTimeMillis + ":" + j);
        if (j >= 28) {
            QLog.i(tag, "refresh ticket >= BaseConstants.FORCE_REFRESH_INTERVAL:" + sSOGuestIdentifier + ":" + j);
            tlsRefreshID(sSOGuestIdentifier, true);
            return;
        }
        if (j > 7) {
            QLog.i(tag, "refresh ticket >BaseConstants.REFRESH_INTERVAL:" + sSOGuestIdentifier + ":" + j);
            tlsRefreshID(sSOGuestIdentifier, false);
        }
        e.b().b(sSOGuestIdentifier, new b(this, sSOGuestIdentifier));
    }

    public void tlsRefreshID(String str, boolean z) {
        TLSLoginHelper.getInstance().TLSRefreshUserSig(str, new c(this, str, z));
    }

    public void generateAndLoginGuest() {
        TLSHelper.getInstance().TLSSSOGuestLogin(new e(this));
    }
}
