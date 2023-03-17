package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSorterTest {

    @Test
    void sort() {
        int[] numbers = {2,3,6,1,5};
        BubbleSorter bubbleSorter = new BubbleSorter();
        bubbleSorter.sort(numbers);
        assert(numbers[0] == 1);
        assert(numbers[1] == 2);
        assert(numbers[2] == 3);
        assert(numbers[3] == 5);
        assert(numbers[4] == 6);
    }
}