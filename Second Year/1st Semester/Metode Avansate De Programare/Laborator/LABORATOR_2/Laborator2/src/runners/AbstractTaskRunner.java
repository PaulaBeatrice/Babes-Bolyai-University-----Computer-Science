package runners;

import model.Task;

public abstract class AbstractTaskRunner implements TaskRunner
{
    protected TaskRunner runner;
    public AbstractTaskRunner(TaskRunner runner)
    {
        this.runner = runner;
    }

    public abstract void executeOneTask();
    public void executeAll()
    {
        while (runner.hasTask()) {
            executeOneTask();
        }
    }

    public void addTask(Task t)
    {
        runner.addTask(t);
    }

    public boolean hasTask()
    {
        return runner.hasTask();
    }
}
