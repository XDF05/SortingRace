package g56020.atlg4.sorting.model;

public enum Difficulty {
    VERY_EASY("Very easy : 0-100", 100),
    EASY("Easy : 0-1K", 1_000),
    HARD("Hard : 0-10K", 10_000),
    VERY_HARD("Very hard : 0-100K", 100_000);
    //...

    public final String label;
    public final int n;

    Difficulty(String label, int n) {
        this.label = label;
        this.n = n;
    }

    @Override
    public String toString() {
        return label;
    }

    public int getN() {
        return n;
    }
}
