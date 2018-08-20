package kupusoglu.orhan.quicksort;

import java.util.Arrays;
import java.util.Random;

/**
 * Quicksort - can handle duplicates
 * <br>
 * @see <a href="https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme">Wikipedia - Quicksort - Hoare partition scheme</a>
 * @see <a href="https://en.wikipedia.org/wiki/Dutch_national_flag_problem">Wikipedia - Dutch national flag problem</a>
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
        QuickSort qs = new QuickSort(arr, PIVOT_TYPE.LOW);
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
    private int partition;
    private int swap;
    private QuickSortMeta meta;


    private QuickSort(int[] arr) {
        if (arr == null) {
            this.arr = new int[0];
            this.len = -1;
        } else {
            this.arr = arr;
            this.len = arr.length;
        }
        this.pivot = PIVOT_TYPE.LOW;
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
        meta.step(" : [ lo - hi ] : pv : dnf[ lo - hi ]"); // the first "hi" is the last index = len - 1
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
                long sumMid = lo + hi;
                p = this.arr[(int)(sumMid / 2)];
                break;

            case MEDIAN:
                long sumMed = lo + hi;
                int mid = (int)(sumMed / 2);

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

    // inner class to pass index values: Dutch National Flag
    private class DNF {
        final int lo;
        final int hi;

        DNF(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }
    }

    /**
     * Partition by Dutch National Flag method by Edsger Dijkstra, "A Discipline of Programming", 1976
     * <br>
     * @param lo = starting index on the array
     * @param hi = ending index on the array
     * @return DNF.lo = index of low elements < pivot
     *         DNF.hi = index of high elements > pivot
     */
    private DNF partition(int lo, int hi) {
        partition++;

        int pv = pivot(lo, hi); // value of the pivot element
        int i = lo;
        int j = lo;
        int n = hi;

        while (j <=n) {
            if (arr[j] < pv) {
                swap(i, j);
                i++;
                j++;
            } else if (arr[j] > pv) {
                swap(j, n);
                n--;
            } else {
                j++;
            }
        }

        if (meta != null) {
            meta.step(Arrays.toString(arr));
            meta.step(" : [ ");
            meta.step(lo);
            meta.step(" - ");
            meta.step(hi);
            meta.step(" ] : ");
            meta.step(pv);
            meta.step(" : [ ");
            meta.step(i);
            meta.step(" - ");
            meta.step(j);
            meta.step(" ]");
            meta.step("\n");
        }

        return new DNF(i, j);
    }

    private void quickSort(int lo, int hi) {
        if (lo < hi) {
            DNF dnf = partition(lo, hi);
            quickSort(lo, dnf.lo - 1);
            quickSort(dnf.hi, hi);
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
