package factory;

import Enum.ContainerStrategy;
import container.Container;

public interface Factory {
    public Container createContainer(ContainerStrategy strategy);
}
