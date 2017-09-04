package com.tencent;

public enum TIMPageDirectionType {
    TIM_PAGE_DIRECTION_UP_TYPE(1),
    TIM_PAGE_DIRECTION_DOWN_TYPE(2);
    
    private int value;

    private TIMPageDirectionType(int i) {
        this.value = i;
    }

    static TIMPageDirectionType getType(int i) {
        switch (i) {
            case 1:
                return TIM_PAGE_DIRECTION_UP_TYPE;
            case 2:
                return TIM_PAGE_DIRECTION_DOWN_TYPE;
            default:
                return TIM_PAGE_DIRECTION_UP_TYPE;
        }
    }

    public final int getValue() {
        return this.value;
    }
}
