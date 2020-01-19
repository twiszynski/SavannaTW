package pl.twisz;

import java.util.Random;

enum Weather {
    SUN, RAIN, FIRE;

    public static void randomizeWeatherConditions(Cell cell) {
        Random random = new Random();
        int c = random.nextInt(20);
        if (c%20 == 19) {
            cell.setWeather(Weather.FIRE);
        } else if (c%20 <= 4) {
            cell.setWeather(Weather.RAIN);
        } else {
            cell.setWeather(Weather.SUN);
        }
    }
}
