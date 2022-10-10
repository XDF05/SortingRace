package g56020.atlg4.sorting.model;

public enum SortType {
    BUBBLE_SORT("Bubble sort"),

    MERGE_SORT("Merge sort"),

    INSERTION_SORT("Insertion sort");
    //...

    public final String label;

    SortType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
