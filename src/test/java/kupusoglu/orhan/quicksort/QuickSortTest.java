package kupusoglu.orhan.quicksort;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.*;
import java.util.stream.Collectors;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuickSortTest {
    private enum UNIT_TIME {
        NANO_SECOND(1, "ns"),
        MILLI_SECOND(1_000_000, "ms");

        private final int conversionFactor;
        private final String unit;

        UNIT_TIME(int conversionFactor, String unit) {
            this.conversionFactor = conversionFactor;
            this.unit = unit;
        }

        public int conversionFactor() {
            return this.conversionFactor;
        }

        public String unit() {
            return this.unit;
        }
    };

    private final String SEP = String.join("", Collections.nCopies(80, "-"));
    private final int[][] ARRS_RAW = {
        null,
        {},
        {1},
        {2, 1},
        {1, 2, 3},
        {3, 2, 1},
        {1, 1, 1, 1}, // duplicates!
        {1, 3, 3, 3, 3, 2},
        {1, 2, 3, 4, 2, 1, 1},
        {1_000_000, 10_001, 10, 30, 40, 50, 1, 999},
        {1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4},
        {4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1},
        {1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1},
        {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
        {2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2},
        {2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2},
        {100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100},
        {100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2},
        {16, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16}
    };
    private final int[][] ARRS_ORD = {
        {},
        {},
        {1},
        {1, 2},
        {1, 2, 3},
        {1, 2, 3},
        {1, 1, 1, 1},
        {1, 2, 3, 3, 3, 3},
        {1, 1, 1, 2, 2, 3, 4},
        {1, 10, 30, 40, 50, 999, 10_001, 1_000_000},
        {1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4},
        {1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4},
        {1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4},
        {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
        {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100},
        {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16}
    };
    private final int ARR_TOTAL_LOOP = 10;
    private final int ARR_ARR_LEN = 100;
    private final int RND_TOTAL_LOOP = 10;
    private final int RND_ARR_LEN = 100;


    /**
     * Sorts a given array and compares with the sorted array in a loop and displays the average durations
     * <br>
     * @param name name of the test
     * @param loop number of loops
     * @param len length of the array
     * @param arr array to be sorted, if null an array of length len will be filled with random values
     * @param sarr sorted array to be compared with, if null it will be created from arr
     */
    public void testSortArrays(String name, int loop, int len, int[] arr, int[] sarr) {
        Map<QuickSort.PIVOT_TYPE, long[]> pivotDurations = new HashMap<>();

        System.out.printf("\n\n%s\nQUICKSORT: %d x array[%d] - %s\n%s\n",
                          this.SEP, loop, len, name, this.SEP);

        System.out.printf("original:\n%s\nsorted:\n%s\n",
                Arrays.toString(arr), Arrays.toString(sarr));

        for (QuickSort.PARTITION_TYPE partitionType : QuickSort.PARTITION_TYPE.values()) {
            for (QuickSort.PIVOT_TYPE pivotType : QuickSort.PIVOT_TYPE.values()) {
                System.out.printf("\nname: %s - partition: %s - pivot: %s\n%s\n",
                                  name, partitionType, pivotType, this.SEP);

                long[] durations = new long[loop];

                for (int j = 0; j < loop; j++) {
                    long startTime = System.nanoTime();

                    QuickSort quicksort = new QuickSort(arr == null ? null : arr.clone(), pivotType, partitionType);
                    quicksort.sort();
                    int[] sorted_arr = quicksort.getArray();

                    long duration = System.nanoTime() - startTime;
                    durations[j] = duration;

                    Assert.assertTrue("NOT sorted - " + name + " - array length = " + len,
                                      Arrays.equals(sorted_arr, sarr));

                    System.out.printf("loop: #%d - %s - duration [%s]: %d\n",
                                      j, pivotType, UNIT_TIME.NANO_SECOND.unit(), (duration / UNIT_TIME.NANO_SECOND.conversionFactor()));
                }

                pivotDurations.put(pivotType, durations);
            }

            System.out.printf("\nname: %s - partition: %s - averages: %d x array[%d]\n%s\n",
                              name, partitionType, loop, len, this.SEP);

            System.out.printf("%12s | %-20s\n%s\n", "pivot", "duration [ns]", this.SEP);
            for (QuickSort.PIVOT_TYPE pivotType : QuickSort.PIVOT_TYPE.values()) {
                double avg = Arrays.stream(pivotDurations.get(pivotType)).average().orElse(0.0);
                System.out.printf("%12s | %12.0f\n",
                                  pivotType, (avg / UNIT_TIME.NANO_SECOND.conversionFactor()));
            }
        }
    }

    @Test
    public void test1SortBasic() {
        //System.out.printf("nQUICKSORT: basics\n");
        System.out.printf("\n\n%s\nQUICKSORT: basics\n", this.SEP);

        for (QuickSort.PARTITION_TYPE partitionType : QuickSort.PARTITION_TYPE.values()) {
            for (QuickSort.PIVOT_TYPE pivotType : QuickSort.PIVOT_TYPE.values()) {
                long startTime = System.nanoTime();

                for (int i = 0; i < this.ARRS_RAW.length; i++) {
                    QuickSortMeta meta = new QuickSortMeta();
                    QuickSort quicksort = new QuickSort(ARRS_RAW[i] == null ? null : ARRS_RAW[i].clone(), pivotType, partitionType);
                    quicksort.setMeta(meta);
                    quicksort.sort();
                    int[] sorted_arr = quicksort.getArray();

                    System.out.printf("\npartition: %s - pivot: %s\n%s\n",
                                      partitionType, pivotType, this.SEP);

                    System.out.print(meta.display());

                    Assert.assertTrue("NOT sorted: #" + i + " : " + Arrays.toString(sorted_arr),
                                      Arrays.equals(sorted_arr, this.ARRS_ORD[i]));
                }

                long endTime = System.nanoTime();
                long duration = (endTime - startTime);

                System.out.printf("total duration [%s]:\n%d\n\n",
                                  UNIT_TIME.NANO_SECOND.unit(), (duration / UNIT_TIME.NANO_SECOND.conversionFactor()));
            }
        }
    }

    @Test
    public void test2SortOrdered() {
        int[] arr = new int[this.ARR_ARR_LEN];
        int[] sarr = new int[this.ARR_ARR_LEN];

        for (int i = 0; i < this.ARR_ARR_LEN; i++) {
            arr[i] = i + 1;
            sarr[i] = i + 1;
        }

        testSortArrays("ORDERED", this.ARR_TOTAL_LOOP, this.ARR_ARR_LEN, arr, sarr);
    }

    @Test
    public void test3SortReverse() {
        int[] arr = new int[this.ARR_ARR_LEN];
        int[] sarr = new int[this.ARR_ARR_LEN];

        for (int i = 0; i < this.ARR_ARR_LEN; i++) {
            arr[i] = this.ARR_ARR_LEN - i;
            sarr[i] = i + 1;
        }

        testSortArrays("REVERSE", this.ARR_TOTAL_LOOP, this.ARR_ARR_LEN, arr, sarr);
    }

    @Test
    public void test4SortOneOff() {
        int[] arr = new int[this.ARR_ARR_LEN];
        int[] sarr = new int[this.ARR_ARR_LEN];

        for (int i = 0; i < this.ARR_ARR_LEN - 1; i++) {
            arr[i] = i + 2;
            sarr[i] = i + 1;
        }
        arr[this.ARR_ARR_LEN - 1] = 1;
        sarr[this.ARR_ARR_LEN - 1] = this.ARR_ARR_LEN;

        testSortArrays("ONEOFF", this.ARR_TOTAL_LOOP, this.ARR_ARR_LEN, arr, sarr);
    }

    @Test
    public void test5SortShuffled() {
        int[] arr = new int[this.ARR_ARR_LEN];
        int[] sarr = new int[this.ARR_ARR_LEN];

        for (int i = 0; i < this.ARR_ARR_LEN; i++) {
            arr[i] = i + 1;
            sarr[i] = i + 1;
        }

        // to List with <Integer>
        List<Integer> larr  = Arrays.stream(arr)
                                    .boxed()
                                    .collect(Collectors.toList());

        // shuffle requires list with Generics
        Collections.shuffle(larr);

        // back to Array
        int[] xarr = larr.stream()
                         .mapToInt(Integer::valueOf)
                         .toArray();

        testSortArrays("SHUFFLED", this.ARR_TOTAL_LOOP, this.ARR_ARR_LEN, xarr, sarr);
    }

    @Test
    public void test6SortRandom() {
        int[] arr = new int[this.ARR_ARR_LEN];
        int[] sarr;

        Random random = new Random();

        for (int k = 0; k < this.ARR_ARR_LEN; k++) {
            int rnd = random.nextInt(this.ARR_ARR_LEN);
            arr[k] = rnd;
        }

        sarr = arr.clone();
        Arrays.sort(sarr);

        testSortArrays("RANDOM", this.RND_TOTAL_LOOP, this.RND_ARR_LEN, arr, sarr);
    }
}
