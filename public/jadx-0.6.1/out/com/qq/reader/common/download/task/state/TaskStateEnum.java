package com.qq.reader.common.download.task.state;

public enum TaskStateEnum {
    Prepared,
    DeactivePrepared,
    Started,
    DeactiveStarted,
    Failed,
    Paused,
    Removed,
    Finished,
    Installing,
    InstallCompleted,
    InstallFailed,
    Uninstalled
}
