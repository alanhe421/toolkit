package com.qq.reader.common.download.task.state;

import com.qq.reader.common.download.task.TaskStateChangeException;
import com.qq.reader.common.download.task.n;

public class TaskFinishedState extends TaskState {
    private static final long serialVersionUID = 1;

    public TaskFinishedState() {
        super(TaskStateEnum.Finished);
    }

    public TaskState stateChange(n nVar) throws TaskStateChangeException {
        switch (nVar.b()) {
            case Restart:
                nVar.e().d(nVar.d());
                return new TaskPreparedState();
            case Remove:
                nVar.e().e(nVar.d());
                return new TaskRemovedState();
            case Install:
                nVar.e().s(nVar.d());
                return new TaskInstallingState();
            default:
                return invalidStateChange(nVar);
        }
    }
}
