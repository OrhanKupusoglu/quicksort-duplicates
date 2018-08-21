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

In this implementation the original [Hoare partition](https://gir.im/https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme) is used.

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

A summary of a typical test run is given below.
To see the algoritm in action, give a [QuickSortMeta](./src/main/java/kupusoglu/orhan/quicksort/QuickSortMeta.java) instance, as in the first example.
The last values after the array are:

- **[lo - hi]** : range, ax expected the first **hi = len -1**
- **pv** : pivot value
- **ix** : index value returned
- **sw** : swaps of this partition

Each value applies to the previous array.

The default **Median** pivot type swaps, too. therefore total swaps of all the partitions combined may be less than the **number of partitions** line.

```
QUICKSORT: basics

--------------------------------------------------------------------------------
pivot: MEDIAN
--------------------------------------------------------------------------------

duration [ns]: 65173
number of partitions: 19
number of swaps: 37
[16, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ lo - hi ] : pv : ix : sw
[7, 9, 0, 1, 2, 3, 4, 5, 6, 16, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 18 ] : 16 : 17 : 1
[6, 9, 0, 1, 2, 3, 4, 5, 7, 9, 8, 16, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 17 ] : 9 : 10 : 2
[3, 8, 0, 1, 2, 6, 4, 5, 7, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 10 ] : 8 : 8 : 1
[2, 7, 0, 1, 3, 6, 4, 5, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 8 ] : 7 : 7 : 1
[1, 5, 0, 2, 3, 4, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 7 ] : 5 : 5 : 2
[0, 4, 1, 2, 3, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 5 ] : 4 : 4 : 1
[0, 1, 3, 2, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 4 ] : 1 : 1 : 1
[0, 1, 3, 2, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 1 ] : 0 : 0 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 2 - 4 ] : 4 : 4 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 2 - 4 ] : 3 : 3 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 2 - 3 ] : 2 : 2 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 6 - 7 ] : 6 : 6 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 9 - 10 ] : 9 : 9 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 16, 13, 14, 12, 16] : [ 11 - 17 ] : 9 : 11 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 12 - 17 ] : 12 : 14 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 12 - 14 ] : 11 : 13 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 12 - 13 ] : 10 : 12 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 15 - 17 ] : 14 : 16 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 15 - 16 ] : 13 : 15 : 1


total duration [ns]:
1600086

averages: 10 x array[100] - ORDERED
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |        32030
         MID |        14539
      MEDIAN |        18784
        HIGH |        35284
      RANDOM |        30800

averages: 10 x array[100] - REVERSE
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |        13354
         MID |         8155
      MEDIAN |         9153
        HIGH |        12737
      RANDOM |        14436

averages: 10 x array[100] - ONEOFF
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |       182354
         MID |        17460
      MEDIAN |        20255
        HIGH |        39950
      RANDOM |       108618

averages: 10 x array[100] - SHUFFLED
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |        15230
         MID |        13744
      MEDIAN |        11844
        HIGH |        11985
      RANDOM |        16358

averages: 10 x array[100] - RANDOM
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |        13418
         MID |        14694
      MEDIAN |        43559
        HIGH |        24359
      RANDOM |        31018
```
