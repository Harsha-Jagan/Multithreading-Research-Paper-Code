/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainrunner.QuickSort;

public class QuickSort_Recursive_Thread extends Thread{
    private int[] subArray;
    private int st;
    private int en;

    QuickSort_Recursive_Thread(int[] arr, int s, int e) {
        subArray = arr;
        st = s;
        en = e;
    }

    public void run() {
        quickSort(subArray, st, en);
    }

    public int[] getArray() {
        return subArray;
    }

    public static void quickSort(int[] array, int start, int end) 
    {
        if(end-start > 0)
        {
            int pivot = array[end];
            int i = start - 1;
            int temp = 0;
            
            for(int j=start; j<end; j++)
            {
                if(array[j] <= pivot)
                {
                    i++;
                    temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }

            i++;
            temp = array[i];
            array[i] = array[end];
            array[end] = temp;
            try{
            quickSort(array, start, i-1);
            quickSort(array, i+1, end);
            }
            catch(StackOverflowError | OutOfMemoryError e)
            {
                System.out.println(e);
            }
        }
    }
}
