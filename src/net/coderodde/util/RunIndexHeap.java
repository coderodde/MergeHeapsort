package net.coderodde.util;

/**
 * This class implements a minimum binary heap for holding the run indices. For 
 * each run, we put its index to this heap and specify its length as a priority 
 * key.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Apr 17, 2016)
 */
final class RunIndexHeap {
    
    private final int[] runLengthArray;
    private final int[] runIndexArray;
    private int size;
    
    RunIndexHeap(final int[] runStartIndexArray, final int size) {
        this.runLengthArray = new int[size];
        this.runIndexArray  = new int[size];
        this.size = size;
        
        for (int i = 0; i < size; ++i) {
            runLengthArray[i] = runStartIndexArray[i + 1] - 
                                runStartIndexArray[i];
            runIndexArray[i] = i;
        }
        
        buildMinHeap();
    }
    
    int size() {
        return size;
    }
    
    int extractShortestRunIndex() {
        final int shortestRunIndex = runIndexArray[0];
        runIndexArray [0] = runIndexArray[size - 1];
        runLengthArray[0] = runLengthArray[--size]; 
        siftDown(0);
        return shortestRunIndex;
    }
    
    private void buildMinHeap() {
        for (int i = (size / 2); i >= 0; --i) {
            siftDown(i);
        }
    }
    
    private static int left(int index) {
        return (index << 1) + 1;
    }
    
    private void siftDown(int index) {
        int leftChildIndex  = left(index);
        int rightChildIndex = leftChildIndex + 1;
        int minChildIndex   = index;
        int targetRunLength = runLengthArray[index];
        int targetRunIndex  = runIndexArray [index];
        
        while (true) {
            if (leftChildIndex < size
                    && runLengthArray[leftChildIndex] < targetRunLength) {
                minChildIndex = leftChildIndex;
            }
            
            if (minChildIndex == index) {
                if (rightChildIndex < size
                        && runLengthArray[rightChildIndex] < targetRunLength) {
                    minChildIndex = rightChildIndex;
                }
            } else if (rightChildIndex < size
                    && runLengthArray[rightChildIndex] <
                       runLengthArray[leftChildIndex]) {
                minChildIndex = rightChildIndex;
            }
            
            if (minChildIndex == index) {
                runLengthArray[minChildIndex] = targetRunLength;
                runIndexArray [minChildIndex] = targetRunIndex;
                return;
            }
            
            runLengthArray[index] = runLengthArray[minChildIndex];
            runIndexArray [index] = runIndexArray [minChildIndex];
            index = minChildIndex;
            leftChildIndex = left(index);
            rightChildIndex = leftChildIndex + 1;
        }
    }
}
