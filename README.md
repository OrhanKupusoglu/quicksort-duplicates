# Quicksort

The [Quicksort](https://en.wikipedia.org/wiki/Quicksort) algorithm for sorting arrays is invented by Sir [Tony Hoare](https://en.wikipedia.org/wiki/Tony_Hoare) in 1959. **Quicksort** is a popular choice among sorting algorithms, and widely used in many programming languages.

As of Java 8, JDK uses an implementation of the [Dual Pivot Quicksort](http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/tip/src/share/classes/java/util/DualPivotQuicksort.java) for primitive types, and an implementation of [Timsort](https://en.wikipedia.org/wiki/Timsort) for object types. This duality is caused by the Java [type system](https://en.wikipedia.org/wiki/Type_system), where for performance reasons the Java designers kept generic types with no identity in parallel with the new object types which have [identity](https://docs.oracle.com/javase/8/docs/api/java/util/Objects.html#equals-java.lang.Object-java.lang.Object-).

&nbsp;

## Overview

Since its inception, Quicksort has been a benchmark among the sorting algortihtms. An excellent overview can be found in [Algorithms, 4th Edition](https://algs4.cs.princeton.edu/23quicksort/) and [PDF presentation](https://www.cs.princeton.edu/courses/archive/fall12/cos226/lectures/23Quicksort.pdf)  by Professor [Robert Sedgewick](https://www.cs.princeton.edu/people/profile/rs).

[Wikipedia](https://en.wikipedia.org/wiki/Quicksort) explains the algorithm as:

> Quicksort is a divide and conquer algorithm. Quicksort first divides a large array into two smaller sub-arrays: the low elements and the high elements. Quicksort can then recursively sort the sub-arrays.

> The steps are:
>
> 1. Pick an element, called a **pivot**, from the array.
> 2. **Partitioning**: reorder the array so that all elements with values less than the pivot come before the pivot, while all elements with values greater than the pivot come after it (equal values can go either way). After this partitioning, the pivot is in its final position. This is called the partition operation.
> 3. Recursively apply the above steps to the sub-array of elements with smaller values and separately to the sub-array of elements with greater values.

### Stability

Quicksort is **not** a [stable sorting algorithm](https://en.wikipedia.org/wiki/Sorting_algorithm#Stability), meaning array elements may be moved in the input array, even when they could have kept their positions. Stability is a desirable feature, and this is the main reason for why Quicksort is not selected to order object types. With primitive types an unstable sorting algorithm has still value if it performs well.


### Complexity

The following values summarizes in the [Big-O Notation](https://en.wikipedia.org/wiki/Big_O_notation) the characteristics of the Qulcksort algorithm.
For comparison to other sorting algorithms please refer to [Sorting Algorithms - Comparison](https://en.wikipedia.org/wiki/Sorting_algorithm#Comparison_of_algorithms).

| TIME COMPLEXITY  | VALUE      |
| :--------------- | ---------- |
| Worst-case       | O(n*n)     |
| Best-case        | O(n log n) |
| Average-case     | O(n log n) |

| SPACE COMPLEXITY  | VALUE      |
| :---------------- | ---------- |
| Worst-case        | O(n)       |
| Average-case      | O(n log n) |

&nbsp;

## Variants

The algorithm depends upon the selection of the **pivot** and **partitioning** for efficient sorting.

### Pivot Selection

Given a range of **[lo, hi]** index values, in this implementation the **pivot element** can be chosen in five different ways:

| PIVOT    | SELECTION                                                  |
| :------- | ---------------------------------------------------------- |
| Low      | Select the first index in the given range: **lo**          |
| Mid      | Select the **mid** index: (lo + hi) / 2                    |
| Median   | Select the **median** of [lo, mid, hi]                     |
| High     | Select the next to last index: **hi - 1**                  |
| Random   | Select a random index: **random[lo, hi]**                  |

For the Median pivot selection, see [Choice of pivot](https://en.wikipedia.org/wiki/Quicksort#Choice_of_pivot).

### Partition Selection

Quicksort's partitioning swaps elements of array relative to the pivot element.

In this implementation the **DNF**, [Dutch National Flag](https://en.wikipedia.org/wiki/Dutch_national_flag_problem) method by [Edsger Dijkstra](https://en.wikipedia.org/wiki/Edsger_Dijkstra) is used.

&nbsp;

## Build

The project can be both built with [Apache Maven](https://maven.apache.org/) or [Google Bazel](https://bazel.build/). For further information about the build tools, please refer to the [Bazelize Maven Pulgin](https://github.com/OrhanKupusoglu/bazelize-maven-plugin).

### Maven
Commands for a typical Maven build is given below:

```
$ mvn clean install
```
### Bazel

Commands for a typical Maven build is given below:

```
$ ./bazelize.sh

$ bazel build

$ bazel test ... --test_output all
```

&nbsp;

## Test Results

There are six test cases:

|  #  | TEST CASE | EXPLANATION                                         |
| --- | --------- | :-------------------------------------------------- |
| 1   | Basics    | Intented to test the basics of the implementation, it displays details at each run. |
| 2   | Ordered   | Ordered arrays [1..N] are sorted M times            |
| 3   | Reversed  | Reverse ordered arrays [N..1] are sorted M times    |
| 4   | One-Off   | One-off arrays [2..N,1] are sorted M times          |
| 5   | Shuffled  | Shuffled arrays [1..N] are sorted M times           |
| 6   | Random    | At each run random values fill the arrays M times   |

A summary of a typical test run is given below:

```
QUICKSORT: basics

--------------------------------------------------------------------------------
PIVOT: MEDIAN
--------------------------------------------------------------------------------

duration [ns]: 14192
number of partitions: 3
number of swaps: 6
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ]
[1, 1, 1, 2, 4, 3, 2] : [ 0 - 6 ] : 1 : [ 0 - 3 ]
[1, 1, 1, 2, 2, 3, 4] : [ 3 - 6 ] : 2 : [ 3 - 5 ]
[1, 1, 1, 2, 2, 3, 4] : [ 5 - 6 ] : 3 : [ 5 - 6 ]


total duration [ns]:
1278542

averages: 10 x array[100] - ORDERED
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |        17558
         MID |        11775
      MEDIAN |        11778
        HIGH |        21695
      RANDOM |        18391

averages: 10 x array[100] - REVERSE
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |       308213
         MID |        69301
      MEDIAN |        96979
        HIGH |       231960
      RANDOM |        84254

averages: 10 x array[100] - ONEOFF
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |       254691
         MID |        21253
      MEDIAN |        37715
        HIGH |        68856
      RANDOM |        74953

averages: 10 x array[100] - SHUFFLED
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |        77441
         MID |        82668
      MEDIAN |        76270
        HIGH |        77260
      RANDOM |        83737

averages: 10 x array[100] - RANDOM
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |        16447
         MID |        14969
      MEDIAN |        14523
        HIGH |        15259
      RANDOM |        18887
```
