package runners;

import Model.Task;

public interface TaskRunner {
    void executeOneTask();
    void executeAll();
    void addTask(Task t);
    boolean hasTasks();
}
