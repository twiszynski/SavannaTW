package pl.twisz.animals;

import pl.twisz.Cell;

public interface Animal {
    int checkStarvingDays();
    void eat(Cell cell);
}
