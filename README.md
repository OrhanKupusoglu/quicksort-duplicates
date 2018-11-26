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

In this implementation, using the [Gang of Four](https://en.wikipedia.org/wiki/Design_Patterns) [Factory Method Pattern](https://en.wikipedia.org/wiki/Factory_method_pattern), both the original [Hoare Partition](https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme) and the more efficient **DNF**, [Dutch National Flag](https://en.wikipedia.org/wiki/Dutch_national_flag_problem) partition, by [Edsger Dijkstra](https://en.wikipedia.org/wiki/Edsger_Dijkstra) can be used.

&nbsp;

## Source Lines of Code

[SLOC](https://en.wikipedia.org/wiki/Source_lines_of_code) of the project can be counted by the [Source Lines of Code Maven Plugin](https://github.com/OrhanKupusoglu/sloc-maven-plugin).

```
$ mvn kupusoglu.orhan:sloc-maven-plugin:sloc
[INFO] Scanning for projects...
[INFO] Inspecting build with total of 1 modules...
[INFO] Installing Nexus Staging features:
[INFO]   ... total of 1 executions of maven-deploy-plugin replaced with nexus-staging-maven-plugin
[INFO]
[INFO] ----------------< kupusoglu.orhan:quicksort-duplicates >----------------
[INFO] Building quicksort-duplicates 0.3.1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- sloc-maven-plugin:0.1.4:sloc (default-cli) @ quicksort-duplicates ---
[INFO] SLOC - directory: /home/orhanku/ME/DEV/OK/quicksort-duplicates/src
+------------------+---------------------------+----------+----------+----------+----------+----------+----------+
| Package Name     | File Name                 | Type     | Blank    | JavaDoc  | Comment  | Code     | Total    |
+------------------+---------------------------+----------+----------+----------+----------+----------+----------+
| quicksort        | BasePartitionFactory.java | src      |        1 |        0 |        0 |        4 |        5 |
| quicksort        | BasePivotFactory.java     | src      |        1 |        0 |        0 |        4 |        5 |
| quicksort        | Partition.java            | src      |        1 |        0 |        0 |        5 |        6 |
| quicksort        | Pivot.java                | src      |        1 |        0 |        0 |        4 |        5 |
| quicksort        | QuickSort.java            | src      |       56 |       20 |        5 |      271 |      352 |
| quicksort        | QuickSortMeta.java        | src      |       12 |        0 |        1 |       41 |       54 |
| quicksort        | QuickSortTest.java        | test     |       50 |        9 |        4 |      195 |      258 |
+------------------+---------------------------+----------+----------+----------+----------+----------+----------+
| 1 package(s)     | 7 file(s)                 | java     |      122 |       29 |       10 |      524 |      685 |
+------------------+---------------------------+----------+----------+----------+----------+----------+----------+

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.955 s
[INFO] Finished at: 2018-11-26T09:44:34+03:00
[INFO] ------------------------------------------------------------------------

```
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

Output of a test run is given below:

```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running kupusoglu.orhan.quicksort.QuickSortTest


--------------------------------------------------------------------------------
QUICKSORT: basics

partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 37974
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 4024
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 20436
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 111079
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2] : [ 0 - 1 ] : 2 : 0 : 1


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 44094
number of partitions: 2
number of swaps: 0
[1, 2, 3] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 1 : 0 : 0
[1, 2, 3] : [ 1 - 2 ] : 2 : 1 : 0


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 37424
number of partitions: 2
number of swaps: 1
[3, 2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 3 : 1 : 1
[1, 2, 3] : [ 0 - 1 ] : 1 : 0 : 0


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 72461
number of partitions: 3
number of swaps: 4
[1, 1, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1] : [ 2 - 3 ] : 1 : 2 : 1


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 131364
number of partitions: 5
number of swaps: 4
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : ix : sw
[1, 3, 3, 3, 3, 2] : [ 0 - 5 ] : 1 : 0 : 0
[1, 2, 3, 3, 3, 3] : [ 1 - 5 ] : 3 : 3 : 2
[1, 2, 3, 3, 3, 3] : [ 1 - 3 ] : 2 : 1 : 0
[1, 2, 3, 3, 3, 3] : [ 2 - 3 ] : 3 : 2 : 1
[1, 2, 3, 3, 3, 3] : [ 4 - 5 ] : 3 : 4 : 1


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 115243
number of partitions: 6
number of swaps: 7
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 3, 4, 2, 2, 1] : [ 0 - 6 ] : 1 : 1 : 2
[1, 1, 3, 4, 2, 2, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 2, 2, 4, 3] : [ 2 - 6 ] : 3 : 4 : 2
[1, 1, 1, 2, 2, 4, 3] : [ 2 - 4 ] : 1 : 2 : 0
[1, 1, 1, 2, 2, 4, 3] : [ 3 - 4 ] : 2 : 3 : 1
[1, 1, 1, 2, 2, 3, 4] : [ 5 - 6 ] : 4 : 5 : 1


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 157275
number of partitions: 7
number of swaps: 7
[1000000, 10001, 10, 30, 40, 50, 1, 999] : [ lo - hi ] : pv : ix : sw
[999, 10001, 10, 30, 40, 50, 1, 1000000] : [ 0 - 7 ] : 1000000 : 6 : 1
[1, 50, 10, 30, 40, 10001, 999, 1000000] : [ 0 - 6 ] : 999 : 4 : 2
[1, 50, 10, 30, 40, 10001, 999, 1000000] : [ 0 - 4 ] : 1 : 0 : 0
[1, 40, 10, 30, 50, 10001, 999, 1000000] : [ 1 - 4 ] : 50 : 3 : 1
[1, 30, 10, 40, 50, 10001, 999, 1000000] : [ 1 - 3 ] : 40 : 2 : 1
[1, 10, 30, 40, 50, 10001, 999, 1000000] : [ 1 - 2 ] : 30 : 1 : 1
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 5 - 6 ] : 10001 : 5 : 1


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 384891
number of partitions: 15
number of swaps: 14
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 1 : 1 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 15 ] : 1 : 2 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 15 ] : 2 : 5 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 5 ] : 2 : 4 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 4 ] : 2 : 3 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 15 ] : 2 : 6 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 15 ] : 2 : 7 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 15 ] : 3 : 9 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 9 ] : 3 : 8 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 15 ] : 3 : 10 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 11 - 15 ] : 4 : 13 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 11 - 13 ] : 4 : 12 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 11 - 12 ] : 4 : 11 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 379213
number of partitions: 15
number of swaps: 23
[4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1] : [ lo - hi ] : pv : ix : sw
[1, 2, 1, 2, 4, 2, 3, 2, 1, 3, 2, 3, 4, 4, 4, 4] : [ 0 - 15 ] : 4 : 11 : 4
[1, 1, 2, 2, 4, 2, 3, 2, 1, 3, 2, 3, 4, 4, 4, 4] : [ 0 - 11 ] : 1 : 1 : 2
[1, 1, 2, 2, 4, 2, 3, 2, 1, 3, 2, 3, 4, 4, 4, 4] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 2, 1, 2, 2, 3, 4, 2, 3, 2, 3, 4, 4, 4, 4] : [ 2 - 11 ] : 2 : 5 : 3
[1, 1, 2, 1, 2, 2, 3, 4, 2, 3, 2, 3, 4, 4, 4, 4] : [ 2 - 5 ] : 2 : 4 : 1
[1, 1, 2, 1, 2, 2, 3, 4, 2, 3, 2, 3, 4, 4, 4, 4] : [ 2 - 4 ] : 2 : 3 : 1
[1, 1, 1, 2, 2, 2, 3, 4, 2, 3, 2, 3, 4, 4, 4, 4] : [ 2 - 3 ] : 2 : 2 : 1
[1, 1, 1, 2, 2, 2, 3, 2, 2, 3, 4, 3, 4, 4, 4, 4] : [ 6 - 11 ] : 3 : 9 : 2
[1, 1, 1, 2, 2, 2, 3, 2, 2, 3, 4, 3, 4, 4, 4, 4] : [ 6 - 9 ] : 3 : 8 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 3, 4, 4, 4, 4] : [ 6 - 8 ] : 3 : 7 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 3, 4, 4, 4, 4] : [ 6 - 7 ] : 2 : 6 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 4 : 10 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 12 - 15 ] : 4 : 13 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 12 - 13 ] : 4 : 12 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 355475
number of partitions: 15
number of swaps: 16
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ 0 - 15 ] : 1 : 1 : 2
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 15 ] : 4 : 12 : 3
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 12 ] : 1 : 2 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 12 ] : 1 : 3 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 12 ] : 2 : 5 : 2
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 5 ] : 2 : 4 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 12 ] : 2 : 6 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 12 ] : 2 : 7 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 12 ] : 3 : 9 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 9 ] : 3 : 8 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 12 ] : 3 : 10 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 11 - 12 ] : 4 : 11 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 13 - 15 ] : 4 : 14 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 13 - 14 ] : 4 : 13 : 1


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 387313
number of partitions: 16
number of swaps: 33
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : ix : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : 8 : 8
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 8 ] : 2 : 4 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 4 ] : 2 : 2 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 2 ] : 2 : 1 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 1 ] : 2 : 0 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 3 - 4 ] : 2 : 3 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 8 ] : 2 : 6 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 6 ] : 2 : 5 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 7 - 8 ] : 2 : 7 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 16 ] : 2 : 12 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 12 ] : 2 : 10 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 10 ] : 2 : 9 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 11 - 12 ] : 2 : 11 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 16 ] : 2 : 14 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 14 ] : 2 : 13 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 15 - 16 ] : 2 : 15 : 1


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 387203
number of partitions: 16
number of swaps: 33
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : ix : sw
[2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : 8 : 8
[2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 8 ] : 2 : 4 : 4
[2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 4 ] : 2 : 2 : 2
[2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 2 ] : 2 : 1 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 1 ] : 2 : 0 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 3 - 4 ] : 2 : 3 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 8 ] : 2 : 6 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 6 ] : 2 : 5 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 7 - 8 ] : 2 : 7 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 16 ] : 2 : 12 : 4
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 12 ] : 2 : 10 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 10 ] : 2 : 9 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 11 - 12 ] : 2 : 11 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 16 ] : 2 : 14 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 14 ] : 2 : 13 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 15 - 16 ] : 2 : 15 : 1


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 358254
number of partitions: 16
number of swaps: 32
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : ix : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : 7 : 8
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 7 ] : 2 : 3 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 3 ] : 2 : 1 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 1 ] : 2 : 0 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 2 - 3 ] : 2 : 2 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 4 - 7 ] : 2 : 5 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 4 - 5 ] : 2 : 4 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 6 - 7 ] : 2 : 6 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 16 ] : 2 : 12 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 12 ] : 2 : 10 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 10 ] : 2 : 9 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 9 ] : 2 : 8 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 11 - 12 ] : 2 : 11 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 13 - 16 ] : 2 : 14 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 13 - 14 ] : 2 : 13 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 15 - 16 ] : 3 : 15 : 1


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 350079
number of partitions: 17
number of swaps: 31
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ lo - hi ] : pv : ix : sw
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ 0 - 17 ] : 100 : 16 : 1
[2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 0 - 16 ] : 100 : 15 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 15 ] : 2 : 14 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 14 ] : 1 : 7 : 7
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 7 ] : 1 : 3 : 4
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 2 - 3 ] : 1 : 2 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 4 - 7 ] : 1 : 5 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 4 - 5 ] : 1 : 4 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 6 - 7 ] : 1 : 6 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 8 - 14 ] : 1 : 11 : 3
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 8 - 11 ] : 1 : 9 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 8 - 9 ] : 1 : 8 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 10 - 11 ] : 1 : 10 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 12 - 14 ] : 1 : 13 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 12 - 13 ] : 1 : 12 : 1


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 465057
number of partitions: 17
number of swaps: 30
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2] : [ lo - hi ] : pv : ix : sw
[2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 0 - 17 ] : 100 : 16 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 16 ] : 2 : 14 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 14 ] : 1 : 7 : 7
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 7 ] : 1 : 3 : 4
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 2 - 3 ] : 1 : 2 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 4 - 7 ] : 1 : 5 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 4 - 5 ] : 1 : 4 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 6 - 7 ] : 1 : 6 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 8 - 14 ] : 1 : 11 : 3
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 8 - 11 ] : 1 : 9 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 8 - 9 ] : 1 : 8 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 10 - 11 ] : 1 : 10 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 12 - 14 ] : 1 : 13 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 12 - 13 ] : 1 : 12 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 15 - 16 ] : 2 : 15 : 0


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 248062
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

total duration [ns]:
34660078


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 13863
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 2175
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 3310
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 37508
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2] : [ 0 - 1 ] : 2 : 0 : 1


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 45454
number of partitions: 2
number of swaps: 0
[1, 2, 3] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : 1 : 0
[1, 2, 3] : [ 0 - 1 ] : 1 : 0 : 0


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 25143
number of partitions: 2
number of swaps: 1
[3, 2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : 1 : 1
[1, 2, 3] : [ 0 - 1 ] : 1 : 0 : 0


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 30477
number of partitions: 3
number of swaps: 4
[1, 1, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1] : [ 2 - 3 ] : 1 : 2 : 1


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 46140
number of partitions: 5
number of swaps: 4
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : ix : sw
[1, 2, 3, 3, 3, 3] : [ 0 - 5 ] : 3 : 3 : 2
[1, 2, 3, 3, 3, 3] : [ 0 - 3 ] : 2 : 1 : 0
[1, 2, 3, 3, 3, 3] : [ 0 - 1 ] : 1 : 0 : 0
[1, 2, 3, 3, 3, 3] : [ 2 - 3 ] : 3 : 2 : 1
[1, 2, 3, 3, 3, 3] : [ 4 - 5 ] : 3 : 4 : 1


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 61344
number of partitions: 6
number of swaps: 7
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 2, 3, 1, 2, 1, 4] : [ 0 - 6 ] : 4 : 5 : 1
[1, 2, 1, 1, 2, 3, 4] : [ 0 - 5 ] : 3 : 4 : 1
[1, 1, 2, 1, 2, 3, 4] : [ 0 - 4 ] : 1 : 1 : 2
[1, 1, 2, 1, 2, 3, 4] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 2, 2, 3, 4] : [ 2 - 4 ] : 1 : 2 : 1
[1, 1, 1, 2, 2, 3, 4] : [ 3 - 4 ] : 2 : 3 : 1


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 80087
number of partitions: 7
number of swaps: 7
[1000000, 10001, 10, 30, 40, 50, 1, 999] : [ lo - hi ] : pv : ix : sw
[1, 30, 10, 10001, 40, 50, 1000000, 999] : [ 0 - 7 ] : 30 : 2 : 2
[1, 10, 30, 10001, 40, 50, 1000000, 999] : [ 0 - 2 ] : 30 : 1 : 1
[1, 10, 30, 10001, 40, 50, 1000000, 999] : [ 0 - 1 ] : 1 : 0 : 0
[1, 10, 30, 50, 40, 10001, 1000000, 999] : [ 3 - 7 ] : 50 : 4 : 1
[1, 10, 30, 40, 50, 10001, 1000000, 999] : [ 3 - 4 ] : 50 : 3 : 1
[1, 10, 30, 40, 50, 10001, 999, 1000000] : [ 5 - 7 ] : 1000000 : 6 : 1
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 5 - 6 ] : 10001 : 5 : 1


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 129183
number of partitions: 15
number of swaps: 14
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 2 : 5 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 5 ] : 1 : 1 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 5 ] : 2 : 4 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 4 ] : 2 : 3 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 3 ] : 1 : 2 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 15 ] : 3 : 9 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 9 ] : 2 : 6 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 9 ] : 3 : 8 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 8 ] : 2 : 7 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 15 ] : 4 : 13 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 13 ] : 4 : 12 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 12 ] : 4 : 11 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 3 : 10 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 132233
number of partitions: 15
number of swaps: 20
[4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 2, 2, 2, 4, 4, 4, 3, 2, 3, 2, 3, 4, 4] : [ 0 - 15 ] : 2 : 5 : 4
[1, 1, 1, 2, 2, 2, 4, 4, 4, 3, 2, 3, 2, 3, 4, 4] : [ 0 - 5 ] : 1 : 1 : 1
[1, 1, 1, 2, 2, 2, 4, 4, 4, 3, 2, 3, 2, 3, 4, 4] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 2, 2, 2, 4, 4, 4, 3, 2, 3, 2, 3, 4, 4] : [ 2 - 5 ] : 2 : 4 : 1
[1, 1, 1, 2, 2, 2, 4, 4, 4, 3, 2, 3, 2, 3, 4, 4] : [ 2 - 4 ] : 2 : 3 : 1
[1, 1, 1, 2, 2, 2, 4, 4, 4, 3, 2, 3, 2, 3, 4, 4] : [ 2 - 3 ] : 1 : 2 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 4, 3, 4, 3, 4, 3, 4, 4] : [ 6 - 15 ] : 2 : 7 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 4, 3, 4, 3, 4, 3, 4, 4] : [ 6 - 7 ] : 2 : 6 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 3, 4, 4, 4, 4] : [ 8 - 15 ] : 3 : 9 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 3, 4, 4, 4, 4] : [ 8 - 9 ] : 3 : 8 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 3, 4, 4, 4, 4] : [ 10 - 15 ] : 4 : 13 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 13 ] : 3 : 10 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 11 - 13 ] : 4 : 12 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 11 - 12 ] : 4 : 11 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 139377
number of partitions: 15
number of swaps: 17
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 2 : 5 : 4
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 5 ] : 1 : 1 : 2
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 5 ] : 1 : 2 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 5 ] : 2 : 4 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 4 ] : 1 : 3 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 15 ] : 3 : 9 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 9 ] : 2 : 6 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 9 ] : 3 : 8 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 8 ] : 2 : 7 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 15 ] : 4 : 13 : 2
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 13 ] : 4 : 12 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 12 ] : 4 : 11 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 3 : 10 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 151864
number of partitions: 16
number of swaps: 33
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : ix : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : 8 : 8
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 8 ] : 2 : 4 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 4 ] : 2 : 2 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 2 ] : 2 : 1 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 1 ] : 2 : 0 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 3 - 4 ] : 2 : 3 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 8 ] : 2 : 6 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 6 ] : 2 : 5 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 7 - 8 ] : 2 : 7 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 16 ] : 2 : 12 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 12 ] : 2 : 10 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 10 ] : 2 : 9 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 11 - 12 ] : 2 : 11 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 16 ] : 2 : 14 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 14 ] : 2 : 13 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 15 - 16 ] : 2 : 15 : 1


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 3454255
number of partitions: 16
number of swaps: 33
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : ix : sw
[2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : 8 : 8
[2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 8 ] : 2 : 4 : 4
[2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 4 ] : 2 : 2 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 2 ] : 1 : 0 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 1 - 2 ] : 2 : 1 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 3 - 4 ] : 2 : 3 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 8 ] : 2 : 6 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 6 ] : 2 : 5 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 7 - 8 ] : 2 : 7 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 16 ] : 2 : 12 : 4
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 12 ] : 2 : 10 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 10 ] : 2 : 9 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 11 - 12 ] : 2 : 11 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 16 ] : 2 : 14 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 14 ] : 2 : 13 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 15 - 16 ] : 2 : 15 : 1


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 128907
number of partitions: 16
number of swaps: 32
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : ix : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : 7 : 8
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 7 ] : 2 : 3 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 3 ] : 2 : 1 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 1 ] : 2 : 0 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 2 - 3 ] : 2 : 2 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 4 - 7 ] : 2 : 5 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 4 - 5 ] : 2 : 4 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 6 - 7 ] : 2 : 6 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 16 ] : 2 : 12 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 12 ] : 2 : 10 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 10 ] : 2 : 9 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 9 ] : 2 : 8 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 11 - 12 ] : 2 : 11 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 13 - 16 ] : 2 : 14 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 13 - 14 ] : 2 : 13 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 15 - 16 ] : 3 : 15 : 1


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 107530
number of partitions: 17
number of swaps: 31
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 0 - 17 ] : 1 : 7 : 8
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 0 - 7 ] : 1 : 3 : 4
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 2 - 3 ] : 1 : 2 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 4 - 7 ] : 1 : 5 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 4 - 5 ] : 1 : 4 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 6 - 7 ] : 1 : 6 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 8 - 17 ] : 1 : 11 : 3
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 8 - 11 ] : 1 : 9 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 8 - 9 ] : 1 : 8 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 10 - 11 ] : 1 : 10 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 12 - 17 ] : 1 : 13 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 12 - 13 ] : 1 : 12 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 14 - 17 ] : 100 : 16 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 16 ] : 100 : 15 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 15 ] : 1 : 14 : 0


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 91305
number of partitions: 17
number of swaps: 30
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 0 - 17 ] : 1 : 7 : 8
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 0 - 7 ] : 1 : 3 : 4
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 2 - 3 ] : 1 : 2 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 4 - 7 ] : 1 : 5 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 4 - 5 ] : 1 : 4 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 6 - 7 ] : 1 : 6 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 8 - 17 ] : 1 : 11 : 3
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 8 - 11 ] : 1 : 9 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 8 - 9 ] : 1 : 8 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 10 - 11 ] : 1 : 10 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 12 - 17 ] : 1 : 13 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 12 - 13 ] : 1 : 12 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 17 ] : 100 : 16 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 16 ] : 2 : 15 : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 15 ] : 1 : 14 : 0


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 104022
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

total duration [ns]:
22002278


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 3270
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 1871
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 3215
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 58818
number of partitions: 2
number of swaps: 3
[2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2] : median swaps : 1
[1, 2] : [ 0 - 1 ] : 2 : 1 : 0
[2, 1] : median swaps : 1
[1, 2] : [ 0 - 1 ] : 1 : 0 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 22344
number of partitions: 2
number of swaps: 4
[1, 2, 3] : [ lo - hi ] : pv : ix : sw
[1, 3, 2] : median swaps : 1
[1, 2, 3] : [ 0 - 2 ] : 2 : 1 : 1
[2, 1, 3] : median swaps : 1
[1, 2, 3] : [ 0 - 1 ] : 1 : 0 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 23693
number of partitions: 3
number of swaps: 5
[3, 2, 1] : [ lo - hi ] : pv : ix : sw
[2, 3, 1] : median swaps : 1
[1, 3, 2] : [ 0 - 2 ] : 1 : 0 : 1
[1, 2, 3] : median swaps : 1
[1, 2, 3] : [ 1 - 2 ] : 3 : 2 : 0
[1, 3, 2] : median swaps : 1
[1, 2, 3] : [ 1 - 2 ] : 2 : 1 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 23193
number of partitions: 3
number of swaps: 4
[1, 1, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1] : median swaps : 0
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1] : median swaps : 0
[1, 1, 1, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1] : median swaps : 0
[1, 1, 1, 1] : [ 2 - 3 ] : 1 : 2 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 39390
number of partitions: 5
number of swaps: 7
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : ix : sw
[1, 3, 3, 3, 3, 2] : median swaps : 0
[1, 2, 3, 3, 3, 3] : [ 0 - 5 ] : 2 : 1 : 1
[2, 1, 3, 3, 3, 3] : median swaps : 1
[1, 2, 3, 3, 3, 3] : [ 0 - 1 ] : 1 : 0 : 1
[1, 2, 3, 3, 3, 3] : median swaps : 0
[1, 2, 3, 3, 3, 3] : [ 2 - 5 ] : 3 : 3 : 2
[1, 2, 3, 3, 3, 3] : median swaps : 0
[1, 2, 3, 3, 3, 3] : [ 2 - 3 ] : 3 : 2 : 1
[1, 2, 3, 3, 3, 3] : median swaps : 0
[1, 2, 3, 3, 3, 3] : [ 4 - 5 ] : 3 : 4 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 53650
number of partitions: 7
number of swaps: 12
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 2, 3, 4, 2, 1, 1] : median swaps : 0
[1, 1, 3, 4, 2, 2, 1] : [ 0 - 6 ] : 1 : 1 : 2
[1, 1, 3, 4, 2, 2, 1] : median swaps : 0
[1, 1, 3, 4, 2, 2, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 2, 4, 3, 2, 1] : median swaps : 1
[1, 1, 1, 4, 3, 2, 2] : [ 2 - 6 ] : 1 : 2 : 1
[1, 1, 1, 3, 4, 2, 2] : median swaps : 1
[1, 1, 1, 2, 2, 4, 3] : [ 3 - 6 ] : 2 : 4 : 2
[1, 1, 1, 2, 2, 4, 3] : median swaps : 0
[1, 1, 1, 2, 2, 4, 3] : [ 3 - 4 ] : 2 : 3 : 1
[1, 1, 1, 2, 2, 3, 4] : median swaps : 1
[1, 1, 1, 2, 2, 3, 4] : [ 5 - 6 ] : 4 : 6 : 0
[1, 1, 1, 2, 2, 4, 3] : median swaps : 1
[1, 1, 1, 2, 2, 3, 4] : [ 5 - 6 ] : 3 : 5 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 67601
number of partitions: 8
number of swaps: 17
[1000000, 10001, 10, 30, 40, 50, 1, 999] : [ lo - hi ] : pv : ix : sw
[30, 10001, 10, 1000000, 40, 50, 1, 999] : median swaps : 1
[30, 999, 10, 1, 40, 50, 1000000, 10001] : [ 0 - 7 ] : 999 : 5 : 2
[10, 999, 30, 1, 40, 50, 1000000, 10001] : median swaps : 1
[10, 50, 30, 1, 40, 999, 1000000, 10001] : [ 0 - 5 ] : 50 : 4 : 1
[10, 50, 40, 1, 30, 999, 1000000, 10001] : median swaps : 1
[10, 30, 1, 40, 50, 999, 1000000, 10001] : [ 0 - 4 ] : 30 : 2 : 2
[1, 30, 10, 40, 50, 999, 1000000, 10001] : median swaps : 1
[1, 10, 30, 40, 50, 999, 1000000, 10001] : [ 0 - 2 ] : 10 : 1 : 1
[10, 1, 30, 40, 50, 999, 1000000, 10001] : median swaps : 1
[1, 10, 30, 40, 50, 999, 1000000, 10001] : [ 0 - 1 ] : 1 : 0 : 1
[1, 10, 30, 50, 40, 999, 1000000, 10001] : median swaps : 1
[1, 10, 30, 40, 50, 999, 1000000, 10001] : [ 3 - 4 ] : 40 : 3 : 1
[1, 10, 30, 40, 50, 999, 10001, 1000000] : median swaps : 1
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 6 - 7 ] : 1000000 : 7 : 0
[1, 10, 30, 40, 50, 999, 1000000, 10001] : median swaps : 1
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 6 - 7 ] : 10001 : 6 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 6466195
number of partitions: 15
number of swaps: 26
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 2, 2, 2, 2, 4, 3, 3, 3, 4, 4, 4, 4, 2] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 4, 3, 3, 3, 4, 4, 4, 4, 2] : [ 0 - 15 ] : 2 : 5 : 2
[1, 1, 2, 2, 2, 1, 2, 4, 3, 3, 3, 4, 4, 4, 4, 2] : median swaps : 1
[1, 1, 2, 2, 2, 1, 2, 4, 3, 3, 3, 4, 4, 4, 4, 2] : [ 0 - 5 ] : 1 : 1 : 1
[1, 1, 2, 2, 2, 1, 2, 4, 3, 3, 3, 4, 4, 4, 4, 2] : median swaps : 0
[1, 1, 2, 2, 2, 1, 2, 4, 3, 3, 3, 4, 4, 4, 4, 2] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 2, 2, 2, 2, 4, 3, 3, 3, 4, 4, 4, 4, 2] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 4, 3, 3, 3, 4, 4, 4, 4, 2] : [ 2 - 5 ] : 2 : 4 : 1
[1, 1, 1, 2, 2, 2, 2, 4, 3, 3, 3, 4, 4, 4, 4, 2] : median swaps : 0
[1, 1, 1, 2, 2, 2, 2, 4, 3, 3, 3, 4, 4, 4, 4, 2] : [ 2 - 4 ] : 2 : 3 : 1
[1, 1, 2, 1, 2, 2, 2, 4, 3, 3, 3, 4, 4, 4, 4, 2] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 4, 3, 3, 3, 4, 4, 4, 4, 2] : [ 2 - 3 ] : 1 : 2 : 1
[1, 1, 1, 2, 2, 2, 2, 4, 3, 3, 3, 4, 4, 4, 4, 2] : median swaps : 0
[1, 1, 1, 2, 2, 2, 2, 4, 3, 3, 3, 4, 4, 4, 4, 2] : [ 6 - 15 ] : 2 : 6 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 15 ] : 4 : 13 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 3, 4, 4] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 3, 4, 4] : [ 7 - 13 ] : 3 : 9 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 3, 4, 4] : median swaps : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 3, 4, 4] : [ 7 - 9 ] : 3 : 8 : 1
[1, 1, 1, 2, 2, 2, 2, 3, 2, 3, 4, 4, 4, 3, 4, 4] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 3, 4, 4] : [ 7 - 8 ] : 2 : 7 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 13 ] : 4 : 12 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 12 ] : 4 : 11 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 3, 4, 4, 4, 4] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 3 : 10 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 120589
number of partitions: 15
number of swaps: 26
[4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1] : [ lo - hi ] : pv : ix : sw
[2, 2, 1, 2, 4, 2, 4, 4, 4, 3, 2, 3, 1, 3, 4, 1] : median swaps : 1
[1, 1, 1, 2, 4, 2, 4, 4, 4, 3, 2, 3, 2, 3, 4, 2] : [ 0 - 15 ] : 1 : 2 : 2
[1, 1, 1, 2, 4, 2, 4, 4, 4, 3, 2, 3, 2, 3, 4, 2] : median swaps : 0
[1, 1, 1, 2, 4, 2, 4, 4, 4, 3, 2, 3, 2, 3, 4, 2] : [ 0 - 2 ] : 1 : 1 : 1
[1, 1, 1, 2, 4, 2, 4, 4, 4, 3, 2, 3, 2, 3, 4, 2] : median swaps : 0
[1, 1, 1, 2, 4, 2, 4, 4, 4, 3, 2, 3, 2, 3, 4, 2] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 2, 4, 2, 4, 4, 4, 3, 2, 3, 2, 3, 4, 2] : median swaps : 0
[1, 1, 1, 2, 2, 2, 4, 4, 4, 3, 2, 3, 4, 3, 4, 2] : [ 3 - 15 ] : 2 : 5 : 3
[1, 1, 1, 2, 2, 2, 4, 4, 4, 3, 2, 3, 4, 3, 4, 2] : median swaps : 0
[1, 1, 1, 2, 2, 2, 4, 4, 4, 3, 2, 3, 4, 3, 4, 2] : [ 3 - 5 ] : 2 : 4 : 1
[1, 1, 1, 2, 2, 2, 4, 4, 4, 3, 2, 3, 4, 3, 4, 2] : median swaps : 0
[1, 1, 1, 2, 2, 2, 4, 4, 4, 3, 2, 3, 4, 3, 4, 2] : [ 3 - 4 ] : 2 : 3 : 1
[1, 1, 1, 2, 2, 2, 2, 4, 4, 3, 4, 3, 4, 3, 4, 2] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 4, 4, 3, 4, 3, 4, 3, 4, 2] : [ 6 - 15 ] : 2 : 6 : 1
[1, 1, 1, 2, 2, 2, 2, 3, 4, 3, 4, 4, 4, 3, 4, 2] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 2, 4, 3, 4, 4, 4, 3, 4, 3] : [ 7 - 15 ] : 2 : 7 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 3, 4, 4] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 3, 4, 4, 4] : [ 8 - 15 ] : 4 : 12 : 3
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 3, 4, 4, 4] : median swaps : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 3, 4, 4, 4] : [ 8 - 12 ] : 3 : 9 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 3, 4, 4, 4] : median swaps : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 3, 4, 4, 4] : [ 8 - 9 ] : 3 : 8 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 12 ] : 4 : 11 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 3, 4, 4, 4, 4] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 3 : 10 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 13 - 15 ] : 4 : 14 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 13 - 14 ] : 4 : 13 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 200639
number of partitions: 15
number of swaps: 26
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : median swaps : 0
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ 0 - 15 ] : 1 : 1 : 2
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : median swaps : 0
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 3, 4, 2, 2, 2, 2, 4, 3, 3, 4, 4, 4, 1, 1] : median swaps : 1
[1, 1, 1, 1, 2, 2, 2, 2, 4, 3, 3, 4, 4, 4, 4, 3] : [ 2 - 15 ] : 1 : 3 : 2
[1, 1, 1, 1, 2, 2, 2, 2, 4, 3, 3, 4, 4, 4, 4, 3] : median swaps : 0
[1, 1, 1, 1, 2, 2, 2, 2, 4, 3, 3, 4, 4, 4, 4, 3] : [ 2 - 3 ] : 1 : 2 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 4, 3, 3, 4, 4, 4, 4, 3] : median swaps : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 15 ] : 3 : 9 : 2
[1, 1, 1, 1, 2, 2, 3, 2, 3, 2, 3, 4, 4, 4, 4, 4] : median swaps : 1
[1, 1, 1, 1, 2, 2, 3, 2, 3, 2, 3, 4, 4, 4, 4, 4] : [ 4 - 9 ] : 2 : 5 : 2
[1, 1, 1, 1, 2, 2, 3, 2, 3, 2, 3, 4, 4, 4, 4, 4] : median swaps : 0
[1, 1, 1, 1, 2, 2, 3, 2, 3, 2, 3, 4, 4, 4, 4, 4] : [ 4 - 5 ] : 2 : 4 : 1
[1, 1, 1, 1, 2, 2, 2, 3, 3, 2, 3, 4, 4, 4, 4, 4] : median swaps : 1
[1, 1, 1, 1, 2, 2, 2, 3, 3, 2, 3, 4, 4, 4, 4, 4] : [ 6 - 9 ] : 2 : 6 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 9 ] : 3 : 8 : 1
[1, 1, 1, 1, 2, 2, 2, 3, 2, 3, 3, 4, 4, 4, 4, 4] : median swaps : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 8 ] : 2 : 7 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 15 ] : 4 : 13 : 2
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 13 ] : 4 : 12 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 12 ] : 4 : 11 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 3, 4, 4, 4, 4] : median swaps : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 3 : 10 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 103926
number of partitions: 16
number of swaps: 33
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : ix : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : 8 : 8
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 8 ] : 2 : 4 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 4 ] : 2 : 2 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 2 ] : 2 : 1 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 1 ] : 2 : 0 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 3 - 4 ] : 2 : 3 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 8 ] : 2 : 6 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 6 ] : 2 : 5 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 7 - 8 ] : 2 : 7 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 16 ] : 2 : 12 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 12 ] : 2 : 10 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 10 ] : 2 : 9 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 11 - 12 ] : 2 : 11 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 16 ] : 2 : 14 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 14 ] : 2 : 13 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 15 - 16 ] : 2 : 15 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 74115
number of partitions: 16
number of swaps: 35
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : ix : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : 8 : 8
[2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 8 ] : 2 : 4 : 4
[2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 4 ] : 2 : 2 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 2 ] : 2 : 1 : 1
[2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 1 ] : 1 : 0 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 3 - 4 ] : 2 : 3 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 8 ] : 2 : 6 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 6 ] : 2 : 5 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 7 - 8 ] : 2 : 7 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 16 ] : 2 : 12 : 4
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 12 ] : 2 : 10 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 10 ] : 2 : 9 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 11 - 12 ] : 2 : 11 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 16 ] : 2 : 14 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 14 ] : 2 : 13 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 15 - 16 ] : 2 : 15 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 117450
number of partitions: 17
number of swaps: 34
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : ix : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : 7 : 8
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 7 ] : 2 : 3 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 3 ] : 2 : 1 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 1 ] : 2 : 0 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 2 - 3 ] : 2 : 2 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 4 - 7 ] : 2 : 5 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 4 - 5 ] : 2 : 4 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 6 - 7 ] : 2 : 6 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 16 ] : 2 : 12 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 12 ] : 2 : 10 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 10 ] : 2 : 9 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 9 ] : 2 : 8 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 11 - 12 ] : 2 : 11 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 13 - 16 ] : 2 : 14 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 13 - 14 ] : 2 : 13 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : median swaps : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 15 - 16 ] : 3 : 16 : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : median swaps : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 15 - 16 ] : 2 : 15 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 97144
number of partitions: 18
number of swaps: 38
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1, 1, 1, 1, 1, 100, 1, 1, 1, 1, 1, 1, 1, 2, 100] : median swaps : 1
[1, 1, 1, 1, 1, 1, 1, 1, 100, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ 0 - 17 ] : 100 : 16 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 100, 1, 1, 1, 1, 1, 1, 1, 2, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 0 - 16 ] : 2 : 15 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 0 - 15 ] : 1 : 7 : 7
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 0 - 7 ] : 1 : 3 : 4
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 2 - 3 ] : 1 : 2 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 4 - 7 ] : 1 : 5 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 4 - 5 ] : 1 : 4 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 6 - 7 ] : 1 : 6 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 100, 100] : median swaps : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 100, 100] : [ 8 - 15 ] : 1 : 11 : 4
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 100, 100] : [ 8 - 11 ] : 1 : 9 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 100, 100] : [ 8 - 9 ] : 1 : 8 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 100, 100] : [ 10 - 11 ] : 1 : 10 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 100, 100] : median swaps : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 100, 100] : [ 12 - 15 ] : 1 : 13 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 100, 100] : [ 12 - 13 ] : 1 : 12 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : median swaps : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 15 ] : 2 : 15 : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 100, 100] : median swaps : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 15 ] : 1 : 14 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 102904
number of partitions: 18
number of swaps: 38
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1, 1, 1, 1, 1, 100, 1, 1, 1, 1, 1, 1, 1, 100, 2] : median swaps : 1
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 0 - 17 ] : 2 : 15 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 0 - 15 ] : 1 : 7 : 7
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 0 - 7 ] : 1 : 3 : 4
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 2 - 3 ] : 1 : 2 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 4 - 7 ] : 1 : 5 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 4 - 5 ] : 1 : 4 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 6 - 7 ] : 1 : 6 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 100, 100] : median swaps : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 100, 100] : [ 8 - 15 ] : 1 : 11 : 4
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 100, 100] : [ 8 - 11 ] : 1 : 9 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 100, 100] : [ 8 - 9 ] : 1 : 8 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 100, 100] : [ 10 - 11 ] : 1 : 10 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 100, 100] : median swaps : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 100, 100] : [ 12 - 15 ] : 1 : 13 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 100, 100] : [ 12 - 13 ] : 1 : 12 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : median swaps : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 15 ] : 2 : 15 : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 100, 100] : median swaps : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 15 ] : 1 : 14 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 16 - 17 ] : 100 : 16 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 84599
number of partitions: 19
number of swaps: 37
[16, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ lo - hi ] : pv : ix : sw
[7, 9, 0, 1, 2, 3, 4, 5, 6, 16, 8, 9, 10, 11, 12, 13, 14, 9, 16] : median swaps : 1
[7, 9, 0, 1, 2, 3, 4, 5, 6, 16, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 18 ] : 16 : 17 : 1
[6, 9, 0, 1, 2, 3, 4, 5, 7, 16, 8, 9, 10, 11, 12, 13, 14, 9, 16] : median swaps : 1
[6, 9, 0, 1, 2, 3, 4, 5, 7, 9, 8, 16, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 17 ] : 9 : 10 : 2
[3, 9, 0, 1, 2, 6, 4, 5, 7, 9, 8, 16, 10, 11, 12, 13, 14, 9, 16] : median swaps : 1
[3, 8, 0, 1, 2, 6, 4, 5, 7, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 10 ] : 8 : 8 : 1
[2, 8, 0, 1, 3, 6, 4, 5, 7, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : median swaps : 1
[2, 7, 0, 1, 3, 6, 4, 5, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 8 ] : 7 : 7 : 1
[1, 7, 0, 2, 3, 6, 4, 5, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : median swaps : 1
[1, 5, 0, 2, 3, 4, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 7 ] : 5 : 5 : 2
[0, 5, 1, 2, 3, 4, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : median swaps : 1
[0, 4, 1, 2, 3, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 5 ] : 4 : 4 : 1
[0, 4, 3, 2, 1, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : median swaps : 1
[0, 1, 3, 2, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 4 ] : 1 : 1 : 1
[1, 0, 3, 2, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : median swaps : 1
[0, 1, 3, 2, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 0 - 1 ] : 0 : 0 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : median swaps : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 2 - 4 ] : 4 : 4 : 0
[0, 1, 2, 4, 3, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : median swaps : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 2 - 4 ] : 3 : 3 : 1
[0, 1, 3, 2, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : median swaps : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 2 - 3 ] : 2 : 2 : 1
[0, 1, 2, 3, 4, 5, 7, 6, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : median swaps : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 6 - 7 ] : 6 : 6 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : median swaps : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 16, 10, 11, 12, 13, 14, 9, 16] : [ 9 - 10 ] : 9 : 9 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 12, 10, 11, 16, 13, 14, 9, 16] : median swaps : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 16, 13, 14, 12, 16] : [ 11 - 17 ] : 9 : 11 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 16, 13, 14, 12, 16] : median swaps : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 12 - 17 ] : 12 : 14 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 12, 11, 13, 14, 16, 16] : median swaps : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 12 - 14 ] : 11 : 13 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 10, 12, 13, 14, 16, 16] : median swaps : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 12 - 13 ] : 10 : 12 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 16, 14, 16] : median swaps : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 15 - 17 ] : 14 : 16 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 14, 13, 16, 16] : median swaps : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 15 - 16 ] : 13 : 15 : 1

total duration [ns]:
37949489


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 3255
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 1726
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 2915
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 32462
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2] : [ 0 - 1 ] : 2 : 0 : 1


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 8160
number of partitions: 2
number of swaps: 0
[1, 2, 3] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : 1 : 0
[1, 2, 3] : [ 0 - 1 ] : 1 : 0 : 0


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 10759
number of partitions: 2
number of swaps: 1
[3, 2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : 1 : 1
[1, 2, 3] : [ 0 - 1 ] : 1 : 0 : 0


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 8847
number of partitions: 3
number of swaps: 4
[1, 1, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1] : [ 2 - 3 ] : 1 : 2 : 1


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 16079
number of partitions: 5
number of swaps: 4
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : ix : sw
[1, 2, 3, 3, 3, 3] : [ 0 - 5 ] : 3 : 3 : 2
[1, 2, 3, 3, 3, 3] : [ 0 - 3 ] : 3 : 2 : 1
[1, 2, 3, 3, 3, 3] : [ 0 - 2 ] : 2 : 1 : 0
[1, 2, 3, 3, 3, 3] : [ 0 - 1 ] : 1 : 0 : 0
[1, 2, 3, 3, 3, 3] : [ 4 - 5 ] : 3 : 4 : 1


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 14424
number of partitions: 6
number of swaps: 7
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 3, 4, 2, 2, 1] : [ 0 - 6 ] : 1 : 1 : 2
[1, 1, 3, 4, 2, 2, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 2, 2, 4, 3] : [ 2 - 6 ] : 2 : 4 : 2
[1, 1, 1, 2, 2, 4, 3] : [ 2 - 4 ] : 2 : 3 : 1
[1, 1, 1, 2, 2, 4, 3] : [ 2 - 3 ] : 1 : 2 : 0
[1, 1, 1, 2, 2, 3, 4] : [ 5 - 6 ] : 4 : 5 : 1


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 23575
number of partitions: 7
number of swaps: 7
[1000000, 10001, 10, 30, 40, 50, 1, 999] : [ lo - hi ] : pv : ix : sw
[1, 10001, 10, 30, 40, 50, 1000000, 999] : [ 0 - 7 ] : 1 : 0 : 1
[1, 10001, 10, 30, 40, 50, 999, 1000000] : [ 1 - 7 ] : 1000000 : 6 : 1
[1, 50, 10, 30, 40, 10001, 999, 1000000] : [ 1 - 6 ] : 50 : 4 : 1
[1, 30, 10, 50, 40, 10001, 999, 1000000] : [ 1 - 4 ] : 30 : 2 : 1
[1, 10, 30, 50, 40, 10001, 999, 1000000] : [ 1 - 2 ] : 30 : 1 : 1
[1, 10, 30, 40, 50, 10001, 999, 1000000] : [ 3 - 4 ] : 50 : 3 : 1
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 5 - 6 ] : 10001 : 5 : 1


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 44531
number of partitions: 15
number of swaps: 14
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 4 : 13 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 13 ] : 4 : 12 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 12 ] : 4 : 11 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 11 ] : 3 : 9 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 9 ] : 3 : 8 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 8 ] : 2 : 5 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 5 ] : 2 : 4 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 4 ] : 2 : 3 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 3 ] : 1 : 1 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 3 ] : 1 : 2 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 8 ] : 2 : 6 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 8 ] : 2 : 7 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 3 : 10 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 39591
number of partitions: 15
number of swaps: 23
[4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1] : [ lo - hi ] : pv : ix : sw
[1, 2, 1, 2, 4, 2, 3, 2, 1, 3, 2, 3, 4, 4, 4, 4] : [ 0 - 15 ] : 4 : 11 : 4
[1, 2, 1, 1, 2, 2, 3, 4, 2, 3, 2, 3, 4, 4, 4, 4] : [ 0 - 11 ] : 2 : 5 : 3
[1, 2, 1, 1, 2, 2, 3, 4, 2, 3, 2, 3, 4, 4, 4, 4] : [ 0 - 5 ] : 2 : 4 : 1
[1, 1, 2, 1, 2, 2, 3, 4, 2, 3, 2, 3, 4, 4, 4, 4] : [ 0 - 4 ] : 1 : 1 : 2
[1, 1, 2, 1, 2, 2, 3, 4, 2, 3, 2, 3, 4, 4, 4, 4] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 2, 2, 2, 3, 4, 2, 3, 2, 3, 4, 4, 4, 4] : [ 2 - 4 ] : 1 : 2 : 1
[1, 1, 1, 2, 2, 2, 3, 4, 2, 3, 2, 3, 4, 4, 4, 4] : [ 3 - 4 ] : 2 : 3 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 4, 3, 3, 3, 4, 4, 4, 4] : [ 6 - 11 ] : 2 : 7 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 4, 3, 3, 3, 4, 4, 4, 4] : [ 6 - 7 ] : 2 : 6 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 11 ] : 3 : 9 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 9 ] : 3 : 8 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 3 : 10 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 12 - 15 ] : 4 : 13 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 12 - 13 ] : 4 : 12 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 45464
number of partitions: 15
number of swaps: 17
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ 0 - 15 ] : 1 : 1 : 2
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 15 ] : 1 : 3 : 2
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 3 ] : 1 : 2 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 15 ] : 4 : 13 : 2
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 13 ] : 4 : 12 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 12 ] : 4 : 11 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 11 ] : 3 : 9 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 9 ] : 3 : 8 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 8 ] : 2 : 5 : 2
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 5 ] : 2 : 4 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 8 ] : 2 : 6 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 8 ] : 2 : 7 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 3 : 10 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 54471
number of partitions: 16
number of swaps: 33
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : ix : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : 8 : 8
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 8 ] : 2 : 4 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 4 ] : 2 : 2 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 2 ] : 2 : 1 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 1 ] : 2 : 0 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 3 - 4 ] : 2 : 3 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 8 ] : 2 : 6 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 6 ] : 2 : 5 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 7 - 8 ] : 2 : 7 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 16 ] : 2 : 12 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 12 ] : 2 : 10 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 10 ] : 2 : 9 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 11 - 12 ] : 2 : 11 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 16 ] : 2 : 14 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 14 ] : 2 : 13 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 15 - 16 ] : 2 : 15 : 1


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 45952
number of partitions: 16
number of swaps: 33
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : ix : sw
[2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : 8 : 8
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 8 ] : 1 : 0 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 1 - 8 ] : 2 : 4 : 4
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 1 - 4 ] : 2 : 2 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 1 - 2 ] : 2 : 1 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 3 - 4 ] : 2 : 3 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 8 ] : 2 : 6 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 6 ] : 2 : 5 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 7 - 8 ] : 2 : 7 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 16 ] : 2 : 12 : 4
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 12 ] : 2 : 10 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 10 ] : 2 : 9 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 11 - 12 ] : 2 : 11 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 16 ] : 2 : 14 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 14 ] : 2 : 13 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 15 - 16 ] : 2 : 15 : 1


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 50081
number of partitions: 16
number of swaps: 32
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : ix : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : 7 : 8
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 7 ] : 2 : 3 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 3 ] : 2 : 1 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 1 ] : 2 : 0 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 2 - 3 ] : 2 : 2 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 4 - 7 ] : 2 : 5 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 4 - 5 ] : 2 : 4 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 6 - 7 ] : 2 : 6 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 16 ] : 2 : 12 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 12 ] : 2 : 10 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 10 ] : 2 : 9 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 9 ] : 2 : 8 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 11 - 12 ] : 2 : 11 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 13 - 16 ] : 3 : 15 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 13 - 15 ] : 2 : 14 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 13 - 14 ] : 2 : 13 : 1


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 55932
number of partitions: 17
number of swaps: 31
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ lo - hi ] : pv : ix : sw
[2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 0 - 17 ] : 2 : 15 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 15 ] : 1 : 7 : 8
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 7 ] : 1 : 3 : 4
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 2 - 3 ] : 1 : 2 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 4 - 7 ] : 1 : 5 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 4 - 5 ] : 1 : 4 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 6 - 7 ] : 1 : 6 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 8 - 15 ] : 1 : 11 : 3
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 8 - 11 ] : 1 : 9 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 8 - 9 ] : 1 : 8 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 10 - 11 ] : 1 : 10 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 12 - 15 ] : 1 : 13 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 12 - 13 ] : 1 : 12 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 15 ] : 1 : 14 : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 16 - 17 ] : 100 : 16 : 1


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 59152
number of partitions: 17
number of swaps: 30
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2] : [ lo - hi ] : pv : ix : sw
[2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100] : [ 0 - 17 ] : 100 : 16 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 16 ] : 1 : 7 : 8
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 7 ] : 1 : 3 : 4
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 2 - 3 ] : 1 : 2 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 4 - 7 ] : 1 : 5 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 4 - 5 ] : 1 : 4 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 6 - 7 ] : 1 : 6 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 8 - 16 ] : 2 : 15 : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 8 - 15 ] : 1 : 11 : 3
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 8 - 11 ] : 1 : 9 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 8 - 9 ] : 1 : 8 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 10 - 11 ] : 1 : 10 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 12 - 15 ] : 1 : 13 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 12 - 13 ] : 1 : 12 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 15 ] : 1 : 14 : 0


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 59762
number of partitions: 18
number of swaps: 15
[16, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ lo - hi ] : pv : ix : sw
[9, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 18 ] : 9 : 10 : 2
[7, 6, 0, 1, 2, 3, 4, 5, 9, 9, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 10 ] : 7 : 7 : 2
[4, 3, 0, 1, 2, 6, 7, 5, 9, 9, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 7 ] : 4 : 4 : 2
[1, 0, 3, 4, 2, 6, 7, 5, 9, 9, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 4 ] : 1 : 1 : 2
[0, 1, 3, 4, 2, 6, 7, 5, 9, 9, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 1 ] : 1 : 0 : 1
[0, 1, 3, 2, 4, 6, 7, 5, 9, 9, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 2 - 4 ] : 4 : 3 : 1
[0, 1, 2, 3, 4, 6, 7, 5, 9, 9, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 2 - 3 ] : 3 : 2 : 1
[0, 1, 2, 3, 4, 6, 5, 7, 9, 9, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 5 - 7 ] : 7 : 6 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 9, 9, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 5 - 6 ] : 6 : 5 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 8 - 10 ] : 9 : 9 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 8 - 9 ] : 8 : 8 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 11 - 18 ] : 16 : 17 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 11 - 17 ] : 14 : 16 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 11 - 16 ] : 13 : 15 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 11 - 15 ] : 12 : 14 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 11 - 14 ] : 11 : 13 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 11 - 13 ] : 10 : 12 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 11 - 12 ] : 9 : 11 : 0

total duration [ns]:
14365948


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 3535
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 2538
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 2524
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 32706
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2] : [ 0 - 1 ] : 2 : 0 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 12215
number of partitions: 3
number of swaps: 0
[1, 2, 3] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 3 : 2 : 0
[1, 2, 3] : [ 0 - 2 ] : 1 : 0 : 0
[1, 2, 3] : [ 1 - 2 ] : 2 : 1 : 0


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 9149
number of partitions: 2
number of swaps: 1
[3, 2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : 1 : 1
[1, 2, 3] : [ 0 - 1 ] : 1 : 0 : 0


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 12420
number of partitions: 3
number of swaps: 4
[1, 1, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1] : [ 2 - 3 ] : 1 : 2 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 18285
number of partitions: 5
number of swaps: 4
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : ix : sw
[1, 2, 3, 3, 3, 3] : [ 0 - 5 ] : 3 : 3 : 2
[1, 2, 3, 3, 3, 3] : [ 0 - 3 ] : 3 : 2 : 1
[1, 2, 3, 3, 3, 3] : [ 0 - 2 ] : 1 : 0 : 0
[1, 2, 3, 3, 3, 3] : [ 1 - 2 ] : 2 : 1 : 0
[1, 2, 3, 3, 3, 3] : [ 4 - 5 ] : 3 : 4 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 18032
number of partitions: 8
number of swaps: 6
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 2, 3, 1, 2, 1, 4] : [ 0 - 6 ] : 4 : 5 : 1
[1, 1, 3, 2, 2, 1, 4] : [ 0 - 5 ] : 1 : 1 : 2
[1, 1, 3, 2, 2, 1, 4] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 2, 2, 3, 4] : [ 2 - 5 ] : 2 : 3 : 2
[1, 1, 1, 2, 2, 3, 4] : [ 2 - 3 ] : 1 : 2 : 0
[1, 1, 1, 2, 2, 3, 4] : [ 4 - 5 ] : 3 : 5 : 0
[1, 1, 1, 2, 2, 3, 4] : [ 4 - 5 ] : 3 : 5 : 0
[1, 1, 1, 2, 2, 3, 4] : [ 4 - 5 ] : 2 : 4 : 0


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 22395
number of partitions: 7
number of swaps: 7
[1000000, 10001, 10, 30, 40, 50, 1, 999] : [ lo - hi ] : pv : ix : sw
[1, 10001, 10, 30, 40, 50, 1000000, 999] : [ 0 - 7 ] : 1 : 0 : 1
[1, 50, 10, 30, 40, 10001, 1000000, 999] : [ 1 - 7 ] : 50 : 4 : 1
[1, 30, 10, 50, 40, 10001, 1000000, 999] : [ 1 - 4 ] : 30 : 2 : 1
[1, 10, 30, 50, 40, 10001, 1000000, 999] : [ 1 - 2 ] : 10 : 1 : 1
[1, 10, 30, 40, 50, 10001, 1000000, 999] : [ 3 - 4 ] : 50 : 3 : 1
[1, 10, 30, 40, 50, 10001, 999, 1000000] : [ 5 - 7 ] : 1000000 : 6 : 1
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 5 - 6 ] : 999 : 5 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 45615
number of partitions: 16
number of swaps: 14
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 2 : 5 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 5 ] : 2 : 4 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 4 ] : 2 : 3 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 3 ] : 2 : 3 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 3 ] : 1 : 1 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 3 ] : 1 : 2 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 15 ] : 2 : 6 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 15 ] : 4 : 13 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 13 ] : 2 : 7 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 13 ] : 3 : 9 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 9 ] : 3 : 8 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 13 ] : 4 : 12 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 12 ] : 3 : 10 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 11 - 12 ] : 4 : 11 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 53147
number of partitions: 15
number of swaps: 21
[4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1] : [ lo - hi ] : pv : ix : sw
[1, 2, 1, 2, 4, 2, 3, 2, 1, 3, 2, 3, 4, 4, 4, 4] : [ 0 - 15 ] : 4 : 11 : 4
[1, 2, 1, 2, 3, 2, 2, 2, 1, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 11 ] : 3 : 9 : 2
[1, 1, 2, 2, 3, 2, 2, 2, 1, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 9 ] : 1 : 1 : 2
[1, 1, 2, 2, 3, 2, 2, 2, 1, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 2, 2, 2, 3, 2, 2, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 9 ] : 2 : 5 : 3
[1, 1, 1, 2, 2, 2, 3, 2, 2, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 5 ] : 1 : 2 : 0
[1, 1, 1, 2, 2, 2, 3, 2, 2, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 5 ] : 2 : 4 : 1
[1, 1, 1, 2, 2, 2, 3, 2, 2, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 4 ] : 2 : 3 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 9 ] : 2 : 7 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 7 ] : 2 : 6 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 9 ] : 3 : 8 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 3 : 10 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 12 - 15 ] : 4 : 13 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 12 - 13 ] : 4 : 12 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 91058
number of partitions: 20
number of swaps: 17
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 3 : 9 : 3
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 9 ] : 1 : 1 : 2
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 9 ] : 3 : 8 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 8 ] : 2 : 5 : 2
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 5 ] : 1 : 2 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 5 ] : 2 : 4 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 4 ] : 2 : 4 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 4 ] : 2 : 4 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 4 ] : 2 : 4 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 4 ] : 1 : 3 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 8 ] : 2 : 6 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 8 ] : 3 : 8 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 8 ] : 2 : 7 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 15 ] : 4 : 13 : 2
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 13 ] : 4 : 12 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 12 ] : 4 : 11 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 4 : 11 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 3 : 10 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 63700
number of partitions: 16
number of swaps: 33
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : ix : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : 8 : 8
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 8 ] : 2 : 4 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 4 ] : 2 : 2 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 2 ] : 2 : 1 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 1 ] : 2 : 0 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 3 - 4 ] : 2 : 3 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 8 ] : 2 : 6 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 6 ] : 2 : 5 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 7 - 8 ] : 2 : 7 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 16 ] : 2 : 12 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 12 ] : 2 : 10 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 10 ] : 2 : 9 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 11 - 12 ] : 2 : 11 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 16 ] : 2 : 14 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 14 ] : 2 : 13 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 15 - 16 ] : 2 : 15 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 52138
number of partitions: 16
number of swaps: 33
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : ix : sw
[2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : 8 : 8
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 8 ] : 1 : 0 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 1 - 8 ] : 2 : 4 : 4
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 1 - 4 ] : 2 : 2 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 1 - 2 ] : 2 : 1 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 3 - 4 ] : 2 : 3 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 8 ] : 2 : 6 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 5 - 6 ] : 2 : 5 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 7 - 8 ] : 2 : 7 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 16 ] : 2 : 12 : 4
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 12 ] : 2 : 10 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 9 - 10 ] : 2 : 9 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 11 - 12 ] : 2 : 11 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 16 ] : 2 : 14 : 2
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 13 - 14 ] : 2 : 13 : 1
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 15 - 16 ] : 2 : 15 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 62406
number of partitions: 16
number of swaps: 32
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : ix : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : 7 : 8
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 7 ] : 2 : 3 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 3 ] : 2 : 1 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 1 ] : 2 : 0 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 2 - 3 ] : 2 : 2 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 4 - 7 ] : 2 : 5 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 4 - 5 ] : 2 : 4 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ 6 - 7 ] : 2 : 6 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 16 ] : 2 : 12 : 4
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 12 ] : 2 : 10 : 2
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 10 ] : 2 : 9 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 8 - 9 ] : 2 : 8 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2] : [ 11 - 12 ] : 2 : 11 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 13 - 16 ] : 3 : 15 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 13 - 15 ] : 2 : 14 : 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 13 - 14 ] : 2 : 13 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 61276
number of partitions: 17
number of swaps: 31
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 0 - 17 ] : 1 : 7 : 8
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 0 - 7 ] : 1 : 3 : 4
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 2 - 3 ] : 1 : 2 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 4 - 7 ] : 1 : 5 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 4 - 5 ] : 1 : 4 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 6 - 7 ] : 1 : 6 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 8 - 17 ] : 1 : 11 : 3
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 8 - 11 ] : 1 : 9 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 8 - 9 ] : 1 : 8 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 10 - 11 ] : 1 : 10 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 12 - 17 ] : 1 : 13 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 12 - 13 ] : 1 : 12 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 14 - 17 ] : 100 : 16 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 14 - 16 ] : 1 : 14 : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 15 - 16 ] : 100 : 15 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 66961
number of partitions: 19
number of swaps: 30
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 0 - 17 ] : 1 : 7 : 8
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 0 - 7 ] : 1 : 3 : 4
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 2 - 3 ] : 1 : 2 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 4 - 7 ] : 1 : 5 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 4 - 5 ] : 1 : 4 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 6 - 7 ] : 1 : 6 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 8 - 17 ] : 1 : 11 : 3
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 8 - 11 ] : 1 : 9 : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 8 - 9 ] : 1 : 8 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 10 - 11 ] : 1 : 10 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 12 - 17 ] : 1 : 13 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100, 2] : [ 12 - 13 ] : 1 : 12 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 17 ] : 100 : 16 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 16 ] : 100 : 16 : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 16 ] : 1 : 14 : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 15 - 16 ] : 100 : 16 : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 15 - 16 ] : 2 : 15 : 0


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 70823
number of partitions: 21
number of swaps: 15
[16, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ lo - hi ] : pv : ix : sw
[9, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 18 ] : 11 : 13 : 1
[0, 9, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 13 ] : 0 : 0 : 1
[0, 2, 1, 9, 9, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 1 - 13 ] : 2 : 2 : 2
[0, 1, 2, 9, 9, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 1 - 2 ] : 1 : 1 : 1
[0, 1, 2, 8, 7, 3, 4, 5, 6, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 3 - 13 ] : 8 : 8 : 2
[0, 1, 2, 6, 5, 3, 4, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 3 - 8 ] : 6 : 6 : 2
[0, 1, 2, 3, 5, 6, 4, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 3 - 6 ] : 3 : 3 : 1
[0, 1, 2, 3, 5, 4, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 4 - 6 ] : 6 : 5 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 4 - 5 ] : 5 : 4 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 7 - 8 ] : 8 : 8 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 7 - 8 ] : 7 : 7 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 9 - 13 ] : 10 : 12 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 9 - 12 ] : 9 : 10 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 9 - 10 ] : 9 : 9 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 11 - 12 ] : 10 : 12 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 11 - 12 ] : 9 : 11 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 14 - 18 ] : 13 : 15 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 14 - 15 ] : 13 : 15 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 14 - 15 ] : 12 : 14 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 16 - 18 ] : 16 : 17 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 16 - 17 ] : 14 : 16 : 0

total duration [ns]:
11001753


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 6604
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 1721
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 1733
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 57633
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2] : [ 0 - 1 ] : 2 : [ 1 - 2 ] : 1


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 16930
number of partitions: 2
number of swaps: 2
[1, 2, 3] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 3, 2] : [ 0 - 2 ] : 1 : [ 0 - 1 ] : 1
[1, 2, 3] : [ 1 - 2 ] : 3 : [ 2 - 3 ] : 1


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 13332
number of partitions: 2
number of swaps: 3
[3, 2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 1, 3] : [ 0 - 2 ] : 3 : [ 2 - 3 ] : 2
[1, 2, 3] : [ 0 - 1 ] : 2 : [ 1 - 2 ] : 1


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 9779
number of partitions: 1
number of swaps: 0
[1, 1, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : [ 0 - 4 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 11249
number of partitions: 2
number of swaps: 5
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 3, 3, 3, 2, 3] : [ 0 - 5 ] : 1 : [ 0 - 1 ] : 4
[1, 2, 3, 3, 3, 3] : [ 1 - 5 ] : 3 : [ 2 - 6 ] : 1


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 13816
number of partitions: 3
number of swaps: 4
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 2, 4, 3, 2] : [ 0 - 6 ] : 1 : [ 0 - 3 ] : 3
[1, 1, 1, 2, 2, 3, 4] : [ 3 - 6 ] : 2 : [ 3 - 5 ] : 1
[1, 1, 1, 2, 2, 3, 4] : [ 5 - 6 ] : 3 : [ 5 - 6 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 50960
number of partitions: 5
number of swaps: 21
[1000000, 10001, 10, 30, 40, 50, 1, 999] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[10001, 10, 30, 40, 50, 1, 999, 1000000] : [ 0 - 7 ] : 1000000 : [ 7 - 8 ] : 7
[10, 30, 40, 50, 1, 999, 10001, 1000000] : [ 0 - 6 ] : 10001 : [ 6 - 7 ] : 6
[1, 10, 50, 40, 999, 30, 10001, 1000000] : [ 0 - 5 ] : 10 : [ 1 - 2 ] : 4
[1, 10, 40, 30, 50, 999, 10001, 1000000] : [ 2 - 5 ] : 50 : [ 4 - 5 ] : 3
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 2 - 3 ] : 40 : [ 3 - 4 ] : 1


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 32859
number of partitions: 4
number of swaps: 23
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 2] : [ 0 - 15 ] : 1 : [ 0 - 3 ] : 12
[1, 1, 1, 2, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4, 3, 3] : [ 3 - 15 ] : 2 : [ 3 - 8 ] : 7
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 15 ] : 3 : [ 8 - 11 ] : 4
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 11 - 15 ] : 4 : [ 11 - 16 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 35283
number of partitions: 4
number of swaps: 17
[4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 1, 2, 2, 2, 3, 2, 3, 1, 3, 1, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 4 : [ 11 - 16 ] : 11
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 10 ] : 2 : [ 3 - 8 ] : 6
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 2 ] : 1 : [ 0 - 3 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 10 ] : 3 : [ 8 - 11 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 34762
number of partitions: 4
number of swaps: 22
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 2, 4, 4] : [ 0 - 15 ] : 1 : [ 0 - 4 ] : 11
[1, 1, 1, 1, 2, 2, 2, 2, 3, 4, 4, 4, 3, 4, 4, 3] : [ 4 - 15 ] : 2 : [ 4 - 8 ] : 7
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 15 ] : 3 : [ 8 - 11 ] : 4
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 11 - 15 ] : 4 : [ 11 - 16 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 11566
number of partitions: 1
number of swaps: 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 0 - 17 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 10829
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 1 - 17 ] : 1


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 13909
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 0 - 16 ] : 2 : [ 0 - 16 ] : 1


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 23164
number of partitions: 2
number of swaps: 16
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 17 ] : 100 : [ 16 - 18 ] : 16
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 15 ] : 1 : [ 0 - 15 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 22947
number of partitions: 2
number of swaps: 16
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 17 ] : 100 : [ 16 - 18 ] : 16
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 15 ] : 1 : [ 0 - 15 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 79183
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

total duration [ns]:
10620498


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 16744
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 1999
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 2653
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 30311
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2] : [ 0 - 1 ] : 2 : [ 1 - 2 ] : 1


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 7558
number of partitions: 1
number of swaps: 0
[1, 2, 3] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : [ 1 - 2 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 6290
number of partitions: 1
number of swaps: 1
[3, 2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : [ 1 - 2 ] : 1


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 8420
number of partitions: 1
number of swaps: 0
[1, 1, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : [ 0 - 4 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 10918
number of partitions: 2
number of swaps: 1
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3, 3, 3, 3] : [ 0 - 5 ] : 3 : [ 2 - 6 ] : 1
[1, 2, 3, 3, 3, 3] : [ 0 - 1 ] : 1 : [ 0 - 1 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 15461
number of partitions: 4
number of swaps: 8
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3, 2, 1, 1, 4] : [ 0 - 6 ] : 4 : [ 6 - 7 ] : 3
[1, 2, 2, 1, 1, 3, 4] : [ 0 - 5 ] : 3 : [ 5 - 6 ] : 3
[1, 1, 1, 2, 2, 3, 4] : [ 0 - 4 ] : 2 : [ 3 - 5 ] : 2
[1, 1, 1, 2, 2, 3, 4] : [ 0 - 2 ] : 1 : [ 0 - 3 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 19430
number of partitions: 4
number of swaps: 7
[1000000, 10001, 10, 30, 40, 50, 1, 999] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 10, 30, 40, 50, 10001, 999, 1000000] : [ 0 - 7 ] : 30 : [ 2 - 3 ] : 6
[1, 10, 30, 40, 50, 10001, 999, 1000000] : [ 0 - 1 ] : 1 : [ 0 - 1 ] : 0
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 3 - 7 ] : 10001 : [ 6 - 7 ] : 1
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 3 - 5 ] : 50 : [ 4 - 5 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 19185
number of partitions: 4
number of swaps: 8
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 3] : [ 0 - 15 ] : 2 : [ 3 - 8 ] : 7
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 3] : [ 0 - 2 ] : 1 : [ 0 - 3 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 15 ] : 4 : [ 11 - 16 ] : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 10 ] : 3 : [ 8 - 11 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 19084
number of partitions: 4
number of swaps: 11
[4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 2, 2, 2, 2, 2, 3, 4, 3, 4, 3, 4, 4, 4] : [ 0 - 15 ] : 2 : [ 3 - 8 ] : 9
[1, 1, 1, 2, 2, 2, 2, 2, 3, 4, 3, 4, 3, 4, 4, 4] : [ 0 - 2 ] : 1 : [ 0 - 3 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 15 ] : 4 : [ 11 - 16 ] : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 10 ] : 3 : [ 8 - 11 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 16207
number of partitions: 4
number of swaps: 8
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 4, 3, 4, 4] : [ 0 - 15 ] : 2 : [ 4 - 8 ] : 7
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 4, 3, 4, 4] : [ 0 - 3 ] : 1 : [ 0 - 4 ] : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 15 ] : 4 : [ 11 - 16 ] : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 10 ] : 3 : [ 8 - 11 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 12371
number of partitions: 1
number of swaps: 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 0 - 17 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 10924
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 1 - 17 ] : 1


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 11699
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 0 - 16 ] : 2 : [ 0 - 16 ] : 1


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 17788
number of partitions: 2
number of swaps: 3
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 17 ] : 1 : [ 0 - 15 ] : 3
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 15 - 17 ] : 100 : [ 16 - 18 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 25054
number of partitions: 3
number of swaps: 5
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 0 - 17 ] : 1 : [ 0 - 15 ] : 3
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 15 - 17 ] : 2 : [ 15 - 16 ] : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 16 - 17 ] : 100 : [ 16 - 18 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 50310
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

total duration [ns]:
7128165


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 2243
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 2105
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 2002
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 13387
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2] : median swaps : 1
[1, 2] : [ 0 - 1 ] : 2 : [ 1 - 2 ] : 0


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 9957
number of partitions: 1
number of swaps: 2
[1, 2, 3] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 3, 2] : median swaps : 1
[1, 2, 3] : [ 0 - 2 ] : 2 : [ 1 - 2 ] : 1


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 15963
number of partitions: 2
number of swaps: 3
[3, 2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 3, 1] : median swaps : 1
[1, 3, 2] : [ 0 - 2 ] : 1 : [ 0 - 1 ] : 1
[1, 2, 3] : median swaps : 1
[1, 2, 3] : [ 1 - 2 ] : 3 : [ 2 - 3 ] : 0


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 12138
number of partitions: 1
number of swaps: 0
[1, 1, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1] : median swaps : 0
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : [ 0 - 4 ] : 0


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 18196
number of partitions: 2
number of swaps: 3
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 3, 3, 3, 3, 2] : median swaps : 0
[1, 2, 3, 3, 3, 3] : [ 0 - 5 ] : 2 : [ 1 - 2 ] : 3
[1, 2, 3, 3, 3, 3] : median swaps : 0
[1, 2, 3, 3, 3, 3] : [ 2 - 5 ] : 3 : [ 2 - 6 ] : 0


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 22097
number of partitions: 3
number of swaps: 6
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3, 4, 2, 1, 1] : median swaps : 0
[1, 1, 1, 2, 4, 3, 2] : [ 0 - 6 ] : 1 : [ 0 - 3 ] : 3
[1, 1, 1, 2, 4, 3, 2] : median swaps : 0
[1, 1, 1, 2, 2, 3, 4] : [ 3 - 6 ] : 2 : [ 3 - 5 ] : 1
[1, 1, 1, 2, 2, 4, 3] : median swaps : 1
[1, 1, 1, 2, 2, 3, 4] : [ 5 - 6 ] : 3 : [ 5 - 6 ] : 1


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 39150
number of partitions: 5
number of swaps: 13
[1000000, 10001, 10, 30, 40, 50, 1, 999] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[30, 10001, 10, 1000000, 40, 50, 1, 999] : median swaps : 1
[30, 10, 1, 40, 50, 999, 1000000, 10001] : [ 0 - 7 ] : 999 : [ 5 - 6 ] : 6
[1, 10, 30, 40, 50, 999, 1000000, 10001] : median swaps : 1
[1, 10, 30, 40, 50, 999, 1000000, 10001] : [ 0 - 4 ] : 50 : [ 4 - 5 ] : 0
[1, 40, 30, 10, 50, 999, 1000000, 10001] : median swaps : 1
[1, 10, 30, 40, 50, 999, 1000000, 10001] : [ 0 - 3 ] : 10 : [ 1 - 2 ] : 1
[1, 10, 40, 30, 50, 999, 1000000, 10001] : median swaps : 1
[1, 10, 30, 40, 50, 999, 1000000, 10001] : [ 2 - 3 ] : 30 : [ 2 - 3 ] : 1
[1, 10, 30, 40, 50, 999, 10001, 1000000] : median swaps : 1
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 6 - 7 ] : 1000000 : [ 7 - 8 ] : 0


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 33084
number of partitions: 4
number of swaps: 9
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 2, 2, 2, 2, 4, 3, 3, 3, 4, 4, 4, 4, 2] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 3, 4] : [ 0 - 15 ] : 2 : [ 3 - 8 ] : 7
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 3, 4] : median swaps : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 3, 4] : [ 0 - 2 ] : 1 : [ 0 - 3 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 3, 4] : median swaps : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 15 ] : 4 : [ 11 - 16 ] : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 10 ] : 3 : [ 8 - 11 ] : 0


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 34814
number of partitions: 4
number of swaps: 22
[4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 1, 2, 4, 2, 4, 4, 4, 3, 2, 3, 1, 3, 4, 1] : median swaps : 1
[1, 1, 1, 4, 2, 4, 4, 4, 3, 2, 3, 2, 3, 4, 2, 2] : [ 0 - 15 ] : 1 : [ 0 - 3 ] : 12
[1, 1, 1, 2, 2, 4, 4, 4, 3, 4, 3, 2, 3, 4, 2, 2] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 2, 4, 3, 3, 3, 4, 4, 4, 4] : [ 3 - 15 ] : 2 : [ 3 - 8 ] : 7
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 15 ] : 4 : [ 11 - 16 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 10 ] : 3 : [ 8 - 11 ] : 0


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 35158
number of partitions: 4
number of swaps: 17
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : median swaps : 0
[1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 2, 4, 4] : [ 0 - 15 ] : 1 : [ 0 - 4 ] : 11
[1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 4, 2, 4, 3] : median swaps : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 15 ] : 3 : [ 8 - 11 ] : 5
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 7 ] : 2 : [ 4 - 8 ] : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : median swaps : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 11 - 15 ] : 4 : [ 11 - 16 ] : 0


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 14572
number of partitions: 1
number of swaps: 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 0 - 17 ] : 0


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 14357
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 1 - 17 ] : 1


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 11161
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 0 - 16 ] : 2 : [ 0 - 16 ] : 1


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 21336
number of partitions: 2
number of swaps: 11
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 100, 1, 1, 1, 1, 1, 1, 1, 2, 100] : median swaps : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 17 ] : 100 : [ 16 - 18 ] : 8
[1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 15 ] : 1 : [ 0 - 15 ] : 1


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 28108
number of partitions: 3
number of swaps: 9
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 100, 1, 1, 1, 1, 1, 1, 1, 100, 2] : median swaps : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 17 ] : 2 : [ 15 - 16 ] : 8
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 14 ] : 1 : [ 0 - 15 ] : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : median swaps : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 16 - 17 ] : 100 : [ 16 - 18 ] : 0


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 76432
number of partitions: 10
number of swaps: 37
[16, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[7, 9, 0, 1, 2, 3, 4, 5, 6, 16, 8, 9, 10, 11, 12, 13, 14, 9, 16] : median swaps : 1
[7, 9, 0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 14, 9, 16, 16] : [ 0 - 18 ] : 16 : [ 17 - 19 ] : 8
[6, 9, 0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16, 16] : median swaps : 1
[6, 0, 1, 2, 3, 4, 5, 7, 8, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : [ 0 - 16 ] : 9 : [ 9 - 12 ] : 12
[3, 0, 1, 2, 6, 4, 5, 7, 8, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : median swaps : 1
[3, 0, 1, 2, 6, 4, 5, 7, 8, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : [ 0 - 8 ] : 8 : [ 8 - 9 ] : 0
[2, 0, 1, 3, 6, 4, 5, 7, 8, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : median swaps : 1
[2, 0, 1, 3, 6, 4, 5, 7, 8, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : [ 0 - 7 ] : 7 : [ 7 - 8 ] : 0
[2, 0, 1, 5, 6, 4, 3, 7, 8, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : median swaps : 1
[2, 0, 1, 3, 4, 6, 5, 7, 8, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : [ 0 - 6 ] : 3 : [ 3 - 4 ] : 2
[0, 2, 1, 3, 4, 6, 5, 7, 8, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : median swaps : 1
[0, 1, 2, 3, 4, 6, 5, 7, 8, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : [ 0 - 2 ] : 1 : [ 1 - 2 ] : 1
[0, 1, 2, 3, 4, 6, 5, 7, 8, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : median swaps : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 12, 13, 14, 11, 10, 16, 16] : [ 4 - 6 ] : 5 : [ 5 - 6 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 13, 14, 11, 12, 16, 16] : median swaps : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 14, 13, 16, 16] : [ 12 - 16 ] : 12 : [ 14 - 15 ] : 3
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 10, 12, 14, 13, 16, 16] : median swaps : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 14, 13, 16, 16] : [ 12 - 13 ] : 10 : [ 12 - 13 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : median swaps : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 15 - 16 ] : 14 : [ 16 - 17 ] : 0

total duration [ns]:
7468940


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 3476
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 1605
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 1866
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 10459
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2] : [ 0 - 1 ] : 2 : [ 1 - 2 ] : 1


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 7803
number of partitions: 1
number of swaps: 0
[1, 2, 3] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : [ 1 - 2 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 7168
number of partitions: 1
number of swaps: 1
[3, 2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : [ 1 - 2 ] : 1


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 6502
number of partitions: 1
number of swaps: 0
[1, 1, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : [ 0 - 4 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 13025
number of partitions: 2
number of swaps: 1
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3, 3, 3, 3] : [ 0 - 5 ] : 3 : [ 2 - 6 ] : 1
[1, 2, 3, 3, 3, 3] : [ 0 - 1 ] : 1 : [ 0 - 1 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 15347
number of partitions: 3
number of swaps: 4
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 2, 4, 3, 2] : [ 0 - 6 ] : 1 : [ 0 - 3 ] : 3
[1, 1, 1, 2, 2, 3, 4] : [ 3 - 6 ] : 3 : [ 5 - 6 ] : 1
[1, 1, 1, 2, 2, 3, 4] : [ 3 - 4 ] : 2 : [ 3 - 5 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 25426
number of partitions: 5
number of swaps: 9
[1000000, 10001, 10, 30, 40, 50, 1, 999] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 10, 30, 40, 50, 10001, 999, 1000000] : [ 0 - 7 ] : 1 : [ 0 - 1 ] : 6
[1, 10, 30, 40, 50, 999, 1000000, 10001] : [ 1 - 7 ] : 999 : [ 5 - 6 ] : 2
[1, 10, 30, 40, 50, 999, 1000000, 10001] : [ 1 - 4 ] : 40 : [ 3 - 4 ] : 0
[1, 10, 30, 40, 50, 999, 1000000, 10001] : [ 1 - 2 ] : 10 : [ 1 - 2 ] : 0
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 6 - 7 ] : 1000000 : [ 7 - 8 ] : 1


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 24683
number of partitions: 4
number of swaps: 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 4 : [ 11 - 16 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 10 ] : 3 : [ 8 - 11 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 7 ] : 2 : [ 3 - 8 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 2 ] : 1 : [ 0 - 3 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 40798
number of partitions: 4
number of swaps: 18
[4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 1, 2, 2, 2, 3, 2, 3, 1, 3, 1, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 4 : [ 11 - 16 ] : 11
[2, 1, 2, 2, 2, 2, 1, 1, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 10 ] : 3 : [ 8 - 11 ] : 3
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 7 ] : 1 : [ 0 - 3 ] : 4
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 7 ] : 2 : [ 3 - 8 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 24465
number of partitions: 4
number of swaps: 13
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 2, 4, 4] : [ 0 - 15 ] : 1 : [ 0 - 4 ] : 11
[1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 2, 4, 4, 4, 4, 4] : [ 4 - 15 ] : 4 : [ 11 - 16 ] : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 10 ] : 3 : [ 8 - 11 ] : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 7 ] : 2 : [ 4 - 8 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 9837
number of partitions: 1
number of swaps: 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 0 - 17 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 10882
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 1 - 17 ] : 1


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 11844
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 0 - 16 ] : 2 : [ 0 - 16 ] : 1


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 20668
number of partitions: 3
number of swaps: 17
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 17 ] : 2 : [ 15 - 16 ] : 17
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 14 ] : 1 : [ 0 - 15 ] : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 16 - 17 ] : 100 : [ 16 - 18 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 17018
number of partitions: 2
number of swaps: 16
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 17 ] : 100 : [ 16 - 18 ] : 16
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 15 ] : 1 : [ 0 - 15 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 48318
number of partitions: 10
number of swaps: 19
[16, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 12, 13, 14, 10, 16, 16] : [ 0 - 18 ] : 9 : [ 9 - 12 ] : 15
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 12, 13, 14, 10, 16, 16] : [ 0 - 8 ] : 7 : [ 7 - 8 ] : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 12, 13, 14, 10, 16, 16] : [ 0 - 6 ] : 5 : [ 5 - 6 ] : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 12, 13, 14, 10, 16, 16] : [ 0 - 4 ] : 3 : [ 3 - 4 ] : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 12, 13, 14, 10, 16, 16] : [ 0 - 2 ] : 1 : [ 1 - 2 ] : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 12, 13, 14, 10, 16, 16] : [ 12 - 18 ] : 16 : [ 17 - 19 ] : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 12, 13, 10, 14, 16, 16] : [ 12 - 16 ] : 14 : [ 16 - 17 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 12, 10, 13, 14, 16, 16] : [ 12 - 15 ] : 13 : [ 15 - 16 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 10, 12, 13, 14, 16, 16] : [ 12 - 14 ] : 12 : [ 14 - 15 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 12 - 13 ] : 11 : [ 13 - 14 ] : 1

total duration [ns]:
7237255


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 2336
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 2020
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 2189
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 12526
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2] : [ 0 - 1 ] : 1 : [ 0 - 1 ] : 1


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 12692
number of partitions: 2
number of swaps: 0
[1, 2, 3] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3] : [ 0 - 2 ] : 3 : [ 2 - 3 ] : 0
[1, 2, 3] : [ 0 - 1 ] : 2 : [ 1 - 2 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 13681
number of partitions: 2
number of swaps: 1
[3, 2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3] : [ 0 - 2 ] : 1 : [ 0 - 1 ] : 1
[1, 2, 3] : [ 1 - 2 ] : 3 : [ 2 - 3 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 9008
number of partitions: 1
number of swaps: 0
[1, 1, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : [ 0 - 4 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 15239
number of partitions: 2
number of swaps: 1
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3, 3, 3, 3] : [ 0 - 5 ] : 3 : [ 2 - 6 ] : 1
[1, 2, 3, 3, 3, 3] : [ 0 - 1 ] : 2 : [ 1 - 2 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 19896
number of partitions: 3
number of swaps: 6
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3, 2, 1, 1, 4] : [ 0 - 6 ] : 4 : [ 6 - 7 ] : 3
[1, 1, 1, 2, 3, 2, 4] : [ 0 - 5 ] : 1 : [ 0 - 3 ] : 2
[1, 1, 1, 2, 2, 3, 4] : [ 3 - 5 ] : 2 : [ 3 - 5 ] : 1


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 32278
number of partitions: 5
number of swaps: 17
[1000000, 10001, 10, 30, 40, 50, 1, 999] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[999, 10, 30, 40, 50, 1, 10001, 1000000] : [ 0 - 7 ] : 10001 : [ 6 - 7 ] : 6
[1, 30, 40, 50, 10, 999, 10001, 1000000] : [ 0 - 5 ] : 1 : [ 0 - 1 ] : 4
[1, 10, 50, 40, 999, 30, 10001, 1000000] : [ 1 - 5 ] : 10 : [ 1 - 2 ] : 3
[1, 10, 40, 30, 50, 999, 10001, 1000000] : [ 2 - 5 ] : 50 : [ 4 - 5 ] : 3
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 2 - 3 ] : 30 : [ 2 - 3 ] : 1


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 37326
number of partitions: 4
number of swaps: 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 4 : [ 11 - 16 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 10 ] : 2 : [ 3 - 8 ] : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 2 ] : 1 : [ 0 - 3 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 10 ] : 3 : [ 8 - 11 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 29386
number of partitions: 4
number of swaps: 20
[4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 4, 2, 4, 2, 4, 3, 2, 3, 2, 3, 4, 2, 4] : [ 0 - 15 ] : 1 : [ 0 - 3 ] : 12
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 15 ] : 3 : [ 8 - 11 ] : 8
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 7 ] : 2 : [ 3 - 8 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 11 - 15 ] : 4 : [ 11 - 16 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 26233
number of partitions: 4
number of swaps: 13
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 2, 2, 2, 2, 3, 3, 3, 1, 1, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 4 : [ 11 - 16 ] : 9
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 10 ] : 2 : [ 4 - 8 ] : 4
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 3 ] : 1 : [ 0 - 4 ] : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 10 ] : 3 : [ 8 - 11 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 9843
number of partitions: 1
number of swaps: 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 0 - 17 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 16588
number of partitions: 2
number of swaps: 15
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 1 : [ 0 - 1 ] : 15
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 1 - 16 ] : 2 : [ 1 - 17 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 10161
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 0 - 16 ] : 2 : [ 0 - 16 ] : 1


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 21003
number of partitions: 3
number of swaps: 17
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 17 ] : 2 : [ 15 - 16 ] : 17
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 14 ] : 1 : [ 0 - 15 ] : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 16 - 17 ] : 100 : [ 16 - 18 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 28700
number of partitions: 2
number of swaps: 4
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 0 - 17 ] : 1 : [ 0 - 15 ] : 3
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 15 - 17 ] : 100 : [ 16 - 18 ] : 1


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 59704
number of partitions: 11
number of swaps: 28
[16, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 12, 13, 14, 10, 16, 16] : [ 0 - 18 ] : 9 : [ 9 - 12 ] : 15
[0, 1, 2, 3, 4, 5, 7, 8, 6, 9, 9, 9, 11, 12, 13, 14, 10, 16, 16] : [ 0 - 8 ] : 5 : [ 5 - 6 ] : 2
[0, 1, 3, 4, 2, 5, 7, 8, 6, 9, 9, 9, 11, 12, 13, 14, 10, 16, 16] : [ 0 - 4 ] : 1 : [ 1 - 2 ] : 2
[0, 1, 2, 3, 4, 5, 7, 8, 6, 9, 9, 9, 11, 12, 13, 14, 10, 16, 16] : [ 2 - 4 ] : 3 : [ 3 - 4 ] : 2
[0, 1, 2, 3, 4, 5, 7, 6, 8, 9, 9, 9, 11, 12, 13, 14, 10, 16, 16] : [ 6 - 8 ] : 8 : [ 8 - 9 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 12, 13, 14, 10, 16, 16] : [ 6 - 7 ] : 7 : [ 7 - 8 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 12, 13, 10, 14, 16, 16] : [ 12 - 18 ] : 14 : [ 16 - 17 ] : 2
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 11, 12, 10, 13, 14, 16, 16] : [ 12 - 15 ] : 13 : [ 15 - 16 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 12, 11, 13, 14, 16, 16] : [ 12 - 14 ] : 10 : [ 12 - 13 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 13 - 14 ] : 12 : [ 14 - 15 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 17 - 18 ] : 16 : [ 17 - 19 ] : 0

total duration [ns]:
7840105



--------------------------------------------------------------------------------
QUICKSORT: 10 x array[100] - ORDERED
--------------------------------------------------------------------------------
original:
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100]
sorted:
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100]

name: ORDERED - partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 83133
loop: #1 - LOW - duration [ns]: 45863
loop: #2 - LOW - duration [ns]: 81409
loop: #3 - LOW - duration [ns]: 40696
loop: #4 - LOW - duration [ns]: 30443
loop: #5 - LOW - duration [ns]: 62313
loop: #6 - LOW - duration [ns]: 33584
loop: #7 - LOW - duration [ns]: 33591
loop: #8 - LOW - duration [ns]: 31852
loop: #9 - LOW - duration [ns]: 30614

name: ORDERED - partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 30280
loop: #1 - MID - duration [ns]: 39255
loop: #2 - MID - duration [ns]: 35698
loop: #3 - MID - duration [ns]: 25570
loop: #4 - MID - duration [ns]: 25886
loop: #5 - MID - duration [ns]: 23672
loop: #6 - MID - duration [ns]: 28615
loop: #7 - MID - duration [ns]: 16371
loop: #8 - MID - duration [ns]: 15921
loop: #9 - MID - duration [ns]: 16908

name: ORDERED - partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 52119
loop: #1 - MEDIAN - duration [ns]: 56134
loop: #2 - MEDIAN - duration [ns]: 56506
loop: #3 - MEDIAN - duration [ns]: 52713
loop: #4 - MEDIAN - duration [ns]: 58557
loop: #5 - MEDIAN - duration [ns]: 60360
loop: #6 - MEDIAN - duration [ns]: 33361
loop: #7 - MEDIAN - duration [ns]: 14134
loop: #8 - MEDIAN - duration [ns]: 13659
loop: #9 - MEDIAN - duration [ns]: 13502

name: ORDERED - partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 44182
loop: #1 - HIGH - duration [ns]: 36283
loop: #2 - HIGH - duration [ns]: 25878
loop: #3 - HIGH - duration [ns]: 26043
loop: #4 - HIGH - duration [ns]: 24759
loop: #5 - HIGH - duration [ns]: 25729
loop: #6 - HIGH - duration [ns]: 33352
loop: #7 - HIGH - duration [ns]: 30902
loop: #8 - HIGH - duration [ns]: 31100
loop: #9 - HIGH - duration [ns]: 42772

name: ORDERED - partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 89460
loop: #1 - RANDOM - duration [ns]: 106031
loop: #2 - RANDOM - duration [ns]: 80578
loop: #3 - RANDOM - duration [ns]: 72926
loop: #4 - RANDOM - duration [ns]: 75225
loop: #5 - RANDOM - duration [ns]: 74495
loop: #6 - RANDOM - duration [ns]: 89628
loop: #7 - RANDOM - duration [ns]: 80274
loop: #8 - RANDOM - duration [ns]: 81093
loop: #9 - RANDOM - duration [ns]: 85939

name: ORDERED - partition: HOARE - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        47350
         MID |        25818
      MEDIAN |        41105
        HIGH |        32100
      RANDOM |        83565

name: ORDERED - partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 144713
loop: #1 - LOW - duration [ns]: 153126
loop: #2 - LOW - duration [ns]: 144232
loop: #3 - LOW - duration [ns]: 141496
loop: #4 - LOW - duration [ns]: 155327
loop: #5 - LOW - duration [ns]: 163654
loop: #6 - LOW - duration [ns]: 171389
loop: #7 - LOW - duration [ns]: 173739
loop: #8 - LOW - duration [ns]: 158886
loop: #9 - LOW - duration [ns]: 165054

name: ORDERED - partition: DNF - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 57443
loop: #1 - MID - duration [ns]: 23583
loop: #2 - MID - duration [ns]: 21600
loop: #3 - MID - duration [ns]: 20372
loop: #4 - MID - duration [ns]: 31565
loop: #5 - MID - duration [ns]: 44566
loop: #6 - MID - duration [ns]: 20803
loop: #7 - MID - duration [ns]: 23610
loop: #8 - MID - duration [ns]: 23361
loop: #9 - MID - duration [ns]: 20122

name: ORDERED - partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 64639
loop: #1 - MEDIAN - duration [ns]: 23128
loop: #2 - MEDIAN - duration [ns]: 37877
loop: #3 - MEDIAN - duration [ns]: 18781
loop: #4 - MEDIAN - duration [ns]: 21322
loop: #5 - MEDIAN - duration [ns]: 23163
loop: #6 - MEDIAN - duration [ns]: 21791
loop: #7 - MEDIAN - duration [ns]: 21843
loop: #8 - MEDIAN - duration [ns]: 20937
loop: #9 - MEDIAN - duration [ns]: 22396

name: ORDERED - partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 48039
loop: #1 - HIGH - duration [ns]: 43708
loop: #2 - HIGH - duration [ns]: 53760
loop: #3 - HIGH - duration [ns]: 44936
loop: #4 - HIGH - duration [ns]: 43062
loop: #5 - HIGH - duration [ns]: 43052
loop: #6 - HIGH - duration [ns]: 42379
loop: #7 - HIGH - duration [ns]: 44688
loop: #8 - HIGH - duration [ns]: 42170
loop: #9 - HIGH - duration [ns]: 42394

name: ORDERED - partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 45625
loop: #1 - RANDOM - duration [ns]: 25284
loop: #2 - RANDOM - duration [ns]: 23574
loop: #3 - RANDOM - duration [ns]: 23478
loop: #4 - RANDOM - duration [ns]: 26787
loop: #5 - RANDOM - duration [ns]: 24777
loop: #6 - RANDOM - duration [ns]: 27956
loop: #7 - RANDOM - duration [ns]: 28885
loop: #8 - RANDOM - duration [ns]: 27079
loop: #9 - RANDOM - duration [ns]: 33277

name: ORDERED - partition: DNF - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |       157162
         MID |        28703
      MEDIAN |        27588
        HIGH |        44819
      RANDOM |        28672


--------------------------------------------------------------------------------
QUICKSORT: 10 x array[100] - REVERSE
--------------------------------------------------------------------------------
original:
[100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83, 82, 81, 80, 79, 78, 77, 76, 75, 74, 73, 72, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
sorted:
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100]

name: REVERSE - partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 59691
loop: #1 - LOW - duration [ns]: 36622
loop: #2 - LOW - duration [ns]: 35847
loop: #3 - LOW - duration [ns]: 36248
loop: #4 - LOW - duration [ns]: 33189
loop: #5 - LOW - duration [ns]: 37962
loop: #6 - LOW - duration [ns]: 38281
loop: #7 - LOW - duration [ns]: 33458
loop: #8 - LOW - duration [ns]: 36038
loop: #9 - LOW - duration [ns]: 39956

name: REVERSE - partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 40649
loop: #1 - MID - duration [ns]: 12417
loop: #2 - MID - duration [ns]: 16567
loop: #3 - MID - duration [ns]: 15526
loop: #4 - MID - duration [ns]: 15233
loop: #5 - MID - duration [ns]: 15883
loop: #6 - MID - duration [ns]: 19175
loop: #7 - MID - duration [ns]: 17559
loop: #8 - MID - duration [ns]: 16835
loop: #9 - MID - duration [ns]: 10776

name: REVERSE - partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 24602
loop: #1 - MEDIAN - duration [ns]: 19607
loop: #2 - MEDIAN - duration [ns]: 32366
loop: #3 - MEDIAN - duration [ns]: 16246
loop: #4 - MEDIAN - duration [ns]: 35240
loop: #5 - MEDIAN - duration [ns]: 18445
loop: #6 - MEDIAN - duration [ns]: 17667
loop: #7 - MEDIAN - duration [ns]: 16524
loop: #8 - MEDIAN - duration [ns]: 25833
loop: #9 - MEDIAN - duration [ns]: 23626

name: REVERSE - partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 44003
loop: #1 - HIGH - duration [ns]: 46453
loop: #2 - HIGH - duration [ns]: 37421
loop: #3 - HIGH - duration [ns]: 31704
loop: #4 - HIGH - duration [ns]: 35876
loop: #5 - HIGH - duration [ns]: 39551
loop: #6 - HIGH - duration [ns]: 36658
loop: #7 - HIGH - duration [ns]: 38484
loop: #8 - HIGH - duration [ns]: 53195
loop: #9 - HIGH - duration [ns]: 32885

name: REVERSE - partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 28589
loop: #1 - RANDOM - duration [ns]: 41023
loop: #2 - RANDOM - duration [ns]: 33192
loop: #3 - RANDOM - duration [ns]: 32505
loop: #4 - RANDOM - duration [ns]: 34647
loop: #5 - RANDOM - duration [ns]: 28092
loop: #6 - RANDOM - duration [ns]: 29567
loop: #7 - RANDOM - duration [ns]: 35830
loop: #8 - RANDOM - duration [ns]: 29292
loop: #9 - RANDOM - duration [ns]: 54748

name: REVERSE - partition: HOARE - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        38729
         MID |        18062
      MEDIAN |        23016
        HIGH |        39623
      RANDOM |        34749

name: REVERSE - partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 45344
loop: #1 - LOW - duration [ns]: 30360
loop: #2 - LOW - duration [ns]: 31951
loop: #3 - LOW - duration [ns]: 28283
loop: #4 - LOW - duration [ns]: 27260
loop: #5 - LOW - duration [ns]: 28100
loop: #6 - LOW - duration [ns]: 28373
loop: #7 - LOW - duration [ns]: 24993
loop: #8 - LOW - duration [ns]: 23531
loop: #9 - LOW - duration [ns]: 22230

name: REVERSE - partition: DNF - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 31446
loop: #1 - MID - duration [ns]: 12122
loop: #2 - MID - duration [ns]: 11136
loop: #3 - MID - duration [ns]: 10985
loop: #4 - MID - duration [ns]: 12119
loop: #5 - MID - duration [ns]: 10452
loop: #6 - MID - duration [ns]: 10523
loop: #7 - MID - duration [ns]: 10343
loop: #8 - MID - duration [ns]: 10635
loop: #9 - MID - duration [ns]: 10787

name: REVERSE - partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 21017
loop: #1 - MEDIAN - duration [ns]: 15852
loop: #2 - MEDIAN - duration [ns]: 17150
loop: #3 - MEDIAN - duration [ns]: 15970
loop: #4 - MEDIAN - duration [ns]: 15166
loop: #5 - MEDIAN - duration [ns]: 15279
loop: #6 - MEDIAN - duration [ns]: 15061
loop: #7 - MEDIAN - duration [ns]: 35170
loop: #8 - MEDIAN - duration [ns]: 15615
loop: #9 - MEDIAN - duration [ns]: 15350

name: REVERSE - partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 26005
loop: #1 - HIGH - duration [ns]: 25034
loop: #2 - HIGH - duration [ns]: 23652
loop: #3 - HIGH - duration [ns]: 21641
loop: #4 - HIGH - duration [ns]: 20253
loop: #5 - HIGH - duration [ns]: 30704
loop: #6 - HIGH - duration [ns]: 24128
loop: #7 - HIGH - duration [ns]: 26548
loop: #8 - HIGH - duration [ns]: 21961
loop: #9 - HIGH - duration [ns]: 21730

name: REVERSE - partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 15320
loop: #1 - RANDOM - duration [ns]: 16867
loop: #2 - RANDOM - duration [ns]: 16977
loop: #3 - RANDOM - duration [ns]: 409973
loop: #4 - RANDOM - duration [ns]: 38251
loop: #5 - RANDOM - duration [ns]: 28152
loop: #6 - RANDOM - duration [ns]: 19826
loop: #7 - RANDOM - duration [ns]: 22162
loop: #8 - RANDOM - duration [ns]: 30710
loop: #9 - RANDOM - duration [ns]: 34295

name: REVERSE - partition: DNF - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        29043
         MID |        13055
      MEDIAN |        18163
        HIGH |        24166
      RANDOM |        63253


--------------------------------------------------------------------------------
QUICKSORT: 10 x array[100] - ONEOFF
--------------------------------------------------------------------------------
original:
[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 1]
sorted:
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100]

name: ONEOFF - partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 100129
loop: #1 - LOW - duration [ns]: 42250
loop: #2 - LOW - duration [ns]: 41003
loop: #3 - LOW - duration [ns]: 36945
loop: #4 - LOW - duration [ns]: 33741
loop: #5 - LOW - duration [ns]: 38417
loop: #6 - LOW - duration [ns]: 30704
loop: #7 - LOW - duration [ns]: 41332
loop: #8 - LOW - duration [ns]: 31288
loop: #9 - LOW - duration [ns]: 29978

name: ONEOFF - partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 16445
loop: #1 - MID - duration [ns]: 14974
loop: #2 - MID - duration [ns]: 15923
loop: #3 - MID - duration [ns]: 16550
loop: #4 - MID - duration [ns]: 14247
loop: #5 - MID - duration [ns]: 14073
loop: #6 - MID - duration [ns]: 14693
loop: #7 - MID - duration [ns]: 16201
loop: #8 - MID - duration [ns]: 14046
loop: #9 - MID - duration [ns]: 14462

name: ONEOFF - partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 30668
loop: #1 - MEDIAN - duration [ns]: 34951
loop: #2 - MEDIAN - duration [ns]: 27261
loop: #3 - MEDIAN - duration [ns]: 26558
loop: #4 - MEDIAN - duration [ns]: 19921
loop: #5 - MEDIAN - duration [ns]: 25026
loop: #6 - MEDIAN - duration [ns]: 26848
loop: #7 - MEDIAN - duration [ns]: 26580
loop: #8 - MEDIAN - duration [ns]: 44323
loop: #9 - MEDIAN - duration [ns]: 26536

name: ONEOFF - partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 29091
loop: #1 - HIGH - duration [ns]: 33150
loop: #2 - HIGH - duration [ns]: 35425
loop: #3 - HIGH - duration [ns]: 32530
loop: #4 - HIGH - duration [ns]: 33691
loop: #5 - HIGH - duration [ns]: 33496
loop: #6 - HIGH - duration [ns]: 32216
loop: #7 - HIGH - duration [ns]: 35965
loop: #8 - HIGH - duration [ns]: 34562
loop: #9 - HIGH - duration [ns]: 32285

name: ONEOFF - partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 20838
loop: #1 - RANDOM - duration [ns]: 22158
loop: #2 - RANDOM - duration [ns]: 25112
loop: #3 - RANDOM - duration [ns]: 23794
loop: #4 - RANDOM - duration [ns]: 21820
loop: #5 - RANDOM - duration [ns]: 22330
loop: #6 - RANDOM - duration [ns]: 18503
loop: #7 - RANDOM - duration [ns]: 25698
loop: #8 - RANDOM - duration [ns]: 23600
loop: #9 - RANDOM - duration [ns]: 21940

name: ONEOFF - partition: HOARE - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        42579
         MID |        15161
      MEDIAN |        28867
        HIGH |        33241
      RANDOM |        22579

name: ONEOFF - partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 10882
loop: #1 - LOW - duration [ns]: 7869
loop: #2 - LOW - duration [ns]: 6300
loop: #3 - LOW - duration [ns]: 5937
loop: #4 - LOW - duration [ns]: 6351
loop: #5 - LOW - duration [ns]: 5784
loop: #6 - LOW - duration [ns]: 5814
loop: #7 - LOW - duration [ns]: 5888
loop: #8 - LOW - duration [ns]: 5814
loop: #9 - LOW - duration [ns]: 5771

name: ONEOFF - partition: DNF - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 7423
loop: #1 - MID - duration [ns]: 6443
loop: #2 - MID - duration [ns]: 5354
loop: #3 - MID - duration [ns]: 4681
loop: #4 - MID - duration [ns]: 4171
loop: #5 - MID - duration [ns]: 4267
loop: #6 - MID - duration [ns]: 4041
loop: #7 - MID - duration [ns]: 4560
loop: #8 - MID - duration [ns]: 4276
loop: #9 - MID - duration [ns]: 4250

name: ONEOFF - partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 10358
loop: #1 - MEDIAN - duration [ns]: 7740
loop: #2 - MEDIAN - duration [ns]: 6810
loop: #3 - MEDIAN - duration [ns]: 6730
loop: #4 - MEDIAN - duration [ns]: 11154
loop: #5 - MEDIAN - duration [ns]: 12282
loop: #6 - MEDIAN - duration [ns]: 13057
loop: #7 - MEDIAN - duration [ns]: 12110
loop: #8 - MEDIAN - duration [ns]: 15012
loop: #9 - MEDIAN - duration [ns]: 12454

name: ONEOFF - partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 15829
loop: #1 - HIGH - duration [ns]: 17093
loop: #2 - HIGH - duration [ns]: 14615
loop: #3 - HIGH - duration [ns]: 14559
loop: #4 - HIGH - duration [ns]: 15301
loop: #5 - HIGH - duration [ns]: 14517
loop: #6 - HIGH - duration [ns]: 14481
loop: #7 - HIGH - duration [ns]: 14464
loop: #8 - HIGH - duration [ns]: 14770
loop: #9 - HIGH - duration [ns]: 15449

name: ONEOFF - partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 9908
loop: #1 - RANDOM - duration [ns]: 10972
loop: #2 - RANDOM - duration [ns]: 8334
loop: #3 - RANDOM - duration [ns]: 8048
loop: #4 - RANDOM - duration [ns]: 7997
loop: #5 - RANDOM - duration [ns]: 7718
loop: #6 - RANDOM - duration [ns]: 8235
loop: #7 - RANDOM - duration [ns]: 7543
loop: #8 - RANDOM - duration [ns]: 7565
loop: #9 - RANDOM - duration [ns]: 7683

name: ONEOFF - partition: DNF - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |         6641
         MID |         4947
      MEDIAN |        10771
        HIGH |        15108
      RANDOM |         8400


--------------------------------------------------------------------------------
QUICKSORT: 10 x array[100] - SHUFFLED
--------------------------------------------------------------------------------
original:
[100, 21, 63, 75, 62, 72, 81, 53, 43, 28, 56, 45, 31, 11, 78, 80, 95, 47, 4, 9, 74, 66, 24, 96, 71, 1, 77, 87, 42, 94, 2, 55, 91, 17, 18, 44, 6, 25, 20, 46, 93, 37, 19, 88, 97, 67, 48, 38, 99, 83, 33, 84, 65, 61, 73, 10, 90, 89, 7, 92, 85, 54, 76, 52, 27, 12, 14, 68, 30, 69, 41, 58, 15, 23, 70, 79, 51, 29, 26, 82, 5, 39, 13, 86, 64, 36, 32, 3, 34, 98, 57, 59, 40, 16, 49, 8, 50, 22, 60, 35]
sorted:
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100]

name: SHUFFLED - partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 27500
loop: #1 - LOW - duration [ns]: 19656
loop: #2 - LOW - duration [ns]: 18168
loop: #3 - LOW - duration [ns]: 20003
loop: #4 - LOW - duration [ns]: 20360
loop: #5 - LOW - duration [ns]: 15848
loop: #6 - LOW - duration [ns]: 15379
loop: #7 - LOW - duration [ns]: 15638
loop: #8 - LOW - duration [ns]: 18060
loop: #9 - LOW - duration [ns]: 11852

name: SHUFFLED - partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 14545
loop: #1 - MID - duration [ns]: 14483
loop: #2 - MID - duration [ns]: 9096
loop: #3 - MID - duration [ns]: 9082
loop: #4 - MID - duration [ns]: 9296
loop: #5 - MID - duration [ns]: 7928
loop: #6 - MID - duration [ns]: 7648
loop: #7 - MID - duration [ns]: 7786
loop: #8 - MID - duration [ns]: 9936
loop: #9 - MID - duration [ns]: 11972

name: SHUFFLED - partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 16154
loop: #1 - MEDIAN - duration [ns]: 12222
loop: #2 - MEDIAN - duration [ns]: 11669
loop: #3 - MEDIAN - duration [ns]: 10865
loop: #4 - MEDIAN - duration [ns]: 10120
loop: #5 - MEDIAN - duration [ns]: 25650
loop: #6 - MEDIAN - duration [ns]: 10043
loop: #7 - MEDIAN - duration [ns]: 10359
loop: #8 - MEDIAN - duration [ns]: 10400
loop: #9 - MEDIAN - duration [ns]: 9818

name: SHUFFLED - partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 13225
loop: #1 - HIGH - duration [ns]: 10800
loop: #2 - HIGH - duration [ns]: 11365
loop: #3 - HIGH - duration [ns]: 8144
loop: #4 - HIGH - duration [ns]: 7494
loop: #5 - HIGH - duration [ns]: 7539
loop: #6 - HIGH - duration [ns]: 7507
loop: #7 - HIGH - duration [ns]: 8174
loop: #8 - HIGH - duration [ns]: 14207
loop: #9 - HIGH - duration [ns]: 9391

name: SHUFFLED - partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 26575
loop: #1 - RANDOM - duration [ns]: 16767
loop: #2 - RANDOM - duration [ns]: 36708
loop: #3 - RANDOM - duration [ns]: 25499
loop: #4 - RANDOM - duration [ns]: 18600
loop: #5 - RANDOM - duration [ns]: 16516
loop: #6 - RANDOM - duration [ns]: 17504
loop: #7 - RANDOM - duration [ns]: 17506
loop: #8 - RANDOM - duration [ns]: 20517
loop: #9 - RANDOM - duration [ns]: 16700

name: SHUFFLED - partition: HOARE - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        18246
         MID |        10177
      MEDIAN |        12730
        HIGH |         9785
      RANDOM |        21289

name: SHUFFLED - partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 9767
loop: #1 - LOW - duration [ns]: 21044
loop: #2 - LOW - duration [ns]: 6231
loop: #3 - LOW - duration [ns]: 5221
loop: #4 - LOW - duration [ns]: 4920
loop: #5 - LOW - duration [ns]: 4774
loop: #6 - LOW - duration [ns]: 4622
loop: #7 - LOW - duration [ns]: 4638
loop: #8 - LOW - duration [ns]: 4536
loop: #9 - LOW - duration [ns]: 5409

name: SHUFFLED - partition: DNF - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 9226
loop: #1 - MID - duration [ns]: 7158
loop: #2 - MID - duration [ns]: 5670
loop: #3 - MID - duration [ns]: 4904
loop: #4 - MID - duration [ns]: 4627
loop: #5 - MID - duration [ns]: 4735
loop: #6 - MID - duration [ns]: 5198
loop: #7 - MID - duration [ns]: 25535
loop: #8 - MID - duration [ns]: 12089
loop: #9 - MID - duration [ns]: 9527

name: SHUFFLED - partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 15307
loop: #1 - MEDIAN - duration [ns]: 13933
loop: #2 - MEDIAN - duration [ns]: 9309
loop: #3 - MEDIAN - duration [ns]: 41509
loop: #4 - MEDIAN - duration [ns]: 7751
loop: #5 - MEDIAN - duration [ns]: 6529
loop: #6 - MEDIAN - duration [ns]: 4943
loop: #7 - MEDIAN - duration [ns]: 4912
loop: #8 - MEDIAN - duration [ns]: 7724
loop: #9 - MEDIAN - duration [ns]: 6397

name: SHUFFLED - partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 11909
loop: #1 - HIGH - duration [ns]: 8007
loop: #2 - HIGH - duration [ns]: 22987
loop: #3 - HIGH - duration [ns]: 9218
loop: #4 - HIGH - duration [ns]: 5581
loop: #5 - HIGH - duration [ns]: 6199
loop: #6 - HIGH - duration [ns]: 6090
loop: #7 - HIGH - duration [ns]: 6106
loop: #8 - HIGH - duration [ns]: 4647
loop: #9 - HIGH - duration [ns]: 5056

name: SHUFFLED - partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 11774
loop: #1 - RANDOM - duration [ns]: 10541
loop: #2 - RANDOM - duration [ns]: 9798
loop: #3 - RANDOM - duration [ns]: 9293
loop: #4 - RANDOM - duration [ns]: 8619
loop: #5 - RANDOM - duration [ns]: 9691
loop: #6 - RANDOM - duration [ns]: 9690
loop: #7 - RANDOM - duration [ns]: 8763
loop: #8 - RANDOM - duration [ns]: 18139
loop: #9 - RANDOM - duration [ns]: 14986

name: SHUFFLED - partition: DNF - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |         7116
         MID |         8867
      MEDIAN |        11831
        HIGH |         8580
      RANDOM |        11129


--------------------------------------------------------------------------------
QUICKSORT: 10 x array[100] - RANDOM
--------------------------------------------------------------------------------
original:
[65, 19, 33, 1, 55, 12, 61, 28, 20, 83, 15, 68, 91, 91, 22, 40, 25, 92, 58, 37, 99, 53, 86, 40, 24, 23, 87, 74, 52, 9, 58, 83, 37, 88, 21, 12, 36, 81, 71, 26, 14, 58, 65, 9, 45, 58, 40, 75, 75, 8, 33, 10, 43, 7, 61, 12, 64, 1, 19, 16, 62, 25, 32, 68, 34, 80, 21, 19, 6, 60, 92, 57, 32, 80, 21, 10, 53, 52, 27, 2, 25, 85, 8, 92, 97, 68, 53, 30, 0, 88, 83, 70, 84, 75, 76, 31, 95, 35, 63, 81]
sorted:
[0, 1, 1, 2, 6, 7, 8, 8, 9, 9, 10, 10, 12, 12, 12, 14, 15, 16, 19, 19, 19, 20, 21, 21, 21, 22, 23, 24, 25, 25, 25, 26, 27, 28, 30, 31, 32, 32, 33, 33, 34, 35, 36, 37, 37, 40, 40, 40, 43, 45, 52, 52, 53, 53, 53, 55, 57, 58, 58, 58, 58, 60, 61, 61, 62, 63, 64, 65, 65, 68, 68, 68, 70, 71, 74, 75, 75, 75, 76, 80, 80, 81, 81, 83, 83, 83, 84, 85, 86, 87, 88, 88, 91, 91, 92, 92, 92, 95, 97, 99]

name: RANDOM - partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 17100
loop: #1 - LOW - duration [ns]: 13027
loop: #2 - LOW - duration [ns]: 13756
loop: #3 - LOW - duration [ns]: 10702
loop: #4 - LOW - duration [ns]: 9338
loop: #5 - LOW - duration [ns]: 9775
loop: #6 - LOW - duration [ns]: 11214
loop: #7 - LOW - duration [ns]: 7876
loop: #8 - LOW - duration [ns]: 14519
loop: #9 - LOW - duration [ns]: 16701

name: RANDOM - partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 15906
loop: #1 - MID - duration [ns]: 11630
loop: #2 - MID - duration [ns]: 10095
loop: #3 - MID - duration [ns]: 9829
loop: #4 - MID - duration [ns]: 19334
loop: #5 - MID - duration [ns]: 17007
loop: #6 - MID - duration [ns]: 17984
loop: #7 - MID - duration [ns]: 18232
loop: #8 - MID - duration [ns]: 16922
loop: #9 - MID - duration [ns]: 11409

name: RANDOM - partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 15684
loop: #1 - MEDIAN - duration [ns]: 12847
loop: #2 - MEDIAN - duration [ns]: 11358
loop: #3 - MEDIAN - duration [ns]: 13552
loop: #4 - MEDIAN - duration [ns]: 18714
loop: #5 - MEDIAN - duration [ns]: 10841
loop: #6 - MEDIAN - duration [ns]: 11189
loop: #7 - MEDIAN - duration [ns]: 9770
loop: #8 - MEDIAN - duration [ns]: 23615
loop: #9 - MEDIAN - duration [ns]: 8797

name: RANDOM - partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 16419
loop: #1 - HIGH - duration [ns]: 10891
loop: #2 - HIGH - duration [ns]: 10863
loop: #3 - HIGH - duration [ns]: 8582
loop: #4 - HIGH - duration [ns]: 9516
loop: #5 - HIGH - duration [ns]: 8556
loop: #6 - HIGH - duration [ns]: 9520
loop: #7 - HIGH - duration [ns]: 12771
loop: #8 - HIGH - duration [ns]: 9645
loop: #9 - HIGH - duration [ns]: 8275

name: RANDOM - partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 16806
loop: #1 - RANDOM - duration [ns]: 16135
loop: #2 - RANDOM - duration [ns]: 15685
loop: #3 - RANDOM - duration [ns]: 25867
loop: #4 - RANDOM - duration [ns]: 27715
loop: #5 - RANDOM - duration [ns]: 17855
loop: #6 - RANDOM - duration [ns]: 14344
loop: #7 - RANDOM - duration [ns]: 16111
loop: #8 - RANDOM - duration [ns]: 19182
loop: #9 - RANDOM - duration [ns]: 15492

name: RANDOM - partition: HOARE - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        12401
         MID |        14835
      MEDIAN |        13637
        HIGH |        10504
      RANDOM |        18519

name: RANDOM - partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 10085
loop: #1 - LOW - duration [ns]: 6177
loop: #2 - LOW - duration [ns]: 10074
loop: #3 - LOW - duration [ns]: 4528
loop: #4 - LOW - duration [ns]: 6895
loop: #5 - LOW - duration [ns]: 6304
loop: #6 - LOW - duration [ns]: 4690
loop: #7 - LOW - duration [ns]: 4549
loop: #8 - LOW - duration [ns]: 5425
loop: #9 - LOW - duration [ns]: 3389

name: RANDOM - partition: DNF - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 8537
loop: #1 - MID - duration [ns]: 6552
loop: #2 - MID - duration [ns]: 4975
loop: #3 - MID - duration [ns]: 4129
loop: #4 - MID - duration [ns]: 5462
loop: #5 - MID - duration [ns]: 6658
loop: #6 - MID - duration [ns]: 4947
loop: #7 - MID - duration [ns]: 10766
loop: #8 - MID - duration [ns]: 7701
loop: #9 - MID - duration [ns]: 9165

name: RANDOM - partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 15312
loop: #1 - MEDIAN - duration [ns]: 12215
loop: #2 - MEDIAN - duration [ns]: 10063
loop: #3 - MEDIAN - duration [ns]: 10712
loop: #4 - MEDIAN - duration [ns]: 10201
loop: #5 - MEDIAN - duration [ns]: 13285
loop: #6 - MEDIAN - duration [ns]: 16765
loop: #7 - MEDIAN - duration [ns]: 9810
loop: #8 - MEDIAN - duration [ns]: 10444
loop: #9 - MEDIAN - duration [ns]: 14921

name: RANDOM - partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 13941
loop: #1 - HIGH - duration [ns]: 14192
loop: #2 - HIGH - duration [ns]: 13618
loop: #3 - HIGH - duration [ns]: 14002
loop: #4 - HIGH - duration [ns]: 11963
loop: #5 - HIGH - duration [ns]: 13628
loop: #6 - HIGH - duration [ns]: 14363
loop: #7 - HIGH - duration [ns]: 18559
loop: #8 - HIGH - duration [ns]: 12260
loop: #9 - HIGH - duration [ns]: 12111

name: RANDOM - partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 18971
loop: #1 - RANDOM - duration [ns]: 16228
loop: #2 - RANDOM - duration [ns]: 17160
loop: #3 - RANDOM - duration [ns]: 15715
loop: #4 - RANDOM - duration [ns]: 15856
loop: #5 - RANDOM - duration [ns]: 14379
loop: #6 - RANDOM - duration [ns]: 14873
loop: #7 - RANDOM - duration [ns]: 14503
loop: #8 - RANDOM - duration [ns]: 19802
loop: #9 - RANDOM - duration [ns]: 17012

name: RANDOM - partition: DNF - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |         6212
         MID |         6889
      MEDIAN |        12373
        HIGH |        13864
      RANDOM |        16450
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.423 sec

Results :

Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
```
