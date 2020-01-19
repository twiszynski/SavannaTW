package pl.twisz.plants;

public class Baobab extends Tree {

    public void grow() {
        height += 1;
        if (height < 8){
            branches += 2;
        } else if (height < 30) {
            branches += 3;
        } else {
            branches += 4;
        }
    }

    public void afterRain() {
        height += 2;
        if (height < 8){
            branches += 3;
        } else if (height < 30) {
            branches += 4;
        } else {
            branches += 6;
        }
    }

    @Override
    public String toString() {
        return "B";
    }
}
