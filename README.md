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

For the **Median** pivot selection, see [Choice of Pivot](https://en.wikipedia.org/wiki/Quicksort#Choice_of_pivot).

### Partition Selection

Quicksort's partitioning swaps elements of array relative to the pivot element.

In this implementation the original [Hoare partition](https://gir.im/https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme) is used.

&nbsp;

## Build

The project can be both built with [Apache Maven](https://maven.apache.org/) or [Google Bazel](https://bazel.build/). For further information about the build tools, please refer to the [Bazelize Maven Plugin](https://github.com/OrhanKupusoglu/bazelize-maven-plugin).

### Apache Maven

To build and test with Maven:

```
$ mvn clean package
```
### Google Bazel

To build and test with Bazel:

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
| 6   | Random    | At each run N random values fill the arrays M times |

To see the algoritm in action, give a [QuickSortMeta](./src/main/java/kupusoglu/orhan/quicksort/QuickSortMeta.java) instance, as in the first test **testSortBasic()**. This is called [Dependency Injection](https://en.wikipedia.org/wiki/Dependency_injection).

A summary of a typical test run is given below. The last values after the array are:

- **[lo - hi]** : range, ax expected the first **hi = len -1**
- **pv** : pivot value
- **ix** : index value returned
- **sw** : swaps of this partition

Each value applies to the previous array.

Please note that the default **Median** pivot type swaps, too.

```
QUICKSORT: basics

--------------------------------------------------------------------------------
pivot: LOW
--------------------------------------------------------------------------------

duration [ns]: 166022
number of partitions: 18
number of swaps: 16
[16, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ lo - hi ] : pv : ix : sw
[16, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 18 ] : 16 : 17 : 1
[9, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 17 ] : 16 : 16 : 1
[9, 8, 0, 1, 2, 3, 4, 5, 6, 7, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 16 ] : 9 : 9 : 2
[7, 8, 0, 1, 2, 3, 4, 5, 6, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 9 ] : 9 : 8 : 1
[6, 5, 0, 1, 2, 3, 4, 8, 7, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 8 ] : 7 : 6 : 2
[4, 5, 0, 1, 2, 3, 6, 8, 7, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 6 ] : 6 : 5 : 1
[3, 2, 0, 1, 5, 4, 6, 8, 7, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 5 ] : 4 : 3 : 2
[1, 2, 0, 3, 5, 4, 6, 8, 7, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 3 ] : 3 : 2 : 1
[0, 2, 1, 3, 5, 4, 6, 8, 7, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 2 ] : 1 : 0 : 1
[0, 1, 2, 3, 5, 4, 6, 8, 7, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 1 - 2 ] : 2 : 1 : 1
[0, 1, 2, 3, 4, 5, 6, 8, 7, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 4 - 5 ] : 5 : 4 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 7 - 8 ] : 8 : 7 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 10 - 16 ] : 9 : 10 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 11 - 16 ] : 9 : 11 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 12 - 16 ] : 10 : 12 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 13 - 16 ] : 11 : 13 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 14 - 16 ] : 12 : 14 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 15 - 16 ] : 13 : 15 : 0


averages: 10 x array[100] - ORDERED
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |        21099
         MID |         9062
      MEDIAN |        10574
        HIGH |        30885
      RANDOM |        29200

averages: 10 x array[100] - REVERSE
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |       121683
         MID |        38011
      MEDIAN |        63118
        HIGH |       116921
      RANDOM |        64305

averages: 10 x array[100] - ONEOFF
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |       241293
         MID |        22596
      MEDIAN |        26293
        HIGH |        42236
      RANDOM |       103563

averages: 10 x array[100] - SHUFFLED
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |        46631
         MID |        46421
      MEDIAN |        41841
        HIGH |        46114
      RANDOM |        66220

averages: 10 x array[100] - RANDOM
--------------------------------------------------------------------------------
  pivot type | duration [ns]
--------------------------------------------------------------------------------
         LOW |        17273
         MID |        15343
      MEDIAN |       392652
        HIGH |        15054
      RANDOM |        21184
```
