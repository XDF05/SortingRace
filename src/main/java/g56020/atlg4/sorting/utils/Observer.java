package g56020.atlg4.sorting.utils;

public interface Observer {

    /**
     * Called whenever the observed object has changed
     */
    void update(Observable observable, Object arg);
}
