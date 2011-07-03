package com.rreeves.sorting;

public class QuickSort {
    public void sort(int[] arr) {
        recursiveSort(arr, 0, arr.length-1);
    }

    private void swap(int []arr, int one, int two) {
        int temp = arr[one];

        arr[one] = arr[two];
        arr[two] = temp;
    }

    /*
      Partitions the array around pivot and recursively sorts each half.
    */
    private void recursiveSort(int []arr, int start, int end) {
        if (start > end)
            return;

        int pivot = partition(arr, start, end);
        recursiveSort(arr, start, pivot-1);
        recursiveSort(arr, pivot+1, end);
    }

    private int partition(int []arr, int start, int end) {

        int pivot = start; //TODO - randomize pivot value
        int leftMost = start;
        int unk = start+1;

        //Partitions the array where items less than or equal to pivot are in the first part of the array
        while (unk <= end) {
            if (arr[unk] <= arr[pivot]) {
                swap(arr, ++leftMost, unk);
            }
            unk++;
        }

        //Put pivot in final spot.
        swap(arr, leftMost, pivot);

        return leftMost;
    }
}