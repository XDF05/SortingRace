package g56020.atlg4.sorting.model.sort;

import java.util.LinkedList;
import java.util.Queue;

public class JobManager {
    private final Queue<int[]> data;

    public JobManager() {
        data = new LinkedList<>();
    }

    synchronized public void add(int[] arr) {
        data.offer(arr);
    }

    synchronized public int[] next() {
        return data.poll();
    }
}
