package kupusoglu.orhan.quicksort;

public class QuickSortMeta {
    private long timeStart;
    private long duration;
    private int numPartitions;
    private int numSwaps;
    private StringBuilder steps = new StringBuilder();

    public QuickSortMeta() {
        // use the setters
    }

    public void startTime() {
        this.timeStart = System.nanoTime();
    }

    public void endTime() {
        this.duration = System.nanoTime() - this.timeStart;
    }

    public void setNumPartitions(int numPartitions) {
        this.numPartitions = numPartitions;
    }

    public void setNumSwaps(int numSwaps) {
        this.numSwaps = numSwaps;
    }

    public <T> void step(T step) {
        this.steps.append(step);
    }

    public long duration() {
        return this.duration;
    }

    public long numPartitions() {
        return this.numPartitions;
    }

    public long numSwaps() {
        return this.numSwaps;
    }

    public String steps() {
        return this.steps.toString();
    }

    public String display() {
        return String.format("duration [ns]: %d\nnumber of partitions: %d\nnumber of swaps: %d\n%s\n",
                             this.duration(), this.numPartitions(), this.numSwaps(), this.steps());
    }
}
