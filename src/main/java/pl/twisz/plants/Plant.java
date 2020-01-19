package pl.twisz.plants;

import pl.twisz.animals.Herbivore;

public interface Plant {
    void grow();
    void afterRain();
    void afterFire();
    void beEaten(Herbivore animal);
    String info();
}
