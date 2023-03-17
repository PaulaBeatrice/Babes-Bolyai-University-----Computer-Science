package model;
import Enum.SortStrategy;

public abstract class AbstractSorter
{
    public abstract void sort(int[] numbers);
}
/*
    private int[] vector;

    public AbstractSorter(int[] vector) {
        this.vector = vector;
    }

    public abstract void sort();



    public void bubbleSort(int arr[])
    {

    }

    public void sort()
    {

        if(sortStrategy == SortStrategy.BUBBLESORT) {
            bubbleSort(vector);
        }
        else if(sortStrategy == SortStrategy.QUCIKSORT)
        {
            quickSort(vector, 0, vector.length);
        }
    }
 */