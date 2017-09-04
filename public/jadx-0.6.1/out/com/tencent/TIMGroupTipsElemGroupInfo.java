package com.tencent;

public class TIMGroupTipsElemGroupInfo {
    private String content;
    private TIMGroupTipsGroupInfoType type;

    public String getContent() {
        return this.content;
    }

    public TIMGroupTipsGroupInfoType getType() {
        return this.type;
    }

    void setContent(String str) {
        this.content = str;
    }

    void setType(int i) {
        switch (i) {
            case 1:
                this.type = TIMGroupTipsGroupInfoType.ModifyName;
                return;
            case 2:
                this.type = TIMGroupTipsGroupInfoType.ModifyIntroduction;
                return;
            case 3:
                this.type = TIMGroupTipsGroupInfoType.ModifyNotification;
                return;
            case 4:
                this.type = TIMGroupTipsGroupInfoType.ModifyFaceUrl;
                return;
            case 5:
                this.type = TIMGroupTipsGroupInfoType.ModifyOwner;
                return;
            default:
                return;
        }
    }
}
