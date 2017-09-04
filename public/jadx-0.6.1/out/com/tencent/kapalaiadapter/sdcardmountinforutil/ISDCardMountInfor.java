package com.tencent.kapalaiadapter.sdcardmountinforutil;

import java.util.ArrayList;

public interface ISDCardMountInfor {
    ArrayList<String> getAllPath();

    boolean isExSdcard(String str);
}
