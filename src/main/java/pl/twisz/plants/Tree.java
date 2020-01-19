package pl.twisz.plants;

import pl.twisz.animals.Herbivore;

public abstract class Tree implements Plant {
    int height = 1;
    int branches = 2;

    @Override
    public void beEaten(Herbivore animal) {
        if (branches >= 3) {
            branches -= 3; // Giraffe eats typically 3 branches if there is enough on the tree
            animal.setStarvingDays(0);
        } else if (branches >= 1) {
            branches = 0; // When there is less than 3 branches, giraffe eats all remaining ones and its number drops to 0
            animal.setStarvingDays(0);
        } else {
            branches = 0; // When there is no branches on the tree, giraffe is starving
            animal.setStarvingDays(animal.getStarvingDays() + 1);
        }
    }

    @Override
    public void afterFire() {
        branches = 0;
    }

    @Override
    public String info() {
        return ("" + getClass().getSimpleName() + " - current height: " + height + ", branches: " + branches);
    }
}
