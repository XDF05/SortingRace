package g56020.atlg4.sorting.model.sort;

import g56020.atlg4.sorting.model.SortType;

public class MergeSort extends Sort {

    public MergeSort(SortType name, int size) {
        super(name, size);
    }

    //Implementation used from: https://www.baeldung.com/java-merge-sort
    public void sort(int[] a, int n) {
        setOperations(getOperations() + 1);
        if (n < 2) { //operation
            setOperations(getOperations() + 1);
            return;
        }
        int mid = n / 2; //operation
        setOperations(getOperations() + 1);
        int[] l = new int[mid]; //operation
        setOperations(getOperations() + 1);
        int[] r = new int[n - mid]; //operation
        setOperations(getOperations() + 1);

        for (int i = 0; i < mid; i++) {
            l[i] = a[i]; //operation
            setOperations(getOperations() + 1);
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i]; //operation
            setOperations(getOperations() + 1);
        }
        sort(l, mid);
        sort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    private void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        setOperations(getOperations() + 3);
        while (i < left && j < right) {
            setOperations(getOperations() + 1);
            if (l[i] <= r[j]) { //operation
                a[k++] = l[i++]; //operation
                setOperations(getOperations() + 1);
            } else {
                a[k++] = r[j++]; //operation
                setOperations(getOperations() + 1);
            }
        }
        while (i < left) {
            a[k++] = l[i++]; //operation
            setOperations(getOperations() + 1);
        }
        while (j < right) {
            a[k++] = r[j++]; //operation
            setOperations(getOperations() + 1);
        }
    }
}
