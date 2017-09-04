package com.qq.reader.common.download.task.state;

import com.qq.reader.common.download.task.TaskStateChangeException;
import com.qq.reader.common.download.task.n;

public class TaskFailedState extends TaskState {
    private static final long serialVersionUID = 1;

    public TaskFailedState() {
        super(TaskStateEnum.Failed);
    }

    protected TaskState stateChange(n nVar) throws TaskStateChangeException {
        switch (nVar.b()) {
            case Restart:
                nVar.e().d(nVar.d());
                return new TaskPreparedState();
            case Resume:
                nVar.e().h(nVar.d());
                return new TaskPreparedState();
            case Remove:
                nVar.e().e(nVar.d());
                return new TaskRemovedState();
            default:
                return invalidStateChange(nVar);
        }
    }
}
