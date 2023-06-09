import Enum.ContainerStrategy;

public class Main {
    public static void main(String[] args)
    {
        TestRunner testRunner = new TestRunner();
        System.out.println("Cerinta 4");
        testRunner.runTask();
        ContainerStrategy strategy = ContainerStrategy.valueOf( args[0]);
        System.out.println("\nCerinta 10");
        testRunner.runTaskWithStrategy_10(strategy);
        System.out.println("\nCerinta 13");
        testRunner.runTaskWithStrategy_13(strategy);
        System.out.println("\nCerinta 14");
        testRunner.runTaskWithStrategy_14(strategy);
    }
}