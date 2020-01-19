package pl.twisz;

public class SavannaSimulator {

    //runs simulation with given number of days
    public void simulateByGivenDays(Savanna savanna, int timeInDays){
        InfoPrinter ip = new InfoPrinter(savanna);
        ip.printHello();
        ip.printInitialElementsInfo();
        ip.printLegend();
        savanna.showSavannaBoard();

        for (int i = 0; i < timeInDays; i++) {
            savanna.oneDay();
            savanna.lookForDead();
            savanna.animalMigration();
            savanna.nextDayPassed();
            savanna.showSavannaBoard();
            ip.printAnimalsLeft();
            ip.showRainRange();
        }
        ip.simulationByDaysCompleted();
    }

    //runs simulation which ends ehn there is no more animals left on the board
    //there can be situations when loop never ends so limit has been set for 999 days
    public void simulateWhileAnimalsLive(Savanna savanna) {
        InfoPrinter ip = new InfoPrinter(savanna);
        ip.printHello();
        ip.printInitialElementsInfo();
        ip.printLegend();
        savanna.showSavannaBoard();

        while (savanna.getAnimalsLeft() > 0 && savanna.getDaysPassed() < 999) {

            savanna.oneDay();
            savanna.lookForDead();
            savanna.animalMigration();
            savanna.nextDayPassed();
            savanna.showSavannaBoard();
            ip.printAnimalsLeft();
            ip.showRainRange();
        }
        ip.simulationWhileAnimalsCompleted();
    }
}
