java_library(
    name = "kupusoglu_orhan_quicksort_duplicates_0_3_1",
    visibility = ["//visibility:public"],
    srcs = glob(["src/main/java/kupusoglu/orhan/quicksort/*.java"]),
    resources = [
    ],
    deps = [
        "@junit_junit_4_12//jar",
        "@org_hamcrest_hamcrest_core_1_3//jar",
    ],
)

java_test(
    name = "kupusoglu_orhan_quicksort_QuickSortTest",
    size = "small",
    test_class = "kupusoglu.orhan.quicksort.QuickSortTest",
    srcs = ["src/test/java/kupusoglu/orhan/quicksort/QuickSortTest.java"],
    resources = [
    ],
    deps = [
        ":kupusoglu_orhan_quicksort_duplicates_0_3_1",
    ],
)
