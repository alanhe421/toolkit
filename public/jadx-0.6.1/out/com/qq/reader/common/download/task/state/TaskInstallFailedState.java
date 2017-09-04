package com.qq.reader.common.download.task.state;

import com.qq.reader.common.download.task.TaskStateChangeException;
import com.qq.reader.common.download.task.n;

public class TaskInstallFailedState extends TaskState {
    private static final long serialVersionUID = 1;

    public TaskInstallFailedState() {
        super(TaskStateEnum.InstallFailed);
    }

    protected TaskState stateChange(n nVar) throws TaskStateChangeException {
        switch (nVar.b()) {
            case Remove:
                nVar.e().e(nVar.d());
                return new TaskRemovedState();
            case Install:
                nVar.e().s(nVar.d());
                return new TaskInstallingState();
            case Restart:
                nVar.e().d(nVar.d());
                return new TaskPreparedState();
            case InstallComplete:
                nVar.e().q(nVar.d());
                return new TaskInstallCompletedState();
            case Uninstall:
                nVar.e().f(nVar.d());
                return new TaskUninstallState();
            default:
                return invalidStateChange(nVar);
        }
    }
}
