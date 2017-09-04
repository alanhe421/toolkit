package com.qq.reader.common.download.task.state;

import com.qq.reader.common.download.task.TaskStateChangeException;
import com.qq.reader.common.download.task.n;

public class TaskDeactivePreparedState extends TaskState {
    private static final long serialVersionUID = 1;

    public TaskDeactivePreparedState() {
        super(TaskStateEnum.DeactivePrepared);
    }

    protected TaskState stateChange(n nVar) throws TaskStateChangeException {
        switch (nVar.b()) {
            case Activate:
                nVar.e().n(nVar.d());
                return new TaskPreparedState();
            case Remove:
                nVar.e().e(nVar.d());
                return new TaskRemovedState();
            case Resume:
                nVar.e().n(nVar.d());
                return new TaskPreparedState();
            case Pause:
                nVar.e().j(nVar.d());
                return new TaskPausedState();
            default:
                return invalidStateChange(nVar);
        }
    }
}
