package pl.twisz.animals;

import pl.twisz.Cell;

public interface Animal {
    int getStarvingDays();
    int getStarvingLimit();
    void eat(Cell cell);
}
