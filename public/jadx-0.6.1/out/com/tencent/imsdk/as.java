package com.tencent.imsdk;

import com.tencent.qalsdk.QALLogListener;

final class as implements QALLogListener {
    as(IMMsfCoreProxy iMMsfCoreProxy) {
    }

    public final void log(int i, String str, String str2) {
        switch (i) {
            case 1:
                QLog.efile(str, 1, str2);
                return;
            case 2:
                QLog.wfile(str, 1, str2);
                return;
            case 3:
                QLog.ifile(str, 1, str2);
                return;
            default:
                QLog.dfile(str, 1, str2);
                return;
        }
    }
}
