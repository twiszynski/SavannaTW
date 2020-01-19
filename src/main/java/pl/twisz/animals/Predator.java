package pl.twisz.animals;

public abstract class Predator implements Animal {
    private int starvingDays = 0;

    public int getStarvingDays() {
        return starvingDays;
    }

    public void setStarvingDays(int starvingDays) {
        this.starvingDays = starvingDays;
    }
}
