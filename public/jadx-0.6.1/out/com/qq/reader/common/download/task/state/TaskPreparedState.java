package com.qq.reader.common.download.task.state;

import com.qq.reader.common.download.task.TaskStateChangeException;
import com.qq.reader.common.download.task.n;

public class TaskPreparedState extends TaskState {
    private static final long serialVersionUID = 1;

    public TaskPreparedState() {
        super(TaskStateEnum.Prepared);
    }

    public TaskState stateChange(n nVar) throws TaskStateChangeException {
        switch (nVar.b()) {
            case Start:
                nVar.e().g(nVar.d());
                return new TaskStartedState();
            case Remove:
                nVar.e().e(nVar.d());
                return new TaskRemovedState();
            case Pause:
                nVar.e().j(nVar.d());
                return new TaskPausedState();
            case Deactivate:
                nVar.e().l(nVar.d());
                return new TaskDeactivePreparedState();
            case Err:
                nVar.e().k(nVar.d());
                return new TaskFailedState();
            default:
                return invalidStateChange(nVar);
        }
    }
}
