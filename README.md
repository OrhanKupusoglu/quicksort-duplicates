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

For the **Mid** pivot selection, see the [article](https://ai.googleblog.com/2006/06/extra-extra-read-all-about-it-nearly.html) by [Joshua Bloch](https://en.wikipedia.org/wiki/Joshua_Bloch).

For the **Median** pivot selection, see [Choice of pivot](https://en.wikipedia.org/wiki/Quicksort#Choice_of_pivot).

### Partition Selection

Quicksort's partitioning swaps elements of array relative to the pivot element.

In this implementation the more efficient **DNF**, [Dutch National Flag](https://en.wikipedia.org/wiki/Dutch_national_flag_problem) partition by [Edsger Dijkstra](https://en.wikipedia.org/wiki/Edsger_Dijkstra) is used.

The original [Hoare partition](https://gir.im/https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme) can be checked at its own branch **feature/partition-hoare**.

&nbsp;

## Build

The project can be both built with [Apache Maven](https://maven.apache.org/) or [Google Bazel](https://bazel.build/). For further information about the build tools, please refer to the [Bazelize Maven Plugin](https://github.com/OrhanKupusoglu/bazelize-maven-plugin).

### Maven

Commands for a typical Maven build is given below:

```
$ mvn clean install
```
### Bazel

Commands for a typical Maven build is given below:

```
$ bazel build

$ bazel test ... --test_output all
```
If the pom.xml changes, you can re-genearate the Bazel scripts by the [Bazelize Maven Plugin](https://github.com/OrhanKupusoglu/bazelize-maven-plugin).
```
$ ./bazelize.sh
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
| 6   | Random  | At each run random values fill the arrays M times     |

To see the algoritm in action, give a [QuickSortMeta](./src/main/java/kupusoglu/orhan/quicksort/QuickSortMeta.java) instance, as in the first test **testSortBasic()**. This is called [Dependency Injection](https://en.wikipedia.org/wiki/Dependency_injection).

A summary of a typical test run is given below. The last values after the array are:

- **[lo - hi]** : range, ax expected the first **hi = len -1**
- **pv** : pivot value
- **dnf[lo - hi]** : index values returned by DNF
- **sw** : swaps of this partition

Each value applies to the previous array.

Please note that the default **Median** pivot type swaps, too.

```
QUICKSORT: basics

--------------------------------------------------------------------------------
pivot: LOW
--------------------------------------------------------------------------------

duration [ns]: 294384
number of partitions: 11
number of swaps: 55
[16, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16, 16] : [ 0 - 18 ] : 16 : [ 17 - 19 ] : 17
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : [ 0 - 16 ] : 9 : [ 9 - 12 ] : 13
[0, 2, 3, 4, 5, 6, 7, 8, 1, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : [ 0 - 8 ] : 0 : [ 0 - 1 ] : 7
[0, 1, 2, 5, 6, 7, 8, 4, 3, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : [ 1 - 8 ] : 2 : [ 2 - 3 ] : 6
[0, 1, 2, 3, 4, 5, 8, 7, 6, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : [ 3 - 8 ] : 5 : [ 5 - 6 ] : 4
[0, 1, 2, 3, 4, 5, 8, 7, 6, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : [ 3 - 4 ] : 3 : [ 3 - 4 ] : 0
[0, 1, 2, 3, 4, 5, 7, 6, 8, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : [ 6 - 8 ] : 8 : [ 8 - 9 ] : 2
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : [ 6 - 7 ] : 7 : [ 7 - 8 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 14, 13, 16, 16] : [ 12 - 16 ] : 12 : [ 14 - 15 ] : 4
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 14, 13, 16, 16] : [ 12 - 13 ] : 10 : [ 12 - 13 ] : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 15 - 16 ] : 14 : [ 16 - 17 ] : 1


averages: 10 x array[100] - ORDERED
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |        14409
         MID |        10604
      MEDIAN |        11355
        HIGH |        18533
      RANDOM |        18418

averages: 10 x array[100] - REVERSE
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |       302830
         MID |        68305
      MEDIAN |        93319
        HIGH |       205327
      RANDOM |        78754

averages: 10 x array[100] - ONEOFF
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |       250155
         MID |        14125
      MEDIAN |        22888
        HIGH |        55935
      RANDOM |        83344

averages: 10 x array[100] - SHUFFLED
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |        58181
         MID |        79078
      MEDIAN |        72888
        HIGH |        82110
      RANDOM |        81857

averages: 10 x array[100] - RANDOM
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |        11895
         MID |        13248
      MEDIAN |        11726
        HIGH |        11013
      RANDOM |        13977
```
