import com.rreeves.sorting.MergeSort;
import com.rreeves.sorting.QuickSort;
import java.util.Random;

public class Sorter {

    static private int[] createRandomArray() {

        int[] arr = new int[99990];

        Random rand = new Random();

        for (int i = 0; i < arr.length; ++i) {
            arr[i] = rand.nextInt(500);
        }
        return arr;
    }

    static private void verify(int []arr) {
        int len = arr.length-1;

        boolean sorted = true;
        for (int i = 0; i < len && sorted; ++i) {
            sorted = arr[i] <= arr[i+1];
        }

        if (sorted) {
            System.out.println("Array is sorted");
        } else {
            System.out.println("Array isn't sorted");
        }
    }

    static public void main(String[] args) {

        QuickSort qs = new QuickSort();

        int []arr = createRandomArray();
        qs.sort(arr);
        verify(arr);

        MergeSort ms = new MergeSort();

        arr = createRandomArray();
        ms.sort(arr);
        verify(arr);
    }

}