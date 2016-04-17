package net.coderodde.util;

import java.util.Arrays;

/**
 * This class implements merge heap sort.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Apr 17, 2016)
 */
public final class MergeHeapsort {

    public static void sort(Object[] array) {
        sort(array, 0, array.length);
    }
    
    public static void sort(Object[] array, int fromIndex, int toIndex) {
        if (toIndex - fromIndex < 2) {
            return;
        }
        
        ScanResult scanResult =
                scanInputRangeAndComputeStartingIndexArray(array,
                                                           fromIndex,
                                                           toIndex);
        
        if (scanResult.numberOfRuns == 1) {
            return;
        }
        
        
    }
    
    private static final class ScanResult {
        int[] runStartIndexArray;
        int numberOfRuns;
        
        ScanResult(int[] runStartIndexArray, int numberOfRuns) {
            this.runStartIndexArray = runStartIndexArray;
            this.numberOfRuns = numberOfRuns;
        } 
    }
    
    private static ScanResult
        scanInputRangeAndComputeStartingIndexArray(final Object[] array,
                                                   final int fromIndex,
                                                   final int toIndex) {
        final int rangeLength = toIndex - fromIndex;
        final int[] startingIndexArray = new int[rangeLength / 2 + 2];
        
        int numberOfRuns = 0;
        int head;
        int left  = fromIndex;
        int right = fromIndex + 1;
        boolean lastRunWasDescending = false;
        
        final int last = toIndex - 1; // The index of the very last element
                                      // in the input range.
        while (left < last) {
            head = left;
            
            if (((Comparable) array[left++]).compareTo(array[right++]) <= 0) {
                // Scan an ascending run.
                while (left < last 
                        && ((Comparable) array[left])
                              .compareTo(array[right]) <= 0) {
                    ++left;
                    ++right;
                }
                
                startingIndexArray[numberOfRuns] = head;
                
                if (lastRunWasDescending
                        && ((Comparable) array[head - 1])
                              .compareTo(array[head]) <= 0) {
                    startingIndexArray[numberOfRuns] = right;
                } else {
                    ++numberOfRuns;
                }
                
                lastRunWasDescending = false;
            } else {
                // Scan a strictly descending run.
                while (left < last
                        && ((Comparable) array[left])
                              .compareTo(array[right]) > 0) {
                    ++left;
                    ++right;
                }
                
                reverseRange(array, head, right);
                startingIndexArray[numberOfRuns] = head;
                
                if (lastRunWasDescending
                        && ((Comparable) array[head - 1])
                        .compareTo(array[head]) <= 0) {
                        startingIndexArray[numberOfRuns] = right;
                } else {
                    ++numberOfRuns;
                }
                
                lastRunWasDescending = true;
            }
            
            ++left;
            ++right;
        }
        
        if (left == last) {
            startingIndexArray[numberOfRuns++] = last;
        }
        
        // A sentinel component.
        startingIndexArray[numberOfRuns] = toIndex;
        return new ScanResult(startingIndexArray, numberOfRuns);
    }
        
    /**
     * Reverses the given array range {@code array[fromIndex],
     * array[fromIndex + 1], ..., array[toIndex - 1]}.
     * 
     * @param array      the target array.
     * @param fromIndex  the inclusive start index.
     * @param toIndex    the exclusive end index.
     */
    private static void reverseRange(final Object[] array, 
                                     int fromIndex, 
                                     int toIndex) {
        for (int left = fromIndex, right = toIndex - 1; 
                left < right; 
                ++left, --right) {
            swap(array, left, right);
        }
    }
    
    /**
     * Swaps the array components {@code array[index1]} and 
     * {@code array[index2]}.
     * 
     * @param array  the target array.
     * @param index1 the index of the first element.
     * @param index2 the index of the second element.
     */
    private static void swap(Object[] array, int index1, int index2) {
        Object tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    private MergeHeapsort() {}
}
