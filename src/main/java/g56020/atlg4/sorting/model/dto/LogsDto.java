package g56020.atlg4.sorting.model.dto;

import g56020.atlg4.sorting.model.SortType;

import java.sql.Timestamp;

public class LogsDto extends Dto<Integer> {

    private final Timestamp timestamp;
    private final String sortType;
    private final int maxSize;

    /**
     * Dto constructor with the key of the data.
     *
     * @param key       key
     * @param timestamp time at which the log has been done
     * @param sortType  type of sorting applied
     * @param maxSize   maximum size of the sorted array
     */
    public LogsDto(Integer key, Timestamp timestamp, String sortType, int maxSize) {
        super(key);
        this.sortType = sortType;
        this.maxSize = maxSize;
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getSortType() {
        return sortType;
    }

    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public String toString() {
        return "LogsDto{" +
                "timestamp=" + timestamp +
                ", sortType=" + sortType +
                ", maxSize=" + maxSize +
                '}';
    }
}
