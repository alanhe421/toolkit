package com.tencent;

import com.tencent.imcore.FriendFutureItem;
import com.tencent.imcore.FutureType;
import com.tencent.imcore.MapKeyFetcher;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class TIMFriendFutureItem {
    private String addSource = "";
    private long addTime;
    private String addWording = "";
    private String identifier = "";
    private TIMUserProfile profile;
    private Map<String, String> recommendTags = new HashMap();
    private TIMFutureFriendType type;

    TIMFriendFutureItem(FriendFutureItem friendFutureItem) {
        if (friendFutureItem.getEType() == FutureType.FutureTypeRecommend) {
            this.type = TIMFutureFriendType.TIM_FUTURE_FRIEND_RECOMMEND_TYPE;
            int size = (int) friendFutureItem.getMpRecommendTags().size();
            MapKeyFetcher mapKeyFetcher = new MapKeyFetcher();
            mapKeyFetcher.fetchMapKeys(friendFutureItem.getMpRecommendTags());
            this.recommendTags.clear();
            for (int i = 0; i < size; i++) {
                byte[] key = mapKeyFetcher.getKey(i);
                byte[] value = mapKeyFetcher.getValue(friendFutureItem.getMpRecommendTags(), i);
                try {
                    this.recommendTags.put(new String(key, "utf-8"), new String(value, "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (friendFutureItem.getEType() == FutureType.FutureTypePendencyComeIn) {
                this.type = TIMFutureFriendType.TIM_FUTURE_FRIEND_PENDENCY_IN_TYPE;
            } else if (friendFutureItem.getEType() == FutureType.FutureTypePendencySendOut) {
                this.type = TIMFutureFriendType.TIM_FUTURE_FRIEND_PENDENCY_OUT_TYPE;
            } else if (friendFutureItem.getEType() == FutureType.FutureTypeDecide) {
                this.type = TIMFutureFriendType.TIM_FUTURE_FRIEND_DECIDE_TYPE;
            }
            try {
                this.addSource = new String(friendFutureItem.getSAddSource(), "utf-8");
                this.addWording = new String(friendFutureItem.getSAddWording(), "utf-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        setIdentifier(friendFutureItem.getSIdentifier());
        setAddTime(friendFutureItem.getDdwAddTime());
        setProfile(new TIMUserProfile(friendFutureItem.getStProfile()));
    }

    public String getAddSource() {
        return this.addSource;
    }

    public long getAddTime() {
        return this.addTime;
    }

    public String getAddWording() {
        return this.addWording;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public TIMUserProfile getProfile() {
        return this.profile;
    }

    public Map<String, String> getRecommendTags() {
        return this.recommendTags;
    }

    public TIMFutureFriendType getType() {
        return this.type;
    }

    void setAddSource(String str) {
        this.addSource = str;
    }

    void setAddTime(long j) {
        this.addTime = j;
    }

    void setAddWording(String str) {
        this.addWording = str;
    }

    void setIdentifier(String str) {
        this.identifier = str;
    }

    void setProfile(TIMUserProfile tIMUserProfile) {
        this.profile = tIMUserProfile;
    }

    void setType(TIMFutureFriendType tIMFutureFriendType) {
        this.type = tIMFutureFriendType;
    }
}
