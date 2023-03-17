package model;
import Enum.SortStrategy;

public class SortingTask extends Task
{
    private AbstractSorter abstractSorter;
    private int[] numbers;
    private SortStrategy sortStrategy;

    public AbstractSorter getAbstractSorter()
    {
        return abstractSorter;
    }

    public SortingTask(String taskId, String description, int[] numbers, SortStrategy sortStrategy)
    {
        super(taskId, description);
        this.numbers = numbers;
        this.sortStrategy = sortStrategy;

        switch (sortStrategy) {
            case BUBBLESORT -> abstractSorter = new BubbleSorter();
            case QUCIKSORT -> abstractSorter = new QuickSorter();
            default -> {
            }
        }
    }

    public void execute()
    {
        abstractSorter.sort(numbers);
    }
}
