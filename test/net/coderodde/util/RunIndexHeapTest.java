package net.coderodde.util;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;

public class RunIndexHeapTest {
    
    @Test
    public void test() {
        // 2, 7, 4, 5, 11, 3
        int[] runStartIndexArray = new int[]{ 1, 3, 10, 14, 19, 30, 33, -1, -1 };
        RunIndexHeap heap = new RunIndexHeap(runStartIndexArray, 6);
        
        while (heap.size() > 0) {
            System.out.println(heap.extractShortestRunIndex());
        }
//        assertEquals(6, heap.size());
//        assertEquals(0, heap.extractShortestRunIndex());
//        
//        assertEquals(5, heap.size());
//        assertEquals(2, heap.extractShortestRunIndex());
//        
//        assertEquals(4, heap.size());
//        assertEquals(4, heap.extractShortestRunIndex());
//        
//        assertEquals(3, heap.size());
//        assertEquals(5, heap.extractShortestRunIndex());
    }
}
