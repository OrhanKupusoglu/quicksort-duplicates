package kupusoglu.orhan.quicksort;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;


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
        {-2, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, -1}
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
        {-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}
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
        boolean isRandom = false;

        if ((arr == null) || (sarr == null)) {
            isRandom = true;
        }

        System.out.printf("\n%s\nQUICKSORT: %d x array[%d] - %s\n%s\n",
                          this.SEP, loop, len, name, this.SEP);

        for (QuickSort.PIVOT_TYPE pivotType : QuickSort.PIVOT_TYPE.values()) {
            long[] durations = new long[loop];

            System.out.printf("PIVOT TYPE: %s\n%s\n", pivotType, this.SEP);

            for (int j = 0; j < loop; j++) {
                if (isRandom) {
                    isRandom = true;
                    arr = new int[len];
                    Random random = new Random();

                    for (int k = 0; k < len; k++) {
                        int rnd = random.nextInt(len);
                        arr[k] = rnd;
                    }

                    sarr = arr.clone();
                    Arrays.sort(sarr);
                }

                System.out.printf("loop: #%d - %s - %s - sorted\n%s\n", (j + 1), name, pivotType, this.SEP);

                if (isRandom) {
                    System.out.printf("original:\n%s\n", Arrays.toString(arr));
                }

                long startTime = System.nanoTime();

                int[] sorted_arr = QuickSort.sort(arr == null ? null : (isRandom ? arr : arr.clone()), pivotType);
                long duration = System.nanoTime() - startTime;
                durations[j] = duration;

                if (isRandom) {
                    System.out.printf("sorted:\n%s\n%s\n", Arrays.toString(sorted_arr), this.SEP);
                }

                Assert.assertTrue("NOT sorted - " + name + " - array length = " + len,
                                  Arrays.equals(sorted_arr, sarr));

                System.out.printf("duration [%s]:\n%d\n\n",
                                  UNIT_TIME.NANO_SECOND.unit(), (duration / UNIT_TIME.NANO_SECOND.conversionFactor()));
            }

            pivotDurations.put(pivotType, durations);
        }

        System.out.printf("averages: %d x array[%d] - %s\n%s\n",
                          loop, len, name, this.SEP);

        System.out.printf("%12s | %-20s\n%s\n", "pivot type", "duration [ns]", this.SEP);
        for (QuickSort.PIVOT_TYPE pivotType : QuickSort.PIVOT_TYPE.values()) {
            double avg = Arrays.stream(pivotDurations.get(pivotType)).average().orElse(0.0);
            System.out.printf("%12s | %12.0f\n",
                              pivotType, (avg / UNIT_TIME.NANO_SECOND.conversionFactor()));
        }
    }

    @Test
    public void testSortBasic() {
        System.out.printf("\n%s\nQUICKSORT: basics\n%s\n\n",
                          this.SEP, this.SEP);

        for (QuickSort.PIVOT_TYPE pivot : QuickSort.PIVOT_TYPE.values()) {
            System.out.printf("%s\nPIVOT: %s\n%s\n", this.SEP, pivot, this.SEP);

            long startTime = System.nanoTime();

            for (int i = 0; i < this.ARRS_RAW.length; i++) {
                QuickSortMeta meta = new QuickSortMeta();

                int[] sorted_arr = QuickSort.sort(ARRS_RAW[i] == null ? null : ARRS_RAW[i].clone(), pivot, meta);

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

    @Test
    public void testSortOrdered() {
        int[] arr = new int[this.ARR_ARR_LEN];
        int[] sarr = new int[this.ARR_ARR_LEN];

        for (int i = 0; i < this.ARR_ARR_LEN; i++) {
            arr[i] = i + 1;
            sarr[i] = i + 1;
        }

        System.out.printf("\n%s\noriginal:\n%s\nsorted:\n%s\n%s\n",
                          this.SEP, Arrays.toString(arr), Arrays.toString(sarr), this.SEP);

        testSortArrays("ORDERED", this.ARR_TOTAL_LOOP, this.ARR_ARR_LEN, arr, sarr);
    }

    @Test
    public void testSortReverse() {
        int[] arr = new int[this.ARR_ARR_LEN];
        int[] sarr = new int[this.ARR_ARR_LEN];

        for (int i = 0; i < this.ARR_ARR_LEN; i++) {
            arr[i] = this.ARR_ARR_LEN - i;
            sarr[i] = i + 1;
        }

        System.out.printf("\n%s\noriginal:\n%s\nsorted:\n%s\n%s\n",
                          this.SEP, Arrays.toString(arr), Arrays.toString(sarr), this.SEP);

        testSortArrays("REVERSE", this.ARR_TOTAL_LOOP, this.ARR_ARR_LEN, arr, sarr);
    }

    @Test
    public void testSortOneOff() {
        int[] arr = new int[this.ARR_ARR_LEN];
        int[] sarr = new int[this.ARR_ARR_LEN];

        for (int i = 0; i < this.ARR_ARR_LEN - 1; i++) {
            arr[i] = i + 2;
            sarr[i] = i + 1;
        }
        arr[this.ARR_ARR_LEN - 1] = 1;
        sarr[this.ARR_ARR_LEN - 1] = this.ARR_ARR_LEN;

        System.out.printf("\n%s\noriginal:\n%s\nsorted:\n%s\n%s\n",
                this.SEP, Arrays.toString(arr), Arrays.toString(sarr), this.SEP);

        testSortArrays("ONEOFF", this.ARR_TOTAL_LOOP, this.ARR_ARR_LEN, arr, sarr);
    }

    @Test
    public void testSortShuffled() {
        int[] arr = new int[this.ARR_ARR_LEN];
        int[] sarr = new int[this.ARR_ARR_LEN];

        for (int i = 0; i < this.ARR_ARR_LEN; i++) {
            arr[i] = i + 1;
            sarr[i] = i + 1;
        }

        List<Integer> larr  = Arrays.stream(arr)
                                    .boxed()
                                    .collect(Collectors.toList());

        Collections.shuffle(larr);

        int[] xarr = larr.stream()
                         .mapToInt(Integer::valueOf)
                         .toArray();

        System.out.printf("\n%s\noriginal:\n%s\nsorted:\n%s\n%s\n",
                          this.SEP, Arrays.toString(xarr), Arrays.toString(sarr), this.SEP);

        testSortArrays("SHUFFLED", this.ARR_TOTAL_LOOP, this.ARR_ARR_LEN, xarr, sarr);
    }

    @Test
    public void testSortRandom() {
        testSortArrays("RANDOM", this.RND_TOTAL_LOOP, this.RND_ARR_LEN, null, null);
    }
}
