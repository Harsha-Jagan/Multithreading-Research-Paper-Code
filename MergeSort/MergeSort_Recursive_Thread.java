/*
 *  Name    : Jagannathan Harshavardhan
 *  User ID : hxj4805
 *  Lab #   : MergeSort_NonRecursive_2
 */
package mainrunner.MergeSort;

class MergeSort_Recursive_Thread extends Thread {

    private int[] subArray;

    MergeSort_Recursive_Thread(int[] arr) {
        subArray = arr;
    }

    public void run() {
        mergeSort(subArray);
    }

    public int[] getArray() {
        return subArray;
    }

    public void mergeSort(int[] array) {
        if (array.length > 1) {
            int[] left = leftHalf(array);
            int[] right = rightHalf(array);
            try {
                mergeSort(left);
                mergeSort(right);
                merge(array, left, right);
            } catch (StackOverflowError | OutOfMemoryError e) {
                System.out.println(e);
            }

        }
    }

    public int[] leftHalf(int[] array) {
        int size1 = array.length / 2;
        int[] left = new int[size1];

        for (int i = 0; i < size1; i++) {
            left[i] = array[i];
        }

        return left;
    }

    public int[] rightHalf(int[] array) {
        int size1 = array.length / 2;
        int size2 = array.length - size1;
        int[] right = new int[size2];

        for (int i = 0; i < size2; i++) {
            right[i] = array[i + size1];
        }

        return right;
    }

    public void merge(int[] result, int[] left, int[] right) {
        int leftIdx = 0;
        int rightIdx = 0;

        for (int i = 0; i < result.length; i++) {
            if ((rightIdx >= right.length) || (leftIdx < left.length && left[leftIdx] <= right[rightIdx])) {
                result[i] = left[leftIdx];
                leftIdx++;
            } else {
                result[i] = right[rightIdx];
                rightIdx++;
            }
        }
    }
}
