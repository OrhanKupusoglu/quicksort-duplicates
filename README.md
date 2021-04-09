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
+------------------+--------------------+----------+----------+----------+----------+----------+----------+
| Package Name     | File Name          | Type     | Blank    | JavaDoc  | Comment  | Code     | Total    |
+------------------+--------------------+----------+----------+----------+----------+----------+----------+
| quicksort        | QuickSort.java     | src      |       59 |       20 |        5 |      284 |      368 |
| quicksort        | QuickSortMeta.java | src      |       12 |        0 |        1 |       41 |       54 |
| quicksort        | QuickSortTest.java | test     |       50 |        9 |        4 |      195 |      258 |
+------------------+--------------------+----------+----------+----------+----------+----------+----------+
| 1 package(s)     | 3 file(s)          | java     |      121 |       29 |       10 |      520 |      680 |
+------------------+--------------------+----------+----------+----------+----------+----------+----------+

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.870 s
[INFO] Finished at: 2018-12-02T02:38:18+03:00
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
### Google Bazel with Bazelisk

The Maven script is migrated to Google Bazel with the [Bazelize Maven Plugin](https://github.com/OrhanKupusoglu/bazelize-maven-plugin).
But this plugin uses an old version of Bazel, therefore [Bazelisk](https://github.com/bazelbuild/bazelisk) is required.

> Bazelisk<br>
> A user-friendly launcher for Bazel.

A Bash script, [bazelize.sh](./bazelize.sh), is provided for convenience.
This script needs to be customized for the path of Bazelisk.

```
## check options
$ ./bazelize.sh -h
usage:
	./bazelize.sh <option>
options:
	migrate to bazel: -m | --migrate | -g | --generate
	clean bazel:      -c | --clean
	build with bazel: -b | --build
	test with bazel:  -t | --test
	run with bazel:   -r | --run
requires Bazelisk for Bazel v0.14.1:
	/home/orhanku/ME/DEV/bazelisk/bin/bazelisk-linux-amd64

## already migrated, build
$ ./bazelize.sh -b

## test
$ ./bazelize.sh -t
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
$ mvn clean test
[INFO] Scanning for projects...
[INFO] Inspecting build with total of 1 modules...
[INFO] Installing Nexus Staging features:
[INFO]   ... total of 1 executions of maven-deploy-plugin replaced with nexus-staging-maven-plugin
[INFO]
[INFO] ----------------< kupusoglu.orhan:quicksort-duplicates >----------------
[INFO] Building quicksort-duplicates 0.3.1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ quicksort-duplicates ---
[INFO] Deleting /home/orhanku/ME/DEV/OK/quicksort-duplicates/target
[INFO]
[INFO] --- maven-enforcer-plugin:3.0.0-M2:enforce (default-cli) @ quicksort-duplicates ---
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ quicksort-duplicates ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/orhanku/ME/DEV/OK/quicksort-duplicates/src/main/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.7.0:compile (default-compile) @ quicksort-duplicates ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 2 source files to /home/orhanku/ME/DEV/OK/quicksort-duplicates/target/classes
[INFO]
[INFO] --- plexus-component-metadata:1.7.1:generate-metadata (default) @ quicksort-duplicates ---
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ quicksort-duplicates ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/orhanku/ME/DEV/OK/quicksort-duplicates/src/test/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.7.0:testCompile (default-testCompile) @ quicksort-duplicates ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /home/orhanku/ME/DEV/OK/quicksort-duplicates/target/test-classes
[INFO]
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ quicksort-duplicates ---
[INFO] Surefire report directory: /home/orhanku/ME/DEV/OK/quicksort-duplicates/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running kupusoglu.orhan.quicksort.QuickSortTest


--------------------------------------------------------------------------------
QUICKSORT: basics

partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 20543
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 3208
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 10142
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 105100
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2] : [ 0 - 1 ] : 2 : 0 : 1


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 39970
number of partitions: 2
number of swaps: 0
[1, 2, 3] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 1 : 0 : 0
[1, 2, 3] : [ 1 - 2 ] : 2 : 1 : 0


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 46379
number of partitions: 2
number of swaps: 1
[3, 2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 3 : 1 : 1
[1, 2, 3] : [ 0 - 1 ] : 1 : 0 : 0


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 78531
number of partitions: 3
number of swaps: 4
[1, 1, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1] : [ 2 - 3 ] : 1 : 2 : 1


partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 147859
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
duration [ns]: 124185
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
duration [ns]: 169431
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
duration [ns]: 321688
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
duration [ns]: 253803
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
duration [ns]: 250551
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
duration [ns]: 205552
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
duration [ns]: 185760
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
duration [ns]: 130530
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
duration [ns]: 183726
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
duration [ns]: 147272
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
duration [ns]: 156972
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
23904105


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 4136
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 2242
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 2960
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 45792
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2] : [ 0 - 1 ] : 2 : 0 : 1


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 34338
number of partitions: 2
number of swaps: 0
[1, 2, 3] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : 1 : 0
[1, 2, 3] : [ 0 - 1 ] : 1 : 0 : 0


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 20487
number of partitions: 2
number of swaps: 1
[3, 2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : 1 : 1
[1, 2, 3] : [ 0 - 1 ] : 1 : 0 : 0


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 41162
number of partitions: 3
number of swaps: 4
[1, 1, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1] : [ 2 - 3 ] : 1 : 2 : 1


partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 33452
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
duration [ns]: 37091
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
duration [ns]: 49656
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
duration [ns]: 112053
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
duration [ns]: 99085
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
duration [ns]: 56884
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
duration [ns]: 126025
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
duration [ns]: 159892
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
duration [ns]: 110405
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
duration [ns]: 102977
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
duration [ns]: 82030
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
duration [ns]: 93573
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
15091665


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 3203
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 2578
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 2363
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 52562
number of partitions: 2
number of swaps: 3
[2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2] : median swaps : 1
[1, 2] : [ 0 - 1 ] : 2 : 1 : 0
[2, 1] : median swaps : 1
[1, 2] : [ 0 - 1 ] : 1 : 0 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 12732
number of partitions: 2
number of swaps: 4
[1, 2, 3] : [ lo - hi ] : pv : ix : sw
[1, 3, 2] : median swaps : 1
[1, 2, 3] : [ 0 - 2 ] : 2 : 1 : 1
[2, 1, 3] : median swaps : 1
[1, 2, 3] : [ 0 - 1 ] : 1 : 0 : 1


partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 21001
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
duration [ns]: 22414
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
duration [ns]: 36021
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
duration [ns]: 38362
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
duration [ns]: 64306
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
duration [ns]: 111638
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
duration [ns]: 63946
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
duration [ns]: 59301
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
duration [ns]: 111123
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
duration [ns]: 110377
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
duration [ns]: 114260
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
duration [ns]: 141023
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
duration [ns]: 211010
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
duration [ns]: 96553
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
15949070


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 2794
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 1655
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 2430
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 27807
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2] : [ 0 - 1 ] : 2 : 0 : 1


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 7272
number of partitions: 2
number of swaps: 0
[1, 2, 3] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : 1 : 0
[1, 2, 3] : [ 0 - 1 ] : 1 : 0 : 0


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 8835
number of partitions: 2
number of swaps: 1
[3, 2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : 1 : 1
[1, 2, 3] : [ 0 - 1 ] : 1 : 0 : 0


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 8871
number of partitions: 3
number of swaps: 4
[1, 1, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1] : [ 2 - 3 ] : 1 : 2 : 1


partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 13533
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
duration [ns]: 17224
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
duration [ns]: 15805
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
duration [ns]: 26497
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
duration [ns]: 34678
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
duration [ns]: 44579
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
duration [ns]: 46227
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
duration [ns]: 46205
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
duration [ns]: 48401
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
duration [ns]: 53381
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
duration [ns]: 55470
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
duration [ns]: 51917
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
12455169


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 3542
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 2224
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 2824
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : ix : sw


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 27876
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2] : [ 0 - 1 ] : 1 : 0 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 19472
number of partitions: 5
number of swaps: 0
[1, 2, 3] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 3 : 2 : 0
[1, 2, 3] : [ 0 - 2 ] : 1 : 0 : 0
[1, 2, 3] : [ 1 - 2 ] : 3 : 2 : 0
[1, 2, 3] : [ 1 - 2 ] : 3 : 2 : 0
[1, 2, 3] : [ 1 - 2 ] : 2 : 1 : 0


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 12667
number of partitions: 2
number of swaps: 1
[3, 2, 1] : [ lo - hi ] : pv : ix : sw
[1, 2, 3] : [ 0 - 2 ] : 3 : 1 : 1
[1, 2, 3] : [ 0 - 1 ] : 1 : 0 : 0


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 15433
number of partitions: 3
number of swaps: 4
[1, 1, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : 1 : 2
[1, 1, 1, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1] : [ 2 - 3 ] : 1 : 2 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 18780
number of partitions: 5
number of swaps: 5
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : ix : sw
[1, 2, 3, 3, 3, 3] : [ 0 - 5 ] : 2 : 1 : 1
[1, 2, 3, 3, 3, 3] : [ 0 - 1 ] : 1 : 0 : 0
[1, 2, 3, 3, 3, 3] : [ 2 - 5 ] : 3 : 3 : 2
[1, 2, 3, 3, 3, 3] : [ 2 - 3 ] : 3 : 2 : 1
[1, 2, 3, 3, 3, 3] : [ 4 - 5 ] : 3 : 4 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 21744
number of partitions: 6
number of swaps: 7
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 3, 4, 2, 2, 1] : [ 0 - 6 ] : 1 : 1 : 2
[1, 1, 3, 4, 2, 2, 1] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 2, 2, 4, 3] : [ 2 - 6 ] : 3 : 4 : 2
[1, 1, 1, 2, 2, 4, 3] : [ 2 - 4 ] : 1 : 2 : 0
[1, 1, 1, 2, 2, 4, 3] : [ 3 - 4 ] : 2 : 3 : 1
[1, 1, 1, 2, 2, 3, 4] : [ 5 - 6 ] : 3 : 5 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 29002
number of partitions: 7
number of swaps: 7
[1000000, 10001, 10, 30, 40, 50, 1, 999] : [ lo - hi ] : pv : ix : sw
[999, 1, 10, 30, 40, 50, 10001, 1000000] : [ 0 - 7 ] : 10001 : 5 : 2
[10, 1, 999, 30, 40, 50, 10001, 1000000] : [ 0 - 5 ] : 10 : 1 : 1
[1, 10, 999, 30, 40, 50, 10001, 1000000] : [ 0 - 1 ] : 1 : 0 : 1
[1, 10, 40, 30, 999, 50, 10001, 1000000] : [ 2 - 5 ] : 40 : 3 : 1
[1, 10, 30, 40, 999, 50, 10001, 1000000] : [ 2 - 3 ] : 30 : 2 : 1
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 4 - 5 ] : 50 : 4 : 1
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 6 - 7 ] : 10001 : 6 : 0


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 58765
number of partitions: 15
number of swaps: 14
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 4 : 13 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 13 ] : 2 : 5 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 5 ] : 1 : 1 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 5 ] : 2 : 4 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 4 ] : 2 : 3 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 3 ] : 1 : 2 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 13 ] : 3 : 9 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 9 ] : 2 : 6 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 9 ] : 2 : 7 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 9 ] : 3 : 8 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 13 ] : 4 : 12 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 12 ] : 3 : 10 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 11 - 12 ] : 4 : 11 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 59875
number of partitions: 18
number of swaps: 21
[4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1] : [ lo - hi ] : pv : ix : sw
[1, 2, 1, 2, 3, 2, 1, 2, 3, 2, 3, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 3 : 9 : 5
[1, 1, 2, 2, 3, 2, 1, 2, 3, 2, 3, 4, 4, 4, 4, 4] : [ 0 - 9 ] : 1 : 1 : 2
[1, 1, 2, 2, 3, 2, 1, 2, 3, 2, 3, 4, 4, 4, 4, 4] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 2, 2, 1, 2, 3, 2, 3, 2, 3, 4, 4, 4, 4, 4] : [ 2 - 9 ] : 2 : 5 : 3
[1, 1, 1, 2, 2, 2, 3, 2, 3, 2, 3, 4, 4, 4, 4, 4] : [ 2 - 5 ] : 1 : 2 : 1
[1, 1, 1, 2, 2, 2, 3, 2, 3, 2, 3, 4, 4, 4, 4, 4] : [ 3 - 5 ] : 2 : 4 : 1
[1, 1, 1, 2, 2, 2, 3, 2, 3, 2, 3, 4, 4, 4, 4, 4] : [ 3 - 4 ] : 2 : 3 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 9 ] : 3 : 8 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 8 ] : 2 : 6 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 8 ] : 3 : 8 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 8 ] : 2 : 7 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 15 ] : 4 : 13 : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 13 ] : 4 : 12 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 12 ] : 4 : 11 : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 4 : 11 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 4 : 11 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 3 : 10 : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 70622
number of partitions: 21
number of swaps: 17
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ lo - hi ] : pv : ix : sw
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 3 : 9 : 3
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 9 ] : 2 : 5 : 2
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 5 ] : 1 : 1 : 2
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 1 ] : 1 : 0 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 5 ] : 2 : 4 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 4 ] : 2 : 4 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 2 - 4 ] : 1 : 2 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 4 ] : 2 : 4 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 4 ] : 2 : 4 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 4 ] : 2 : 4 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 4 ] : 2 : 4 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 4 ] : 1 : 3 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 9 ] : 3 : 8 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 6 - 8 ] : 2 : 6 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 8 ] : 3 : 8 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 7 - 8 ] : 2 : 7 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 15 ] : 4 : 13 : 2
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 13 ] : 4 : 12 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 12 ] : 4 : 11 : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 10 - 11 ] : 3 : 10 : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 14 - 15 ] : 4 : 14 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 56311
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
duration [ns]: 63487
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


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 66282
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


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 68541
number of partitions: 19
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
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 17 ] : 2 : 15 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 15 ] : 2 : 15 : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 15 ] : 2 : 15 : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 15 ] : 1 : 14 : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 16 - 17 ] : 100 : 16 : 1


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 106457
number of partitions: 18
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
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 12 - 17 ] : 100 : 16 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 12 - 16 ] : 2 : 15 : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 12 - 15 ] : 1 : 13 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 12 - 13 ] : 1 : 12 : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 15 ] : 2 : 15 : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 14 - 15 ] : 1 : 14 : 0


partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 85799
number of partitions: 24
number of swaps: 16
[16, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ lo - hi ] : pv : ix : sw
[9, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 18 ] : 13 : 15 : 1
[9, 8, 0, 1, 2, 3, 4, 5, 6, 7, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 15 ] : 9 : 9 : 2
[1, 0, 8, 9, 2, 3, 4, 5, 6, 7, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 9 ] : 1 : 1 : 2
[0, 1, 8, 9, 2, 3, 4, 5, 6, 7, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 1 ] : 0 : 0 : 1
[0, 1, 3, 2, 9, 8, 4, 5, 6, 7, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 2 - 9 ] : 3 : 3 : 2
[0, 1, 2, 3, 9, 8, 4, 5, 6, 7, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 2 - 3 ] : 3 : 2 : 1
[0, 1, 2, 3, 5, 4, 8, 9, 6, 7, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 4 - 9 ] : 5 : 5 : 2
[0, 1, 2, 3, 4, 5, 8, 9, 6, 7, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 4 - 5 ] : 4 : 4 : 1
[0, 1, 2, 3, 4, 5, 6, 9, 8, 7, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 6 - 9 ] : 6 : 6 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 7 - 9 ] : 7 : 7 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 8 - 9 ] : 9 : 9 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 8 - 9 ] : 9 : 9 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 8 - 9 ] : 9 : 9 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 8 - 9 ] : 8 : 8 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 10 - 15 ] : 10 : 12 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 10 - 12 ] : 9 : 10 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 11 - 12 ] : 9 : 11 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 13 - 15 ] : 11 : 13 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 14 - 15 ] : 13 : 15 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 14 - 15 ] : 12 : 14 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 16 - 18 ] : 16 : 17 : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 16 - 17 ] : 16 : 17 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 16 - 17 ] : 16 : 17 : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 16 - 17 ] : 14 : 16 : 0

total duration [ns]:
12942212


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 7628
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 2097
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 2183
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 54021
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2] : [ 0 - 1 ] : 2 : [ 1 - 2 ] : 1


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 12212
number of partitions: 2
number of swaps: 2
[1, 2, 3] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 3, 2] : [ 0 - 2 ] : 1 : [ 0 - 1 ] : 1
[1, 2, 3] : [ 1 - 2 ] : 3 : [ 2 - 3 ] : 1


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 11249
number of partitions: 2
number of swaps: 3
[3, 2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 1, 3] : [ 0 - 2 ] : 3 : [ 2 - 3 ] : 2
[1, 2, 3] : [ 0 - 1 ] : 2 : [ 1 - 2 ] : 1


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 9698
number of partitions: 1
number of swaps: 0
[1, 1, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : [ 0 - 4 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 14312
number of partitions: 2
number of swaps: 5
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 3, 3, 3, 2, 3] : [ 0 - 5 ] : 1 : [ 0 - 1 ] : 4
[1, 2, 3, 3, 3, 3] : [ 1 - 5 ] : 3 : [ 2 - 6 ] : 1


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 35451
number of partitions: 3
number of swaps: 4
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 2, 4, 3, 2] : [ 0 - 6 ] : 1 : [ 0 - 3 ] : 3
[1, 1, 1, 2, 2, 3, 4] : [ 3 - 6 ] : 2 : [ 3 - 5 ] : 1
[1, 1, 1, 2, 2, 3, 4] : [ 5 - 6 ] : 3 : [ 5 - 6 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 29754
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
duration [ns]: 45705
number of partitions: 4
number of swaps: 23
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 2] : [ 0 - 15 ] : 1 : [ 0 - 3 ] : 12
[1, 1, 1, 2, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4, 3, 3] : [ 3 - 15 ] : 2 : [ 3 - 8 ] : 7
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 15 ] : 3 : [ 8 - 11 ] : 4
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 11 - 15 ] : 4 : [ 11 - 16 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 56909
number of partitions: 4
number of swaps: 17
[4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 1, 2, 2, 2, 3, 2, 3, 1, 3, 1, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 4 : [ 11 - 16 ] : 11
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 10 ] : 2 : [ 3 - 8 ] : 6
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 2 ] : 1 : [ 0 - 3 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 10 ] : 3 : [ 8 - 11 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 24915
number of partitions: 4
number of swaps: 22
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 2, 4, 4] : [ 0 - 15 ] : 1 : [ 0 - 4 ] : 11
[1, 1, 1, 1, 2, 2, 2, 2, 3, 4, 4, 4, 3, 4, 4, 3] : [ 4 - 15 ] : 2 : [ 4 - 8 ] : 7
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 15 ] : 3 : [ 8 - 11 ] : 4
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 11 - 15 ] : 4 : [ 11 - 16 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 10147
number of partitions: 1
number of swaps: 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 0 - 17 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 11208
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 1 - 17 ] : 1


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 11505
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 0 - 16 ] : 2 : [ 0 - 16 ] : 1


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 17678
number of partitions: 2
number of swaps: 16
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 17 ] : 100 : [ 16 - 18 ] : 16
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 15 ] : 1 : [ 0 - 15 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 17848
number of partitions: 2
number of swaps: 16
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 17 ] : 100 : [ 16 - 18 ] : 16
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 15 ] : 1 : [ 0 - 15 ] : 0


partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
duration [ns]: 55567
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
8849447


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 4077
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 1709
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 2481
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 11026
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2] : [ 0 - 1 ] : 2 : [ 1 - 2 ] : 1


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 11845
number of partitions: 1
number of swaps: 0
[1, 2, 3] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : [ 1 - 2 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 10499
number of partitions: 1
number of swaps: 1
[3, 2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : [ 1 - 2 ] : 1


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 7496
number of partitions: 1
number of swaps: 0
[1, 1, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : [ 0 - 4 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 14170
number of partitions: 2
number of swaps: 1
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3, 3, 3, 3] : [ 0 - 5 ] : 3 : [ 2 - 6 ] : 1
[1, 2, 3, 3, 3, 3] : [ 0 - 1 ] : 1 : [ 0 - 1 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 20903
number of partitions: 4
number of swaps: 8
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3, 2, 1, 1, 4] : [ 0 - 6 ] : 4 : [ 6 - 7 ] : 3
[1, 2, 2, 1, 1, 3, 4] : [ 0 - 5 ] : 3 : [ 5 - 6 ] : 3
[1, 1, 1, 2, 2, 3, 4] : [ 0 - 4 ] : 2 : [ 3 - 5 ] : 2
[1, 1, 1, 2, 2, 3, 4] : [ 0 - 2 ] : 1 : [ 0 - 3 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 23288
number of partitions: 4
number of swaps: 7
[1000000, 10001, 10, 30, 40, 50, 1, 999] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 10, 30, 40, 50, 10001, 999, 1000000] : [ 0 - 7 ] : 30 : [ 2 - 3 ] : 6
[1, 10, 30, 40, 50, 10001, 999, 1000000] : [ 0 - 1 ] : 1 : [ 0 - 1 ] : 0
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 3 - 7 ] : 10001 : [ 6 - 7 ] : 1
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 3 - 5 ] : 50 : [ 4 - 5 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 31835
number of partitions: 4
number of swaps: 8
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 3] : [ 0 - 15 ] : 2 : [ 3 - 8 ] : 7
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 3] : [ 0 - 2 ] : 1 : [ 0 - 3 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 15 ] : 4 : [ 11 - 16 ] : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 10 ] : 3 : [ 8 - 11 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 20927
number of partitions: 4
number of swaps: 11
[4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 2, 2, 2, 2, 2, 3, 4, 3, 4, 3, 4, 4, 4] : [ 0 - 15 ] : 2 : [ 3 - 8 ] : 9
[1, 1, 1, 2, 2, 2, 2, 2, 3, 4, 3, 4, 3, 4, 4, 4] : [ 0 - 2 ] : 1 : [ 0 - 3 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 15 ] : 4 : [ 11 - 16 ] : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 10 ] : 3 : [ 8 - 11 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 17673
number of partitions: 4
number of swaps: 8
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 4, 3, 4, 4] : [ 0 - 15 ] : 2 : [ 4 - 8 ] : 7
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 4, 3, 4, 4] : [ 0 - 3 ] : 1 : [ 0 - 4 ] : 0
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 15 ] : 4 : [ 11 - 16 ] : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 10 ] : 3 : [ 8 - 11 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 10443
number of partitions: 1
number of swaps: 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 0 - 17 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 7276
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 1 - 17 ] : 1


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 7749
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 0 - 16 ] : 2 : [ 0 - 16 ] : 1


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 12593
number of partitions: 2
number of swaps: 3
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 17 ] : 1 : [ 0 - 15 ] : 3
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 15 - 17 ] : 100 : [ 16 - 18 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 15435
number of partitions: 3
number of swaps: 5
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 0 - 17 ] : 1 : [ 0 - 15 ] : 3
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 15 - 17 ] : 2 : [ 15 - 16 ] : 2
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 16 - 17 ] : 100 : [ 16 - 18 ] : 0


partition: DNF - pivot: MID
--------------------------------------------------------------------------------
duration [ns]: 32470
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
6454014


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 2269
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 1450
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 1702
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 10157
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2] : median swaps : 1
[1, 2] : [ 0 - 1 ] : 2 : [ 1 - 2 ] : 0


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 7423
number of partitions: 1
number of swaps: 2
[1, 2, 3] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 3, 2] : median swaps : 1
[1, 2, 3] : [ 0 - 2 ] : 2 : [ 1 - 2 ] : 1


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 11299
number of partitions: 2
number of swaps: 3
[3, 2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 3, 1] : median swaps : 1
[1, 3, 2] : [ 0 - 2 ] : 1 : [ 0 - 1 ] : 1
[1, 2, 3] : median swaps : 1
[1, 2, 3] : [ 1 - 2 ] : 3 : [ 2 - 3 ] : 0


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 8385
number of partitions: 1
number of swaps: 0
[1, 1, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1] : median swaps : 0
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : [ 0 - 4 ] : 0


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 11139
number of partitions: 2
number of swaps: 3
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 3, 3, 3, 3, 2] : median swaps : 0
[1, 2, 3, 3, 3, 3] : [ 0 - 5 ] : 2 : [ 1 - 2 ] : 3
[1, 2, 3, 3, 3, 3] : median swaps : 0
[1, 2, 3, 3, 3, 3] : [ 2 - 5 ] : 3 : [ 2 - 6 ] : 0


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 19349
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
duration [ns]: 24437
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
duration [ns]: 24000
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
duration [ns]: 23362
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
duration [ns]: 24186
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
duration [ns]: 12430
number of partitions: 1
number of swaps: 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 0 - 17 ] : 0


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 19710
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 1 - 17 ] : 1


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 11271
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : median swaps : 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 0 - 16 ] : 2 : [ 0 - 16 ] : 1


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 16180
number of partitions: 2
number of swaps: 11
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 100, 1, 1, 1, 1, 1, 1, 1, 2, 100] : median swaps : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 17 ] : 100 : [ 16 - 18 ] : 8
[1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 100, 100] : median swaps : 1
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 15 ] : 1 : [ 0 - 15 ] : 1


partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
duration [ns]: 18139
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
duration [ns]: 50067
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
5678859


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 1439
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 1494
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 1665
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 9914
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2] : [ 0 - 1 ] : 2 : [ 1 - 2 ] : 1


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 7382
number of partitions: 1
number of swaps: 0
[1, 2, 3] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : [ 1 - 2 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 5142
number of partitions: 1
number of swaps: 1
[3, 2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : [ 1 - 2 ] : 1


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 6991
number of partitions: 1
number of swaps: 0
[1, 1, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : [ 0 - 4 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 7522
number of partitions: 2
number of swaps: 1
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3, 3, 3, 3] : [ 0 - 5 ] : 3 : [ 2 - 6 ] : 1
[1, 2, 3, 3, 3, 3] : [ 0 - 1 ] : 1 : [ 0 - 1 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 10713
number of partitions: 3
number of swaps: 4
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 2, 4, 3, 2] : [ 0 - 6 ] : 1 : [ 0 - 3 ] : 3
[1, 1, 1, 2, 2, 3, 4] : [ 3 - 6 ] : 3 : [ 5 - 6 ] : 1
[1, 1, 1, 2, 2, 3, 4] : [ 3 - 4 ] : 2 : [ 3 - 5 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 17227
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
duration [ns]: 22097
number of partitions: 4
number of swaps: 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 4 : [ 11 - 16 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 10 ] : 3 : [ 8 - 11 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 7 ] : 2 : [ 3 - 8 ] : 0
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 2 ] : 1 : [ 0 - 3 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 18850
number of partitions: 4
number of swaps: 18
[4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 1, 2, 2, 2, 3, 2, 3, 1, 3, 1, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 4 : [ 11 - 16 ] : 11
[2, 1, 2, 2, 2, 2, 1, 1, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 10 ] : 3 : [ 8 - 11 ] : 3
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 7 ] : 1 : [ 0 - 3 ] : 4
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 7 ] : 2 : [ 3 - 8 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 18147
number of partitions: 4
number of swaps: 13
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 2, 4, 4] : [ 0 - 15 ] : 1 : [ 0 - 4 ] : 11
[1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 2, 4, 4, 4, 4, 4] : [ 4 - 15 ] : 4 : [ 11 - 16 ] : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 10 ] : 3 : [ 8 - 11 ] : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 7 ] : 2 : [ 4 - 8 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 5396
number of partitions: 1
number of swaps: 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 0 - 17 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 7450
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 1 - 17 ] : 1


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 8143
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 0 - 16 ] : 2 : [ 0 - 16 ] : 1


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 19192
number of partitions: 3
number of swaps: 17
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 17 ] : 2 : [ 15 - 16 ] : 17
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 14 ] : 1 : [ 0 - 15 ] : 0
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 16 - 17 ] : 100 : [ 16 - 18 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 15995
number of partitions: 2
number of swaps: 16
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 17 ] : 100 : [ 16 - 18 ] : 16
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 15 ] : 1 : [ 0 - 15 ] : 0


partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
duration [ns]: 42750
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
5297526


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 1747
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 1494
number of partitions: 0
number of swaps: 0
[] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 1812
number of partitions: 0
number of swaps: 0
[1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 10707
number of partitions: 1
number of swaps: 1
[2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2] : [ 0 - 1 ] : 1 : [ 0 - 1 ] : 1


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 9790
number of partitions: 1
number of swaps: 0
[1, 2, 3] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : [ 1 - 2 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 7776
number of partitions: 1
number of swaps: 1
[3, 2, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3] : [ 0 - 2 ] : 2 : [ 1 - 2 ] : 1


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 7504
number of partitions: 1
number of swaps: 0
[1, 1, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1] : [ 0 - 3 ] : 1 : [ 0 - 4 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 15166
number of partitions: 2
number of swaps: 3
[1, 3, 3, 3, 3, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 3, 3, 3, 3] : [ 0 - 5 ] : 2 : [ 1 - 2 ] : 3
[1, 2, 3, 3, 3, 3] : [ 2 - 5 ] : 3 : [ 2 - 6 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 16034
number of partitions: 3
number of swaps: 5
[1, 2, 3, 4, 2, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 1, 2, 1, 3, 4] : [ 0 - 6 ] : 3 : [ 5 - 6 ] : 4
[1, 1, 1, 2, 2, 3, 4] : [ 0 - 4 ] : 1 : [ 0 - 3 ] : 1
[1, 1, 1, 2, 2, 3, 4] : [ 3 - 4 ] : 2 : [ 3 - 5 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 44419
number of partitions: 5
number of swaps: 11
[1000000, 10001, 10, 30, 40, 50, 1, 999] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[999, 10, 30, 40, 50, 1, 10001, 1000000] : [ 0 - 7 ] : 10001 : [ 6 - 7 ] : 6
[1, 10, 30, 50, 40, 999, 10001, 1000000] : [ 0 - 5 ] : 30 : [ 2 - 3 ] : 2
[1, 10, 30, 50, 40, 999, 10001, 1000000] : [ 0 - 1 ] : 1 : [ 0 - 1 ] : 0
[1, 10, 30, 40, 999, 50, 10001, 1000000] : [ 3 - 5 ] : 40 : [ 3 - 4 ] : 2
[1, 10, 30, 40, 50, 999, 10001, 1000000] : [ 4 - 5 ] : 50 : [ 4 - 5 ] : 1


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 25963
number of partitions: 4
number of swaps: 15
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 2] : [ 0 - 15 ] : 1 : [ 0 - 3 ] : 12
[1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 2, 4, 4, 4, 4, 4] : [ 3 - 15 ] : 4 : [ 11 - 16 ] : 1
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 10 ] : 2 : [ 3 - 8 ] : 2
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 8 - 10 ] : 3 : [ 8 - 11 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 27031
number of partitions: 4
number of swaps: 18
[4, 2, 1, 2, 4, 2, 4, 2, 4, 3, 2, 3, 1, 3, 4, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 1, 2, 2, 2, 3, 2, 3, 1, 3, 1, 4, 4, 4, 4, 4] : [ 0 - 15 ] : 4 : [ 11 - 16 ] : 11
[2, 1, 2, 2, 2, 2, 1, 1, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 10 ] : 3 : [ 8 - 11 ] : 3
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 0 - 7 ] : 1 : [ 0 - 3 ] : 4
[1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 3 - 7 ] : 2 : [ 3 - 8 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 24448
number of partitions: 4
number of swaps: 13
[1, 1, 4, 4, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 2, 4, 4] : [ 0 - 15 ] : 1 : [ 0 - 4 ] : 11
[1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 2, 4, 4, 4, 4, 4] : [ 4 - 15 ] : 4 : [ 11 - 16 ] : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 10 ] : 3 : [ 8 - 11 ] : 1
[1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4] : [ 4 - 7 ] : 2 : [ 4 - 8 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 9595
number of partitions: 1
number of swaps: 0
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 2 : [ 0 - 17 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 18602
number of partitions: 2
number of swaps: 15
[2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 0 - 16 ] : 1 : [ 0 - 1 ] : 15
[1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2] : [ 1 - 16 ] : 2 : [ 1 - 17 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 12460
number of partitions: 1
number of swaps: 1
[2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3] : [ 0 - 16 ] : 2 : [ 0 - 16 ] : 1


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 18001
number of partitions: 2
number of swaps: 3
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 0 - 17 ] : 1 : [ 0 - 15 ] : 3
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 15 - 17 ] : 100 : [ 16 - 18 ] : 0


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 20238
number of partitions: 2
number of swaps: 4
[100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100, 2, 100] : [ 0 - 17 ] : 1 : [ 0 - 15 ] : 3
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 100, 100] : [ 15 - 17 ] : 100 : [ 16 - 18 ] : 1


partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
duration [ns]: 57679
number of partitions: 10
number of swaps: 28
[16, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 9, 16] : [ lo - hi ] : pv : dnf[ lo - hi ] : sw
[9, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 16] : [ 0 - 18 ] : 13 : [ 15 - 16 ] : 2
[7, 0, 1, 2, 3, 4, 5, 6, 8, 9, 9, 10, 11, 12, 9, 13, 14, 16, 16] : [ 0 - 14 ] : 8 : [ 8 - 9 ] : 14
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 11, 12, 9, 13, 14, 16, 16] : [ 0 - 7 ] : 2 : [ 2 - 3 ] : 7
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 11, 12, 9, 13, 14, 16, 16] : [ 0 - 1 ] : 0 : [ 0 - 1 ] : 0
[0, 1, 2, 3, 4, 5, 7, 6, 8, 9, 9, 10, 11, 12, 9, 13, 14, 16, 16] : [ 3 - 7 ] : 5 : [ 5 - 6 ] : 1
[0, 1, 2, 3, 4, 5, 7, 6, 8, 9, 9, 10, 11, 12, 9, 13, 14, 16, 16] : [ 3 - 4 ] : 4 : [ 4 - 5 ] : 0
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 11, 12, 9, 13, 14, 16, 16] : [ 6 - 7 ] : 7 : [ 7 - 8 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 9, 11, 12, 13, 14, 16, 16] : [ 9 - 14 ] : 11 : [ 13 - 14 ] : 2
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 9 - 12 ] : 9 : [ 9 - 12 ] : 1
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 11, 12, 13, 14, 16, 16] : [ 16 - 18 ] : 16 : [ 17 - 19 ] : 0

total duration [ns]:
6019367



--------------------------------------------------------------------------------
QUICKSORT: 10 x array[100] - ORDERED
--------------------------------------------------------------------------------
original:
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100]
sorted:
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100]

name: ORDERED - partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 102680
loop: #1 - LOW - duration [ns]: 42885
loop: #2 - LOW - duration [ns]: 45000
loop: #3 - LOW - duration [ns]: 35172
loop: #4 - LOW - duration [ns]: 39712
loop: #5 - LOW - duration [ns]: 50964
loop: #6 - LOW - duration [ns]: 38210
loop: #7 - LOW - duration [ns]: 38296
loop: #8 - LOW - duration [ns]: 36766
loop: #9 - LOW - duration [ns]: 36391

name: ORDERED - partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 31651
loop: #1 - MID - duration [ns]: 35144
loop: #2 - MID - duration [ns]: 35538
loop: #3 - MID - duration [ns]: 21707
loop: #4 - MID - duration [ns]: 17721
loop: #5 - MID - duration [ns]: 26735
loop: #6 - MID - duration [ns]: 15051
loop: #7 - MID - duration [ns]: 16178
loop: #8 - MID - duration [ns]: 15712
loop: #9 - MID - duration [ns]: 15234

name: ORDERED - partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 60164
loop: #1 - MEDIAN - duration [ns]: 54497
loop: #2 - MEDIAN - duration [ns]: 64965
loop: #3 - MEDIAN - duration [ns]: 57959
loop: #4 - MEDIAN - duration [ns]: 56584
loop: #5 - MEDIAN - duration [ns]: 37912
loop: #6 - MEDIAN - duration [ns]: 21596
loop: #7 - MEDIAN - duration [ns]: 21472
loop: #8 - MEDIAN - duration [ns]: 20858
loop: #9 - MEDIAN - duration [ns]: 18286

name: ORDERED - partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 59592
loop: #1 - HIGH - duration [ns]: 42456
loop: #2 - HIGH - duration [ns]: 41113
loop: #3 - HIGH - duration [ns]: 38739
loop: #4 - HIGH - duration [ns]: 39715
loop: #5 - HIGH - duration [ns]: 37775
loop: #6 - HIGH - duration [ns]: 37759
loop: #7 - HIGH - duration [ns]: 32953
loop: #8 - HIGH - duration [ns]: 33244
loop: #9 - HIGH - duration [ns]: 33929

name: ORDERED - partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 99923
loop: #1 - RANDOM - duration [ns]: 93616
loop: #2 - RANDOM - duration [ns]: 78874
loop: #3 - RANDOM - duration [ns]: 50186
loop: #4 - RANDOM - duration [ns]: 52164
loop: #5 - RANDOM - duration [ns]: 49139
loop: #6 - RANDOM - duration [ns]: 32458
loop: #7 - RANDOM - duration [ns]: 31702
loop: #8 - RANDOM - duration [ns]: 29725
loop: #9 - RANDOM - duration [ns]: 28702

name: ORDERED - partition: HOARE - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        46608
         MID |        23067
      MEDIAN |        41429
        HIGH |        39728
      RANDOM |        54649

name: ORDERED - partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 174406
loop: #1 - LOW - duration [ns]: 196603
loop: #2 - LOW - duration [ns]: 170098
loop: #3 - LOW - duration [ns]: 199194
loop: #4 - LOW - duration [ns]: 421038
loop: #5 - LOW - duration [ns]: 210066
loop: #6 - LOW - duration [ns]: 186468
loop: #7 - LOW - duration [ns]: 183136
loop: #8 - LOW - duration [ns]: 231448
loop: #9 - LOW - duration [ns]: 189757

name: ORDERED - partition: DNF - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 98375
loop: #1 - MID - duration [ns]: 101530
loop: #2 - MID - duration [ns]: 262018
loop: #3 - MID - duration [ns]: 28183
loop: #4 - MID - duration [ns]: 34901
loop: #5 - MID - duration [ns]: 35227
loop: #6 - MID - duration [ns]: 27180
loop: #7 - MID - duration [ns]: 25598
loop: #8 - MID - duration [ns]: 47724
loop: #9 - MID - duration [ns]: 24757

name: ORDERED - partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 65241
loop: #1 - MEDIAN - duration [ns]: 32173
loop: #2 - MEDIAN - duration [ns]: 31258
loop: #3 - MEDIAN - duration [ns]: 27283
loop: #4 - MEDIAN - duration [ns]: 27547
loop: #5 - MEDIAN - duration [ns]: 26915
loop: #6 - MEDIAN - duration [ns]: 27091
loop: #7 - MEDIAN - duration [ns]: 27032
loop: #8 - MEDIAN - duration [ns]: 25713
loop: #9 - MEDIAN - duration [ns]: 25084

name: ORDERED - partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 76100
loop: #1 - HIGH - duration [ns]: 50076
loop: #2 - HIGH - duration [ns]: 68938
loop: #3 - HIGH - duration [ns]: 66762
loop: #4 - HIGH - duration [ns]: 56159
loop: #5 - HIGH - duration [ns]: 55394
loop: #6 - HIGH - duration [ns]: 56685
loop: #7 - HIGH - duration [ns]: 55043
loop: #8 - HIGH - duration [ns]: 55423
loop: #9 - HIGH - duration [ns]: 54956

name: ORDERED - partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 30899
loop: #1 - RANDOM - duration [ns]: 29345
loop: #2 - RANDOM - duration [ns]: 47088
loop: #3 - RANDOM - duration [ns]: 37937
loop: #4 - RANDOM - duration [ns]: 39726
loop: #5 - RANDOM - duration [ns]: 36495
loop: #6 - RANDOM - duration [ns]: 41951
loop: #7 - RANDOM - duration [ns]: 125384
loop: #8 - RANDOM - duration [ns]: 41391
loop: #9 - RANDOM - duration [ns]: 39368

name: ORDERED - partition: DNF - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |       216221
         MID |        68549
      MEDIAN |        31534
        HIGH |        59554
      RANDOM |        46958


--------------------------------------------------------------------------------
QUICKSORT: 10 x array[100] - REVERSE
--------------------------------------------------------------------------------
original:
[100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83, 82, 81, 80, 79, 78, 77, 76, 75, 74, 73, 72, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
sorted:
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100]

name: REVERSE - partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 53802
loop: #1 - LOW - duration [ns]: 44482
loop: #2 - LOW - duration [ns]: 56226
loop: #3 - LOW - duration [ns]: 55084
loop: #4 - LOW - duration [ns]: 43339
loop: #5 - LOW - duration [ns]: 48125
loop: #6 - LOW - duration [ns]: 50100
loop: #7 - LOW - duration [ns]: 44720
loop: #8 - LOW - duration [ns]: 53002
loop: #9 - LOW - duration [ns]: 47778

name: REVERSE - partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 29859
loop: #1 - MID - duration [ns]: 21679
loop: #2 - MID - duration [ns]: 22346
loop: #3 - MID - duration [ns]: 22017
loop: #4 - MID - duration [ns]: 20838
loop: #5 - MID - duration [ns]: 22815
loop: #6 - MID - duration [ns]: 23825
loop: #7 - MID - duration [ns]: 22388
loop: #8 - MID - duration [ns]: 26257
loop: #9 - MID - duration [ns]: 21225

name: REVERSE - partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 42386
loop: #1 - MEDIAN - duration [ns]: 38339
loop: #2 - MEDIAN - duration [ns]: 35234
loop: #3 - MEDIAN - duration [ns]: 36989
loop: #4 - MEDIAN - duration [ns]: 39874
loop: #5 - MEDIAN - duration [ns]: 36122
loop: #6 - MEDIAN - duration [ns]: 35473
loop: #7 - MEDIAN - duration [ns]: 32230
loop: #8 - MEDIAN - duration [ns]: 37463
loop: #9 - MEDIAN - duration [ns]: 33393

name: REVERSE - partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 45442
loop: #1 - HIGH - duration [ns]: 45570
loop: #2 - HIGH - duration [ns]: 43671
loop: #3 - HIGH - duration [ns]: 45920
loop: #4 - HIGH - duration [ns]: 42775
loop: #5 - HIGH - duration [ns]: 47744
loop: #6 - HIGH - duration [ns]: 47635
loop: #7 - HIGH - duration [ns]: 49977
loop: #8 - HIGH - duration [ns]: 49200
loop: #9 - HIGH - duration [ns]: 44611

name: REVERSE - partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 48776
loop: #1 - RANDOM - duration [ns]: 42246
loop: #2 - RANDOM - duration [ns]: 43982
loop: #3 - RANDOM - duration [ns]: 41561
loop: #4 - RANDOM - duration [ns]: 43873
loop: #5 - RANDOM - duration [ns]: 59274
loop: #6 - RANDOM - duration [ns]: 43548
loop: #7 - RANDOM - duration [ns]: 46298
loop: #8 - RANDOM - duration [ns]: 36920
loop: #9 - RANDOM - duration [ns]: 37892

name: REVERSE - partition: HOARE - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        49666
         MID |        23325
      MEDIAN |        36750
        HIGH |        46255
      RANDOM |        44437

name: REVERSE - partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 130170
loop: #1 - LOW - duration [ns]: 180124
loop: #2 - LOW - duration [ns]: 122893
loop: #3 - LOW - duration [ns]: 132958
loop: #4 - LOW - duration [ns]: 127582
loop: #5 - LOW - duration [ns]: 137906
loop: #6 - LOW - duration [ns]: 126188
loop: #7 - LOW - duration [ns]: 116391
loop: #8 - LOW - duration [ns]: 130119
loop: #9 - LOW - duration [ns]: 121631

name: REVERSE - partition: DNF - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 35318
loop: #1 - MID - duration [ns]: 29401
loop: #2 - MID - duration [ns]: 30385
loop: #3 - MID - duration [ns]: 28119
loop: #4 - MID - duration [ns]: 27484
loop: #5 - MID - duration [ns]: 30556
loop: #6 - MID - duration [ns]: 28717
loop: #7 - MID - duration [ns]: 30217
loop: #8 - MID - duration [ns]: 24941
loop: #9 - MID - duration [ns]: 26974

name: REVERSE - partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 51486
loop: #1 - MEDIAN - duration [ns]: 42029
loop: #2 - MEDIAN - duration [ns]: 40562
loop: #3 - MEDIAN - duration [ns]: 44679
loop: #4 - MEDIAN - duration [ns]: 35353
loop: #5 - MEDIAN - duration [ns]: 43440
loop: #6 - MEDIAN - duration [ns]: 37518
loop: #7 - MEDIAN - duration [ns]: 37130
loop: #8 - MEDIAN - duration [ns]: 37707
loop: #9 - MEDIAN - duration [ns]: 36799

name: REVERSE - partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 74581
loop: #1 - HIGH - duration [ns]: 65191
loop: #2 - HIGH - duration [ns]: 65072
loop: #3 - HIGH - duration [ns]: 74181
loop: #4 - HIGH - duration [ns]: 49983
loop: #5 - HIGH - duration [ns]: 25211
loop: #6 - HIGH - duration [ns]: 23674
loop: #7 - HIGH - duration [ns]: 22087
loop: #8 - HIGH - duration [ns]: 20886
loop: #9 - HIGH - duration [ns]: 18853

name: REVERSE - partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 36064
loop: #1 - RANDOM - duration [ns]: 18480
loop: #2 - RANDOM - duration [ns]: 28687
loop: #3 - RANDOM - duration [ns]: 39720
loop: #4 - RANDOM - duration [ns]: 21822
loop: #5 - RANDOM - duration [ns]: 33348
loop: #6 - RANDOM - duration [ns]: 24346
loop: #7 - RANDOM - duration [ns]: 53102
loop: #8 - RANDOM - duration [ns]: 18876
loop: #9 - RANDOM - duration [ns]: 17402

name: REVERSE - partition: DNF - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |       132596
         MID |        29211
      MEDIAN |        40670
        HIGH |        43972
      RANDOM |        29185


--------------------------------------------------------------------------------
QUICKSORT: 10 x array[100] - ONEOFF
--------------------------------------------------------------------------------
original:
[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 1]
sorted:
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100]

name: ONEOFF - partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 55609
loop: #1 - LOW - duration [ns]: 39656
loop: #2 - LOW - duration [ns]: 38347
loop: #3 - LOW - duration [ns]: 34044
loop: #4 - LOW - duration [ns]: 35701
loop: #5 - LOW - duration [ns]: 36208
loop: #6 - LOW - duration [ns]: 36084
loop: #7 - LOW - duration [ns]: 35141
loop: #8 - LOW - duration [ns]: 33819
loop: #9 - LOW - duration [ns]: 28942

name: ONEOFF - partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 18937
loop: #1 - MID - duration [ns]: 17881
loop: #2 - MID - duration [ns]: 14987
loop: #3 - MID - duration [ns]: 17041
loop: #4 - MID - duration [ns]: 16778
loop: #5 - MID - duration [ns]: 15300
loop: #6 - MID - duration [ns]: 29240
loop: #7 - MID - duration [ns]: 17101
loop: #8 - MID - duration [ns]: 16542
loop: #9 - MID - duration [ns]: 17337

name: ONEOFF - partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 54023
loop: #1 - MEDIAN - duration [ns]: 27378
loop: #2 - MEDIAN - duration [ns]: 23562
loop: #3 - MEDIAN - duration [ns]: 25839
loop: #4 - MEDIAN - duration [ns]: 24734
loop: #5 - MEDIAN - duration [ns]: 21427
loop: #6 - MEDIAN - duration [ns]: 26344
loop: #7 - MEDIAN - duration [ns]: 29829
loop: #8 - MEDIAN - duration [ns]: 29825
loop: #9 - MEDIAN - duration [ns]: 27091

name: ONEOFF - partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 42676
loop: #1 - HIGH - duration [ns]: 39717
loop: #2 - HIGH - duration [ns]: 39517
loop: #3 - HIGH - duration [ns]: 39194
loop: #4 - HIGH - duration [ns]: 34520
loop: #5 - HIGH - duration [ns]: 36934
loop: #6 - HIGH - duration [ns]: 38415
loop: #7 - HIGH - duration [ns]: 36930
loop: #8 - HIGH - duration [ns]: 37064
loop: #9 - HIGH - duration [ns]: 36022

name: ONEOFF - partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 28842
loop: #1 - RANDOM - duration [ns]: 24862
loop: #2 - RANDOM - duration [ns]: 26653
loop: #3 - RANDOM - duration [ns]: 23377
loop: #4 - RANDOM - duration [ns]: 24116
loop: #5 - RANDOM - duration [ns]: 34135
loop: #6 - RANDOM - duration [ns]: 26729
loop: #7 - RANDOM - duration [ns]: 28779
loop: #8 - RANDOM - duration [ns]: 25232
loop: #9 - RANDOM - duration [ns]: 28160

name: ONEOFF - partition: HOARE - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        37355
         MID |        18114
      MEDIAN |        29005
        HIGH |        38099
      RANDOM |        27089

name: ONEOFF - partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 20516
loop: #1 - LOW - duration [ns]: 17016
loop: #2 - LOW - duration [ns]: 14516
loop: #3 - LOW - duration [ns]: 14964
loop: #4 - LOW - duration [ns]: 15031
loop: #5 - LOW - duration [ns]: 33643
loop: #6 - LOW - duration [ns]: 24114
loop: #7 - LOW - duration [ns]: 19782
loop: #8 - LOW - duration [ns]: 17600
loop: #9 - LOW - duration [ns]: 15561

name: ONEOFF - partition: DNF - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 18040
loop: #1 - MID - duration [ns]: 17197
loop: #2 - MID - duration [ns]: 13240
loop: #3 - MID - duration [ns]: 13543
loop: #4 - MID - duration [ns]: 13348
loop: #5 - MID - duration [ns]: 11436
loop: #6 - MID - duration [ns]: 10652
loop: #7 - MID - duration [ns]: 10508
loop: #8 - MID - duration [ns]: 11671
loop: #9 - MID - duration [ns]: 9180

name: ONEOFF - partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 35959
loop: #1 - MEDIAN - duration [ns]: 15253
loop: #2 - MEDIAN - duration [ns]: 14711
loop: #3 - MEDIAN - duration [ns]: 11768
loop: #4 - MEDIAN - duration [ns]: 12432
loop: #5 - MEDIAN - duration [ns]: 12132
loop: #6 - MEDIAN - duration [ns]: 13692
loop: #7 - MEDIAN - duration [ns]: 20171
loop: #8 - MEDIAN - duration [ns]: 11196
loop: #9 - MEDIAN - duration [ns]: 12924

name: ONEOFF - partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 29325
loop: #1 - HIGH - duration [ns]: 31709
loop: #2 - HIGH - duration [ns]: 33136
loop: #3 - HIGH - duration [ns]: 29389
loop: #4 - HIGH - duration [ns]: 24417
loop: #5 - HIGH - duration [ns]: 28509
loop: #6 - HIGH - duration [ns]: 26261
loop: #7 - HIGH - duration [ns]: 28394
loop: #8 - HIGH - duration [ns]: 27424
loop: #9 - HIGH - duration [ns]: 27486

name: ONEOFF - partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 16067
loop: #1 - RANDOM - duration [ns]: 17435
loop: #2 - RANDOM - duration [ns]: 15300
loop: #3 - RANDOM - duration [ns]: 14209
loop: #4 - RANDOM - duration [ns]: 14217
loop: #5 - RANDOM - duration [ns]: 16454
loop: #6 - RANDOM - duration [ns]: 15248
loop: #7 - RANDOM - duration [ns]: 16127
loop: #8 - RANDOM - duration [ns]: 15496
loop: #9 - RANDOM - duration [ns]: 15819

name: ONEOFF - partition: DNF - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        19274
         MID |        12882
      MEDIAN |        16024
        HIGH |        28605
      RANDOM |        15637


--------------------------------------------------------------------------------
QUICKSORT: 10 x array[100] - SHUFFLED
--------------------------------------------------------------------------------
original:
[53, 48, 2, 89, 9, 28, 86, 60, 57, 15, 23, 55, 27, 8, 18, 35, 26, 29, 3, 61, 65, 13, 45, 31, 79, 97, 54, 69, 70, 16, 11, 7, 33, 85, 66, 62, 47, 21, 25, 1, 4, 87, 100, 42, 24, 56, 32, 51, 92, 99, 6, 12, 78, 10, 72, 67, 77, 30, 88, 75, 91, 64, 43, 5, 63, 49, 73, 98, 17, 81, 82, 93, 84, 36, 38, 22, 34, 71, 37, 39, 80, 94, 76, 58, 96, 40, 90, 95, 46, 50, 52, 44, 41, 68, 74, 59, 83, 20, 19, 14]
sorted:
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100]

name: SHUFFLED - partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 30206
loop: #1 - LOW - duration [ns]: 24684
loop: #2 - LOW - duration [ns]: 20168
loop: #3 - LOW - duration [ns]: 19713
loop: #4 - LOW - duration [ns]: 21519
loop: #5 - LOW - duration [ns]: 19385
loop: #6 - LOW - duration [ns]: 19303
loop: #7 - LOW - duration [ns]: 20533
loop: #8 - LOW - duration [ns]: 30931
loop: #9 - LOW - duration [ns]: 19163

name: SHUFFLED - partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 33540
loop: #1 - MID - duration [ns]: 24464
loop: #2 - MID - duration [ns]: 22489
loop: #3 - MID - duration [ns]: 19835
loop: #4 - MID - duration [ns]: 21358
loop: #5 - MID - duration [ns]: 19576
loop: #6 - MID - duration [ns]: 19561
loop: #7 - MID - duration [ns]: 21491
loop: #8 - MID - duration [ns]: 21567
loop: #9 - MID - duration [ns]: 19211

name: SHUFFLED - partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 27931
loop: #1 - MEDIAN - duration [ns]: 56720
loop: #2 - MEDIAN - duration [ns]: 34706
loop: #3 - MEDIAN - duration [ns]: 24920
loop: #4 - MEDIAN - duration [ns]: 22735
loop: #5 - MEDIAN - duration [ns]: 25574
loop: #6 - MEDIAN - duration [ns]: 25519
loop: #7 - MEDIAN - duration [ns]: 23976
loop: #8 - MEDIAN - duration [ns]: 23098
loop: #9 - MEDIAN - duration [ns]: 22035

name: SHUFFLED - partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 25249
loop: #1 - HIGH - duration [ns]: 32318
loop: #2 - HIGH - duration [ns]: 18658
loop: #3 - HIGH - duration [ns]: 31721
loop: #4 - HIGH - duration [ns]: 19210
loop: #5 - HIGH - duration [ns]: 19194
loop: #6 - HIGH - duration [ns]: 20825
loop: #7 - HIGH - duration [ns]: 14108
loop: #8 - HIGH - duration [ns]: 27940
loop: #9 - HIGH - duration [ns]: 17489

name: SHUFFLED - partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 33105
loop: #1 - RANDOM - duration [ns]: 43100
loop: #2 - RANDOM - duration [ns]: 35516
loop: #3 - RANDOM - duration [ns]: 31496
loop: #4 - RANDOM - duration [ns]: 32349
loop: #5 - RANDOM - duration [ns]: 30819
loop: #6 - RANDOM - duration [ns]: 28626
loop: #7 - RANDOM - duration [ns]: 31756
loop: #8 - RANDOM - duration [ns]: 43968
loop: #9 - RANDOM - duration [ns]: 32773

name: SHUFFLED - partition: HOARE - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        22561
         MID |        22309
      MEDIAN |        28721
        HIGH |        22671
      RANDOM |        34351

name: SHUFFLED - partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 17735
loop: #1 - LOW - duration [ns]: 14254
loop: #2 - LOW - duration [ns]: 10575
loop: #3 - LOW - duration [ns]: 10651
loop: #4 - LOW - duration [ns]: 10121
loop: #5 - LOW - duration [ns]: 11116
loop: #6 - LOW - duration [ns]: 9091
loop: #7 - LOW - duration [ns]: 9574
loop: #8 - LOW - duration [ns]: 9478
loop: #9 - LOW - duration [ns]: 9080

name: SHUFFLED - partition: DNF - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 13767
loop: #1 - MID - duration [ns]: 23559
loop: #2 - MID - duration [ns]: 12847
loop: #3 - MID - duration [ns]: 14697
loop: #4 - MID - duration [ns]: 11118
loop: #5 - MID - duration [ns]: 10547
loop: #6 - MID - duration [ns]: 14423
loop: #7 - MID - duration [ns]: 12050
loop: #8 - MID - duration [ns]: 59908
loop: #9 - MID - duration [ns]: 11613

name: SHUFFLED - partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 15335
loop: #1 - MEDIAN - duration [ns]: 13234
loop: #2 - MEDIAN - duration [ns]: 98750
loop: #3 - MEDIAN - duration [ns]: 12516
loop: #4 - MEDIAN - duration [ns]: 10976
loop: #5 - MEDIAN - duration [ns]: 10889
loop: #6 - MEDIAN - duration [ns]: 10684
loop: #7 - MEDIAN - duration [ns]: 11516
loop: #8 - MEDIAN - duration [ns]: 9534
loop: #9 - MEDIAN - duration [ns]: 12000

name: SHUFFLED - partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 13849
loop: #1 - HIGH - duration [ns]: 11478
loop: #2 - HIGH - duration [ns]: 13215
loop: #3 - HIGH - duration [ns]: 10047
loop: #4 - HIGH - duration [ns]: 9654
loop: #5 - HIGH - duration [ns]: 9910
loop: #6 - HIGH - duration [ns]: 9872
loop: #7 - HIGH - duration [ns]: 11634
loop: #8 - HIGH - duration [ns]: 10659
loop: #9 - HIGH - duration [ns]: 10229

name: SHUFFLED - partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 14691
loop: #1 - RANDOM - duration [ns]: 14116
loop: #2 - RANDOM - duration [ns]: 13397
loop: #3 - RANDOM - duration [ns]: 13398
loop: #4 - RANDOM - duration [ns]: 14502
loop: #5 - RANDOM - duration [ns]: 12559
loop: #6 - RANDOM - duration [ns]: 12669
loop: #7 - RANDOM - duration [ns]: 13925
loop: #8 - RANDOM - duration [ns]: 12615
loop: #9 - RANDOM - duration [ns]: 12421

name: SHUFFLED - partition: DNF - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        11168
         MID |        18453
      MEDIAN |        20543
        HIGH |        11055
      RANDOM |        13429


--------------------------------------------------------------------------------
QUICKSORT: 10 x array[100] - RANDOM
--------------------------------------------------------------------------------
original:
[40, 85, 30, 40, 48, 22, 24, 22, 44, 34, 19, 60, 47, 1, 98, 70, 75, 23, 21, 33, 94, 11, 67, 70, 53, 59, 64, 8, 70, 44, 14, 5, 36, 80, 6, 82, 29, 72, 95, 27, 76, 41, 24, 50, 84, 79, 76, 72, 94, 12, 0, 38, 11, 23, 56, 61, 94, 84, 78, 41, 86, 68, 64, 37, 54, 23, 0, 75, 79, 98, 27, 52, 11, 44, 24, 62, 47, 77, 20, 86, 97, 57, 19, 83, 11, 80, 44, 48, 52, 2, 53, 55, 81, 82, 66, 18, 27, 56, 65, 26]
sorted:
[0, 0, 1, 2, 5, 6, 8, 11, 11, 11, 11, 12, 14, 18, 19, 19, 20, 21, 22, 22, 23, 23, 23, 24, 24, 24, 26, 27, 27, 27, 29, 30, 33, 34, 36, 37, 38, 40, 40, 41, 41, 44, 44, 44, 44, 47, 47, 48, 48, 50, 52, 52, 53, 53, 54, 55, 56, 56, 57, 59, 60, 61, 62, 64, 64, 65, 66, 67, 68, 70, 70, 70, 72, 72, 75, 75, 76, 76, 77, 78, 79, 79, 80, 80, 81, 82, 82, 83, 84, 84, 85, 86, 86, 94, 94, 94, 95, 97, 98, 98]

name: RANDOM - partition: HOARE - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 26076
loop: #1 - LOW - duration [ns]: 22088
loop: #2 - LOW - duration [ns]: 21291
loop: #3 - LOW - duration [ns]: 16176
loop: #4 - LOW - duration [ns]: 17600
loop: #5 - LOW - duration [ns]: 16447
loop: #6 - LOW - duration [ns]: 19945
loop: #7 - LOW - duration [ns]: 17431
loop: #8 - LOW - duration [ns]: 17440
loop: #9 - LOW - duration [ns]: 19973

name: RANDOM - partition: HOARE - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 20923
loop: #1 - MID - duration [ns]: 19855
loop: #2 - MID - duration [ns]: 17433
loop: #3 - MID - duration [ns]: 16197
loop: #4 - MID - duration [ns]: 16305
loop: #5 - MID - duration [ns]: 16255
loop: #6 - MID - duration [ns]: 17989
loop: #7 - MID - duration [ns]: 15633
loop: #8 - MID - duration [ns]: 15460
loop: #9 - MID - duration [ns]: 15948

name: RANDOM - partition: HOARE - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 24199
loop: #1 - MEDIAN - duration [ns]: 25035
loop: #2 - MEDIAN - duration [ns]: 23635
loop: #3 - MEDIAN - duration [ns]: 26150
loop: #4 - MEDIAN - duration [ns]: 25127
loop: #5 - MEDIAN - duration [ns]: 25021
loop: #6 - MEDIAN - duration [ns]: 25143
loop: #7 - MEDIAN - duration [ns]: 33630
loop: #8 - MEDIAN - duration [ns]: 24826
loop: #9 - MEDIAN - duration [ns]: 23759

name: RANDOM - partition: HOARE - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 24972
loop: #1 - HIGH - duration [ns]: 22297
loop: #2 - HIGH - duration [ns]: 21459
loop: #3 - HIGH - duration [ns]: 22203
loop: #4 - HIGH - duration [ns]: 21436
loop: #5 - HIGH - duration [ns]: 51357
loop: #6 - HIGH - duration [ns]: 38303
loop: #7 - HIGH - duration [ns]: 21324
loop: #8 - HIGH - duration [ns]: 21385
loop: #9 - HIGH - duration [ns]: 21371

name: RANDOM - partition: HOARE - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 30345
loop: #1 - RANDOM - duration [ns]: 24418
loop: #2 - RANDOM - duration [ns]: 23542
loop: #3 - RANDOM - duration [ns]: 27253
loop: #4 - RANDOM - duration [ns]: 22623
loop: #5 - RANDOM - duration [ns]: 24470
loop: #6 - RANDOM - duration [ns]: 22545
loop: #7 - RANDOM - duration [ns]: 21662
loop: #8 - RANDOM - duration [ns]: 22276
loop: #9 - RANDOM - duration [ns]: 23338

name: RANDOM - partition: HOARE - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |        19447
         MID |        17200
      MEDIAN |        25653
        HIGH |        26611
      RANDOM |        24247

name: RANDOM - partition: DNF - pivot: LOW
--------------------------------------------------------------------------------
loop: #0 - LOW - duration [ns]: 12527
loop: #1 - LOW - duration [ns]: 6465
loop: #2 - LOW - duration [ns]: 4605
loop: #3 - LOW - duration [ns]: 4474
loop: #4 - LOW - duration [ns]: 6852
loop: #5 - LOW - duration [ns]: 7317
loop: #6 - LOW - duration [ns]: 7110
loop: #7 - LOW - duration [ns]: 6808
loop: #8 - LOW - duration [ns]: 7514
loop: #9 - LOW - duration [ns]: 8278

name: RANDOM - partition: DNF - pivot: MID
--------------------------------------------------------------------------------
loop: #0 - MID - duration [ns]: 12153
loop: #1 - MID - duration [ns]: 9695
loop: #2 - MID - duration [ns]: 8868
loop: #3 - MID - duration [ns]: 8275
loop: #4 - MID - duration [ns]: 8055
loop: #5 - MID - duration [ns]: 7505
loop: #6 - MID - duration [ns]: 7310
loop: #7 - MID - duration [ns]: 6930
loop: #8 - MID - duration [ns]: 7252
loop: #9 - MID - duration [ns]: 6604

name: RANDOM - partition: DNF - pivot: MEDIAN
--------------------------------------------------------------------------------
loop: #0 - MEDIAN - duration [ns]: 13790
loop: #1 - MEDIAN - duration [ns]: 9563
loop: #2 - MEDIAN - duration [ns]: 8551
loop: #3 - MEDIAN - duration [ns]: 7595
loop: #4 - MEDIAN - duration [ns]: 7183
loop: #5 - MEDIAN - duration [ns]: 7597
loop: #6 - MEDIAN - duration [ns]: 6981
loop: #7 - MEDIAN - duration [ns]: 6963
loop: #8 - MEDIAN - duration [ns]: 7381
loop: #9 - MEDIAN - duration [ns]: 6646

name: RANDOM - partition: DNF - pivot: HIGH
--------------------------------------------------------------------------------
loop: #0 - HIGH - duration [ns]: 13752
loop: #1 - HIGH - duration [ns]: 9459
loop: #2 - HIGH - duration [ns]: 8857
loop: #3 - HIGH - duration [ns]: 7922
loop: #4 - HIGH - duration [ns]: 7835
loop: #5 - HIGH - duration [ns]: 7391
loop: #6 - HIGH - duration [ns]: 7465
loop: #7 - HIGH - duration [ns]: 7034
loop: #8 - HIGH - duration [ns]: 7449
loop: #9 - HIGH - duration [ns]: 7040

name: RANDOM - partition: DNF - pivot: RANDOM
--------------------------------------------------------------------------------
loop: #0 - RANDOM - duration [ns]: 12784
loop: #1 - RANDOM - duration [ns]: 12963
loop: #2 - RANDOM - duration [ns]: 11669
loop: #3 - RANDOM - duration [ns]: 11669
loop: #4 - RANDOM - duration [ns]: 11405
loop: #5 - RANDOM - duration [ns]: 11250
loop: #6 - RANDOM - duration [ns]: 11486
loop: #7 - RANDOM - duration [ns]: 11354
loop: #8 - RANDOM - duration [ns]: 10428
loop: #9 - RANDOM - duration [ns]: 10108

name: RANDOM - partition: DNF - averages: 10 x array[100]
--------------------------------------------------------------------------------
       pivot | duration [ns]
--------------------------------------------------------------------------------
         LOW |         7195
         MID |         8265
      MEDIAN |         8225
        HIGH |         8420
      RANDOM |        11512
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.354 sec

Results :

Tests run: 6, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 2.522 s
[INFO] Finished at: 2018-12-01T20:43:28+03:00
[INFO] ------------------------------------------------------------------------
```
