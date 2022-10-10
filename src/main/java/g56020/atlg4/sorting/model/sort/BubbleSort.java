package g56020.atlg4.sorting.model.sort;

import g56020.atlg4.sorting.model.SortType;

public class BubbleSort extends Sort {

    public BubbleSort(SortType name, int size) {
        super(name, size);
    }

    //implementation from : https://www.geeksforgeeks.org/bubble-sort/
    public void sort(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    setOperations(getOperations() + 1); //comparison
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    setOperations(getOperations() + 1); //temp assignation
                    arr[j] = arr[j + 1];
                    setOperations(getOperations() + 1); //arr[j] assignation
                    arr[j + 1] = temp;
                    setOperations(getOperations() + 1); //arr[j+1] assignation
                }
            }
        }
    }
}
