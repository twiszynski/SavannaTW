package pl.twisz.plants;

public class Acacia extends Tree {

    @Override
    public void grow() {
        height += 1;
        if (height < 5){
            branches++;
        } else if (height < 12) {
            branches += 2;
        } else {
            branches += 3;
        }
    }

    @Override
    public void afterRain() {
        height += 2;
        if (height < 5){
            branches += 2;
        } else if (height < 12) {
            branches += 3;
        } else {
            branches += 4;
        }
    }

    @Override
    public String toString() {
        return "A";
    }
}
