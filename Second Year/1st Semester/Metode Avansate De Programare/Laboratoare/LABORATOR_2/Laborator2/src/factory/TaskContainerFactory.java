package factory;

import container.Container;
import Enum.ContainerStrategy;
import container.QueueContainer;
import container.StackContainer;

public class TaskContainerFactory implements Factory
{
    private TaskContainerFactory() {};
    //SINGLETON
    private static TaskContainerFactory instance = new TaskContainerFactory();

    public static TaskContainerFactory getInstance()
    {
        return instance;
    }


    @Override
    public Container createContainer(ContainerStrategy strategy) {
        if(strategy == ContainerStrategy.FIFO)
        {
            return new QueueContainer();
        }
        else if(strategy == ContainerStrategy.LIFO)
        {
            return new StackContainer();
        }
        return null;
    }
}
