package kupusoglu.orhan.quicksort;

public class QuickSortMeta {
    private long timeStart;
    private long duration;
    private int partition;
    private int swap;
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

    public void setPartition(int partition) {
        this.partition = partition;
    }

    public void setSwap(int swap) {
        this.swap = swap;
    }

    public void step(String step) {
        this.steps.append(step);
    }

    public void step(int step) {
        this.steps.append(step);
    }

    public long duration() {
        return this.duration;
    }

    public int partition() {
        return this.partition;
    }

    public int swap() {
        return this.swap;
    }

    public String steps() {
        return this.steps.toString();
    }

    public String display() {
        return String.format("duration [ns]: %d\nnumber of partitions: %d\nnumber of swaps: %d\n%s\n",
                             this.duration(), this.partition(), this.swap(), this.steps());
    }
}
