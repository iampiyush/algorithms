package com.rreeves.sorting;

public class MergeSort {

    public void sort(int[] arr) {
        int []temp = new int[arr.length];
        recursiveSort(arr, temp, 0, arr.length-1);
    }

    /*
      Recursively divide the array and merge together. Uses a temp array as temporary buffer.
    */
    private void recursiveSort(int []arr, int []temp, int start, int end) {
        if (start == end)//one item in the list is sorted.
            return;

        int mid = (end - start) / 2 + start;

        //Recursively sort both halves
        recursiveSort(arr, temp, start, mid);
        recursiveSort(arr, temp, mid+1, end);

        //Merge together
        merge(arr, temp, start, mid, end);
    }

    /*
      Merges two array halves (start->mid, mid+1->end) using temp as a scratch array).
    */
    private void merge(int []arr, int[] temp, int start, int mid, int end) {

        int firstCurr = start;
        int firstEnd = mid;
        int secondCurr = mid+1;
        int secondEnd = end;
        int i = 0;

        while (firstCurr <= firstEnd && secondCurr <= secondEnd) {
            if (arr[firstCurr] < arr[secondCurr]) {
                temp[i++] = arr[firstCurr++];
            } else {
                temp[i++] = arr[secondCurr++];
            }
        }

        while (firstCurr <= firstEnd) {
            temp[i++] = arr[firstCurr++];
        }

        while (secondCurr <= secondEnd) {
            temp[i++] = arr[secondCurr++];
        }

        for (i = start; i <= end; ++i) {
            arr[i] = temp[i];
        }
    }
}