/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainrunner.InsertionSort;

import mainrunner.MainRunner;
import java.lang.*;
import static mainrunner.MergeSort.MergeSort_NonRecursive.merge2;
import static mainrunner.MergeSort.MergeSort_NonRecursive.sortedArr;

public class InsertionSort_NonRecursive {
    public static int SIZE;
    public static int[] sortedArr;
    
    static String type = "in";
    static MainRunner obj = new MainRunner();
    
    public InsertionSort_NonRecursive(int[] array) throws InterruptedException
    {
        SIZE = array.length;
        sortedArr = new int[SIZE];
        
        oneThread(array.clone());
        twoThread(array.clone());
        fourThread(array.clone());
    }
    
    public static void oneThread(int[] array) throws InterruptedException
    {
        long startTime = System.nanoTime();
        InsertionSort_NonRecursive_Thread runner1_1 = new InsertionSort_NonRecursive_Thread(array);
        runner1_1.start();
    //wait unil thread is finished
        runner1_1.join();
        long endTime = System.nanoTime();

        obj.record(((endTime-startTime)/1000000000.0), type, 1);
    }
    
    public static void twoThread(int[] array) throws InterruptedException
    {
        long startTime = System.nanoTime();
        
        int[] subArr1 = new int[SIZE/2];
        int[] subArr2 = new int[SIZE - subArr1.length];
        
        System.arraycopy(array, 0, subArr1, 0, SIZE/2);
        System.arraycopy(array, SIZE/2, subArr2, 0, SIZE - (SIZE/2));
        
        InsertionSort_NonRecursive_Thread runner1_2 = new InsertionSort_NonRecursive_Thread(subArr1);
        InsertionSort_NonRecursive_Thread runner2_2 = new InsertionSort_NonRecursive_Thread(subArr2);

        runner1_2.start();
        runner2_2.start();
        
//wait unil both threads are finished
        runner1_2.join(); 
        runner2_2.join();

        merge2(runner1_2.getArray(), runner2_2.getArray());
        
    long endTime = System.nanoTime();
        
        obj.record(((endTime-startTime)/1000000000.0), type, 2);
    }
    
    public static void fourThread(int[] array) throws InterruptedException
    {
        long startTime = System.nanoTime();
        
        int[] subArr1 = new int[SIZE/4];
        int[] subArr2 = new int[SIZE/4];
        int[] subArr3 = new int[SIZE/4];
        int[] subArr4 = new int[array.length - (subArr1.length + subArr2.length + subArr3.length)];
        
        System.arraycopy(array, 0, subArr1, 0, subArr1.length);
        System.arraycopy(array, subArr1.length, subArr2, 0, subArr2.length);
        System.arraycopy(array, subArr1.length+subArr2.length, subArr3, 0, subArr3.length);
        System.arraycopy(array, subArr1.length+subArr2.length+subArr3.length, subArr4, 0, subArr4.length);
        
        InsertionSort_NonRecursive_Thread runner1_4 = new InsertionSort_NonRecursive_Thread(subArr1);
        InsertionSort_NonRecursive_Thread runner2_4 = new InsertionSort_NonRecursive_Thread(subArr2);
        InsertionSort_NonRecursive_Thread runner3_4 = new InsertionSort_NonRecursive_Thread(subArr3);
        InsertionSort_NonRecursive_Thread runner4_4 = new InsertionSort_NonRecursive_Thread(subArr4);
        
        runner1_4.start();
        runner2_4.start();
        runner3_4.start();
        runner4_4.start();
        
//wait unil threads are finished
        runner1_4.join(); 
        runner2_4.join();
        
        
        int[] temp1 = stepMerge(runner1_4.getArray(), runner2_4.getArray());
        
        runner3_4.join(); 
        runner4_4.join();

        int[] temp2 = stepMerge(runner3_4.getArray(), runner4_4.getArray());
        
        merge2(temp1,temp2);
        
    long endTime = System.nanoTime();
        
        obj.record(((endTime-startTime)/1000000000.0), type, 3);
    }
    
    public static void merge2(int[] left, int[] right)
    {
        int leftIdx = 0;
        int rightIdx = 0;
        
        for(int i=0; i<SIZE; i++)
        {
            if((rightIdx >= right.length) || (leftIdx < left.length && left[leftIdx] <= right[rightIdx]))
            {
                sortedArr[i] = left[leftIdx];
                leftIdx++;
            }
            else
            {
                sortedArr[i] = right[rightIdx];
                rightIdx++;
            }
        }
    }
    
    public static int[] stepMerge(int[] left, int[] right)
    {
        int leftIdx = 0;
        int rightIdx = 0;
        
        int s=0;
        if(left.length>right.length)
            s=left.length;
        else
            s=right.length;
        
        int[] output = new int[left.length+right.length];
        for(int i=0; i<s; i++)
        {
            if((rightIdx >= right.length) || (leftIdx < left.length && left[leftIdx] <= right[rightIdx]))
            {
                output[i] = left[leftIdx];
                leftIdx++;
            }
            else
            {
                output[i] = right[rightIdx];
                rightIdx++;
            }
        }
        return output;
    }
}