package com.tencent;

import com.tencent.imcore.PendencyType;

public enum TIMPendencyGetType {
    TIM_PENDENCY_GET_COME_IN(1),
    TIM_PENDENCY_GET_SEND_OUT(2),
    TIM_PENDENCY_GET_BOTH(3);
    
    private int value;

    private TIMPendencyGetType(int i) {
        this.value = i;
    }

    static TIMPendencyGetType getType(PendencyType pendencyType) {
        return pendencyType == PendencyType.PendencyTypeBoth ? TIM_PENDENCY_GET_BOTH : pendencyType == PendencyType.PendencyTypeComeIn ? TIM_PENDENCY_GET_COME_IN : TIM_PENDENCY_GET_SEND_OUT;
    }

    static PendencyType getType(TIMPendencyGetType tIMPendencyGetType) {
        switch (hh.a[tIMPendencyGetType.ordinal()]) {
            case 1:
                return PendencyType.PendencyTypeBoth;
            case 2:
                return PendencyType.PendencyTypeComeIn;
            case 3:
                return PendencyType.PendencyTypeSendOut;
            default:
                return PendencyType.PendencyTypeBoth;
        }
    }

    public final int getValue() {
        return this.value;
    }
}
