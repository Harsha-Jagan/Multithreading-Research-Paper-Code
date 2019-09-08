/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainrunner.InsertionSort;

public class InsertionSort_NonRecursive_Thread extends Thread{
    private int[] subArray;

    InsertionSort_NonRecursive_Thread(int[] arr) 
    {
        subArray = arr;
    }

    public void run() {
        insertionSort(subArray);
    }

    public int[] getArray() {
        return subArray;
    }

    public static void insertionSort(int[] array) 
    {
        for(int a=1; a<array.length; a++)
        {
            int c = a;
            for(int b=a-1; b>=0; b--)
            {
                if(array[b] > array[c])
                {
                    int temp = array[c];
                    array[c] = array[b];
                    array[b] = temp;
                    c--;
                }
                else
                {
                    break;
                }
            }
        }
    }
}