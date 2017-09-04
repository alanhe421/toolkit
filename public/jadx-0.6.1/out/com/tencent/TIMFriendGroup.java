package com.tencent;

import com.tencent.imcore.FriendGroup;
import com.tencent.imcore.FriendProfile;
import com.tencent.imcore.FriendProfileVec;
import com.tencent.imcore.StrVec;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class TIMFriendGroup {
    long count = 0;
    String name = "";
    List<TIMUserProfile> profiles = new ArrayList();
    List<String> users = new ArrayList();

    TIMFriendGroup() {
    }

    TIMFriendGroup(FriendGroup friendGroup) {
        int i = 0;
        try {
            setName(new String(friendGroup.getName(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        setCount(friendGroup.getCount());
        StrVec identifiers = friendGroup.getIdentifiers();
        List arrayList = new ArrayList();
        for (int i2 = 0; ((long) i2) < identifiers.size(); i2++) {
            if (identifiers.get(i2).length() != 0) {
                arrayList.add(identifiers.get(i2));
            }
        }
        setUsers(arrayList);
        FriendProfileVec profiles = friendGroup.getProfiles();
        while (((long) i) < profiles.size()) {
            FriendProfile friendProfile = profiles.get(i);
            if (friendProfile != null) {
                this.profiles.add(new TIMUserProfile(friendProfile));
            }
            i++;
        }
    }

    public long getCount() {
        return this.count;
    }

    public String getGroupName() {
        return this.name;
    }

    public List<TIMUserProfile> getProfiles() {
        return this.profiles;
    }

    public List<String> getUsers() {
        return this.users;
    }

    void setCount(long j) {
        this.count = j;
    }

    void setName(String str) {
        this.name = str;
    }

    void setUsers(List<String> list) {
        this.users = list;
    }
}
