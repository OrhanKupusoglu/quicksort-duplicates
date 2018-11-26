package kupusoglu.orhan.quicksort;

public abstract class BasePartitionFactory {
    public abstract Partition createPartition(QuickSort.PARTITION_TYPE type);
}
