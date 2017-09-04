package com.tencent.qalsdk;

import com.tencent.qalsdk.base.remote.IBaseActionListener;

public class QALSendData {
    byte[] body;
    String cmd;
    IBaseActionListener listener;
    String tinyID;
}
