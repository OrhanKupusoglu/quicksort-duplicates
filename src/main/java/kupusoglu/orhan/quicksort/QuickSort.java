package kupusoglu.orhan.quicksort;

import java.util.Arrays;
import java.util.Random;

/**
 * Quicksort - sorts in-place, can handle duplicate values as well
 * <br>
 * @see <a href="https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme">Wikipedia - Quicksort - Hoare getPartition scheme</a>
 * @see <a href="https://en.wikipedia.org/wiki/Dutch_national_flag_problem">Wikipedia - Dutch national flag problem</a>
 */
public class QuickSort {
    private final int[] arr;
    private final int len;
    private final Random random;
    private PivotFactory pivotFactory;
    private Pivot pivot;
    private PartitionFactory partitionFactory;
    private Partition partition;
    private int numPartitions;
    private int numSwaps;
    private QuickSortMeta meta;

    public enum PIVOT_TYPE{
        LOW,
        MID,
        MEDIAN,
        HIGH,
        RANDOM
    };

    public enum PARTITION_TYPE{
        HOARE,
        DNF
    };

    public QuickSort(int[] arr) {
        if (arr == null) {
            this.arr = new int[0];
            this.len = -1;
        } else {
            this.arr = arr;
            this.len = arr.length;
        }

        this.random = new Random();
        this.pivotFactory = new PivotFactory();
        this.partitionFactory = new PartitionFactory();
    }

    public QuickSort(int[] arr, PIVOT_TYPE pivotType) {
        this(arr);
        this.pivot = this.pivotFactory.createPivot(pivotType);
    }

    public QuickSort(int[] arr, PIVOT_TYPE pivotType, PARTITION_TYPE partitionType) {
        this(arr, pivotType);
        this.partition = this.partitionFactory.createPartition(partitionType);
    }

    public void setMeta(QuickSortMeta meta) {
        this.meta = meta;
        meta.startTime();
        meta.step(Arrays.toString(arr)); // record the original array
        meta.step(this.partition.getHeadersLine()); // the first "hi" is the last index = len - 1
        meta.step("\n");
    }

    private void swap(int i, int j) {
        if (i != j) { // prevent unnecessary swaps
            numSwaps++;
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    private void quickSort(int lo, int hi) {
        if (lo < hi) {
            int[] ix = partition.getPartition(lo, hi);

            int lox;
            int hix;

            if (ix.length == 1) {
                lox = ix[0];
                hix = ix[0] + 1;
            } else {
                lox = ix[0] - 1;
                hix = ix[1];
            }

            quickSort(lo, lox);
            quickSort(hix, hi);
        }
    }

    public void sort() {
        if (pivot == null) {
            this.pivot = this.pivotFactory.createPivot(PIVOT_TYPE.MEDIAN);
        }

        if (partition == null) {
            partition = this.partitionFactory.createPartition(PARTITION_TYPE.DNF);
        }

        if (len > 1) {
            quickSort(0, len - 1);
        }

        if (meta != null) {
            meta.endTime();
            meta.setNumPartitions(numPartitions);
            meta.setNumSwaps(numSwaps);
        }
    }

    public int[] getArray() {
        return arr;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // INNER CLASSES
    // -----------------------------------------------------------------------------------------------------------------

    // FACTORY METHOD PATTERN - Pivots

    private interface Pivot {
        int getPivot(int lo, int hi);
    }

    private class PivotLow implements Pivot {
        @Override
        public int getPivot(int lo, int hi) {
            return arr[lo];
        }
    }

    private class PivotMid implements Pivot {
        @Override
        public int getPivot(int lo, int hi) {
            return arr[lo + ((hi - lo) / 2)];
        }
    }

    private class PivotMedian implements Pivot {
        @Override
        public int getPivot(int lo, int hi) {
            long sw = numSwaps;
            int mid = lo + ((hi - lo) / 2);

            if (arr[mid] < arr[lo]) {
                swap(lo, mid);
            } else if (arr[hi] < arr[lo]) {
                swap(lo, hi);
            } else if (arr[mid] < arr[hi]) {
                swap(mid, hi);
            }

            if (meta != null) {
                meta.step(Arrays.toString(arr));
                meta.step(" : median swaps : ");
                meta.step(numSwaps - sw);
                meta.step("\n");
            }

            return arr[hi];
        }
    }

    private class PivotHigh implements Pivot {
        @Override
        public int getPivot(int lo, int hi) {
            return arr[hi - 1];
        }
    }

    private class PivotRandom implements Pivot {
        @Override
        public int getPivot(int lo, int hi) {
            int r = random.nextInt(hi - lo + 1) + lo;
            return arr[r];
        }
    }

    private abstract class BasePivotFactory {
        public abstract Pivot createPivot(QuickSort.PIVOT_TYPE type);
    }

    private class PivotFactory extends BasePivotFactory {
        @Override
        public Pivot createPivot(PIVOT_TYPE type) {
            Pivot pivot;
            switch (type)
            {
                case LOW:
                    pivot = new PivotLow();
                    break;

                case MID:
                    pivot = new PivotMid();
                    break;

                case MEDIAN:
                    pivot = new PivotMedian();
                    break;

                case HIGH:
                    pivot = new PivotHigh();
                    break;

                case RANDOM:
                    pivot = new PivotRandom();
                    break;

                default: throw new IllegalArgumentException("No such Pivot: <" + type + ">");
            }

            return pivot;
        }
    }

    // FACTORY METHOD PATTERN - Partitions
    private interface Partition {
        int[] getPartition(int lo, int hi);
        String getHeadersLine();
    }

    private class PartitionHoare implements Partition {
        /**
         * Hoare partition scheme
         * <br>
         * @param lo = starting index on the array
         * @param hi = ending index on the array
         * @return starting index of low elements > pivot
         */
        @Override
        public int[] getPartition(int lo, int hi) {
            numPartitions++;

            int pv = pivot.getPivot(lo, hi); // value of the pivot element
            int i = lo - 1;
            int j = hi + 1;
            long sw = numSwaps; // median swaps, too

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
                meta.step(numSwaps - sw);
                meta.step("\n");
            }

            return new int[] {j};
        }

        @Override
        public String getHeadersLine() {
            return " : [ lo - hi ] : pv : ix : sw";
        }
    }

    private class PartitionDNF implements Partition {
        /**
         * DNF partition scheme
         * <br>
         * @param lo = starting index on the array
         * @param hi = ending index on the array
         * @return new lo and high values
         */
        @Override
        public int[] getPartition(int lo, int hi) {
            numPartitions++;

            int pv = pivot.getPivot(lo, hi); // value of the getPivot element
            int i = lo;
            int j = lo;
            int n = hi;
            long sw = numSwaps; // median swaps, too

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
                meta.step(" : ");
                meta.step(numSwaps - sw);
                meta.step("\n");
            }

            return new int[] {i, j};
        }

        @Override
        public String getHeadersLine() {
            return " : [ lo - hi ] : pv : dnf[ lo - hi ] : sw";
        }
    }

    private abstract class BasePartitionFactory {
        public abstract Partition createPartition(QuickSort.PARTITION_TYPE type);
    }

    private class PartitionFactory extends BasePartitionFactory {
        @Override
        public Partition createPartition(PARTITION_TYPE type) {
            Partition partition;
            switch (type)
            {
                case HOARE:
                    partition = new PartitionHoare();
                    break;

                case DNF:
                    partition = new PartitionDNF();
                    break;

                default: throw new IllegalArgumentException("No such Partition: <" + type + ">");
            }

            return partition;
        }
    }
}
