/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainrunner.MergeSort;

class MergeSort_NonRecursive_Thread extends Thread {

    private int[] subArray;

    MergeSort_NonRecursive_Thread(int[] arr) {
        subArray = arr;
    }

    public void run() {
        mergeSort(subArray);
    }

    public int[] getArray() {
        return subArray;
    }

    public int[] mergeSort(int[] array) {
        int iteration = 0;
        int arrSize = (int) Math.pow(2, iteration);
        int numArrs = array.length;
        int[][] holder1 = new int[numArrs][arrSize];

// Initializing Holder1
        for (int a = 0; a < holder1.length; a++) {
            holder1[a][0] = array[a];
        }

        while (arrSize < array.length) {
            iteration++;
            arrSize = (int) Math.pow(2, iteration);
            numArrs = (int) Math.ceil(numArrs / 2.0);

// Creating holder2
            int[][] holder2 = new int[numArrs][];

            for (int a = 0; a < numArrs - 1; a++) {
                holder2[a] = new int[arrSize];
            }
            holder2[holder2.length - 1] = new int[array.length - ((numArrs - 1) * arrSize)];

            if (holder2.length == 1) {
                arrSize = array.length;
            }

// Merging holder1 into holder2
            int counter = 0;
            int count = 0;
            for (int a = 0; a < numArrs; a++) {
                int left = 0;
                int right = 0;
                for (int b = 0; b < holder2[a].length; b++) {
                    if ((counter + arrSize - b > array.length) && (array.length + b - counter <= arrSize / 2)) {
                        for (int c = 0; c < holder1[holder1.length - 1].length; c++) {
                            holder2[a][c] = holder1[holder1.length - 1][c];
                        }
                        break;
                    } else {
                        if ((right >= holder1[count + 1].length) || (left < holder1[count].length && holder1[count][left] < holder1[count + 1][right])) {
                            holder2[a][b] = holder1[count][left];
                            left++;
                        } else {
                            holder2[a][b] = holder1[count + 1][right];
                            right++;
                        }
                        counter++;
                    }
                }
                count += 2;
            }
            holder1 = holder2;
        }
        return holder1[0];
    }
}
