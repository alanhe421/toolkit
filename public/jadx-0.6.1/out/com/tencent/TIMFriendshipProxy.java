package com.tencent;

import android.text.TextUtils;
import com.tencent.imcore.BytesVec;
import com.tencent.imcore.FriendGroupVec;
import com.tencent.imcore.FriendProfileVec;
import com.tencent.imcore.FriendshipProxy;
import com.tencent.imcore.StrVec;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class TIMFriendshipProxy {
    private static TIMFriendshipProxy inst = new TIMFriendshipProxy();
    private static final String tag = "MSF.C.TIMFriendshipProxy";
    private String identifier = "";

    private TIMFriendshipProxy() {
    }

    private TIMFriendshipProxy(String str) {
        this.identifier = str;
    }

    private FriendshipProxy getFriendshipProxy() {
        return TextUtils.isEmpty(this.identifier) ? TIMManager.getInstance().getCoreUser().getFriendShipPrxy() : TIMManager.getInstanceById(this.identifier).getCoreUser().getFriendShipPrxy();
    }

    public static TIMFriendshipProxy getInstance() {
        inst.setIdentifier(TIMManager.getInstance().getIdentification());
        return inst;
    }

    public static TIMFriendshipProxy getInstanceById(String str) {
        if (TextUtils.isEmpty(str)) {
            return inst;
        }
        TIMFriendshipProxy tIMFriendshipProxy = new TIMFriendshipProxy(str);
        tIMFriendshipProxy.setIdentifier(str);
        return tIMFriendshipProxy;
    }

    public List<TIMUserProfile> getFriends() {
        FriendProfileVec friendProfileVec = new FriendProfileVec();
        if (getFriendshipProxy().getFriendList(friendProfileVec) != 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; ((long) i) < friendProfileVec.size(); i++) {
            arrayList.add(new TIMUserProfile(friendProfileVec.get(i)));
        }
        return arrayList;
    }

    public List<TIMFriendGroup> getFriendsByGroups(List<String> list) {
        List<TIMFriendGroup> arrayList = new ArrayList();
        if (list != null && list.size() == 0) {
            return arrayList;
        }
        FriendGroupVec friendGroupVec = new FriendGroupVec();
        BytesVec bytesVec = new BytesVec();
        if (list != null) {
            for (String bytes : list) {
                try {
                    bytesVec.pushBack(bytes.getBytes("utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        if (getFriendshipProxy().getFriendGroup(bytesVec, friendGroupVec) != 0) {
            return null;
        }
        for (int i = 0; ((long) i) < friendGroupVec.size(); i++) {
            arrayList.add(new TIMFriendGroup(friendGroupVec.get(i)));
        }
        return arrayList;
    }

    public List<TIMUserProfile> getFriendsById(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null || list.size() == 0) {
            return arrayList;
        }
        FriendProfileVec friendProfileVec = new FriendProfileVec();
        StrVec strVec = new StrVec();
        for (String str : list) {
            if (!TextUtils.isEmpty(str)) {
                strVec.pushBack(str);
            }
        }
        if (getFriendshipProxy().getFriendProfile(strVec, friendProfileVec) != 0) {
            return null;
        }
        for (int i = 0; ((long) i) < friendProfileVec.size(); i++) {
            arrayList.add(new TIMUserProfile(friendProfileVec.get(i)));
        }
        return arrayList;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    void setIdentifier(String str) {
        this.identifier = str;
    }
}
