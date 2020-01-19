package pl.twisz.animals;

public abstract class Herbivore implements Animal {
    private int starvingDays = 0;

    @Override
    public int checkStarvingDays() {
        return this.getStarvingDays();
    }

    public int getStarvingDays() {
        return starvingDays;
    }

    public void setStarvingDays(int starvingDays) {
        this.starvingDays = starvingDays;
    }
}
