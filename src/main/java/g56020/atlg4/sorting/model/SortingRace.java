package g56020.atlg4.sorting.model;

import g56020.atlg4.sorting.model.config.ConfigManager;
import g56020.atlg4.sorting.model.dto.LogsDto;
import g56020.atlg4.sorting.model.repository.LogsRepository;
import g56020.atlg4.sorting.model.repository.RepositoryException;
import g56020.atlg4.sorting.model.sort.JobManager;
import g56020.atlg4.sorting.model.sort.Sort;
import g56020.atlg4.sorting.model.sort.SortingThread;
import g56020.atlg4.sorting.model.sort.ThreadPool;
import g56020.atlg4.sorting.utils.Observable;
import g56020.atlg4.sorting.utils.Observer;
import g56020.atlg4.sorting.utils.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class SortingRace implements Observer, Observable {
    private final List<Observer> observers;
    private final ThreadPool pool;
    private JobManager jobManager; // en faire un objet.
    private LogsRepository logsRepository;

    public SortingRace() {
        this.observers = new ArrayList<>();
        this.pool = new ThreadPool();
        try {
            ConfigManager.getInstance().load();
            this.logsRepository = new LogsRepository();
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }

    public void start(SortType sortType, int nbThreads, int nbElem) {
        Timestamp ts = Timestamp.from(Instant.now());
        LogsDto logs = new LogsDto(0, ts, sortType.toString(), nbElem);
        try {
            this.logsRepository.insert(logs);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        this.jobManager = new JobManager();
        pool.clear();
        createData(nbElem);
        createThreads(sortType, nbThreads);
        pool.start();
        notifyObservers(State.SORTING);
    }

    private void createThreads(SortType sortType, int nbThreads) {
        for (int i = 0; i < nbThreads; i++) {
            pool.add(new SortingThread(this, sortType, jobManager));
        }
    }

    private void createData(int nbElem) {
        for (int i = 0; i <= nbElem; i += (nbElem / 10)) {
            jobManager.add(generateRandomArray(i));
        }
    }

    private int[] generateRandomArray(int nbElem) {
        Random random = new Random();
        int[] arr = new int[nbElem];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    public void showLogs() {
        try {
            notifyObservers(this.logsRepository.getAll());
        } catch (RepositoryException e) {
            e.printStackTrace();
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

    @Override
    public void update(Observable observable, Object arg) {
        if (arg instanceof Sort) {
            notifyObservers(arg);
        }
    }
}
