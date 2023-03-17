package model;

public class QuickSorter extends AbstractSorter{
    @Override
    public void sort(int[] numbers) {
        quickSort(numbers,0, numbers.length-1);
    }

    private int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin-1);
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                int aux = arr[i];
                arr[i] = arr[j];
                arr[j] = aux;
            }
        }
        int aux = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = aux;
        return i+1;
    }

    public void quickSort(int arr[], int begin, int end)
    {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }
}
