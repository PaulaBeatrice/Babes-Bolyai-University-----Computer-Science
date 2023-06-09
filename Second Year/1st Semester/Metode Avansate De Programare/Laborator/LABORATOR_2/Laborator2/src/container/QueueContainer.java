package container;

import model.Task;
import utils.Constants;

public class QueueContainer extends AbstractContainer{
    public QueueContainer()
    {
        tasks = new Task[Constants.INITIAL_TASK_SIZE];
        size = 0;
    }

    @Override
    public Task remove() {
        if(!isEmpty())
        {
            Task task = tasks[0];
            for(int i = 0; i < size - 1; i++)
                tasks[i] = tasks[i+1];
            size--;
            return task;
        }
        return null;
    }
}
