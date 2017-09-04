package com.qq.reader.common.download.task.state;

import com.qq.reader.common.download.task.TaskStateChangeException;
import com.qq.reader.common.download.task.n;

public class TaskRemovedState extends TaskState {
    private static final long serialVersionUID = 1;

    public TaskRemovedState() {
        super(TaskStateEnum.Removed);
    }

    protected TaskState stateChange(n nVar) throws TaskStateChangeException {
        return invalidStateChange(nVar);
    }
}
