package g56020.atlg4.sorting.model.sort;

import g56020.atlg4.sorting.model.SortType;

public abstract class Sort {
    private final SortType name;
    private final int size;
    private long operations;
    private long duration;

    public Sort(SortType name, int size) {
        this.name = name;
        this.size = size;
        this.operations = 0;
        this.duration = 0;
    }

    abstract public void sort(int[] arr, int n);

    public SortType getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public long getOperations() {
        return operations;
    }

    public long getDuration() {
        return duration;
    }

    public void setOperations(long operations) {
        this.operations = operations;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

}
