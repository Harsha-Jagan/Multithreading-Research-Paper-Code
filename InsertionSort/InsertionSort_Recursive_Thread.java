/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainrunner.InsertionSort;

public class InsertionSort_Recursive_Thread extends Thread
{
    private int[] subArray;

    InsertionSort_Recursive_Thread(int[] arr) 
    {
        subArray = arr;
    }

    public void run() {
        insertionSort(subArray);
    }

    public int[] getArray() {
        return subArray;
    }

    public static int[] insertionSort(int[] array) 
    {
        int[] newArr = new int[array.length-1];
        for(int a=0; a<newArr.length; a++)
        {
            newArr[a] = array[a];
        }
        
        int last = array[array.length-1];
        
        if(newArr.length>1)
        {
            try{
                newArr = insertionSort(newArr);
            }
            catch(StackOverflowError | OutOfMemoryError x)
            {
                System.out.println(x);
            }
        }
        
        int[] answer = new int[newArr.length+1];
        
        int num = last;
        int i=0, a=0;
        boolean flag = true;
        while(i<newArr.length)
        {
            if(num<newArr[i] && flag)
            {
                answer[a] = num;
                a++;
                flag = false;
            }

            answer[a] = newArr[i];
            i++;
            a++;

            if(flag && i==newArr.length)
            {
                answer[a] = num;
            }
        }
        return answer;
    }
}