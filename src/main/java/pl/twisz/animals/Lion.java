package pl.twisz.animals;

import pl.twisz.Cell;

public class Lion extends Predator {

    @Override
    public void eat(Cell cell) {
        this.setStarvingDays(getStarvingDays() + 1);
        for (Animal a : cell.getAnimals()) {
            boolean successfulRemoval;
            if (a instanceof Herbivore) {
                successfulRemoval = cell.getFutureAnimals().remove(a);
                if (successfulRemoval) {
                    this.setStarvingDays(0);
                    break;
                }
            }
        }
    }
}
