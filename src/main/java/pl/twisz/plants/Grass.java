package pl.twisz.plants;

import pl.twisz.animals.Herbivore;

public class Grass implements Plant {

    int size = 1;

    public void grow() {
        size += 2;
    }

    public void afterRain() {
        size += 4;
    }

    @Override
    public void afterFire() {
        size = 0;
    }

    public void beEaten(Herbivore animal) {
        if (size >= 3) {
            size -= 3; // Zebra eats typically 3 units of Grass if grass is high enough
            animal.setStarvingDays(0);
        } else if (size >= 1) {
            size = 0; // when size is lower than 3, it drops to 0 when Zebra eats
            animal.setStarvingDays(0);
        } else {
            size = 0; // if size of grass is 0 from the beginning, Zebra is starving
            animal.setStarvingDays(animal.getStarvingDays() + 1);
        }
    }

    @Override
    public String toString() {
        return "~";
    }

    @Override
    public String info() {
        return ("Grass - current size: " + size);
    }
}
