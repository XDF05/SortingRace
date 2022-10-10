package g56020.atlg4.sorting.model.sort;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
    List<SortingThread> threads;

    public ThreadPool() {
        this.threads = new ArrayList<>();
    }

    public void start() {
        for (SortingThread t : threads) {
            t.start();
        }
    }

    public void clear() {
        threads.clear();
    }

    public void add(SortingThread thread) {
        threads.add(thread);
    }

}
