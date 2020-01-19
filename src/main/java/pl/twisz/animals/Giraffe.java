package pl.twisz.animals;

import pl.twisz.Cell;
import pl.twisz.plants.Plant;
import pl.twisz.plants.Tree;

public class Giraffe extends Herbivore {

    private final int starvingLimit = 5;

    public int getStarvingLimit() {
        return starvingLimit;
    }

    @Override
    public void eat(Cell cell) {
        Plant currentPlant = cell.getPlant();
        if (currentPlant instanceof Tree) {
            currentPlant.beEaten(this);
        } else {
            this.setStarvingDays(this.getStarvingDays() + 1);
        }
    }
}
