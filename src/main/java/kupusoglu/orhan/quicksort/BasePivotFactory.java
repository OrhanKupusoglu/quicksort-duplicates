package kupusoglu.orhan.quicksort;

public abstract class BasePivotFactory {
    public abstract Pivot createPivot(QuickSort.PIVOT_TYPE type);
}
