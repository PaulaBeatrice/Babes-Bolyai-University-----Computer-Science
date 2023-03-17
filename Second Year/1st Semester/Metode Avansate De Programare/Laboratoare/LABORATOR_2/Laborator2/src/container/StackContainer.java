package container;

import model.Task;
import utils.Constants;

public class StackContainer extends AbstractContainer{
    public StackContainer()
    {
        this.tasks = new Task[Constants.INITIAL_TASK_SIZE];
        this.size = 0;
    }

    @Override
    public Task remove() {
        if(!isEmpty())
        {
            --this.size;
            return this.tasks[this.size];
        }
        return null;
    }
}