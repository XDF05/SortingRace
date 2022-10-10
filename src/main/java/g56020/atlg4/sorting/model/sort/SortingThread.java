package g56020.atlg4.sorting.model.sort;

import g56020.atlg4.sorting.model.SortType;
import g56020.atlg4.sorting.model.SortingRace;
import g56020.atlg4.sorting.model.dto.LogsDto;
import g56020.atlg4.sorting.utils.Observable;
import g56020.atlg4.sorting.utils.Observer;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static g56020.atlg4.sorting.utils.State.FINISHED_SORTING;

public class SortingThread extends Thread implements Observable {

    private final SortType type;
    private final JobManager jobManager;
    /* faire un objet avec les (bonnes) méthodes synchronisées 
    JobManager:
        - synchronized add(int[] array) 
        - synchronized next : return null si vide et la tableau sinon.
            
    
    */
    private final List<Observer> observers;

    public SortingThread(SortingRace model, SortType type, JobManager jobManager) {
        this.type = type;
        this.jobManager = jobManager;
        this.observers = new ArrayList<>();
        addObserver(model);
    }

    private void sort(int[] arr) { // le syncronized sert à rien.

        Sort sort = null;
        switch (type) {
            case BUBBLE_SORT -> {
                sort = new BubbleSort(SortType.BUBBLE_SORT, arr.length);
            }
            case MERGE_SORT -> {
                sort = new MergeSort(SortType.MERGE_SORT, arr.length);
            }
            case INSERTION_SORT -> {
                sort = new InsertionSort(SortType.INSERTION_SORT, arr.length);
            }
        }

        LocalDateTime beforeSort = LocalDateTime.now();
        sort.sort(arr, arr.length);
        LocalDateTime afterSort = LocalDateTime.now();

        Duration duration = Duration.between(beforeSort, afterSort);
        sort.setDuration(duration.toMillis());
        notifyObservers(sort);
    }

    @Override
    public void run() {
        int[] arr;
        while ((arr = jobManager.next()) != null) {
            sort(arr);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object args) {
        for (Observer o : observers) {
            o.update(this, args);
        }
    }
}
