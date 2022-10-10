package g56020.atlg4.sorting.model.sort;

import g56020.atlg4.sorting.model.SortType;
//Implementation from : https://fr.wikibooks.org/wiki/Impl%C3%A9mentation_d%27algorithmes_classiques/Algorithmes_de_tri/Tri_par_insertion

public class InsertionSort extends Sort {
    public InsertionSort(SortType name, int size) {
        super(name, size);
    }

    @Override
    public void sort(int[] arr, int n) {
        for (int i = 1; i < n; i++) {
            int element = arr[i];
            setOperations(getOperations() + 1);
            int cpt = i - 1;
            setOperations(getOperations() + 1);
            while (cpt >= 0 && arr[cpt] > element) {
                arr[cpt + 1] = arr[cpt];
                setOperations(getOperations() + 1);
                cpt--;
                setOperations(getOperations() + 1);
            }
            arr[cpt + 1] = element;
            setOperations(getOperations() + 1);
        }
    }
}
