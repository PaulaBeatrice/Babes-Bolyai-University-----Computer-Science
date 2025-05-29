package Factory;

import Container.Container;
import Container.StackContainer;
import Container.Strategy;

public class TaskContainerFactory implements Factory{
    public TaskContainerFactory() {}

    public Container createContainer(Strategy strategy) {
        if (strategy == Strategy.LIFO) {
            return new StackContainer();
        }
        //TO DO FIFO
        return null;
    }
}
