package com.qq.reader.common.download.task.state;

import com.qq.reader.common.download.task.TaskStateChangeException;
import com.qq.reader.common.download.task.n;
import java.io.Serializable;

public abstract class TaskState implements Serializable {
    private static final long serialVersionUID = 1;
    protected TaskStateEnum stateName;

    protected abstract TaskState stateChange(n nVar) throws TaskStateChangeException;

    public TaskState(TaskStateEnum taskStateEnum) {
        this.stateName = taskStateEnum;
    }

    public TaskStateEnum getStateName() {
        return this.stateName;
    }

    public void setStateName(TaskStateEnum taskStateEnum) {
        this.stateName = taskStateEnum;
    }

    protected TaskState invalidStateChange(n nVar) throws TaskStateChangeException {
        TaskStateChangeException taskStateChangeException = new TaskStateChangeException("Illegal State Change: state = " + getStateName() + ", action = " + nVar.b() + ", task = " + nVar.d());
        taskStateChangeException.setContext(nVar);
        throw taskStateChangeException;
    }

    public TaskState doStateChange(n nVar) throws TaskStateChangeException {
        long currentTimeMillis = System.currentTimeMillis();
        TaskState taskState = null;
        try {
            taskState = stateChange(nVar);
            nVar.a(true);
            nVar.a(System.currentTimeMillis() - currentTimeMillis);
            if (taskState != null) {
                nVar.a(taskState.stateName);
            }
            return taskState;
        } catch (TaskStateChangeException e) {
            nVar.a(false);
            throw e;
        } catch (Throwable e2) {
            nVar.a(false);
            throw new TaskStateChangeException(e2);
        } catch (Throwable th) {
            nVar.a(System.currentTimeMillis() - currentTimeMillis);
            if (taskState != null) {
                nVar.a(taskState.stateName);
            }
        }
    }

    public int hashCode() {
        return (this.stateName == null ? 0 : this.stateName.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TaskState taskState = (TaskState) obj;
        if (this.stateName == null) {
            if (taskState.stateName != null) {
                return false;
            }
            return true;
        } else if (this.stateName.equals(taskState.stateName)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "TaskState [stateName=" + this.stateName + "]";
    }
}
