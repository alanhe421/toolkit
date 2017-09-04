package com.qq.reader.module.game;

import com.qq.reader.common.download.task.state.TaskStateEnum;

/* compiled from: GameDataHelper */
/* synthetic */ class a$5 {
    static final /* synthetic */ int[] a = new int[TaskStateEnum.values().length];

    static {
        try {
            a[TaskStateEnum.Paused.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[TaskStateEnum.Installing.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[TaskStateEnum.Finished.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[TaskStateEnum.Started.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[TaskStateEnum.Prepared.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[TaskStateEnum.Removed.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[TaskStateEnum.Failed.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
        try {
            a[TaskStateEnum.DeactiveStarted.ordinal()] = 8;
        } catch (NoSuchFieldError e8) {
        }
        try {
            a[TaskStateEnum.DeactivePrepared.ordinal()] = 9;
        } catch (NoSuchFieldError e9) {
        }
    }
}
