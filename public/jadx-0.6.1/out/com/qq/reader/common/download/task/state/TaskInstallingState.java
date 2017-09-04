package com.qq.reader.common.download.task.state;

import com.qq.reader.common.download.task.TaskStateChangeException;
import com.qq.reader.common.download.task.n;

public class TaskInstallingState extends TaskState {
    private static final long serialVersionUID = 1;

    public TaskInstallingState() {
        super(TaskStateEnum.Installing);
    }

    protected TaskState stateChange(n nVar) throws TaskStateChangeException {
        switch (nVar.b()) {
            case InstallComplete:
                nVar.e().q(nVar.d());
                return new TaskInstallCompletedState();
            case InstallFailed:
                nVar.e().r(nVar.d());
                return new TaskInstallFailedState();
            case Remove:
                nVar.e().e(nVar.d());
                return new TaskRemovedState();
            case Restart:
                nVar.e().d(nVar.d());
                return new TaskPreparedState();
            default:
                return invalidStateChange(nVar);
        }
    }
}
