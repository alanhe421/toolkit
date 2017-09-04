package tencent.tls.tools;

import tencent.tls.tools.I18nMsg.MSG_TYPE;

class I18nMsg$BasicInfo {
    int _localid;
    String _msg;
    MSG_TYPE _type;

    public I18nMsg$BasicInfo(int i, MSG_TYPE msg_type, String str) {
        this._localid = i;
        this._type = msg_type;
        this._msg = str;
    }
}
