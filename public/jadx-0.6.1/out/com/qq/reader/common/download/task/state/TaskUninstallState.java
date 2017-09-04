package com.qq.reader.common.download.task.state;

import com.qq.reader.common.download.task.TaskStateChangeException;
import com.qq.reader.common.download.task.n;

public class TaskUninstallState extends TaskState {
    private static final long serialVersionUID = 1;

    public TaskUninstallState() {
        super(TaskStateEnum.Uninstalled);
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
            default:
                return invalidStateChange(nVar);
        }
    }
}
