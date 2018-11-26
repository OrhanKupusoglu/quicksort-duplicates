# Quicksort

The [Quicksort](https://en.wikipedia.org/wiki/Quicksort) algorithm for sorting arrays is invented by Sir [Tony Hoare](https://en.wikipedia.org/wiki/Tony_Hoare) in 1959. **Quicksort** is a popular choice among sorting algorithms, and widely used in many programming languages.

As of Java 8, JDK uses an implementation of the [Dual Pivot Quicksort](http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/tip/src/share/classes/java/util/DualPivotQuicksort.java) for primitive types (below a certain threshold like **INSERTION_SORT_THRESHOLD = 47**, [Insertion Sort](https://en.wikipedia.org/wiki/Insertion_sort) is used), and an implementation of [Timsort](https://en.wikipedia.org/wiki/Timsort) for reference types.

This duality is caused by the Java [type system](https://en.wikipedia.org/wiki/Type_system), where for performance reasons the Java designers kept generic types with no identity in parallel with the new object types which have [identity](https://docs.oracle.com/javase/8/docs/api/java/util/Objects.html#equals-java.lang.Object-java.lang.Object-).

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

Given a range of **[lo, hi]** index values, the **pivot element** can be chosen in five different ways using using the [Gang of Four](https://en.wikipedia.org/wiki/Design_Patterns) [Factory Method Pattern](https://en.wikipedia.org/wiki/Factory_method_pattern):

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

In this implementation, using the [Gang of Four](https://en.wikipedia.org/wiki/Design_Patterns) [Factory Method Pattern](https://en.wikipedia.org/wiki/Factory_method_pattern), both the more efficient **DNF**, [Dutch National Flag](https://en.wikipedia.org/wiki/Dutch_national_flag_problem) partition by [Edsger Dijkstra](https://en.wikipedia.org/wiki/Edsger_Dijkstra) and the original [Hoare Partition](https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme) can be used.

&nbsp;

## Build

The project can be both built with [Apache Maven](https://maven.apache.org/) or [Google Bazel](https://bazel.build/). For further information about the build tools, please refer to the [Bazelize Maven Plugin](https://github.com/OrhanKupusoglu/bazelize-maven-plugin).

### Apache Maven

To build and test with Maven:

```
$ mvn clean test
```
### Google Bazel

To build and test with Bazel:

```
$ bazel build

$ bazel test ... --test_output all
```
Or you can use the [bazelize.sh](./bazelize.sh) script:
```
$ ./bazelize.sh
```
If the pom.xml changes, you can re-genearate the Bazel scripts by the [Bazelize Maven Plugin](https://github.com/OrhanKupusoglu/bazelize-maven-plugin).
```
$ ./bazelize.sh -g
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
- **ix** : index value returned by Hoare partition - or -
- **dnf[lo - hi]** : index values returned by DNF partition
- **sw** : swaps of this partition

The first line is the input array and headers, thereafter each line's fields apply to the previous array. The final line is the sorted array.

Please note that the default **Median** pivot type swaps, too.

```
--------------------------------------------------------------------------------
QUICKSORT: basics

partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 141762
number of partitions: 18
number of swaps: 14
[16, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ lo - hi ] : pv : ix : sw
[7, 6, 0, 1, 2, 3, 4, 5, 9, 16, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 18 ] : 7 : 7 : 2
[1, 0, 6, 7, 2, 3, 4, 5, 9, 16, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 7 ] : 1 : 1 : 2
[0, 1, 6, 7, 2, 3, 4, 5, 9, 16, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 1 ] : 1 : 0 : 1
[0, 1, 2, 7, 6, 3, 4, 5, 9, 16, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ 2 - 7 ] : 2 : 2 : 1
[0, 1, 2, 3, 6, 7, 4, 5, 9, 16, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ 3 - 7 ] : 3 : 3 : 1
[0, 1, 2, 3, 6, 5, 4, 7, 9, 16, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ 4 - 7 ] : 7 : 6 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 9, 16, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ 4 - 6 ] : 5 : 5 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 9, 16, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ 4 - 5 ] : 4 : 4 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 9, 9, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 8 - 18 ] : 11 : 13 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 8 - 13 ] : 8 : 8 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 9 - 13 ] : 9 : 10 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 9 - 10 ] : 9 : 9 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 11 - 13 ] : 10 : 12 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 11 - 12 ] : 9 : 11 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 14 - 18 ] : 14 : 16 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 14 - 16 ] : 13 : 15 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 14 - 15 ] : 12 : 14 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 17 - 18 ] : 16 : 17 : 1

partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 64473
number of partitions: 10
number of swaps: 35
[16, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[6, 0, 1, 2, 3, 4, 5, 7, 9, 8, 9, 10, 11, 12, 13, 14, 9, 16, 16] : [ 0 - 18 ] : 7 : [ 7 - 8 ] : 18
[0, 1, 2, 3, 4, 5, 6, 7, 9, 8, 9, 10, 11, 12, 13, 14, 9, 16, 16] : [ 0 - 6 ] : 2 : [ 2 - 3 ] : 6
[0, 1, 2, 3, 4, 5, 6, 7, 9, 8, 9, 10, 11, 12, 13, 14, 9, 16, 16] : [ 0 - 1 ] : 0 : [ 0 - 1 ] : 0
[0, 1, 2, 3, 4, 6, 5, 7, 9, 8, 9, 10, 11, 12, 13, 14, 9, 16, 16] : [ 3 - 6 ] : 4 : [ 4 - 5 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 9, 8, 9, 10, 11, 12, 13, 14, 9, 16, 16] : [ 5 - 6 ] : 6 : [ 6 - 7 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 9, 8, 9, 10, 11, 9, 12, 14, 16, 16, 13] : [ 8 - 18 ] : 12 : [ 14 - 15 ] : 4
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 10, 12, 14, 16, 16, 13] : [ 8 - 13 ] : 9 : [ 9 - 12 ] : 2
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 14, 16, 16, 13] : [ 12 - 13 ] : 11 : [ 13 - 14 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 14, 13, 16, 16] : [ 15 - 18 ] : 16 : [ 17 - 19 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 15 - 16 ] : 14 : [ 16 - 17 ] : 1

name: ORDERED - partition: HOARE - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        50392
         MID |        29697
      MEDIAN |        57052
        HIGH |        51407
      RANDOM |       123980

name: ORDERED - partition: DNF - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |       167026
         MID |        92649
      MEDIAN |        95994
        HIGH |       218286
      RANDOM |        25202

name: REVERSE - partition: HOARE - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        41212
         MID |        11543
      MEDIAN |        17280
        HIGH |        26455
      RANDOM |        38344

name: REVERSE - partition: DNF - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        39667
         MID |        13869
      MEDIAN |        20195
        HIGH |        33912
      RANDOM |        21325

name: ONEOFF - partition: HOARE - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        35013
         MID |        15264
      MEDIAN |        26820
        HIGH |        36193
      RANDOM |        16274

name: ONEOFF - partition: DNF - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        22036
         MID |        16734
      MEDIAN |        17313
        HIGH |        30740
      RANDOM |        13596

name: SHUFFLED - partition: HOARE - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        13976
         MID |        17366
      MEDIAN |        32473
        HIGH |        23578
      RANDOM |        37244

name: SHUFFLED - partition: DNF - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        16452
         MID |        16636
      MEDIAN |         9864
        HIGH |         9236
      RANDOM |        15276

name: RANDOM - partition: HOARE - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        13332
         MID |        18385
      MEDIAN |        22061
        HIGH |        17189
      RANDOM |        27693

name: RANDOM - partition: DNF - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        12062
         MID |        16978
      MEDIAN |        12182
        HIGH |        12398
      RANDOM |        21091
```
