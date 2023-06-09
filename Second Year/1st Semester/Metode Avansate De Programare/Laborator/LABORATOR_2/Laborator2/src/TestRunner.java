import model.MessageTask;
import runners.DelayTaskRunner;
import runners.PrinterTaskRunner;
import runners.StrategyTaskRunner;

import java.time.LocalDateTime;
import Enum.ContainerStrategy;
import runners.TaskRunner;

public class TestRunner {
    private static MessageTask[] getMessages() {
        MessageTask temaLab = new MessageTask("1",
                "doua probleme",
                "pentru seminar","Mihai","Florentin", LocalDateTime.now());

        MessageTask temaSeminar = new MessageTask("2",
                "trei probleme","pentru laborator","Andrei","Andreea",LocalDateTime.now());

        MessageTask temaCurs = new MessageTask("3",
                "patru probleme","pentru curs", "Diana", "Paula", LocalDateTime.now());

        MessageTask temaCurs2 = new MessageTask("4",
                "doua probleme","pentru curs", "Ana", "Ion", LocalDateTime.now());

        MessageTask temaLab2 = new MessageTask("5",
                "cinci probleme","pentru laborator", "Elena", "Mary", LocalDateTime.now());
        return new MessageTask[]{temaLab, temaSeminar, temaCurs, temaCurs2, temaLab2};
    }

    public static void runTask()
    {
        MessageTask[] messages = getMessages();
        for (MessageTask message : messages) {
            message.execute();
        }
    }

    public static  void runStrategyTaskRunner(ContainerStrategy strategy , MessageTask[] messages)
    {
        TaskRunner taskRunner = new StrategyTaskRunner(strategy);
        taskRunner.addTask(messages[0]);
        taskRunner.addTask(messages[1]);
        taskRunner.addTask(messages[2]);
        taskRunner.executeAll();
    }

    public static void runPrinterTaskRunner(ContainerStrategy strategy, MessageTask[] messages)
    {
        PrinterTaskRunner printerTaskRunner = new PrinterTaskRunner(new StrategyTaskRunner(strategy));
        printerTaskRunner.addTask(messages[0]);
        printerTaskRunner.addTask(messages[1]);
        printerTaskRunner.addTask(messages[2]);
        printerTaskRunner.executeAll();
    }

    public static void runDelayTaskRunner(ContainerStrategy strategy, MessageTask[] messages)
    {
        DelayTaskRunner delayTaskRunner = new DelayTaskRunner(new StrategyTaskRunner(strategy));
        delayTaskRunner.addTask(messages[0]);
        delayTaskRunner.addTask(messages[1]);
        delayTaskRunner.addTask(messages[2]);
        delayTaskRunner.executeAll();
    }

    public static void runTaskWithStrategy_10(ContainerStrategy strategy )
    {
       //StrategyTaskRunner
        MessageTask[] messages = getMessages();
        runStrategyTaskRunner(strategy, messages);
    }

    public static void runTaskWithStrategy_13(ContainerStrategy strategy)
    {
        //StrategyTaskRunner + Printer TaskRunner
        MessageTask[] messages = getMessages();
        runStrategyTaskRunner(strategy, messages);
        runPrinterTaskRunner(strategy, messages);
    }

    public static void runTaskWithStrategy_14(ContainerStrategy strategy)
    {
        //StrategyTaskRunner + Delay + Printer TaskRunner
        MessageTask[] messages = getMessages();
        runStrategyTaskRunner(strategy, messages);
        runDelayTaskRunner(strategy, messages);
        runPrinterTaskRunner(strategy, messages);
    }
}
