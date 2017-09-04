package com.qq.reader.common.download.task.state;

import com.qq.reader.common.download.task.TaskStateChangeException;
import com.qq.reader.common.download.task.n;

public class TaskStartedState extends TaskState {
    private static final long serialVersionUID = 1;

    public TaskStartedState() {
        super(TaskStateEnum.Started);
    }

    public TaskState stateChange(n nVar) throws TaskStateChangeException {
        switch (nVar.b()) {
            case Deactivate:
                nVar.e().m(nVar.d());
                return new TaskDeactivateStartedState();
            case Err:
                nVar.e().k(nVar.d());
                return new TaskFailedState();
            case Finish:
                nVar.e().i(nVar.d());
                return new TaskFinishedState();
            case Pause:
                nVar.e().j(nVar.d());
                return new TaskPausedState();
            case Receive:
                return this;
            case Remove:
                nVar.e().e(nVar.d());
                return new TaskRemovedState();
            default:
                return invalidStateChange(nVar);
        }
    }
}
