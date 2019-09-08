/*
 *  Name    : Jagannathan Harshavardhan
 *  User ID : hxj4805
 *  Lab #   : MainRunner 
 */
package mainrunner;

import mainrunner.InsertionSort.InsertionSort_NonRecursive;
import mainrunner.InsertionSort.InsertionSort_Recursive;
import mainrunner.MergeSort.MergeSort_NonRecursive;
import mainrunner.MergeSort.MergeSort_Recursive;
import mainrunner.QuickSort.QuickSort_NonRecursive;
import mainrunner.QuickSort.QuickSort_Recursive;

public class MainRunner {

    private static final int SIZE = 10000;
    private static int[] unsortedArr = new int[SIZE];

    static double[] mrCount = new double[3];
    static double[] mnCount = new double[3];
    static double[] qrCount = new double[3];
    static double[] qnCount = new double[3];
    static double[] irCount = new double[3];
    static double[] inCount = new double[3];

    public static void main(String[] args) throws InterruptedException {
        int trials = 1;
        for (int a = 0; a < trials; a++) {
            createArray();
            //MergeSorts
            //Recursive
            new MergeSort_Recursive(unsortedArr.clone());

            //NonRecursive
            new MergeSort_NonRecursive(unsortedArr.clone());

            //QuickSorts
            //Recursive
            new QuickSort_Recursive(unsortedArr.clone());
            //NonRecursive
            new QuickSort_NonRecursive(unsortedArr.clone());

            //InsertionSorts
            //Recursive
            new InsertionSort_Recursive(unsortedArr.clone());
            //NonRecursive
            new InsertionSort_NonRecursive(unsortedArr.clone());
        }

        //output
        for (int a = 0; a < 3; a++) {
            if (a == 2) {
                System.out.print("MergeSort Recursive 4 threaded version: ");
            } else {
                System.out.print("MergeSort Recursive " + (a + 1) + " threaded version: ");
            }
            System.out.println((mrCount[a] / trials) + " seconds");
        }
        System.out.println();
        for (int a = 0; a < 3; a++) {
            if (a == 2) {
                System.out.print("MergeSort Non-Recursive 4 threaded version: ");
            } else {
                System.out.print("MergeSort Non-Recursive " + (a + 1) + " threaded version: ");
            }
            System.out.println((mnCount[a] / trials) + " seconds");
        }
        System.out.println("\n");
        for (int a = 0; a < 3; a++) {
            if (a == 2) {
                System.out.print("QuickSort Recursive 4 threaded version: ");
            } else {
                System.out.print("QuickSort Recursive " + (a + 1) + " threaded version: ");
            }
            System.out.println((qrCount[a] / trials) + " seconds");
        }
        System.out.println();
        for (int a = 0; a < 3; a++) {
            if (a == 2) {
                System.out.print("QuickSort Non-Recursive 4 threaded version: ");
            } else {
                System.out.print("QuickSort Non-Recursive " + (a + 1) + " threaded version: ");
            }
            System.out.println((qnCount[a] / trials) + " seconds");
        }
        System.out.println("\n");
        for (int a = 0; a < 3; a++) {
            if (a == 2) {
                System.out.print("InsertionSort Recursive 4 threaded version: ");
            } else {
                System.out.print("InsertionSort Recursive " + (a + 1) + " threaded version: ");
            }
            System.out.println((irCount[a] / trials) + " seconds");
        }
        System.out.println();
        for (int a = 0; a < 3; a++) {
            if (a == 2) {
                System.out.print("InsertionSort Non-Recursive 4 threaded version: ");
            } else {
                System.out.print("InsertionSort Non-Recursive " + (a + 1) + " threaded version: ");
            }
            System.out.println((inCount[a] / trials) + " seconds");
        }
    }

    public static void createArray() {
        for (int i = 0; i < SIZE; i++) {
            unsortedArr[i] = i;
        }

        for (int i = 0; i < SIZE; i++) {
            int idx = (int) (Math.random() * unsortedArr.length);
            int temp = unsortedArr[i];
            unsortedArr[i] = unsortedArr[idx];
            unsortedArr[idx] = temp;
        }
    }

    public void record(double num, String type, int thread) {
        if (type.equals("ir")) {
            irCount[thread - 1] += num;
        } else if (type.equals("in")) {
            inCount[thread - 1] += num;
        } else if (type.equals("mr")) {
            mrCount[thread - 1] += num;
        } else if (type.equals("mn")) {
            mnCount[thread - 1] += num;
        } else if (type.equals("qr")) {
            qrCount[thread - 1] += num;
        } else if (type.equals("qn")) {
            qnCount[thread - 1] += num;
        }
    }
}
