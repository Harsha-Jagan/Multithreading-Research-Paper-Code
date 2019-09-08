/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainrunner.QuickSort;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author harsh
 */
public class QuickSort_NonRecursive_Thread extends Thread{
    
    private int[] subArray;

    QuickSort_NonRecursive_Thread(int[] arr) {
        subArray = arr;
    }

    public void run() {
        quickSort(subArray);
    }

    public int[] getArray() {
        return subArray;
    }

    public static void quickSort(int[] array) 
    {
        Deque<Integer> stack = new ArrayDeque<Integer>(array.length);
        int low, high;

        // push initial values of low and high to stack
        stack.push(0);
        stack.push(array.length - 1);

        // Keep popping from stack while is not empty
        while (!stack.isEmpty()) {
            high = stack.pop();
            low = stack.pop();

            // get the correct position of the pivot element
            int pivotIdx = partition(array, low, high);

            // Push left side on to  stack
            if (pivotIdx - 1 > low) {
                stack.push(low);
                stack.push(pivotIdx - 1);
            }

            // Push right side on to stack
            if (pivotIdx + 1 < high) {
                stack.push(pivotIdx + 1);
                stack.push(high);
            }
        }
    }

    public static int partition(int[] array, int low, int high) {

        int idx = low;
        int temp;

        // moves all elements <= to pivot value to the left
        for (int j = low; j < high; j++) {
            if (array[j] < array[high]) {
                temp = array[idx];
                array[idx] = array[j];
                array[j] = temp;
                idx++;
            }
        }

        // place pivot value at the correct index
        temp = array[idx];
        array[idx] = array[high];
        array[high] = temp;

        // return pivot index
        return idx;
    }
}
