package kupusoglu.orhan.quicksort;

import java.util.Arrays;
import java.util.Random;

/**
 * Quicksort - sorts in-place, can handle duplicate values as well
 * <br>
 * @see <a href="https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme">Wikipedia - Quicksort - Hoare partition scheme</a>
 */
public class QuickSort {
    // PUBLIC INTERFACE - static factory methods
    public enum PIVOT_TYPE{
        LOW,
        MID,
        MEDIAN,
        HIGH,
        RANDOM
    };

    public static int[] sort(int[] arr) {
        QuickSort qs = new QuickSort(arr);
        qs.sort();
        return qs.arr;
    }

    public static int[] sort(int[] arr, PIVOT_TYPE pivot) {
        QuickSort qs = new QuickSort(arr, pivot);
        qs.sort();
        return qs.arr;
    }

    public static int[] sort(int[] arr, PIVOT_TYPE pivot, QuickSortMeta meta) {
        QuickSort qs = new QuickSort(arr, pivot);
        qs.setMeta(meta);
        qs.sort();
        return qs.arr;
    }


    // PRIVATE INTERFACE - called only by the static factory methods above
    private final int[] arr;
    private final int len;
    private final Random random;
    private PIVOT_TYPE pivot;
    private long partition;
    private long swap;
    private QuickSortMeta meta;


    private QuickSort(int[] arr) {
        if (arr == null) {
            this.arr = new int[0];
            this.len = -1;
        } else {
            this.arr = arr;
            this.len = arr.length;
        }
        this.pivot = PIVOT_TYPE.MEDIAN;
        this.random = new Random();
    }

    private QuickSort(int[] arr, PIVOT_TYPE pivot) {
        this(arr);
        this.pivot = pivot;
    }

    private void setMeta(QuickSortMeta meta) {
        this.meta = meta;
        meta.startTime();
        meta.step(Arrays.toString(arr)); // record the original array
        meta.step(" : [ lo - hi ] : pv : ix : sw"); // the first "hi" is the last index = len - 1
        meta.step("\n");
    }

    private void swap(int i, int j) {
        if (i != j) { // prevent unnecessary swaps
            swap++;
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    private int pivot(int lo, int hi) {
        int p = 0;

        switch(pivot) {
            case LOW:
                p = arr[lo];
                break;

            case MID:
                p = this.arr[lo + ((hi - lo) / 2)];
                break;

            case MEDIAN:
                int mid = lo + ((hi - lo) / 2);

                if (arr[mid] < arr[lo]) {
                    swap(lo, mid);
                } else if (arr[hi] < arr[lo]) {
                    swap(lo, hi);
                } else if (arr[mid] < arr[hi]) {
                    swap(mid, hi);
                }

                p = arr[hi];
                break;

            case HIGH:
                p = arr[hi - 1];
                break;

            case RANDOM:
                int r = this.random.nextInt(hi - lo + 1) + lo;
                p = this.arr[r];
        }

        return p;
    }

    /**
     * Hoare partition scheme
     * <br>
     * @param lo = starting index on the array
     * @param hi = ending index on the array
     * @return starting index of low elements > pivot
     */
    private int partition(int lo, int hi) {
        partition++;

        long sw = swap; // median swaps, too
        int pv = pivot(lo, hi); // value of the pivot element
        int i = lo - 1;
        int j = hi + 1;

        while (true) {
            do {
                i++;
            } while (arr[i] < pv);

            do {
                j--;
            } while (arr[j] > pv);

            if (i >= j) {
                break;
            }

            swap(i, j);
        }

        if (meta != null) {
            meta.step(Arrays.toString(arr));
            meta.step(" : [ ");
            meta.step(lo);
            meta.step(" - ");
            meta.step(hi);
            meta.step(" ] : ");
            meta.step(pv);
            meta.step(" : ");
            meta.step(j);
            meta.step(" : ");
            meta.step(swap - sw);
            meta.step("\n");
        }

        return j;
    }

    private void quickSort(int lo, int hi) {
        if (lo < hi) {
            int ix = partition(lo, hi);
            quickSort(lo, ix);
            quickSort(ix + 1, hi);
        }
    }

    private void sort() {
        if (len > 1) {
            quickSort(0, len - 1);
        }

        if (meta != null) {
            meta.endTime();
            meta.setPartition(partition);
            meta.setSwap(swap);
        }
    }
}
